package fr97.jchess.gui;

import fr97.jchess.core.Game;
import fr97.jchess.core.piece.PieceColor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by Filip on 11/28/2017.
 */
public class GameController {

    private Tile selectedTile;
    private ChessboardComponent chessboardUi;
    private Game game;
    private int[] currentPossibleMoves;

    //private Game.GameStatus gameStatus;
    private PieceColor colorOnMove;

    public GameController(Game game, ChessboardComponent chessboardUi) {
        this.game = game;
        this.chessboardUi = chessboardUi;
        this.currentPossibleMoves = new int[0];

        this.selectedTile = null;

        // this.gameStatus = game.getStatus();
        this.colorOnMove = PieceColor.WHITE;
        chessboardUi.initWithStartingPositions(game.boardAsArray(), tileEvent);
    }

    public void toggleStatus() {
        /*
        this.gameStatus = game.getStatus();
        if(gameStatus == Game.GameStatus.BLACK_WON){
            System.out.println("GAME FINISHED AND BLACK WON");
        }else if(gameStatus == Game.GameStatus.WHITE_WON){
            System.out.println("GAME FINISHED AND WHITE WON");
        }*/
        this.colorOnMove = game.getOnMove();
        System.out.println("Changed on move!");
    }

    EventHandler<ActionEvent> undoEvent = event -> {

    };

    EventHandler<ActionEvent> tileEvent = event -> {
        this.colorOnMove = game.getOnMove();
        Tile newTile = (Tile) event.getSource();
        //System.out.println("Tile clicked: " + newTile);
        if (selectedTile == null) {

            if (newTile.isOccupied() && newTile.getPieceColor() == colorOnMove) {
                selectedTile = newTile;
                selectedTile.select();
                currentPossibleMoves = game.getPossibleMoves(selectedTile.POSITION);

                chessboardUi.setPossibleMoves(currentPossibleMoves);
            }
        } else {
            // If same tile is clicked again
            if (selectedTile.equals(newTile)) {
                chessboardUi.removePossibleMoves(currentPossibleMoves);
                selectedTile.deselect();
                selectedTile = null;
            } else {
                if (newTile.isPossible()) {
                    // MOVE TO NEW POSITION

                    // newTile.setPiece(selectedTile.getPiece());
                    //  selectedTile.setPiece("");

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
                    /*if(game.getAiMove()){

                        chessboardUi.updateTiles(game.boardAs2DString());
                        toggleStatus();
                    }*/

                } else if (selectedTile.isOccupied() && selectedTile.getPieceColor().equals(newTile.getPieceColor())) {
                    // System.out.println(currentPossibleMoves);
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


    };


}
