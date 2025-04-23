package assignment9;

import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;

public class Snake {
    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        segments = new LinkedList<>();
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        // Start moving to the right by default
        deltaX = MOVEMENT_SIZE;
        deltaY = 0;
    }

    public void changeDirection(int direction) {
        if (direction == 1) { // up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { // down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { // left
            deltaX = -MOVEMENT_SIZE;
            deltaY = 0;
        } else if (direction == 4) { // right
            deltaX = MOVEMENT_SIZE;
            deltaY = 0;
        }
    }

    /**
     * Moves the snake forward:
     * - Compute new head position by adding deltaX/deltaY to current head.
     * - Insert a new head at that position.
     * - Remove the last segment to keep length constant (unless we've grown).
     *
     * This shifting of segments is what makes the snake appear to slither.
     */
    public void move() {
        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;
        segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));
        segments.removeLast();
    }

    /**
     * Checks if the head overlaps the food. If so, grows the snake by:
     * - Getting the tail segment's position
     * - Adding a new segment at the tail location, increasing length by one
     * Returns true if growth happened (food was eaten).
     */
    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        double dx = head.getX() - f.getX();
        double dy = head.getY() - f.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance <= SEGMENT_SIZE + Food.FOOD_SIZE) {
            BodySegment tail = segments.getLast();
            segments.addLast(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
            return true;
        }
        return false;
    }

    /**
     * Returns false if the head is outside the [0,1] window bounds; triggers game over.
     * The snake is considered out of bounds if its head's x or y coordinate is less than 0 or greater than 1.
     * This method is called in the game loop to check if the snake is still within the game area.
     * If the snake goes out of bounds, the game ends.
     */
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double x = head.getX();
        double y = head.getY();
        return x >= 0 && x <= 1 && y >= 0 && y <= 1;
    }

    public void draw() {
        for (BodySegment seg : segments) {
            seg.draw();
        }
    }
}