package primitives;

/**
 * Class representing the material properties of a geometric object.
 * The properties include diffuse, specular, and shininess coefficients, as well as the transparency and reflection factors.
 */
public class Material {
    public Double3 kD = Double3.ZERO; // Diffuse coefficient
    public Double3 kS = Double3.ZERO; // Specular coefficient
    public Double3 kT = Double3.ZERO; // Transparency attenuation coefficient
    public Double3 kR = Double3.ZERO; // Reflection attenuation coefficient
    public int Shininess = 0; // Shininess coefficient

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
     * Sets the transparency coefficient.
     *
     * @param kT the transparency coefficient
     * @return the Material object itself for chaining
     */
    public Material setKT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the transparency coefficient.
     *
     * @param kT the transparency coefficient as a double
     * @return the Material object itself for chaining
     */
    public Material setKT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Sets the reflection coefficient.
     *
     * @param kR the reflection coefficient
     * @return the Material object itself for chaining
     */
    public Material setKR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the reflection coefficient.
     *
     * @param kR the reflection coefficient as a double
     * @return the Material object itself for chaining
     */
    public Material setKR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Sets the shininess coefficient.
     *
     * @param Shininess the shininess coefficient
     * @return the Material object itself for chaining
     */
    public Material setShininess(int Shininess) {
        this.Shininess = Shininess;
        return this;
    }
}
