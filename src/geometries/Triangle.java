package geometries;

import primitives.Point;

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
}
