package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}
