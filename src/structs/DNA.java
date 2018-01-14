package structs;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Basic representation of DNA.
 */
public class DNA {

    private ArrayList<Vector> genes;
    private Random variance;
    private final int strandSize = 400;
    private final double force = 0.5;
    private boolean prized;
    private Color col;

    /**
     * Default constructor for DNA.
     */
    public DNA() {
        genes = new ArrayList<Vector>();
        variance = new Random();
        prized = false;
        col = Color.decode(Utility.generateHTMLColorCode());

        for (int i = 0; i < strandSize; i++) {
            Vector v = Vector.random2DVector();
            v.setMagnitude(force);
            genes.add(v);
        }
    }
    /**
     * Constructor for dna, using given genes.
     *
     * @param genes list of vectors.
     */
    public DNA(ArrayList<Vector> genes, Color cc) {
        this.genes = genes;
        variance = new Random();
        prized = false;
        col = cc;
    }

    /**
     * @return returns true if and only if the dna is considered prized;
     */
    public boolean isPrized() { return  prized; }
    /**
     * sets the value of the dna to prized if unprized and vice verse.
     */
    public void prizify()  {  if (prized) { prized = false; } else { prized = true; } }
    /**
     * Returns the particular gene at the given index.
     * @param index index to get the gene from.
     * @return the particular vector.
     */
    public Vector getGene(int index) { return genes.get(index); }
    /**
     * Creates a brand new dna from current and given genes.
     *
     * @param partner second set of dna genes to be used.
     * @return New genes.
     */
    public DNA Breed(DNA partner) {
        ArrayList<Vector> newgenes = new ArrayList<>();
        int divider = variance.nextInt(genes.size());

        int k = (int)(genes.size()*(95/100.0f));
        for (int i = 0; i < genes.size(); i++) {

            if (partner.isPrized())
            {
                if (k >= i) { newgenes.add(partner.getGene(i)); } else { newgenes.add(this.getGene(i)); }
            }
            else if (this.isPrized())
            {
                if (k >= i) { newgenes.add(this.getGene(i)); } else { newgenes.add(partner.getGene(i)); }
            }
            else
            {
                if (i > divider) { newgenes.add(partner.getGene(i)); } else { newgenes.add(this.getGene(i)); }
            }
        }
        return new DNA(newgenes, Utility.blend(getColor(), partner.getColor()));
    }
    public javafx.scene.paint.Paint getPaint()
    {
        int r = col.getRed();
        int g = col.getGreen();
        int b = col.getBlue();
        return javafx.scene.paint.Color.rgb(r, g, b);
    }
    /**
     * @return returns the color of the dna.
     */
    public Color getColor() { return col; }
    /**
     * @return returns an arraylist of vectors.
     */
    public ArrayList<Vector> getGenes() { return genes; }
    /**
     * Alters the genes of the DNA randomly.
     */
    public void Mutate()
    {
        for (int i = 0; i < genes.size(); i++)
        {
            if (variance.nextInt(10000) < 1)
            {
                Vector v = genes.get(i);
                v = Vector.random2DVector();
                v.setMagnitude(force);
            }
        }
    }
}
