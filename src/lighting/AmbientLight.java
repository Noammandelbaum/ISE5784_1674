package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents ambient lighting.
 * It includes an intensity of the ambient light and an attenuation factor.
 */
public class AmbientLight {
    private final Color intensity;

    /**
     * Constructor to initialize the AmbientLight with specific color and attenuation factor.
     *
     * @param Ia The original fill light intensity (RGB components).
     * @param kA The attenuation factor of the fill light.
     */
    public AmbientLight(Color Ia, Double3 kA) {
        this.intensity = Ia.scale(kA);
    }

    /**
     * Constructor to initialize the AmbientLight with specific color and attenuation factor.
     *
     * @param Ia The original fill light intensity (RGB components).
     * @param kA The attenuation factor of the fill light.
     */
    public AmbientLight(Color Ia, double kA) {
        this.intensity = Ia.scale(new Double3(kA));
    }

    /**
     * A constant representing no ambient light.
     * An ambient light object with no intensity and black color.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Get the intensity of the ambient light.
     *
     * @return the intensity of the ambient light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
