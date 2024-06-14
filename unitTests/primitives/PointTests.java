package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link primitives.Point}.
 */
public class PointTests {

    /**
     * Test method for {@link primitives.Point#Point(double, double, double)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test creating a point
        assertDoesNotThrow(() -> new Point(1, 2, 3), "Failed constructing a point");

        // =============== Boundary Values Tests ==================
        // TC10: Test creating a zero point
        assertDoesNotThrow(() -> new Point(0, 0, 0), "Failed constructing a zero point");
    }

    /**
     * Test method for {@link primitives.Point#equals(Object)}.
     */
    @Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(1, 2, 3);
        Point p3 = new Point(4, 5, 6);

        // TC01: Test equality of identical points
        assertEquals(p1, p2, "equals() failed for identical points");

        // TC02: Test inequality of different points
        assertNotEquals(p1, p3, "equals() failed for different points");
    }

    /**
     * Test method for {@link primitives.Point#toString()}.
     */
    @Test
    void testToString() {
        // ============ Equivalence Partitions Tests ==============
        Point p = new Point(1, 2, 3);
        assertEquals("Point{_xyz=(1.0,2.0,3.0)}", p.toString(), "toString() failed");
    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Point p = new Point(1, 2, 3);
        Vector v = new Vector(4, 5, 6);
        Point result = p.add(v);
        assertEquals(new Point(5, 7, 9), result, "add() failed");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        Vector result = p1.subtract(p2);
        assertEquals(new Vector(-3, -3, -3), result, "subtract() failed");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        assertEquals(27, p1.distanceSquared(p2), "distanceSquared() failed");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        assertEquals(Math.sqrt(27), p1.distance(p2), "distance() failed");
    }
}


