package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents a cylinder in a 3D space.
 * The Cylinder class extends Tube and includes an additional height parameter.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructor to initialize the cylinder with a radius, a central axis ray, and a height.
     *
     * @param radius  the radius of the cylinder.
     * @param axisRay the central axis ray of the cylinder.
     * @param height  the height of the cylinder.
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Calculates the normal vector to the cylinder at a given point.
     *
     * @param point the point on the surface of the cylinder where the normal is to be calculated.
     * @return the normal vector to the cylinder at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
