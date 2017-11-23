package core_v2.Pieces;

import core_v2.Chessboards.Chessboard;
import core_v2.Game;
import core_v2.Moves.Move;
import core_v2.Moves.MoveType;
import core_v2.Utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Filip on 11/21/2017.
 */
public class RookTest {

    @Test
    public void withPosition() throws Exception {


    }

    @Test
    public void loadPossibleMoves() throws Exception {



        /* Pawn move/eat/enpassant
        Chessboard chessboard = new Chessboard();
        chessboard.makeMove(new Move(chessboard.getPiece(2, 6), Position.get(2, 4)));
        chessboard.makeMove(new Move(chessboard.getPiece(3, 1), Position.get(3, 3)));
        chessboard.makeMove(new Move(chessboard.getPiece(2, 4), Position.get(2, 3)));
        chessboard.makeMove(new Move(chessboard.getPiece(1, 1), Position.get(1, 3),MoveType.PAWN_DOUBLE_JUMP));
        System.out.println(chessboard.getPiece(2,3).getPossibleMoves());
        chessboard.makeMove(chessboard.getPiece(2,3).getPossibleMoves().get(1));
        System.out.println(chessboard.getPiece(0,1).getPossibleMoves());
        chessboard.makeMove(chessboard.getPiece(0,1).getPossibleMoves().get(2));*/


        /* Castling check
        Chessboard chessboard = new Chessboard();
        chessboard.makeMove(new Move(chessboard.getPiece(4, 6), Position.get(4, 4)));
        chessboard.makeMove(new Move(chessboard.getPiece(2, 1), Position.get(2, 3)));
        chessboard.makeMove(new Move(chessboard.getPiece(6, 7), Position.get(7, 5)));
        chessboard.makeMove(new Move(chessboard.getPiece(1, 1), Position.get(1, 3),MoveType.PAWN_DOUBLE_JUMP));
        chessboard.makeMove(new Move(chessboard.getPiece(5, 7), Position.get(2, 4)));
        chessboard.makeMove(new Move(chessboard.getPiece(5, 1), Position.get(5, 2)));
        System.out.println(chessboard.getPiece(4,7).getPossibleMoves());
        chessboard.makeMove(chessboard.getPiece(4,7).getPossibleMoves().get(4));
        chessboard.makeMove(new Move(chessboard.getPiece(2, 0), Position.get(0, 2)));
        chessboard.makeMove(new Move(chessboard.getPiece(6, 6), Position.get(6, 5)));
        chessboard.makeMove(new Move(chessboard.getPiece(1, 0), Position.get(2, 2)));
        chessboard.makeMove(new Move(chessboard.getPiece(6, 5), Position.get(6, 4)));
        chessboard.makeMove(new Move(chessboard.getPiece(3, 0), Position.get(2, 1)));
        chessboard.makeMove(new Move(chessboard.getPiece(6, 4), Position.get(6, 3)));
        System.out.println(chessboard.getPiece(4,0).getPossibleMoves());
        chessboard.makeMove(chessboard.getPiece(4,0).getPossibleMoves().get(5));
        */

        Game g = new Game();


    }

    @Test
    public void getPossibleMoves() throws Exception {
    }





}