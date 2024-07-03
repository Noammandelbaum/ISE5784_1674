package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link geometries.RadialGeometry}.
 */
public class RadialGeometryTests {

    /**
     * Test method for {@link geometries.RadialGeometry#RadialGeometry(double)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test valid radius
        double radius = 5.0;
        assertDoesNotThrow(() -> new RadialGeometryImpl(radius), "Constructor failed to accept valid radius");

        // =============== Boundary Values Tests ==================
        // TC10: Test zero radius
        double zeroRadius = 0.0;
        assertThrows(IllegalArgumentException.class, () -> new RadialGeometryImpl(zeroRadius), "Constructor failed to throw exception for zero radius");

        // TC11: Test negative radius
        double negativeRadius = -5.0;
        assertThrows(IllegalArgumentException.class, () -> new RadialGeometryImpl(negativeRadius), "Constructor failed to throw exception for negative radius");
    }

    // A simple implementation of RadialGeometry for testing purposes
    private static class RadialGeometryImpl extends RadialGeometry {
        public RadialGeometryImpl(double radius) {
            super(radius);
        }

        @Override
        public Vector getNormal(Point point) {
            return null;
        }

        @Override
        protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
            return null;
        }
    }
}
