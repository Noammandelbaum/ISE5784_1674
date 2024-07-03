package lighting;

import primitives.Color;

/**
 * Abstract class representing a light source.
 */
abstract class Light {
    /**
     * The intensity of the light source.
     */
    protected Color intensity;

    /**
     * Constructor for initializing the light source with a given intensity.
     *
     * @param intensity the intensity of the light source
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets the intensity of the light source.
     *
     * @return the intensity of the light source
     */
    public Color getIntensity() {
        return intensity;
    }
}