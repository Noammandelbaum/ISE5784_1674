package geometries;

import primitives.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.*;


/**
 * Represents a triangle in a 3D space.
 * The Triangle class extends Polygon and is defined by three vertices.
 */
public class Triangle extends Polygon {

    /**
     * Constructor to initialize a triangle with three vertices.
     *
     * @param vertex1 the first vertex of the triangle.
     * @param vertex2 the second vertex of the triangle.
     * @param vertex3 the third vertex of the triangle.
     */
    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        super(vertex1, vertex2, vertex3);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Step 1: Find intersection with the plane of the triangle
        List<GeoPoint> planeGeoIntersections = plane.findGeoIntersectionsHelper(ray);
        if (planeGeoIntersections == null) {
            return null; // The ray does not intersect the plane
        }

        Point p = planeGeoIntersections.getFirst().point;

        // Step 2: Check if the intersection point is inside the triangle
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        Vector n1 = null, n2 = null, n3 = null;
        try {
            n1 = v1.crossProduct(v2).normalize();
            n2 = v2.crossProduct(v3).normalize();
            n3 = v3.crossProduct(v1).normalize();
        } catch (IllegalArgumentException e) {
            return null; // One or more cross products resulted in a zero vector
        }

        double sign1 = alignZero(v.dotProduct(n1));
        double sign2 = alignZero(v.dotProduct(n2));
        double sign3 = alignZero(v.dotProduct(n3));

        // If all have the same sign, the point is inside the triangle
        if (compareSign(sign1, sign2) && compareSign(sign1, sign3)) {
            return List.of(new GeoPoint(this, p));
        }

        return null; // The intersection point is outside the triangle
    }
}
