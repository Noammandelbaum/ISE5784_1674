package scene;

import lighting.LightSource;
import primitives.Color;
import lighting.AmbientLight;
import geometries.Geometries;

import java.util.LinkedList;
import java.util.List;

/**
 * The Scene class represents a 3D scene containing geometric shapes, lights, and other objects.
 * It uses a fluent interface for setting its properties.
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor to initialize the scene with a name.
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background the background color
     * @return the Scene object itself (fluent interface)
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight the ambient light
     * @return the Scene object itself (fluent interface)
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries of the scene.
     *
     * @param geometries the geometries
     * @return the Scene object itself (fluent interface)
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }


    /**
     * Sets the list of lights in the scene.
     *
     * @param lights the list of light sources to add to the scene
     * @return the scene with the updated list of lights
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
