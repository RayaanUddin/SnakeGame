import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 0, 10));
        frame.add(panel);

        JLabel label = new JLabel("Main Menu");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(label);

        JButton button = new JButton("Start Game");
        panel.add(button);

        button.addActionListener(e -> {
            Snake snake = new Snake(0, 0x00FF00, 0.5);
            Game game = new Game(20, 20, snake);
            JFrame frame2 = new JFrame("Snake");
            frame2.add(game);
            frame2.pack();
            frame2.setVisible(true);
            frame2.setResizable(false);
        });

        JButton button2 = new JButton("Exit");
        panel.add(button2);

        button2.addActionListener(e -> {
            frame.dispose();
        });

        frame.revalidate();
    }
}
