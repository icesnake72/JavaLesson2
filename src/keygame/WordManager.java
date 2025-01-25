package keygame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class WordManager {
  private final ArrayList<FallingWord> fallingWords;
  private final JFrame frame;

  public WordManager(JFrame frame) {
    this.fallingWords = new ArrayList<>();
    this.frame = frame;
  }

  public void addWord(String word, int speed) {
    FallingWord fallingWord = new FallingWord(word, speed, frame);
    fallingWords.add(fallingWord);
    frame.add(fallingWord); // UI에 추가
    Thread thread = new Thread(fallingWord);
    thread.start();
  }

  public void removeWord(String word) {
    Iterator<FallingWord> iterator = fallingWords.iterator();
    while (iterator.hasNext()) {
      FallingWord fw = iterator.next();
      if (fw.getText().equalsIgnoreCase(word)) {
        fw.destroy(); // 단어 제거
        frame.remove(fw); // UI에서 제거
        frame.repaint(); // UI 갱신
        iterator.remove(); // 리스트에서 제거
        System.out.println("Removed word: " + word);
        break;
      }
    }
  }

  public void cleanup() {
    Iterator<FallingWord> iterator = fallingWords.iterator();
    while (iterator.hasNext()) {
      FallingWord fw = iterator.next();
      if (!fw.isRunning()) {
        frame.remove(fw);
        iterator.remove();
        fw = null;
      }
    }
    frame.repaint();
  }
}
