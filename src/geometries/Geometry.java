package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric shape in three-dimensional space.
 * It provides a method for calculating the normal vector to the shape at a given point.
 */
public interface Geometry extends Intersectable {

    /**
     * Computes the normal vector to the geometry at the specified point.
     *
     * @param point the point on the surface of the geometry.
     * @return the normal vector to the geometry at the specified point.
     */
    Vector getNormal(Point point);
}
