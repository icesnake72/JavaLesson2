package keygame;

import javax.swing.*;
import java.awt.*;

public class FallingWord extends JLabel implements Runnable {
  private final int speed;
  private int x;
  private int y;
  private final JFrame frame;

  public FallingWord(JFrame frame, String word, int speed) {
    this.frame = frame;
    this.speed = speed;
    x = (int) (Math.random() * 400);
    y = 0;
    setForeground(Color.WHITE);
    setText(word);
    setSize(getPreferredSize());
    setLocation(x, y);
  }

  @Override
  public void run() {
    while (y < 400) {
      y += speed;
      setLocation(x, y);
      try {
        System.out.printf("x: %d, y: %d\n", x, y);
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // Remove the word from the frame and stop the thread
    // Remove the word from the frame and stop the thread
    SwingUtilities.invokeLater(() -> {
      frame.remove(this);          // Swing 컴포넌트에서 제거
      System.out.println("FallingWord removed from frame."); // 제거 시점 확인
      frame.repaint();
    });
  }
}
