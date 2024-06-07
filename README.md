# Chess Engine Project

## Overview
This project is a chess engine implemented in Java. It allows users to play chess with basic functionalities including move validation, check detection, and basic AI.

## Files
- `Board.java`: Represents the chess board and manages the state of the game.
- `CheckScanner.java`: Handles the detection of check conditions in the game.
- `Input.java`: Manages the user inputs and interactions.
- `Main.java`: The main entry point of the application. It initializes the game and manages the game loop.
- `Move.java`: Represents a move in the game, handling move validation and execution.
- `Piece.java`: An abstract class representing a generic chess piece. All specific piece classes inherit from this.
- `Pawn.java`: Represents a pawn piece and its specific movements and behaviors.
- `Rook.java`: Represents a rook piece and its specific movements and behaviors.
- `Knight.java`: Represents a knight piece and its specific movements and behaviors.
- `Bishop.java`: Represents a bishop piece and its specific movements and behaviors.
- `Queen.java`: Represents a queen piece and its specific movements and behaviors.
- `King.java`: Represents a king piece and its specific movements and behaviors.

## Features
- **Move Validation**: Ensures all moves follow the rules of chess.
- **Check Detection**: Detects when a king is in check.
- **User Input Management**: Handles user inputs for making moves.
- **Game Loop**: Manages the flow of the game from start to finish.
