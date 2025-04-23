package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {
    public static final double FOOD_SIZE = 0.02;
    private double x, y;

    /**
    * Represents a piece of food at a random location.
    * The food is created within the bounds of the game area.
    * The coordinates are generated randomly within the range of (FOOD_SIZE, 1 - FOOD_SIZE).
    * When eaten, the snake will grow and a new Food is created elsewhere.
    */
    public Food() {
        x = FOOD_SIZE + Math.random() * (1 - 2 * FOOD_SIZE);
        y = FOOD_SIZE + Math.random() * (1 - 2 * FOOD_SIZE);
    }

    public void draw() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(x, y, FOOD_SIZE);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}