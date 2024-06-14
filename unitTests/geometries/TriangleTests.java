package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        assertDoesNotThrow(() -> new Triangle(
                        new Point(0, 0, 0),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)),
                "Triangle constructor with valid points threw an exception unexpectedly");

        // =============== Boundary Values Tests ==================
        // TC10: Test triangle creation with two coinciding points (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(
                        new Point(0, 0, 0),
                        new Point(0, 0, 0),
                        new Point(0, 1, 0)),
                "Triangle constructor with two coinciding points did not throw an exception");

        // TC11: Test triangle creation with collinear points (should throw an exception)
        assertThrows(IllegalArgumentException.class, () -> new Triangle(
                        new Point(0, 0, 0),
                        new Point(1, 0, 0),
                        new Point(2, 0, 0)),
                "Triangle constructor with collinear points did not throw an exception");
    }

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test getNormal with valid points
        Triangle triangle = new Triangle(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        Vector normal = triangle.getNormal(new Point(0, 0, 0));

        // Expected normal vector should be (0, 0, 1) or (0, 0, -1)
        Vector expectedNormal1 = new Vector(0, 0, 1);
        Vector expectedNormal2 = new Vector(0, 0, -1);

        assertTrue(normal.equals(expectedNormal1) || normal.equals(expectedNormal2),
                "getNormal() result is incorrect");
    }
}
