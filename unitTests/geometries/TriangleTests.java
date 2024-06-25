package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometries.Triangle}.
 */
public class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#Triangle(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test triangle creation with valid points
        assertDoesNotThrow(() -> new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0)), "Triangle constructor with valid points threw an exception unexpectedly");

        // =============== Boundary Values Tests ==================
        // TC10: Test triangle creation with two coinciding points (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(0, 0, 0), new Point(0, 0, 0), new Point(0, 1, 0)), "Triangle constructor with two coinciding points did not throw an exception");

        // TC11: Test triangle creation with collinear points (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(2, 0, 0)), "Triangle constructor with collinear points did not throw an exception");
    }

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test getNormal with valid points
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Vector normal = triangle.getNormal(new Point(0, 0, 0));

        // Expected normal vector should be (0, 0, 1) or (0, 0, -1)
        Vector expectedNormal1 = new Vector(0, 0, 1);
        Vector expectedNormal2 = new Vector(0, 0, -1);

        assertTrue(normal.equals(expectedNormal1) || normal.equals(expectedNormal2), "getNormal() result is incorrect");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(0, 2, 0), new Point(0, 6, 0), new Point(4, 2, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray starts outside the triangle and crosses it (1 point)
        Ray ray1 = new Ray(new Point(1, 3, -1), new Vector(0, 0, 1));
        Point expectedIntersection1 = new Point(1, 3, 0);
        List<Point> result1 = triangle.findIntersections(ray1);
        assertEquals(List.of(expectedIntersection1), result1, "Ray should intersect the triangle");

        // TC02: Ray misses the triangle - in front of edge
        Ray ray2 = new Ray(new Point(2, 0, -2), new Vector(0, 0, 1));
        assertNull(triangle.findIntersections(ray2), "Ray should miss the triangle (in front of edge)");

        // TC03: Ray misses the triangle - in front of vertex
        Ray ray3 = new Ray(new Point(-1, 1, -2), new Vector(0, 0, 1));
        assertNull(triangle.findIntersections(ray3), "Ray should miss the triangle (in front of vertex)");

        // =============== Boundary Values Tests ==================
        // TC10: Ray intersects the edge of the triangle
        Ray ray4 = new Ray(new Point(2, 2, -2), new Vector(0, 0, 1));
        assertNull(triangle.findIntersections(ray4), "Ray should intersect the edge of the triangle");

        // TC11: Ray intersects the vertex of the triangle
        Ray ray5 = new Ray(new Point(4, 2, -2), new Vector(0, 0, 1));
        assertNull(triangle.findIntersections(ray5), "Ray should intersect the vertex of the triangle");

        // TC12: Ray intersects the continuation of the edge of the triangle
        Ray ray6 = new Ray(new Point(-1, 2, -2), new Vector(0, 0, 1));
        assertNull(triangle.findIntersections(ray6), "Ray should intersect the continuation of the edge of the triangle");
    }
}
