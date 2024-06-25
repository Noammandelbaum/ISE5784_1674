package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a collection of geometric shapes.
 * This class implements the Composite design pattern.
 */
public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor (empty)
     */
    public Geometries() {}

    /**
     * Constructor that initializes the list of geometries with the given geometries.
     *
     * @param geometries the geometries to add to the list
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds the given geometries to the list.
     *
     * @param geometries the geometries to add
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    /**
     * Returns the list of geometries.
     *
     * @return the list of geometries
     */
    public List<Intersectable> getGeometries() {
        return geometries;
    }

    @Override
        public List<Point> findIntersections(Ray ray) {
            List<Point> intersections = null;

            for (Intersectable geometry : geometries) {
                List<Point> geometryIntersections = geometry.findIntersections(ray);
                if (geometryIntersections != null) {
                    if (intersections == null) {
                        intersections = new LinkedList<>(); // Create list only when first intersection is found
                    }
                    intersections.addAll(geometryIntersections);
                }
            }

            return intersections; // If no intersections were found, this will return null
        }
    }