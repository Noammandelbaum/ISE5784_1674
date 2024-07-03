package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a spot light source.
 */
public class SpotLight extends PointLight {
    private final Vector direction;

    /**
     * Constructor for SpotLight.
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public SpotLight setKC(double kC) {
        super.setKC(kC);
        return this;
    }

    @Override
    public SpotLight setKL(double kL) {
        super.setKL(kL);
        return this;
    }

    @Override
    public SpotLight setKQ(double kQ) {
        super.setKQ(kQ);
        return this;
    }

    @Override
    public Color getIntensity(Point p) {

        // Return the modified intensity for the SpotLight
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p))));
    }
}
