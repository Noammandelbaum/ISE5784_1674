package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link geometries.Cylinder}.
 */
class CylinderTests {
    /**
     * Test method for {@link Cylinder#getNormal#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        Cylinder cylinder = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 5);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test normal on the curved surface
        Point pointOnSurface = new Point(1, 0, 2);
        Vector expectedNormal = new Vector(1, 0, 0);
        assertEquals(expectedNormal, cylinder.getNormal(pointOnSurface), "getNormal() wrong result for point on the curved surface of the cylinder");

        // TC02: Test normal on the bottom base
        Point pointOnBottomBase = new Point(0.5, 0.5, 0);
        expectedNormal = new Vector(0, 0, -1);
        assertEquals(expectedNormal, cylinder.getNormal(pointOnBottomBase), "getNormal() wrong result for point on the bottom base of the cylinder");

        // TC03: Test normal on the top base
        Point pointOnTopBase = new Point(0.5, 0.5, 5);
        expectedNormal = new Vector(0, 0, 1);
        assertEquals(expectedNormal, cylinder.getNormal(pointOnTopBase), "getNormal() wrong result for point on the top base of the cylinder");

        // =============== Boundary Values Tests ==================

        // TC10: Test normal on the edge of the bottom base
        Point edgePointOnBottomBase = new Point(1, 0, 0);
        expectedNormal = new Vector(0, 0, -1); // Prefer the normal pointing downward
        assertEquals(expectedNormal, cylinder.getNormal(edgePointOnBottomBase), "getNormal() wrong result for point on the edge of the bottom base of the cylinder");

        // TC11: Test normal on the edge of the top base
        Point edgePointOnTopBase = new Point(1, 0, 5);
        expectedNormal = new Vector(0, 0, 1); // Prefer the normal pointing upward
        assertEquals(expectedNormal, cylinder.getNormal(edgePointOnTopBase), "getNormal() wrong result for point on the edge of the top base of the cylinder");

        // TC12: Test normal in the center of the bottom base
        Point centerOfBottomBase = new Point(0, 0, 0);
        expectedNormal = new Vector(0, 0, -1);
        assertEquals(expectedNormal, cylinder.getNormal(centerOfBottomBase), "getNormal() wrong result for point in the center of the bottom base of the cylinder");

        // TC13: Test normal in the center of the top base
        Point centerOfTopBase = new Point(0, 0, 5);
        expectedNormal = new Vector(0, 0, 1);
        assertEquals(expectedNormal, cylinder.getNormal(centerOfTopBase), "getNormal() wrong result for point in the center of the top base of the cylinder");
    }

}