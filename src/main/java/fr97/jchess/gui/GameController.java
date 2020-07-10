package fr97.jchess.gui;

import fr97.jchess.core.Game;
import fr97.jchess.core.piece.PieceColor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by Filip on 11/28/2017.
 */
public class GameController {

    private final ChessboardComponent chessboardUi;
    private final Game game;

    private Tile selectedTile;
    private int[] currentPossibleMoves;

    //private Game.GameStatus gameStatus;
    private PieceColor colorOnMove;

    public GameController(Game game, ChessboardComponent chessboardUi) {
        this.game = game;
        this.chessboardUi = chessboardUi;
        this.currentPossibleMoves = new int[0];
        this.selectedTile = null;
        this.colorOnMove = PieceColor.WHITE;
        chessboardUi.initWithStartingPositions(game.boardAsArray(), this::tileEventHandler);
    }

    public void toggleStatus() {
        this.colorOnMove = game.getOnMove();
    }


    public void tileEventHandler(ActionEvent event){
        this.colorOnMove = game.getOnMove();
        Tile newTile = (Tile) event.getSource();

        if (selectedTile == null) {
            handleNoPreviousTileSelected(newTile);
        } else {
            handlePreviousTileSelected(newTile);
        }
    }

    private void handlePreviousTileSelected(Tile newTile) {
        if (selectedTile.equals(newTile)) {
            deselectTile();
        } else {
            if (newTile.isPossible()) {
                chessboardUi.removeChecked();
                game.makeMove(selectedTile.POSITION, newTile.POSITION);

                deselectTile();

                currentPossibleMoves = new int[0];

                if (game.getCurrentPlayer().isInCheck()) {
                    chessboardUi.setChecked(game.getCurrentPlayer().getKingPosition());
                }

                chessboardUi.updateTiles(game.boardAsArray());
                toggleStatus();
            } else if (selectedTile.isOccupied() && selectedTile.getPieceColor().equals(newTile.getPieceColor())) {
                deselectTile();
                selectTile(newTile);
            }
        }
    }

    private void deselectTile() {
        chessboardUi.removePossibleMoves(currentPossibleMoves);
        selectedTile.deselect();
        selectedTile = null;
    }

    private void selectTile(Tile newSelected){
        this.selectedTile = newSelected;
        selectedTile.select();

        currentPossibleMoves = game.getPossibleMoves(selectedTile.POSITION);
        chessboardUi.setPossibleMoves(currentPossibleMoves);
    }

    private void handleNoPreviousTileSelected(Tile newTile) {
        if (newTile.isOccupied() && newTile.getPieceColor() == colorOnMove) {
            selectedTile = newTile;
            selectedTile.select();
            currentPossibleMoves = game.getPossibleMoves(selectedTile.POSITION);

            chessboardUi.setPossibleMoves(currentPossibleMoves);
        }
    }

    public void undoEventHandler(ActionEvent event){
        game.undoLastMove();
        chessboardUi.updateTiles(game.boardAsArray());
        if (!game.getCurrentPlayer().isInCheck()) {
            chessboardUi.removeChecked();
        }
    }
}
