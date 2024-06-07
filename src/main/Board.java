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

  public int enPassantTile = -1;

  CheckScanner checkScanner = new CheckScanner(this);

  public Board(){
    this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

    this.addMouseListener(input);
    this.addMouseMotionListener(input);

    addPieces();
  }

  public Piece getPiece(int col, int row){

    for(Piece piece : pieceList){
      if(piece.col == col && piece.row == row){
        return piece;
      }
    }

    return null;
  }

  public boolean isValidMove(Move move){
    if(sameTeam(move.piece, move.capture)){
      return false;
    }

    if(!move.piece.isValidMovement(move.newCol, move.newRow)){
      return false;
    }

    if(move.piece.moveCollidesWithPiece(move.newCol, move.newRow)){
      return false;
    }

    if(checkScanner.isKingChecked(move)){
      return false;
    }

    return true;
  }

  public boolean sameTeam(Piece p1, Piece p2){
    if(p1 == null || p2 == null){
      return false;
    }

    return p1.isWhite == p2.isWhite;
  }

  public void makeMove(Move move){

    if(move.piece.name.equals("Pawn")){
      movePawn(move);
    } else {

    move.piece.col = move.newCol;
    move.piece.row = move.newRow;
    move.piece.xPos = move.newCol * tileSize;
    move.piece.yPos = move.newRow * tileSize;

    move.piece.isFirstMove = false;

    capture(move.capture);
    }
  }

  private void movePawn(Move move) {

    int colorIndex = move.piece.isWhite ? 1 : -1;

    if(getTileNum(move.newCol, move.newRow) == enPassantTile){
      move.capture = getPiece(move.newCol, move.newRow + colorIndex);
    }

    if (Math.abs(move.piece.row - move.newRow) == 2) {
      enPassantTile = getTileNum(move.newCol, move.newRow + colorIndex);
    } else {
      enPassantTile = -1;
    }

    colorIndex = move.piece.isWhite ? 0 : 7;
    if(move.newRow == colorIndex){
      promotePawn(move);
    }


    move.piece.col = move.newCol;
    move.piece.row = move.newRow;
    move.piece.xPos = move.newCol * tileSize;
    move.piece.yPos = move.newRow * tileSize;

    move.piece.isFirstMove = false;

    capture(move.capture);
  }

  private void promotePawn(Move move) {
    pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
    capture(move.piece);
  }

  public void capture(Piece piece){
    pieceList.remove(piece);
  }

  Piece findKing(boolean isWhite){
    for(Piece piece : pieceList){
      if(isWhite == piece.isWhite && piece.name.equals("King")){
        return piece;
      }
    }
    return null;
  }

  public int getTileNum(int col, int row){
    return row * rows + col;
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

    if(selectedPiece != null)
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (isValidMove(new Move(this, selectedPiece, j, i))) {
          g2d.setColor(new Color(68, 180, 57, 190));
          g2d.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
        }
      }
    }

    for(Piece pieces : pieceList){
      pieces.paint(g2d);
    }
  }
}