package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Class representing ambient light in the scene.
 */
public class AmbientLight extends Light {

    /**
     * Constructor for AmbientLight.
     *
     * @param Ia the base color intensity
     * @param Ka the attenuation factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * Constructor for AmbientLight with a default color intensity (black).
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
