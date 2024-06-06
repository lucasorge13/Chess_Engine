package main;

import pieces.*;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Board extends JPanel{

  public int tileSize = 85;

  int cols = 8;
  int rows = 8;

  ArrayList<Piece> pieceList = new ArrayList<>();

  public Piece selectedPiece;

  Input input = new Input(this);

  public Board(){
    this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

    this.addMouseListener(input);
    this.addMouseMotionListener(input);

    addPieces();
  }

  public Piece getPiece(int row, int col){

    for(Piece piece : pieceList){
      if(piece.row == row && piece.col == col){
        return piece;
      }
    }

    return null;
  }

  public boolean isValidMove(Move move){
      return !sameTeam(move.piece, move.capture);
  }

  public boolean sameTeam(Piece p1, Piece p2){
    if(p1 == null || p2 == null){
      return false;
    }

    return p1.isWhite == p2.isWhite;
  }

  public void makeMove(Move move){
    move.piece.col = move.newCol;
    move.piece.row = move.newRow;
    move.piece.xPos = move.newCol * tileSize;
    move.piece.yPos = move.newRow * tileSize;

    capture(move);
  }

  public void capture(Move move){
    pieceList.remove(move.capture);
  }

  public void addPieces(){
    pieceList.add(new Rook(this, 0, 0, false));
    pieceList.add(new Knight(this, 1, 0, false));
    pieceList.add(new Bishop(this, 2, 0, false));
    pieceList.add(new Queen(this, 3, 0, false));
    pieceList.add(new King(this, 4, 0, false));
    pieceList.add(new Bishop(this, 5, 0, false));
    pieceList.add(new Knight(this, 6, 0, false));
    pieceList.add(new Rook(this, 7, 0, false));

    pieceList.add(new Pawn(this, 0, 1, false));
    pieceList.add(new Pawn(this, 1, 1, false));
    pieceList.add(new Pawn(this, 2, 1, false));
    pieceList.add(new Pawn(this, 3, 1, false));
    pieceList.add(new Pawn(this, 4, 1, false));
    pieceList.add(new Pawn(this, 5, 1, false));
    pieceList.add(new Pawn(this, 6, 1, false));
    pieceList.add(new Pawn(this, 7, 1, false));

    pieceList.add(new Rook(this, 0, 7, true));
    pieceList.add(new Knight(this, 1, 7, true));
    pieceList.add(new Bishop(this, 2, 7, true));
    pieceList.add(new Queen(this, 3, 7, true));
    pieceList.add(new King(this, 4, 7, true));
    pieceList.add(new Bishop(this, 5, 7, true));
    pieceList.add(new Knight(this, 6, 7, true));
    pieceList.add(new Rook(this, 7, 7, true));

    pieceList.add(new Pawn(this, 0, 6, true));
    pieceList.add(new Pawn(this, 1, 6, true));
    pieceList.add(new Pawn(this, 2, 6, true));
    pieceList.add(new Pawn(this, 3, 6, true));
    pieceList.add(new Pawn(this, 4, 6, true));
    pieceList.add(new Pawn(this, 5, 6, true));
    pieceList.add(new Pawn(this, 6, 6, true));
    pieceList.add(new Pawn(this, 7, 6, true));
  }

  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D) g;

    for(int i = 0; i < rows; i++){
      for (int j = 0; j < cols; j++){
        g2d.setColor((i + j) % 2 == 0 ? Color.white : Color.DARK_GRAY);

        g2d.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
      }
    }

    for(Piece pieces : pieceList){
      pieces.paint(g2d);
    }
  }
}