package keygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TypingGame {
  private static final int FALLING_LABEL_SPEED = 5;
  private static final int FALLING_LABEL_INTERVAL = 1000;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Typing Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setResizable(false);
    frame.setLayout(null);
    frame.getContentPane().setBackground(Color.BLUE);

    // TextField 생성 및 초기 배치
    JTextField textField = new JTextField();
    frame.add(textField);

    // WordManager 인스턴스 생성
    WordManager wordManager = new WordManager(frame);

    // TextField의 초기 위치와 크기를 설정
    SwingUtilities.invokeLater(() -> {
      Insets insets = frame.getInsets();
      textField.setBounds(0, frame.getHeight() - 30 - insets.top, frame.getWidth(), 30);
      textField.requestFocusInWindow(); // 텍스트 필드에 포커스 설정
    });

    // TextField 입력 이벤트 처리
    textField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          String inputText = textField.getText();
          wordManager.removeWord(inputText); // 입력 단어 제거 요청
          textField.setText(""); // 입력 필드 초기화
        }
      }
    });

    // 프레임 크기 조정 시 TextField 위치와 크기 동기화
    frame.addComponentListener(new java.awt.event.ComponentAdapter() {
      @Override
      public void componentResized(java.awt.event.ComponentEvent evt) {
        Insets insets = frame.getInsets();
        textField.setBounds(0, frame.getContentPane().getHeight() - 30, frame.getWidth(), 30);
      }
    });


    // FallingWord 추가 타이머
    Timer timer = new Timer(FALLING_LABEL_INTERVAL, e -> {
      String randomWord = "Word" + (int) (Math.random() * 100);
      wordManager.addWord(randomWord, FALLING_LABEL_SPEED);
    });
    timer.start();

    // 정리 작업 수행
    Timer cleanupTimer = new Timer(500, e -> wordManager.cleanup());
    cleanupTimer.start();

    frame.setVisible(true);
  }
}
