package primitives;

import java.util.Objects;

/**
 * Represents a ray in 3D space.
 * A ray is defined by a starting point (p0) and a direction vector (dir).
 */
public class Ray {

    /** The starting point of the ray. */
    private final Point p0;

    /** The direction vector of the ray. */
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
     * Checks if this ray is equal to another object.
     *
     * @param o the object to compare with this ray.
     * @return true if the specified object is equal to this ray, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    /**
     * Computes a hash code for this ray.
     *
     * @return a hash code value for this ray.
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
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
