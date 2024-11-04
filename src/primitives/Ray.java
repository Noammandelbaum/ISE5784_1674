package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Represents a ray in 3D space.
 * A ray is defined by a starting point (p0) and a direction vector (dir).
 */
public class Ray {

    /**
     * The starting point of the ray.
     */
    private final Point p0;

    /**
     * The direction vector of the ray.
     */
    private final Vector dir;

    private static final double DELTA = 0.1;

    /**
     * Constructs a new ray with the given starting point and direction vector.
     * The direction vector is normalized.
     *
     * @param p0  the starting point of the ray.
     * @param dir the direction vector of the ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructs a new ray with the starting point adjusted along the given normal.
     *
     * @param point     The original starting point of the ray.
     * @param direction The direction vector of the ray.
     * @param normal    The normal vector for direction adjustment.
     */
    public Ray(Point point, Vector direction, Vector normal) {
        double vn = normal.dotProduct(direction);
        if (!isZero(vn)) {
            // Adjust the point along the normal using the DELTA constant
            point = point.add(normal.scale((vn < 0) ? -DELTA : DELTA));
        }

        this.p0 = point;
        this.dir = direction.normalize();
    }


    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return the direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Computes a point on the ray at a given distance t from the starting point.
     *
     * @param t the distance from the starting point
     * @return the point on the ray at distance t
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * Finds the closest point to the ray's starting point from a list of points.
     *
     * @param points the list of points
     * @return the closest point to the ray's starting point, or null if the list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint from a list of GeoPoints to the ray's origin.
     *
     * @param geoPoints List of GeoPoints to check
     * @return The closest GeoPoint to the ray's origin, or null if the list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        if (geoPoints == null || geoPoints.isEmpty()) {
            return null;
        }
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;

        for (GeoPoint geoPoint : geoPoints) {
            double distance = p0.distance(geoPoint.point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }

    /**
     * Checks if this ray is equal to another object.
     *
     * @param o the object to compare with this ray.
     * @return true if the specified object is equal to this ray, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Ray ray) && ray.p0.equals(p0) && ray.dir.equals(dir);
    }

    /**
     * Returns a string representation of this ray.
     *
     * @return a string representation of this ray.
     */
    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }
}
