package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Intersectable class represents geometric shapes that can be intersected by a ray.
 * It provides a method for finding the intersection points between a ray and the geometry.
 */
public abstract class Intersectable {

    /**
     * Finds the intersection points between the given ray and the geometry.
     *
     * @param ray the ray for which to find the intersection points.
     * @return a list of intersection points between the ray and the geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the intersection points between the given ray and the geometry, including the geometry information.
     *
     * @param ray the ray for which to find the intersection points.
     * @return a list of GeoPoint objects representing the intersection points between the ray and the geometry.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper method to find the intersection points between the given ray and the geometry, including the geometry information.
     * This method should be implemented by subclasses.
     *
     * @param ray the ray for which to find the intersection points.
     * @return a list of GeoPoint objects representing the intersection points between the ray and the geometry.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    /**
     * The GeoPoint class is a passive data structure (PDS) that represents a point of intersection
     * with a geometry. It contains a reference to the geometry and the intersection point.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructs a GeoPoint with the specified geometry and point.
         *
         * @param geometry the intersected geometry
         * @param point    the intersection point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof GeoPoint other)
                return this.geometry.equals(other.geometry) && this.point.equals(other.point);
            return false;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
