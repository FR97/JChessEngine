package gui;

import core_v2.Pieces.Piece;
import core_v2.Utils.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Set;

/**
 * Created by Filip on 11/28/2017.
 */
public class ChessboardComponent {
    private final GridPane parent;
    private final GridPane grid;
    private final Tile[][] chessboard = new Tile[8][8];

    public ChessboardComponent() {

        parent = new GridPane();
        grid = new GridPane();

        parent.getStyleClass().add("chessboard-style");
        grid.getStyleClass().add("chessboard-grid");
    }

    public void initWithStartingPositions(String[][] chessboard, EventHandler<ActionEvent> eventHandler) {

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
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                Tile tile = new Tile( Position.get(i, j));
                tile.setPiece(chessboard[i][j]);
                if ((i + j) % 2 == 0) {
                    tile.setColor("white");
                } else {
                    tile.setColor("black");
                }
                tile.setOnAction(eventHandler);
                this.chessboard[i][j] = tile;
                grid.add(tile, i, j);

            }
        }
        parent.add(grid, 1, 1, 9, 9);
    }

    public void updateTiles(String[][] chessboard){
        grid.getChildren().forEach( t -> {
            Tile tile = (Tile) t;
            tile.setPiece(chessboard[tile.POSITION.X][tile.POSITION.Y]);
        });
    }

    public void setChecked(Position kingPosition, boolean checked){
        chessboard[kingPosition.X][kingPosition.Y].setChecked(checked);
    }


    public void setPossibleMoves(Set<Position> positions) {
        positions.forEach(position -> {
            chessboard[position.X][position.Y].markAsPossibleMove(true);
        });
    }

    public void removePossibleMoves(Set<Position> positions) {
        positions.forEach(position -> {
            chessboard[position.X][position.Y].markAsPossibleMove(false);
        });
    }


    public Parent getComponentNode() {
        return parent;
    }
}
