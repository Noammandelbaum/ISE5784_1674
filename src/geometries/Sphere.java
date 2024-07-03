package geometries;

import primitives.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

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
        // Normalize the vector to get a unit vector
        return point.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if (p0.equals(center)) return List.of(new GeoPoint(this, center.add(v.scale(radius))));

        Vector u = center.subtract(p0);

        double tm = v.dotProduct(u);
        double dSquared = u.lengthSquared() - tm * tm;
        double rSquared = radius * radius;

        // if d^2 >= r^2, there are no intersections
        if (Util.alignZero(dSquared - rSquared) >= 0) {
            return null;
        }

        double th = Math.sqrt(rSquared - dSquared);
        double t1 = tm - th;
        double t2 = tm + th;

        // Initialize the list of intersection points
        Point p1 = null, p2 = null;

        if (Util.alignZero(t1) > 0) {
            p1 = ray.getPoint(t1);
        }
        if (Util.alignZero(t2) > 0) {
            p2 = ray.getPoint(t2);
        }

        // Return the list of intersection points
        if (p1 != null && p2 != null) {
            // Sort points by distance from ray origin
            return tm > th ?
                    List.of(new GeoPoint(this, p1), new GeoPoint(this, p2)) :
                    List.of(new GeoPoint(this, p2), new GeoPoint(this, p1));
        } else if (p1 != null) {
            return List.of(new GeoPoint(this, p1));
        } else if (p2 != null) {
            return List.of(new GeoPoint(this, p2));
        } else {
            return null;
        }
    }
}