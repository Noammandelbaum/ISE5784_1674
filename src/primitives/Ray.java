package primitives;

import java.util.Objects;

/**
 * Represents a ray in 3D space.
 * A ray is defined by a starting point (p0) and a direction vector (dir).
 */
public class Ray {

    /**
     * The starting point of the ray.
     */
    private final Point p0;

    /**
     * The direction vector of the ray.
     */
    private final Vector dir;

    /**
     * Constructs a new ray with the given starting point and direction vector.
     * The direction vector is normalized.
     *
     * @param p0  the starting point of the ray.
     * @param dir the direction vector of the ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }


    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return the direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Checks if this ray is equal to another object.
     *
     * @param o the object to compare with this ray.
     * @return true if the specified object is equal to this ray, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Ray ray) && ray.p0.equals(p0) && ray.dir.equals(dir);
    }

    /**
     * Returns a string representation of this ray.
     *
     * @return a string representation of this ray.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
