package geometries;

/**
 * The RadialGeometry abstract class represents a circular geometric body.
 * It extends the Geometry class.
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the circular radial geometry.
     */
    protected double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     *
     * @param radius the radius of the circular radial geometry.
     * @throws IllegalArgumentException if the radius is not positive
     */
    public RadialGeometry(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }
}

