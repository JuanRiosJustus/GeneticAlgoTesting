package structs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Population {

    private ArrayList<Cell> cells;
    private ArrayList<Cell> matingPool;
    private Vector endpoint;
    private Random rand;
    private int populationSize;
    private HashSet<Cell> winners;
    private int fitness;
    private int winningGenes;

    /**
     * Default constructor for the population.
     */
    public Population(Vector end)
    {
        endpoint = end;
        cells = new ArrayList<>();
        matingPool = new ArrayList<>();
        winners = new HashSet<Cell>();
        rand = new Random();
        populationSize = 20;
        fitness = 0;
        for (int i = 0; i < populationSize; i++)
        {
            cells.add(new Cell());
        }
    }
    public void resetEnvironment()
    {
        //winningGenes = 0;
        winners.clear();
    }
    /**
     * Draws all of the cells to the screen.
     * @param gc graphics context used to draw.
     */
    public void render(GraphicsContext gc)
    {

        gc.setFill(Paint.valueOf("GREEN"));
        gc.fillRect(endpoint.x()-30, endpoint.y()-50, 10, 10);

        for (int i = 0; i < cells.size(); i++)
        {
            Cell c = cells.get(i);
            gc.setFill(c.getDna().getPaint());
            gc.fillOval(c.getPosition().x(), c.getPosition().y(), 10, 10);
            c.update(endpoint, rand.nextInt(cells.size()));

            if (c.isCompleted() && winners.contains(c) == false)
            {
                winningGenes++;
                winners.add(c);
            }
        }
    }
    /**
     * Check the fitness via euclidean distance to the endpoint.
     */
    public void evaluate()
    {
        double maxFit = 0;
        for (int i = 0; i < cells.size(); i++)
        {
            Cell c = cells.get(i);
            c.calculateAcuity(endpoint);
            if (c.getAcuity() > maxFit)
            {
                maxFit = c.getAcuity();
            }
        }
        if (maxFit == 0) { maxFit = 1; }
        for (int i = 0; i < cells.size(); i++)
        {
            Cell c = cells.get(i);
            c.setAcuity(c.getAcuity()/maxFit);
        }

        ArrayList<Cell> newMatingPool = new ArrayList<Cell>();
        for (int i = 0; i < cells.size(); i++)
        {
            Cell c = cells.get(i);
            double j = c.getAcuity()* 100;
            for (int k = 0; k < (int)j; k++)
            {
                newMatingPool.add(c);
            }
        }
        if (newMatingPool.size() == 0) { return; }
        matingPool = newMatingPool;
        System.out.println(matingPool.size() + " is the size of the mating pool.");
    }
    /**
     * Creates the new children cells.
     */
    public void naturalSelection()
    {
        ArrayList<Cell> newCells = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++)
        {
            if (matingPool.isEmpty()) { matingPool.add(new Cell()); }
            DNA a = matingPool.get(rand.nextInt(matingPool.size())).getDna();
            DNA b = matingPool.get(rand.nextInt(matingPool.size())).getDna();
            DNA c = a.Breed(b);
            c.Mutate();
            newCells.add(new Cell(c));
        }
        //newCells.add(new Cell());
        cells = newCells;
    }

    /**
     * @return Determines if there are uncrashed cells.
     */
    public boolean isUnsalvageable()
    {
        int j = 0;
        for (int i = 0; i < cells.size(); i++)
        {
            if (cells.get(i).isCrashed()) { j++;}
        }
        return j == cells.size();
    }
    /**
     * @return returns the size of the mating pool, the larger the better
     */
    public int getMatingPoolSize() { return matingPool.size(); }

    /**
     * @return returns the amount of winning genes.
     */
    public int getWinningGenesCount() { return  winningGenes; }
}
