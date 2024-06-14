package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometries.Sphere}.
 */
public class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test correct radius and center point assignment
        double radius = 5.0;
        Point center = new Point(1, 2, 3);
        Sphere sphere = new Sphere(radius, center);
        assertEquals(radius, sphere.radius, "Constructor failed to set radius correctly");
//        assertEquals(center, sphere.center, "Constructor failed to set center point correctly");

        // =============== Boundary Values Tests ==================
        // TC10: Test zero radius (should throw an exception)
        double zeroRadius = 0.0;
        assertThrows(IllegalArgumentException.class, () -> new Sphere(zeroRadius, center), "Constructor failed to throw exception for zero radius");

        // TC11: Test negative radius (should throw an exception)
        double negativeRadius = -5.0;
        assertThrows(IllegalArgumentException.class, () -> new Sphere(negativeRadius, center), "Constructor failed to throw exception for negative radius");
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal calculation on sphere surface
        Sphere sphere = new Sphere(1.0, new Point(0, 0, 0));
        Point p1 = new Point(1, 0, 0);
        Vector expectedNormal1 = new Vector(1, 0, 0);
        assertEquals(expectedNormal1, sphere.getNormal(p1), "getNormal() wrong result for point on sphere surface");

        Point p2 = new Point(0, 1, 0);
        Vector expectedNormal2 = new Vector(0, 1, 0);
        assertEquals(expectedNormal2, sphere.getNormal(p2), "getNormal() wrong result for point on sphere surface");

        Point p3 = new Point(0, 0, 1);
        Vector expectedNormal3 = new Vector(0, 0, 1);
        assertEquals(expectedNormal3, sphere.getNormal(p3), "getNormal() wrong result for point on sphere surface");
    }
}
