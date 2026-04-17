package main;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Start the game on the Swing Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });
    }
}
