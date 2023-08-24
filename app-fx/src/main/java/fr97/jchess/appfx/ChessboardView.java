package fr97.jchess.appfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class ChessboardView implements FXView<GridPane> {
    private final GridPane root;
    private final GridPane grid;
    private final Tile[] chessboard = new Tile[64];

    public ChessboardView() {

        root = new GridPane();
        grid = new GridPane();
        root.setPadding(new Insets(10));
        root.getStyleClass().add("chessboard-style");
        grid.getStyleClass().add("chessboard-grid");

        initFilesAndRanks();

    }

    public void initFilesAndRanks() {
        for (int i = 0; i < 8; i++) {
            char c = 'A';
            c += i;
            Label lblLetters = new Label(Character.toString(c));
            lblLetters.setPadding(new Insets(0, 10, 0, 50));
            Label lblNumbers = new Label(Integer.toString(8 - i));
            lblNumbers.setPadding(new Insets(35, 0, 10, 0));
            lblNumbers.setAlignment(Pos.CENTER);
            root.add(lblLetters, i + 1, 0);
            root.add(lblNumbers, 0, i + 1);
        }
    }

    public void initWithStartingPositions(String[] chessboard, EventHandler<ActionEvent> eventHandler) {

        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i);
            tile.setPiece(chessboard[i]);
            tile.setId("tile" + i);
            if (((i % 8) + (i / 8)) % 2 == 0) {
                tile.setColor("white");
            } else {
                tile.setColor("black");
            }
            tile.setOnAction(eventHandler);
            this.chessboard[i] = tile;
            grid.add(tile, i % 8, i / 8);

        }

        root.add(grid, 1, 1, 9, 9);
    }

    public void removeAllLastMoveMarks() {
        for (Tile t : chessboard)
            t.removeLastMoveMark();
    }

    public Tile getTile(int position) {
        return chessboard[position];
    }

    public void updateTiles(String[] chessboard) {
        grid.getChildren().forEach(t -> {
            Tile tile = (Tile) t;
            tile.setPiece(chessboard[tile.position]);
        });
    }

    public void removeChecked() {
        for (Tile t : chessboard) {
            t.setChecked(false);
        }
    }

    public void setChecked(int kingPosition) {
        chessboard[kingPosition].setChecked(true);
    }


    public void setPossibleMoves(int[] possibleMoves) {
        for (int possibleMove : possibleMoves) {
            chessboard[possibleMove].markAsPossibleMove(true);
        }
    }

    public void setPossibleMoves(List<Integer> possibleMoves) {
        possibleMoves.forEach(i -> chessboard[i].markAsPossibleMove(true));
    }

    public void removePossibleMoves() {
        for (Tile t : chessboard)
            t.markAsPossibleMove(false);
    }


    @Override
    public GridPane getRoot() {
        return root;
    }
}