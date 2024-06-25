package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * The Intersectable interface represents geometric shapes that can be intersected by a ray.
 * It provides a method for finding the intersection points between a ray and the geometry.
 */
public interface Intersectable {

    /**
     * Finds the intersection points between the given ray and the geometry.
     *
     * @param ray the ray for which to find the intersection points.
     * @return a list of intersection points between the ray and the geometry.
     */
    List<Point> findIntersections(Ray ray);
}
