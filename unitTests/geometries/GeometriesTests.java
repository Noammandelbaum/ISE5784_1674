package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Geometries class.
 */
class GeometriesTests {
    Sphere sphere = new Sphere(1, new Point(1, 0, 0));
    Plane plane = new Plane(new Point(0, 0, 1), new Vector(0, 0, 1));
    Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));

    /**
     * Test method for {@link geometries.Geometries#Geometries(Intersectable...)}.
     */
    @Test
    void testConstructor() {
        //testDefaultConstructor
        Geometries geometries = new Geometries();
        // Check if the list is initialized empty
        assertTrue(geometries.getGeometries().isEmpty(), "Default constructor did not initialize an empty list");


        // testConstructorWithGeometries
        geometries = new Geometries(sphere, plane, triangle);

        // Check if geometries are added
        assertEquals(3, geometries.getGeometries().size(), "Geometries not added correctly in constructor");
    }

    /**
     * Test method for {@link geometries.Geometries#add(Intersectable...)}.
     */
    @Test
    void testAdd() {
        Geometries geometries = new Geometries();

        // Add geometries
        geometries.add(sphere, plane, triangle);

        // Check if geometries are added
        assertEquals(3, geometries.getGeometries().size(), "Geometries not added correctly");
    }

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Some shapes (but not all) are intersected (3 points)
        Geometries geometries = new Geometries(sphere, plane, triangle);
        Ray ray = new Ray(new Point(0.5, 0, 2), new Vector(0, 0, -1));
        List<Point> intersections = geometries.findIntersections(ray);
        assertEquals(3, intersections.size(), "Expected 3 intersection points when some shapes are intersected");

        // =============== Boundary Values Tests ==================

        // TC10: Empty collection (0 points)
        geometries = new Geometries();
        ray = new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0));
        assertNull(geometries.findIntersections(ray), "Expected no intersection points with empty collection");

        // TC11: No shapes are intersected (0 points)
        geometries = new Geometries(sphere, plane, triangle);
        ray = new Ray(new Point(-1, 0, 0), new Vector(-1, 0, 0));
        assertNull(geometries.findIntersections(ray), "Expected no intersection points when no shapes are intersected");

        // TC12: Only one shape is intersected (1 point)
        ray = new Ray(new Point(3, 0, 0), new Vector(0, 0, 1));
        intersections = geometries.findIntersections(ray);
        assertEquals(1, intersections.size(), "Expected 1 intersection points when only one shape is intersected");

        // TC13: All shapes are intersected (4 points)
        ray = new Ray(new Point(0.5, 0.25, -1), new Vector(0, 0, 1));
        intersections = geometries.findIntersections(ray);
        assertEquals(4, intersections.size(), "Expected 4 intersection points when all shapes are intersected");
    }
}
