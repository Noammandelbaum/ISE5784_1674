package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents a tube in a 3D space.
 * The Tube class extends RadialGeometry and is defined by a central axis ray and a radius.
 */
public class Tube extends RadialGeometry {

    /**
     * The central axis ray of the tube.
     */
    private final Ray axisRay;

    /**
     * Constructor to initialize the tube with a radius and a central axis ray.
     *
     * @param radius  the radius of the tube.
     * @param axisRay the central axis ray of the tube.
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     *
     * @param point the point on the surface of the tube where the normal is to be calculated.
     * @return the normal vector to the tube at the given point.
     */
    @Override
    public Vector getNormal(Point point) {

        // 𝒕 = 𝒗 ∙ (𝑷 − 𝑷𝟎)
        // 𝑶 = 𝑷𝟎 + 𝒕 ∙ 𝒗
        //= 𝒏𝒐𝒓𝒎𝒂𝒍𝒊𝒛𝒆(𝑷 − 𝑶)

        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        Vector p0ToPoint = point.subtract(p0);
        double t = dir.dotProduct(p0ToPoint);

        if (t == 0) {
            return p0ToPoint.normalize();
        }

        Point o = p0.add(dir.scale(t));
        Vector normal = point.subtract(o);

        return normal.normalize();
    }

}
