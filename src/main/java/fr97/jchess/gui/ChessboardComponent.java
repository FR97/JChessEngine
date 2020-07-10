package fr97.jchess.gui;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by Filip on 11/28/2017.
 */
public class ChessboardComponent {

    private final GridPane parent;
    private final GridPane grid;
    private final Tile[] chessboard = new Tile[64];
    private Tile checkedTile = null;

    public ChessboardComponent() {
        parent = new GridPane();
        grid = new GridPane();

        parent.getStyleClass().add("chessboard-style");
        grid.getStyleClass().add("chessboard-grid");
    }

    public void initWithStartingPositions(String[] chessboard, EventHandler<ActionEvent> eventHandler) {

        for (int i = 0; i < 8; i++) {
            char c = 'A';
            c += i;
            Label lblLetters = new Label(Character.toString(c));
            lblLetters.setPadding(new Insets(0, 10, 0, 50));
            Label lblNumbers = new Label(Integer.toString(i + 1));
            lblNumbers.setPadding(new Insets(35, 0, 10, 0));
            lblNumbers.setAlignment(Pos.CENTER);
            parent.add(lblLetters, i + 1, 0);
            parent.add(lblNumbers, 0, i + 1);

        }
        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i);
            tile.setPiece(chessboard[i]);
            if (((i % 8) +( i / 8))% 2 == 0) {
                tile.setColor("white");
            } else {
                tile.setColor("black");
            }
            tile.setOnAction(eventHandler);
            this.chessboard[i] = tile;
            grid.add(tile, i % 8, i / 8);

        }

        parent.add(grid, 1, 1, 9, 9);
    }



    public void updateTiles(String[] chessboard) {
        for (Node n : grid.getChildren()) {
            Tile tile = (Tile) n;
            tile.setPiece(chessboard[tile.position]);
        }
    }

    public void removeChecked(){
        if(this.checkedTile != null)
            this.checkedTile.setChecked(false);
        this.checkedTile = null;
    }

    public void setChecked(int kingPosition) {
        this.checkedTile = chessboard[kingPosition];
        this.checkedTile.setChecked(true);
    }


    public void setPossibleMoves(int[] possibleMoves) {
        for (int possibleMove : possibleMoves) {
            chessboard[possibleMove].markAsPossibleMove(true);
        }

    }

    public void removePossibleMoves(int[] possibleMoves) {
        for (int possibleMove : possibleMoves) {
            chessboard[possibleMove].markAsPossibleMove(false);
        }
    }


    public Parent getComponentNode() {
        return parent;
    }
}
