package structs;

import java.util.Random;
import java.awt.Color;

public class Utility
{
    private static Random rand = new Random();

    /**
     * @return returns a html string representing a color.
     */
    public static String generateHTMLColorCode()
    {
        int bigFreakinNumber = rand.nextInt(256*256*256);
        String colorCode = String.format("#%06x", bigFreakinNumber);
        return  colorCode;
    }

    /**
     * @param c0 first color.
     * @param c1 seond color.
     * @return mixed color to return.
     */
    public static Color blend(Color c0, Color c1) {
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
}
