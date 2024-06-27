package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class representing a base ray tracer.
 * Contains a scene and an abstract method for tracing rays.
 */
public abstract class RayTracerBase {
    /**
     * The scene to be rendered.
     */
    protected Scene scene;

    /**
     * Constructor that initializes the ray tracer with a scene.
     *
     * @param scene the scene to be rendered
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a ray and returns the color at the intersection point.
     *
     * @param ray the ray to be traced
     * @return the color at the intersection point
     */
    public abstract Color traceRay(Ray ray);
}
