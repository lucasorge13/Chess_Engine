package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    Board board = new Board();

    frame.setMinimumSize(new Dimension(1000, 1000));

    frame.setLocationRelativeTo(null);

    frame.setVisible(true);

    frame.setLayout(new GridBagLayout());

    frame.getContentPane().setBackground(Color.black);

    frame.add(board);
  }
}
