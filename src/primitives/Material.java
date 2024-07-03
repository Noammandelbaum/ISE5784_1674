package primitives;

/**
 * Class representing the material properties of a geometric object.
 * The properties include diffuse, specular, and shininess coefficients, as well as the transparency and reflection factors.
 */
public class Material {
    public Double3 kD = Double3.ZERO; // Diffuse coefficient
    public Double3 kS = Double3.ZERO; // Specular coefficient
    public int nShininess = 0; // Shininess coefficient

    /**
     * Sets the diffuse coefficient.
     *
     * @param kD the diffuse coefficient
     * @return the Material object itself for chaining
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the diffuse coefficient.
     *
     * @param kD the diffuse coefficient as a double
     * @return the Material object itself for chaining
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular coefficient.
     *
     * @param kS the specular coefficient
     * @return the Material object itself for chaining
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the specular coefficient.
     *
     * @param kS the specular coefficient as a double
     * @return the Material object itself for chaining
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess coefficient.
     *
     * @param nShininess the shininess coefficient
     * @return the Material object itself for chaining
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
