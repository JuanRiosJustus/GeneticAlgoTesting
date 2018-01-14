package structs;

public class Cell
{
    private DNA dna;
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private boolean crashed;
    private boolean completed;
    private double acuity;

    /**
     * Default constructor for the cell.
     */
    public Cell()
    {
        dna = new DNA();
        position = new Vector(10, 10);
        velocity = new Vector();
        acceleration = new Vector();
        crashed = false;
        completed = false;
        acuity = 0;
    }
    /**
     * Constructor using dna.
     * @param dna dna to be used.
     */
    public Cell(DNA dna)
    {
        if (dna != null) { this.dna = dna; }
        position = new Vector(50, 50);
        velocity = new Vector();
        acceleration = new Vector();
        crashed = false;
        completed = false;
        acuity = 0;
    }
    /**
     * Calculates the acuity of the cell.
     * @param destination target position for the cell.
     */
    public void calculateAcuity(Vector destination)
    {
        double d = Vector.dist(position, destination);
        acuity = 1/d;
        if (completed) { acuity = acuity * 10; }
        if (crashed) { acuity = acuity / 10; }
    }
    /**
     * Updates the components of the cell.
     * @param destination target of the cell.
     * @param counter dna to fetch.
     */
    public void update(Vector destination, int counter)
    {
        double d = Vector.dist(position, destination);
        if (d < 70)
        {
            completed = true;
            position = destination.copy();
            dna.prizify();
        }

        /** ADD OBSTACLES HERE */
        if (this.position.x() < 0 || this.position.x() > destination.x()-20) { crashed = true; }
        if (this.position.y() < 0 || this.position.y() > destination.y()-40) { crashed = true; }

        accelerate(dna.getGene(counter));
        if (completed == false && crashed == false)
        {
            velocity.add(acceleration);
            position.add(velocity);
            acceleration.multiply(0);
            velocity.limit(4);
        }
    }

    /**
     * Adds a given given to the acceleration.
     */
    public void accelerate(Vector v) { acceleration.add(v); }
    /**
     * Returns the dna of cell.
     */
    public DNA getDna() { return dna; }
    /**
     * @return Determiens whether the cell has reached its destination.
     */
    public boolean isCompleted() { return completed; }
    /**
     * @return Determines whether the cell has crashed or not.
     */
    public boolean isCrashed() { return crashed; }
    /**
     * Returns the position of the cell.
     * @return vector determining the position.
     */
    public Vector getPosition() { return position; }
    /**
     * @return returns the acuity of the cell.
     */
    public double getAcuity() { return acuity; }

    /**
     * Sets the acuity of the cell.
     * @param i acuity to be set to the cell.
     */
    public void setAcuity(double i) { acuity = i; }

}
