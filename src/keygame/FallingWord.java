package keygame;

import javax.swing.*;
import java.awt.*;

public class FallingWord extends JLabel implements Runnable {
  private final int speed;
  private int x;
  private int y;
  private boolean running;
  private final JFrame frame;

  public FallingWord(String word, int speed, JFrame frame) {
    this.speed = speed;
    this.x = (int) (Math.random() * 400); // 초기 x 위치 랜덤
    this.y = 0; // 초기 y 위치
    this.running = true;
    this.frame = frame;

    // UI 초기화
    setText(word);
    setForeground(Color.WHITE);
    setSize(getPreferredSize());
    setLocation(x, y);
  }

  @Override
  public void run() {
    try {
      while (running && y < 400) { // 화면 끝에 도달할 때까지 실행
        y += speed;
        SwingUtilities.invokeLater(() -> setLocation(x, y));
        Thread.sleep(100);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      destroy(); // 항상 정리 작업 수행
    }
  }

  public void destroy() {
    running = false;
    SwingUtilities.invokeLater(() -> {
      frame.remove(this);
      frame.repaint();
    });
  }

  public boolean isRunning() {
    return running;
  }
}
