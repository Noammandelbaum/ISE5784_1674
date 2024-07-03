package geometries;

import primitives.*;

import java.util.List;

/**
 * Abstract class representing geometric objects in the scene.
 */
public abstract class Geometry extends Intersectable {

    private Material material = new Material(); // Default material

    /**
     * Gets the material of the geometry.
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material the material to set
     * @return the geometry itself for chaining
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * The emission color of the geometry.
     */
    protected Color emission = Color.BLACK;

    /**
     * Computes the normal vector to the geometry at the specified point.
     *
     * @param point the point on the surface of the geometry.
     * @return the normal vector to the geometry at the specified point.
     */
    public abstract Vector getNormal(Point point);

    /**
     * Gets the emission color of the geometry.
     *
     * @return the emission color of the geometry.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission the emission color to set.
     * @return the geometry object itself.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    protected abstract List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
