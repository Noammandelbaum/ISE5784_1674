package geometries;

import primitives.Vector;
import primitives.Point;

/**
 * Represents a sphere in a 3D space.
 * The Sphere class extends RadialGeometry and includes a center point and a radius.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    final private Point center;

    /**
     * Constructor to initialize the sphere with a radius and a center point.
     *
     * @param radius the radius of the sphere.
     * @param center the center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     *
     * @param point the point on the surface of the sphere where the normal is to be calculated.
     * @return the normal vector to the sphere at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        // Calculate the normal by subtracting the center from the point
        Vector normal = point.subtract(center);
        // Normalize the vector to get a unit vector
        return normal.normalize();
    }
}