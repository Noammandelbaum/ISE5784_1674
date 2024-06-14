package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Represents a plane in a 3D space defined by a point and a normal vector.
 * The Plane class provides methods for creating a plane and getting its normal vector.
 */
public class Plane extends Geometry {

    /**
     * A point on the plane.
     */
    private final Point point;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructor to initialize the plane with a point and a normal vector.
     *
     * @param point a point on the plane.
     * @param normal the normal vector to the plane.
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }


    /**
     * Constructor to initialize the plane using three points.
     * This constructor calculates the normal vector based on the three points.
     *
     * @param p1 the first point.
     * @param p2 the second point.
     * @param p3 the third point.
     * @throws IllegalArgumentException if the points are collinear or two of them are identical.
     */
    public Plane(Point p1, Point p2, Point p3) {
        // Check for duplicate points
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Two or more points are identical");
        }

        // Check for collinear points
        Vector u = p2.subtract(p1);
        Vector v = p3.subtract(p1);
        Vector cross = u.crossProduct(v);

        if (cross.length() == 0) {
            throw new IllegalArgumentException("Points are collinear");
        }

        this.point = p1;
        this.normal = cross.normalize();
    }

    /**
     * Returns the normal vector to the plane at a given point.
     *
     * @param point the point at which the normal is to be calculated (ignored in this implementation).
     * @return the normal vector to the plane.
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane.
     */
    public Vector getNormal() {
        return normal;
    }
}