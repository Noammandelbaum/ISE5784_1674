package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometries.Plane}.
 */
public class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    void testConstructorThreePoints() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test plane creation with three non-collinear points
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1, p2, p3);
        Vector expectedNormal1 = new Vector(0, 0, 1);
        Vector expectedNormal2 = new Vector(0, 0, -1);
        assertTrue(expectedNormal1.equals(plane.getNormal()) || expectedNormal2.equals(plane.getNormal()),
                "Plane constructor with three points wrong normal calculation");

        // =============== Boundary Values Tests ==================
        // TC10: Test plane creation with three collinear points (should throw an exception)
        Point p4 = new Point(2, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p4), "Plane constructor with collinear points did not throw an exception");

        // TC11: Test plane creation with the first and second points coinciding (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p2), "Plane constructor with coinciding points did not throw an exception");

        // TC12: Test plane creation with the second and third points coinciding (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p2), "Plane constructor with coinciding points did not throw an exception");

        // TC13: Test plane creation with the first and third points coinciding (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p1), "Plane constructor with coinciding points did not throw an exception");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormalPoint() {
        // TC01: Test getNormal on a plane created with three points
        Plane plane = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Point p = new Point(1, 2, 0);
        Vector normal = plane.getNormal(p);
        Vector expectedNormal1 = new Vector(0, 0, 1);
        Vector expectedNormal2 = new Vector(0, 0, -1);
        assertTrue(expectedNormal1.equals(normal) || expectedNormal2.equals(normal),
                "getNormal() wrong result for Plane created with three points");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        // TC01: Test getNormal on a plane created with three points
        Plane plane = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Vector normal = plane.getNormal();
        Vector expectedNormal1 = new Vector(0, 0, 1);
        Vector expectedNormal2 = new Vector(0, 0, -1);
        assertTrue(expectedNormal1.equals(normal) || expectedNormal2.equals(normal),
                "getNormal() wrong result for Plane created with three points");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {

        Plane plane = new Plane(new Point(1, 0, 0), new Vector(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 point)
        Point p1 = new Point(0, 0, 2);
        List<Point> result1 = plane.findIntersections(new Ray(new Point(0, -2, 0), new Vector(0, 1, 1)));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(p1), result1, "Ray intersects the plane");

        // TC02: Ray does not intersect the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(2, -2, 0), new Vector(0, -1, 1))),
                "Ray does not intersect the plane");


        // =============== Boundary Values Tests ==================

        // TC10: Ray is parallel to the plane and not included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "Ray is parallel to the plane and not included in the plane");

        // TC11: Ray is parallel to the plane and included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))),
                "Ray is parallel to the plane and included in the plane");

        // TC12: Ray is orthogonal to the plane and starts before the plane (1 point)
        Point p3 = new Point(1, 0, 0);
        List<Point> result3 = plane.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, 1, 0)));
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(List.of(p3), result3, "Ray is orthogonal to the plane and starts before the plane");

        // TC13: Ray is orthogonal to the plane and starts in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0))),
                "Ray is orthogonal to the plane and starts in the plane");

        // TC14: Ray is orthogonal to the plane and starts after the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "Ray is orthogonal to the plane and starts after the plane");

        // TC15: Ray is neither orthogonal nor parallel to the plane and begins at the plane (P0 is in the plane but not the ray) (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 1, 1))),
                "Ray is neither orthogonal nor parallel to the plane and begins at the plane");

        // TC16: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q) (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 1))),
                "Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q)");
    }
}
