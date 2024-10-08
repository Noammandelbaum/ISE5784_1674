package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Simple ray tracer class that extends RayTracerBase.
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * A constant value for shadow rays starting point offset.
     */
    private static final double DELTA = 0.1;


    /**
     * Constructor that initializes the simple ray tracer with a scene.
     *
     * @param scene the scene to be rendered
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersectionPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionPoints == null)
            return this.scene.background;
        GeoPoint geoPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(geoPoint, ray);
    }

    /**
     * Calculates the color at a given GeoPoint, considering ambient light, emission,
     * and local lighting effects (including shadows).
     *
     * @param geoPoint The point on the geometry where the color is calculated.
     * @param ray      The ray that hits the geometry.
     * @return The calculated color at the GeoPoint.
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity()        // the intensity of the ambient light
                .add(geoPoint.geometry.getEmission())   // the intensity of the geometry
                .add(calcLocalEffects(geoPoint, ray));  // the light sources effect on the intensity
    }

    /**
     * Calculates the local effects of lighting (diffuse and specular) on the color at a given point.
     *
     * @param geoPoint the geometry point containing the geometry and the intersection point
     * @param ray      the ray that intersects the geometry
     * @return the color at the point with the local lighting effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        Material material = geoPoint.geometry.getMaterial(); // Get the material properties of the geometry
        Color color = Color.BLACK; // Initialize the color to black

        // Iterate over all light sources in the scene
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point); // Direction from the light source to the intersection point
            double nl = alignZero(n.dotProduct(l)); // nl is the dot product of the normal and the light direction

            // Check if the light source and the view direction are on the same side of the surface
            if (nl * nv > 0 && unshaded(geoPoint, lightSource, l, n, nl)) {
                Color lightIntensity = lightSource.getIntensity(geoPoint.point);

                color = color.add(calcDiffuse(material.kD, nl, lightIntensity),
                        calcSpecular(material.kS, l, n, nl, v, material.nShininess, lightIntensity));
            }
        }

        return color; // Return the calculated color
    }

    /**
     * Calculates the diffuse component of the Phong reflection model.
     *
     * @param kD             the diffuse coefficient
     * @param nl             the dot product of the normal and the light direction
     * @param lightIntensity the intensity of the light
     * @return the diffuse color
     */
    private Color calcDiffuse(Double3 kD, double nl, Color lightIntensity) {
        return lightIntensity.scale(kD).scale(Math.abs(nl));
    }

    /**
     * Calculates the specular component of the Phong reflection model.
     *
     * @param kS             the specular coefficient
     * @param l              the direction of the light
     * @param n              the normal to the surface
     * @param nl             the dot product of the normal and the light direction
     * @param v              the direction of the view
     * @param nShininess     the shininess coefficient
     * @param lightIntensity the intensity of the light
     * @return the specular color
     */
    private Color calcSpecular(Double3 kS, Vector l, Vector n, double nl, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * nl)).normalize();
        double vr = alignZero(v.dotProduct(r));
        if (vr >= 0) {
            return Color.BLACK; // vr should be negative
        }
        return lightIntensity.scale(kS).scale(Math.pow(-vr, nShininess));
    }

    /**
     * Checks if a point is unshaded by any objects between the point and the light source.
     *
     * @param gp    The GeoPoint on the geometry.
     * @param light The light source.
     * @param l     The vector from the light source to the hit point.
     * @param n     The normal vector to the surface at the hit point.
     * @param nl    The dot product of the normal vector and the vector l.
     * @return True if the point is unshaded (no objects block the light), false otherwise.
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // Direction from the point to the light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA); // Small offset to avoid self-intersection
        Point point = gp.point.add(epsVector); // Slightly move the point along the normal

        // Create a ray from the point towards the light source
        Ray lightRay = new Ray(point, lightDirection);

        // Find intersections between the ray and the geometries
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        // If no intersections, the point is unshaded
        if (intersections == null) {
            return true;
        }

        // Get the distance to the light source
        double lightDistance = light.getDistance(point);

        // Check if any intersection is closer to the point than the light source
        for (GeoPoint intersection : intersections) {
            if (intersection.point.distance(point) <= lightDistance) {
                return false; // The point is in shadow
            }
        }

        return true; // No intersections closer than the light source, point is unshaded
    }

}
