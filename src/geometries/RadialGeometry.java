package geometries;

/**
 * The RadialGeometry abstract class represents a circular geometric body.
 * It extends the Geometry class.
 */
public abstract class RadialGeometry extends Geometry {

    /** The radius of the circular radial geometry. */
    protected double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     *
     * @param radius the radius of the circular radial geometry.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}

