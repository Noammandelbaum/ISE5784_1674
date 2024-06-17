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

    @Override
    public Vector getNormal(Point point) {
        Point p0 = getAxisRay().getP0();
        Vector dir = getAxisRay().getDir();

        // Check if the point is on the bottom base
        if (point.equals(p0)) {
            return dir.scale(-1);
        }

        // Project point onto the cylinder's axis
        double t = dir.dotProduct(point.subtract(p0));

        // Check if the point is on the top or bottom base
        if (t <= 0) {
            return dir.scale(-1).normalize();
        } else if (t >= height) {
            return dir.normalize();
        } else {
            // Point is on the curved surface
            return point.subtract(p0.add(dir.scale(t))).normalize();
        }
    }
}