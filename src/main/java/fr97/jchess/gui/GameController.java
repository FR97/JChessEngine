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
        // If same tile is clicked again
        if (selectedTile.equals(newTile)) {
            chessboardUi.removePossibleMoves(currentPossibleMoves);
            selectedTile.deselect();
            selectedTile = null;
        } else {
            if (newTile.isPossible()) {
                // MOVE TO NEW POSITION

                chessboardUi.removeChecked();
                game.makeMove(selectedTile.POSITION, newTile.POSITION);

                selectedTile.deselect();
                chessboardUi.removePossibleMoves(currentPossibleMoves);
                currentPossibleMoves = new int[0];

                if (game.getCurrentPlayer().isInCheck()) {
                    chessboardUi.setChecked(game.getCurrentPlayer().getKingPosition());
                }

                selectedTile = null;

                chessboardUi.updateTiles(game.boardAsArray());
                toggleStatus();
            } else if (selectedTile.isOccupied() && selectedTile.getPieceColor().equals(newTile.getPieceColor())) {
                chessboardUi.removePossibleMoves(currentPossibleMoves);
                selectedTile.deselect();
                selectedTile = newTile;
                selectedTile.select();

                currentPossibleMoves = new int[0];
                currentPossibleMoves = game.getPossibleMoves(selectedTile.POSITION);

                chessboardUi.setPossibleMoves(currentPossibleMoves);
            }
        }
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
