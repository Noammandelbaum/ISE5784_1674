package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link primitives.Ray}.
 */
public class RayTests {

    /**
     * Test method for {@link primitives.Ray#Ray(primitives.Point, primitives.Vector)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        Point p0 = new Point(1, 2, 3);
        Vector dir = new Vector(1, 0, 0);

        // TC01: Test creating a ray
        assertDoesNotThrow(() -> new Ray(p0, dir), "Failed constructing a ray");
    }


    /**
     * Test method for {@link primitives.Ray#getP0()}.
     */
    @Test
    void testGetP0() {
        Point p0 = new Point(1, 2, 3);
        Vector dir = new Vector(1, 0, 0);
        Ray ray = new Ray(p0, dir);
        assertEquals(p0, ray.getP0(), "getP0() failed to return the correct starting point");
    }

    /**
     * Test method for {@link primitives.Ray#getDir()}.
     */
    @Test
    void testGetDir() {
        Point p0 = new Point(1, 2, 3);
        Vector dir = new Vector(1, 0, 0).normalize();
        Ray ray = new Ray(p0, dir);
        assertEquals(dir, ray.getDir(), "getDir() failed to return the correct direction vector");
    }

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Positive distance
        assertEquals(new Point(3, 2, 3), ray.getPoint(2), "getPoint() wrong result for positive distance");

        // TC02: Negative distance
        assertEquals(new Point(-1, 2, 3), ray.getPoint(-2), "getPoint() wrong result for negative distance");

        // =============== Boundary Values Tests ==================
        // TC10: Distance 0
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "getPoint() wrong result for distance 0");
    }

    /**
     * Test method for {@link primitives.Ray#equals(Object)}.
     */
    @Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        Point p0 = new Point(1, 2, 3);
        Vector dir = new Vector(1, 0, 0);
        Ray ray1 = new Ray(p0, dir);
        Ray ray2 = new Ray(p0, dir);
        Ray ray3 = new Ray(new Point(4, 5, 6), new Vector(1, 0, 0));

        // TC01: Test equality of identical rays
        assertEquals(ray1, ray2, "equals() failed for identical rays");

        // TC02: Test inequality of different rays
        assertNotEquals(ray1, ray3, "equals() failed for different rays");
    }

    /**
     * Test method for {@link primitives.Ray#toString()}.
     */
    @Test
    void testToString() {
        // ============ Equivalence Partitions Tests ==============
        Point p0 = new Point(1, 2, 3);
        Vector dir = new Vector(1, 0, 0);
        Ray ray = new Ray(p0, dir);

        // TC01: Test toString output
        assertEquals("Ray{p0=Point{_xyz=(1.0,2.0,3.0)}, dir=Vector{_xyz=(1.0,0.0,0.0)}}", ray.toString(), "toString() failed");
    }
}
