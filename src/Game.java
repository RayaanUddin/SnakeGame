import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game extends JPanel implements KeyListener {
    static class Position {
        public int x;
        public int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private final Position snakeHeadPosition;
    private final ArrayList<Position> snakeBody;
    private final Position food;
    private final Snake snake;
    private Timer gameTimer = null;
    public Game(int width, int height, Snake snake) {
        super();
        addKeyListener(this);
        this.snake = snake;
        snakeBody = new ArrayList<>();
        setPreferredSize(new Dimension(width * 10, height * 10));
        setBackground(Color.BLACK);
        setFocusable(true);
        snakeHeadPosition = new Position(0, 0);
        food = new Position((int) (Math.random() * width), (int) (Math.random() * height));
        gameTimer = new Timer(100, e -> {
            //gameTimer.setDelay((int) (1000 * snake.getSpeedMultiplier()));
            if (snake.getDirection() == 0) {
                snakeHeadPosition.y--;
            } else if (snake.getDirection() == 1) {
                snakeHeadPosition.x++;
            } else if (snake.getDirection() == 2) {
                snakeHeadPosition.y++;
            } else if (snake.getDirection() == 3) {
                snakeHeadPosition.x--;
            }
            if (snakeHeadPosition.x < 0) {
                snakeHeadPosition.x = width - 1;
            } else if (snakeHeadPosition.x >= width) {
                snakeHeadPosition.x = 0;
            }
            if (snakeHeadPosition.y < 0) {
                snakeHeadPosition.y = height - 1;
            } else if (snakeHeadPosition.y >= height) {
                snakeHeadPosition.y = 0;
            }

            for (Position position : snakeBody) {
                if (position.x == snakeHeadPosition.x && position.y == snakeHeadPosition.y) {
                    System.out.println("Game Over! Your score: " + snake.getLength());
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(this, "Game Over! Your score: " + snake.getLength());
                    System.exit(0);
                }
            }

            snakeBody.add(new Position(snakeHeadPosition.x, snakeHeadPosition.y));
            if (snakeBody.size() > snake.getLength()) {
                snakeBody.remove(0);
            }

            if (snakeHeadPosition.x == food.x && snakeHeadPosition.y == food.y) {
                snake.increaseLength(1);
                food.x = (int) (Math.random() * width);
                food.y = (int) (Math.random() * height);
            }
            repaint();
        });
        gameTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw grid
        //g.setColor(Color.GRAY);
        //for(int i = 0; i < getWidth()/10; i++) {
        //    g.drawLine(i*10, 0, i*10, getHeight());
        //    g.drawLine(0, i*10, getWidth(), i*10);
        //}

        // Draw snake
        g.setColor(new Color(snake.getColor()));
        g.fillRect(snakeHeadPosition.x * 10, snakeHeadPosition.y * 10, 10, 10);

        // Draw snake body
        for (Position position : snakeBody) {
            g.fillRect(position.x * 10, position.y * 10, 10, 10);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * 10, food.y * 10, 10, 10);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (snake.getDirection() == 0 || snake.getDirection() == 2) {
            if (key == KeyEvent.VK_RIGHT) {
                snake.setDirection(1);
            } else if (key == KeyEvent.VK_LEFT) {
                snake.setDirection(3);
            }
        } else if (snake.getDirection() == 1 || snake.getDirection() == 3) {
            if (key == KeyEvent.VK_UP) {
                snake.setDirection(0);
            } else if (key == KeyEvent.VK_DOWN) {
                snake.setDirection(2);
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        Snake snake = new Snake(0, 0x00FF00, 0.5);
        Game game = new Game(20, 20, snake);
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
