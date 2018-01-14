
import userInterface.*;

import javafx.application.Application;
import structs.Vector;

public class Main {
    public static void main(String[] args) {
        Vector r = new Vector(4, 11, 0);
        Vector m = new Vector(2, 11, 0.2);
        r.setMagnitude(52);
        Application.launch(Window.class, args);
    }
}
