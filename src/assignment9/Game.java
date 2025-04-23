package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;

    public Game() {
        StdDraw.enableDoubleBuffering();
        // Optional: explicitly set scale
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        snake = new Snake();
        food = new Food();
    }

    /**
     * play(): runs until the snake leaves the bounds.
     * Each iteration:
     *  1. Read keypress and change snake direction
     *  2. Move snake forward one step
     *  3. Check for food eat; if eaten, respawn new Food()
     *  4. Redraw everything
     * After loop ends, display "Game Over!".
     */
    public void play() {
        while (snake.isInbounds()) {
            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }
            snake.move();
            if (snake.eatFood(food)) {
                food = new Food();
            }
            updateDrawing();
        }
        // literlly just draws a bunch of text that says game over after the loss check lol
        StdDraw.clear();
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.show();
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W) || StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S) || StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A) || StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D) || StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            return 4;
        }
        return -1;
    }

    private void updateDrawing() {
        StdDraw.clear();
        snake.draw();
        food.draw();
        StdDraw.pause(50);
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
