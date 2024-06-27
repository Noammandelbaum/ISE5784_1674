package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * Simple ray tracer class that extends RayTracerBase.
 */
public class SimpleRayTracer extends RayTracerBase {

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
        var intersections = scene.geometries.findIntersections(ray);
        if (intersections == null) {
            return scene.background;
        }

        return calcColor(ray.findClosestPoint(intersections));
    }

    /**
     * Calculates the color at a given point.
     *
     * @param point the point to calculate the color at
     * @return the color at the point
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
