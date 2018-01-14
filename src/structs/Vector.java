package structs;


import java.util.Random;
import java.lang.Math;

/**
 * Basic Vector structure representative of x, y, or z components.
 */
public class Vector
{
    private double x;
    private double y;
    private double z;
    private static Random rand;
    /**
     * Default Vector constructor.
     */
    public Vector() { this.x = 0; this.y = 0; this.z = 0; }
    /**
     * Vector constructor representing a three-dimensional Vector.
     */
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    /**
     * Vector constructor representing a three-dimension Vector.
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Multiplies the vector by the given number.
     */
    public void multiply(double multiplier) {
        this.x = this.x * multiplier;
        this.y = this.y * multiplier;
        this.z = this.z * multiplier;
    }
    /**
     * Divides the vector by the given number.
     */
    public void divide(double divider) {
        this.x = this.x / divider;
        this.y = this.y / divider;
        this.z = this.z / divider;
    }

    /**
     * Adds the given vector components to the vectors components.
     * @param v vector whos components to add.
     */
    public void add(Vector v) {
        this.x = this.x + v.x();
        this.y = this.y + v.y();
        this.z = this.z + v.z();
    }

    /**
     * sets the limit of the magnitude.
     * @param max the max magnitude for the vector.
     */
    public void limit(int max)
    {
        double d = (this.x * this.x) + (this.y * this.y) + (this.z * this.z);
        if (d > max * max)
        {
            divide(magnitude());
            multiply(max);
        }
    }
    /**
     * Returns a copy of the vector.
     * @return copy of the vector.
     */
    public Vector copy()
    {
        return new Vector(this.x, this.y, this.z);
    }
    /**
     * Normalizes the vector component.
     */
    public void normalize() {
        if (magnitude() != 0) {
            divide(magnitude());
        }
    }
    /**
     * Sets the magnitude of the vector.
     * @param mag magnitude to be set to the vector.
     */
    public void setMagnitude(double mag) { normalize(); multiply(mag); }
    /**
     * @return Gets the magnitude of the vector.
     */
    public double magnitude() { return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z)); }
    /** Sets the x component of the vector. */
    public void x(double x) { this.x = x; }
    /** Gets the x component of the vector. */
    public double x() { return this.x; }
    /** Sets the y component of the vector. */
    public void y(double y) { this.y = y; }
    /** Gets the y component of the vector. */
    public double y() { return this.y; }
    /** Sets the z (magnitude) component of the vector. */
    public void z(double z) { this.z = z; }
    /** Gets the z (magnitude) component of the vector. */
    public double z() { return this.z; }

    /**
     * returns a new vector with randomized x and y components.
     */
    public static Vector random2DVector()
    {
        if (rand == null) { rand = new Random(); }
        int x = rand.nextInt(20) - 10;
        int y = rand.nextInt(20) - 10;
        Vector v = new Vector(x, y);
        v.normalize();
        return v;
    }

    /**
     * Returns the Euclidean distance between two vectors as points.
     * @param v1 the starting/beginning vector.
     * @param v2 the ending/finishing vector.
     * @return distance betweem the vectors.
     */
    public static double dist(Vector v1, Vector v2)
    {
        double x = (v2.x() - v1.x()) * (v2.x() - v1.x());
        double y = (v2.y() - v1.y()) * (v2.y() - v1.y());
        return Math.sqrt(x + y);
    }
    @Override
    public String toString()
    {
        return "[x(" + x + ") ,y(" + y + "), z(" + z + ")]";
    }
}