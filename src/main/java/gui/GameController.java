package gui;

import core_v2.Game;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Filip on 11/28/2017.
 */
public class GameController {

    private Tile selectedTile;
    private ChessboardComponent chessboardUi;
    private Game game;
    private TreeSet<Position> currentPossibleMoves;

    private Game.GameStatus gameStatus;
    private PieceColor colorOnMove;

    public GameController(Game game, ChessboardComponent chessboardUi) {
        this.game = game;
        this.chessboardUi = chessboardUi;
        this.currentPossibleMoves = new TreeSet<>();

        this.selectedTile = null;

        this.gameStatus = game.getStatus();
        this.colorOnMove = PieceColor.WHITE;
        chessboardUi.initWithStartingPositions(game.boardAs2DString(), tileEvent);
    }

    public void toggleStatus(){
        this.gameStatus = game.getStatus();
        if(gameStatus == Game.GameStatus.BLACK_WON){
            System.out.println("GAME FINISHED AND BLACK WON");
        }else if(gameStatus == Game.GameStatus.WHITE_WON){
            System.out.println("GAME FINISHED AND WHITE WON");
        }
        this.colorOnMove = game.getOnMove();
        System.out.println("Changed on move!");
    }

    EventHandler<ActionEvent> tileEvent = event -> {

        Tile newTile = (Tile) event.getSource();
        System.out.println("Tile clicked: " + newTile);
        if (selectedTile == null ) {

            if(newTile.isOccupied() && newTile.getPieceColor() == colorOnMove){
                selectedTile = newTile;
                selectedTile.select();
                currentPossibleMoves = game.getPossibleMoveDestinations(selectedTile.POSITION);

                chessboardUi.setPossibleMoves(currentPossibleMoves);
            }
        } else {
            // If same tile is clicked again
            if (selectedTile.equals(newTile)) {
                chessboardUi.removePossibleMoves(currentPossibleMoves);
                selectedTile.deselect();
                selectedTile = null;
            } else {
                if (currentPossibleMoves.contains(newTile.POSITION)) {
                    // MOVE TO NEW POSITION

                    newTile.setPiece(selectedTile.getPiece());
                    selectedTile.setPiece("");
                    if(game.getOnMove() == PieceColor.WHITE){
                        chessboardUi.setChecked(game.getWhiteKingPosition(), false);
                    }
                    else{
                        chessboardUi.setChecked(game.getBlackKingPosition(), false);
                    }
                    //chessboardUi.setChecked(chessboardModel.getKingPosition(colorOnMove), false);

                    if(game.move(selectedTile.POSITION, newTile.POSITION)){

                        selectedTile.deselect();
                        chessboardUi.removePossibleMoves(currentPossibleMoves);
                        currentPossibleMoves = new TreeSet<>();
                        if(game.isKingInCheck()){
                            if(game.getOnMove() == PieceColor.WHITE){
                                chessboardUi.setChecked(game.getWhiteKingPosition(), true);
                            }
                            else{
                                chessboardUi.setChecked(game.getBlackKingPosition(), true);
                            }

                        }

                        selectedTile = null;
                    }
                    toggleStatus();
                    if(game.getAiMove()){

                        chessboardUi.updateTiles(game.boardAs2DString());
                        toggleStatus();
                    }

                    /*
                    if(moveValidator.isKingInDanger(chessboardModel, (colorOnMove == PieceColor.WHITE)? PieceColor.BLACK : PieceColor.WHITE)) {
                        chessboardUi.setChecked(chessboardModel.getKingPosition(colorOnMove), true);
                    }*/

                } else if (selectedTile.isOccupied() && selectedTile.getPieceColor().equals(newTile.getPieceColor())) {
                    chessboardUi.removePossibleMoves(currentPossibleMoves);
                    selectedTile.deselect();
                    selectedTile = newTile;
                    selectedTile.select();

                    currentPossibleMoves = new TreeSet<>();
                    currentPossibleMoves = game.getPossibleMoveDestinations(selectedTile.POSITION);

                    chessboardUi.setPossibleMoves(currentPossibleMoves);
                }


            }
        }


    };


}
