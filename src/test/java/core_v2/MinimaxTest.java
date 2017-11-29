package core_v2;



import core_v2.Moves.Move;
import core_v2.Chessboards.Chessboard;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Pieces.PieceType;
import core_v2.Utils.Position;
import org.junit.Test;

import static core_v2.Chessboards.Chessboard.BoardBuilder;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by Filip on 11/26/2017.
 */
public class MinimaxTest {
    @Test
    public void negamax() throws Exception {

        Instant start = Instant.now();
         BoardBuilder builder = new BoardBuilder();
        Chessboard chessboard = builder.build();
        for (int i = 0; i < 50000000; i++) {
            builder = new BoardBuilder(chessboard);

            builder.setPiece((byte)3,(byte)3);
            chessboard = builder.build();
        }
        Instant end = Instant.now();

        System.out.println("Time was " + Duration.between(start, end).toMillis());


      /*  BoardBuilder builder = new BoardBuilder();
        Chessboard chessboard;
        BoardBuilder builder = new BoardBuilder();
        Instant start = Instant.now();
        chessboard = builder.buildStartingBoard();


        chessboard.printChessboard();

        Move m = new Move(chessboard, chessboard.getPiece(62), Position.get(7,5));
        chessboard = m.make();
        chessboard.printChessboard();

        m = new Move(chessboard, chessboard.getPiece(1), Position.get(0,2));
        chessboard = m.make();
        chessboard.printChessboard();

        m = new Move(chessboard, chessboard.getPiece(61), Position.get(4,4));
        chessboard = m.make();
        chessboard.printChessboard();

        m = new Move(chessboard, chessboard.getPiece(9), Position.get(1,2));
        chessboard = m.make();
        chessboard.printChessboard();

        System.out.println(MoveGenerator.getPossibleCastlings(chessboard.getPiece(60), chessboard));
        MoveGenerator.getPossibleCastlings(chessboard.getPiece(60), chessboard).get(0).make().printChessboard();
        Instant end = Instant.now();
        System.out.println("Time was " + Duration.between(start, end).toMillis());*/
    }

    @Test
    public void getCounter() throws Exception {
    }

    @Test
    public void min() throws Exception {
    }

    @Test
    public void max() throws Exception {
    }


}