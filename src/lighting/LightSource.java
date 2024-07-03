package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface representing a light source in the scene.
 */
public interface LightSource {

    /**
     * Gets the intensity of the light at a given point.
     *
     * @param p the point where the intensity is calculated
     * @return the intensity of the light at the given point
     */
    public Color getIntensity(Point p);

    /**
     * Gets the direction vector from the light source to the given point.
     *
     * @param p the point to which the direction is calculated
     * @return the direction vector from the light source to the given point
     */
    public Vector getL(Point p);
}
