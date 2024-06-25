package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;

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

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Point centerPoint300 = new Point(3, 0, 0);
        Sphere sphere = new Sphere(2d, centerPoint300);

        Point outsidePointZero = Point.ZERO;
        Point outsidePoint600 = new Point(6, 0, 0);
        Point outsidePoint610 = new Point(6, 1, 0);
        Point outsidePoint110 = new Point(1, 1, 0);
        Point outsidePoint220 = new Point(2, 2, 0);
        Point outsidePoint420 = new Point(4, 2, 0);
        Point outsidePoint330 = new Point(3, 3, 0);

        Point onSurfacePoint100 = new Point(1, 0, 0);
        Point onSurfacePoint320 = new Point(3, 2, 0);
        Point onSurfacePoint500 = new Point(5, 0, 0);
        Point onSurfacePointPrime1 = new Point(1.2679491924311228, 1, 0);
        Point onSurfacePointPrime2 = new Point(4.732050807568877, 1, 0);

        Point insidePoint400 = new Point(4, 0, 0);
        Point insidePoint210 = new Point(2, 1, 0);
        Point insidePoint310 = new Point(3, 1, 0);

        Vector vec100 = new Vector(1, 0, 0);


        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        Ray rayOutsideSphere = new Ray(outsidePoint600, vec100);
        assertNull(sphere.findIntersections(rayOutsideSphere), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        List<Point> expectedPoints = List.of(onSurfacePointPrime1, onSurfacePointPrime2);

        Ray rayCrossesSphere = new Ray(outsidePoint110, vec100);
        List<Point> actualPoints = sphere.findIntersections(rayCrossesSphere)
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(outsidePoint110)))
                .toList();
        assertEquals(2, actualPoints.size(), "Wrong number of points");
        assertEquals(expectedPoints, actualPoints, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Ray rayFromInsideSphere = new Ray(insidePoint210, vec100);
        actualPoints = sphere.findIntersections(rayFromInsideSphere)
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(insidePoint210)))
                .toList();
        assertEquals(1, actualPoints.size(), "Wrong number of points");
        assertEquals(List.of(onSurfacePointPrime2), actualPoints, "Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        Ray rayAfterSphere = new Ray(outsidePoint610, vec100);
        assertNull(sphere.findIntersections(rayAfterSphere), "Ray starts after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Rays start on the surface of the sphere

        // TC11: Ray starts at sphere and goes inside (1 point)
        Ray rayFromSurfaceInside = new Ray(onSurfacePointPrime1, vec100);
        actualPoints = sphere.findIntersections(rayFromSurfaceInside);
        assertEquals(1, actualPoints.size(), "Wrong number of points");
        assertEquals(List.of(onSurfacePointPrime2), actualPoints, "Ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        Ray rayFromSurfaceOutside = new Ray(onSurfacePointPrime2, vec100);
        assertNull(sphere.findIntersections(rayFromSurfaceOutside), "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
        Ray rayThroughCenter = new Ray(outsidePointZero, vec100);
        expectedPoints = List.of(onSurfacePoint100, onSurfacePoint500);
        actualPoints = sphere.findIntersections(rayThroughCenter)
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(outsidePointZero)))
                .toList();
        assertEquals(2, actualPoints.size(), "Wrong number of points");
        assertEquals(expectedPoints, actualPoints, "Ray through the center");

        // TC14: Ray start at center of the sphere and goes inside (1 point)
        Ray PassesCenterRay = new Ray(centerPoint300, vec100);
        actualPoints = sphere.findIntersections(PassesCenterRay);
        assertEquals(1, actualPoints.size(), "Wrong number of points");
        assertEquals(List.of(onSurfacePoint500), actualPoints, "Ray starts at/inside/center sphere and goes inside");

        // TC15: Ray starts at sphere and goes inside (1 point)
        PassesCenterRay = new Ray(onSurfacePoint100, vec100);
        actualPoints = sphere.findIntersections(PassesCenterRay);
        assertEquals(1, actualPoints.size(), "Wrong number of points");
        assertEquals(List.of(onSurfacePoint500), actualPoints, "Ray starts at/inside/center sphere and goes inside");

        // TC16: Ray starts inside the sphere and goes inside (after the center) (1 point)
        PassesCenterRay = new Ray(insidePoint400, vec100);
        actualPoints = sphere.findIntersections(PassesCenterRay);
        assertEquals(1, actualPoints.size(), "Wrong number of points");
        assertEquals(List.of(onSurfacePoint500), actualPoints, "Ray starts at/inside/center sphere and goes inside");


        // TC17: Ray starts at sphere and goes outside (0 points)
        Ray rayFromSurfaceOutsideThroughCenter = new Ray(onSurfacePoint500, vec100);
        assertNull(sphere.findIntersections(rayFromSurfaceOutsideThroughCenter), "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        Ray rayAfterSphereThroughCenter = new Ray(outsidePoint600, vec100);
        assertNull(sphere.findIntersections(rayAfterSphereThroughCenter), "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        Ray rayBeforeTangent = new Ray(outsidePoint220, vec100);
        assertNull(sphere.findIntersections(rayBeforeTangent), "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        Ray rayAtTangent = new Ray(onSurfacePoint320, vec100);
        assertNull(sphere.findIntersections(rayAtTangent), "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        Ray rayAfterTangent = new Ray(outsidePoint420, vec100);
        assertNull(sphere.findIntersections(rayAfterTangent), "Ray starts after the tangent point");

        // **** Group: Special cases

        // TC22: Ray's line is inside, ray is orthogonal to ray start to sphere's center line (1 point)
        Ray rayOrthogonal = new Ray(insidePoint310, vec100);
        actualPoints = sphere.findIntersections(rayOrthogonal);
        assertEquals(1, actualPoints.size(), "Wrong number of points");
        assertEquals(List.of(onSurfacePointPrime2), actualPoints, "Ray is orthogonal and inside the sphere");

        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line (0 points)
        rayOrthogonal = new Ray(outsidePoint330, vec100);
        assertNull(sphere.findIntersections(rayOrthogonal), "Ray is orthogonal and outside the sphere");
    }
}
