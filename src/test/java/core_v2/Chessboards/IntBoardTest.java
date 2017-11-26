package core_v2.Chessboards;


import core.Chessboard;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Moves.MoveType;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by Filip on 11/23/2017.
 */
public class IntBoardTest {

    @Test
    public void getMoveHistory() throws Exception {
        IntBoard chessboard = new IntBoard();

        System.out.println(MoveGenerator.getAllPossibleMovesForPlayer(chessboard));

        chessboard.makeMove(new Move(1, Position.get(1,6), Position.get(1,5)));

        IntBoard chessboard2 = new IntBoard(chessboard);
        chessboard2.undoMove();


        assertEquals(1, chessboard.getMoveHistory().size());

        assertEquals(0, chessboard2.getMoveHistory().size());

    }

    @Test
    public void makeMove() throws Exception {
        IntBoard chessboard = new IntBoard();

        System.out.println(MoveGenerator.getPossibleMovesForPieceAt(Position.get(1,6), chessboard));
        chessboard.printChessboard(true);
        System.out.println();
        chessboard.makeMove(new Move(1, Position.get(1,6), Position.get(1,5)));
        chessboard.printChessboard(true);
        chessboard.makeMove(new Move(-1, Position.get(0,1), Position.get(0,2)));
        chessboard.printChessboard(true);
        int expected = 1;
        int actual = chessboard.getPiece(Position.get(1,5));

        assertEquals(expected,actual);
    }

    @Test
    public void undoMove() throws Exception {
        IntBoard chessboard = new IntBoard();

        chessboard.makeMove(new Move(6,Position.get(4,7), Position.get(2,7), MoveType.CASTLING));

        chessboard.undoMove();
        int expected = 1;
        int actual = chessboard.getPiece(Position.get(1,6));

        assertEquals(expected,actual);

        PieceColor expected1 = PieceColor.WHITE;
        PieceColor actual1 = chessboard.getOnMove();

        assertEquals(expected1,actual1);

    }

    @Test
    public void getPiece() throws Exception {
        IntBoard chessboard = new IntBoard();

        int expected = -6;
        int actual = chessboard.getPiece(Position.get(4,0));
        assertEquals(expected, actual);
    }

    @Test
    public void toggleOnMove() throws Exception {
        IntBoard chessboard = new IntBoard();

        chessboard.makeMove(new Move(1, core_v2.Utils.Position.get(1,6), core_v2.Utils.Position.get(1,5)));

        PieceColor expected = PieceColor.BLACK;
        PieceColor actual = chessboard.getOnMove();

        assertEquals(expected,actual);


    }

}