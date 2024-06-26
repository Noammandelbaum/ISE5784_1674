package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a camera in a 3D space.
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width = 0.0;
    private double height = 0.0;
    private double distance = 0.0;

    // Getters
    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }


    /**
     * Private default constructor (no parameters)
     */
    private Camera() {
    }


    /**
     * Returns a new Builder instance.
     *
     * @return a new Builder instance.
     */
    public static Builder getBuilder() {
        return new Builder();
    }

//    /**
//     * Camera constructor with position and direction vectors.
//     *
//     * @param p0  the position of the camera
//     * @param vTo the direction vector towards the view plane
//     * @param vUp the direction vector upwards
//     * @throws IllegalArgumentException if vTo and vUp are not orthogonal
//     */
//    public Camera(Point p0, Vector vTo, Vector vUp) throws IllegalArgumentException {
//        if (!isZero(vTo.dotProduct(vUp))) {
//            throw new IllegalArgumentException("vUp and vTo are not orthogonal");
//        }
//        this.p0 = p0;
//        this.vUp = vUp.normalize();
//        this.vTo = vTo.normalize();
//        this.vRight = vTo.crossProduct(vUp).normalize();
//    }


    /**
     * Constructs a ray from the camera through a pixel (i, j).
     *
     * @param nX number of pixels in the x direction
     * @param nY number of pixels in the y direction
     * @param j  the pixel column index
     * @param i  the pixel row index
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = p0.add(vTo.scale(distance)); // Center point of the view plane
        double Ry = height / nY; // Pixel height
        double Rx = width / nX; // Pixel width

        double yI = alignZero(-(i - (nY - 1) / 2d) * Ry); // Vertical shift
        double xJ = alignZero((j - (nX - 1) / 2d) * Rx); // Horizontal shift

        Point pIJ = pc;
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));

        return new Ray(p0, pIJ.subtract(p0));
    }

    /**
     * Inner static Builder class for constructing Camera objects.
     */
    public static class Builder {
        private final Camera camera;

        /**
         * Default constructor for Builder initializes a new Camera object.
         */
        public Builder() {
            this.camera = new Camera();
        }

        /**
         * Constructor that initializes the Builder with an existing Camera object.
         *
         * @param camera the Camera object to initialize the Builder with
         */
        public Builder(Camera camera) {
            this.camera = camera;
        }


        /**
         * Sets the location of the camera.
         *
         * @param location the new location point
         * @return the Builder object itself
         * @throws IllegalArgumentException if the location is null
         */
        public Builder setLocation(Point location) {
            if (location == null) {
                throw new IllegalArgumentException("Location cannot be null");
            }
            camera.p0 = location;
            return this;
        }

        /**
         * Sets the direction vectors of the camera.
         *
         * @param vTo the direction vector towards the view plane
         * @param vUp the direction vector upwards
         * @return the Builder object itself
         * @throws IllegalArgumentException if vTo and vUp are not orthogonal or if either is null
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (vTo == null || vUp == null) {
                throw new IllegalArgumentException("vTo and vUp cannot be null");
            }
            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("vUp and vTo are not orthogonal");
            }
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * Sets the view plane size.
         *
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return the Builder object itself
         * @throws IllegalArgumentException if width or height is not positive
         */
        public Builder setVPSize(double width, double height) {
            if (alignZero(width) <= 0 || alignZero(height) <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Sets the view plane distance.
         *
         * @param distance the distance to the view plane
         * @return the Builder object itself
         * @throws IllegalArgumentException if distance is not positive
         */
        public Builder setVPDistance(double distance) {
            if (alignZero(distance) <= 0) {
                throw new IllegalArgumentException("Distance must be positive");
            }
            camera.distance = distance;
            return this;
        }


        /**
         * Builds the Camera object after checking that all necessary fields are set.
         *
         * @return the constructed Camera object
         * @throws MissingResourceException if any required field is not set
         * @throws IllegalArgumentException if any field values are invalid
         */
        public Camera build() {
            final String MISSING_RESOURCE_MESSAGE = "Missing rendering data";
            final String CAMERA_CLASS_NAME = "Camera";

            if (isZero(camera.width)) {
                throw new MissingResourceException(MISSING_RESOURCE_MESSAGE, CAMERA_CLASS_NAME, "width");
            }
            if (isZero(camera.height)) {
                throw new MissingResourceException(MISSING_RESOURCE_MESSAGE, CAMERA_CLASS_NAME, "height");
            }
            if (isZero(camera.distance)) {
                throw new MissingResourceException(MISSING_RESOURCE_MESSAGE, CAMERA_CLASS_NAME, "distance");
            }
            if (camera.vUp == null) {
                throw new MissingResourceException(MISSING_RESOURCE_MESSAGE, CAMERA_CLASS_NAME, "vUp");
            }
            if (camera.vTo == null) {
                throw new MissingResourceException(MISSING_RESOURCE_MESSAGE, CAMERA_CLASS_NAME, "vTo");
            }
            if (camera.p0 == null) {
                throw new MissingResourceException(MISSING_RESOURCE_MESSAGE, CAMERA_CLASS_NAME, "p0");
            }

            // Check for invalid values
            if (!isZero(camera.vTo.dotProduct(camera.vUp))) {
                throw new IllegalArgumentException("vTo and vUp are not orthogonal");
            }

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                // This should never happen because Camera implements Cloneable
                throw new RuntimeException(e);
            }
        }
    }
}
