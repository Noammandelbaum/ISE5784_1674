package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import static primitives.Util.alignZero;

/**
 * SimpleRayTracer class for handling ray tracing to determine color at specific points in the scene.
 */
public class SimpleRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructor to initialize the ray tracer with a scene.
     *
     * @param scene The scene to be rendered.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a given ray and determines the color at the closest intersection point.
     *
     * @param ray The ray to trace.
     * @return The color at the intersection point, or the background color if no intersection is found.
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint intersection = findClosestIntersection(ray);
        return intersection == null ? scene.background : calcColor(intersection, ray);
    }

    /**
     * Wrapper function to calculate the color at a point, including ambient light.
     *
     * @param intersection The intersection point and geometry.
     * @param ray The ray hitting the geometry.
     * @return The color at the intersection point with ambient light.
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return calcColor(intersection, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursive calculation of color at a point, excluding ambient light for recursive calls.
     *
     * @param intersection The intersection point.
     * @param ray The ray that hit the point.
     * @param level The recursion level.
     * @param k The attenuation coefficient.
     * @return The calculated color at the point.
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        if (level == 0 || k.lowerThan(MIN_CALC_COLOR_K)) {
            return Color.BLACK;
        }

        return calcLocalEffects(intersection, ray, k)
                .add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculate the local lighting effects (diffuse and specular) at a given point.
     *
     * @param gp The geometry point to evaluate.
     * @param ray The incoming ray.
     * @param k The attenuation coefficient.
     * @return The calculated local color.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0) return color;

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            // Only consider light contributions if light direction and view direction are on the same side of the surface
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(
                            calcDiffusive(material.kD, nl, iL),
                            calcSpecular(material.kS, n, l, nl, v, iL, material.Shininess)
                    );
                }
            }
        }
        return color;
    }

    /**
     * Constructs a reflected ray from a given point.
     *
     * @param gp The geometry point of intersection.
     * @param direction The direction of the incoming ray.
     * @param n The normal vector at the intersection point.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector direction, Vector n) {
        return new Ray(gp.point, direction.subtract(n.scale(2 * direction.dotProduct(n))).normalize(), n);
    }

    /**
     * Constructs a refracted ray from a given point.
     *
     * @param gp The geometry point of intersection.
     * @param direction The direction of the incoming ray.
     * @param n The normal vector at the intersection point.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector direction, Vector n) {
        return new Ray(gp.point, direction, n);
    }

    /**
     * Calculates global lighting effects (reflection and refraction).
     *
     * @param gp The geometry point to evaluate.
     * @param ray The incoming ray.
     * @param level The recursion level.
     * @param k The attenuation coefficient.
     * @return The calculated global color.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);

        // Reflection
        if (!material.kR.equals(Double3.ZERO)) {
            Ray reflectedRay = constructReflectedRay(gp, v, n);
            color = color.add(calcGlobalEffect(reflectedRay, level, k, material.kR));
        }

        // Refraction
        if (!material.kT.equals(Double3.ZERO)) {
            Ray refractedRay = constructRefractedRay(gp, v, n);
            color = color.add(calcGlobalEffect(refractedRay, level, k, material.kT));
        }

        return color;
    }

    /**
     * Helper function to calculate the global effect for a given ray (either reflection or refraction).
     *
     * @param ray The ray to trace.
     * @param level The recursion level.
     * @param k The attenuation coefficient.
     * @param kx The reflection or refraction coefficient.
     * @return The calculated color contribution from the global effect.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * Calculates the transparency factor for a given point by evaluating light occlusions.
     *
     * @param gp The geometry point to evaluate.
     * @param light The light source being considered.
     * @param l The direction from the point to the light source.
     * @param n The normal vector at the intersection point.
     * @return The transparency coefficient (1 if fully transparent, 0 if fully blocked).
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(new Ray(gp.point, l.scale(-1), n));

        if (intersections == null) return Double3.ONE;

        Double3 ktr = Double3.ONE;
        double lightDistance = light.getDistance(gp.point);

        for (GeoPoint intersection : intersections) {
            if (alignZero(intersection.point.distance(gp.point) - lightDistance) <= 0) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * Finds the closest intersection point for a given ray.
     *
     * @param ray The ray to trace.
     * @return The closest intersection point, or null if no intersections are found.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

    /**
     * Calculates the diffusive component of the lighting.
     *
     * @param kD The diffusion coefficient.
     * @param nl The dot product of the normal and light direction.
     * @param iL The intensity of the light source.
     * @return The diffusive color component.
     */
    private Color calcDiffusive(Double3 kD, double nl, Color iL) {
        return iL.scale(kD.scale(Math.abs(nl)));
    }

    /**
     * Calculates the specular component of the lighting.
     *
     * @param kS The specular coefficient.
     * @param n The normal vector at the point.
     * @param l The light direction.
     * @param nl The dot product of the normal and light direction.
     * @param v The view direction.
     * @param iL The intensity of the light source.
     * @param nShininess The shininess factor of the material.
     * @return The specular color component.
     */
    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl, Vector v, Color iL, int nShininess) {
        double minusVR = -alignZero(v.dotProduct(l.subtract(n.scale(nl * 2))));

        if (minusVR <= 0) return Color.BLACK;

        return iL.scale(kS.scale(Math.pow(minusVR, nShininess)));
    }
}