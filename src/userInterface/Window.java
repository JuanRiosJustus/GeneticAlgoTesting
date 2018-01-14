package userInterface;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import structs.Population;
import structs.Vector;

import java.awt.*;

public class Window extends Application
{
    private GraphicsContext gc;
    private Population popul;
    private Canvas canvas;
    private FlowPane pane;
    private Scene scene;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int windowHeight = 720;
    private final int windowWidth = 1280;

    public void start(Stage stage)
    {
        initializeComponents(stage);
        initializeAnimation();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Initializes the components of the application.
     */
    private void initializeComponents(Stage stage)
    {
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setTitle("Cell Genes");
        stage.setResizable(false);

        pane = new FlowPane();
        scene = new Scene(pane, windowWidth, windowHeight);
        canvas = new Canvas(windowWidth, windowHeight);
        popul = new Population(new Vector(windowWidth, windowHeight));
        gc = canvas.getGraphicsContext2D();

        pane.getChildren().add(canvas);
    }
    /**
     * Initializes the animation of the application
     */
    private void initializeAnimation()
    {
        new AnimationTimer()
        {
            int timer = 0;
            int generation = 1;
            int fitness = 0;
            int allFoundGenes = 0;
            int allWinningGenes = 0;
            boolean configure = true;

            public void handle(long currentNanoTime)
            {
                if (configure) { allFoundGenes = allFoundGenes + popul.getMatingPoolSize(); configure = false; }

                gc.setFill(Paint.valueOf("Black"));
                gc.fillRect(0, 0, windowWidth, windowHeight);
                gc.setFill(Paint.valueOf("White"));
                gc.fillText("Generation: " + generation + "\n" +
                            "Perfect Genes: " + popul.getWinningGenesCount() + "\n" +
                            "Current Pool Size: " + popul.getMatingPoolSize() + "\n" +
                            "Total Genes Found: " + allFoundGenes + "\n" +
                            "(Lower the pool, less to select from.)"

                        , windowWidth-250, 100);
                popul.render(gc);

                timer++;
                // reset for next generation
                if (popul.isUnsalvageable())
                {
                    allWinningGenes = allWinningGenes + popul.getWinningGenesCount();
                    popul.evaluate();
                    popul.naturalSelection();
                    popul.resetEnvironment();
                    timer = 0;
                    generation++;
                    configure = true;

                }
            }
        }.start();

    }
}