package keygame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TypingGame {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Typing Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setResizable(false); // Fix the size of the window
    frame.setLayout(null);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.setVisible(true);

    FallingWord word = new FallingWord(frame, "Hello", 5);
    frame.add(word);
    Thread thread = new Thread(word);
    thread.start();
  }
}
