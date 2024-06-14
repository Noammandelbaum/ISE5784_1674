package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometries.Tube}.
 */
class TubeTests {

    /**
     * Test method for {@link geometries.Tube#Tube(double, primitives.Ray)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test correct radius and axis ray assignment
        double radius = 1.0;
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Tube tube = new Tube(radius, axisRay);
        assertEquals(radius, tube.radius, "Constructor failed to set radius correctly");
//        assertEquals(axisRay, tube.axisRay, "Constructor failed to set axis ray correctly");

        // =============== Boundary Values Tests ==================
        // TC10: Test zero radius (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> new Tube(0, axisRay), "Constructor should throw exception for zero radius");

        // TC11: Test negative radius (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> new Tube(-1, axisRay), "Constructor should throw exception for negative radius");
    }

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal to a point on the surface of the tube
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Tube tube = new Tube(1.0, axisRay);
        Point p1 = new Point(1, 0, 1);
        Vector expectedNormal = new Vector(1, 0, 0);
        assertEquals(expectedNormal, tube.getNormal(p1), "getNormal() wrong result for point on the surface of the tube");

        // =============== Boundary Values Tests ==================
        // TC10: Test normal to a point on the surface of the tube that is directly above the ray's origin
        Point p2 = new Point(1, 0, 0);
        Vector expectedNormal2 = new Vector(1, 0, 0);
        assertEquals(expectedNormal2, tube.getNormal(p2), "getNormal() wrong result for point directly above the ray's origin");
    }
}
