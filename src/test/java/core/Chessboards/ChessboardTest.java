package core.Chessboards;


import org.junit.Test;

import java.time.Instant;

/**
 * Created by Filip on 11/28/2017.
 */
public class ChessboardTest {
    @Test
    public void getActivePieces() throws Exception {
        int sum = 0;
        Instant start = Instant.now();
        Chessboard cb = new Chessboard();
/*
        cb.print();
        System.out.println(cb.getPiece(6).toString());
        Move move = new CaptureMove(cb.getActivePieces().get(0), cb.getPiece(6));
        move.execute(cb);
        cb.print();
        move.undo(cb);
        System.out.println(cb.getPiece(6).toString());
        cb.print();
        Instant end = Instant.now();

        System.out.println(Duration.between(start,end).toMillis());
*/

    }

    @Test
    public void getTargetPieces() throws Exception {
    }

    @Test
    public void isEnpassantPossible() throws Exception {
    }

    @Test
    public void isWhiteQueensideCastlingPossible() throws Exception {
    }

    @Test
    public void isWhiteKingsideCastlingPossible() throws Exception {
    }

    @Test
    public void isBlackQueensideCastlingPossible() throws Exception {
    }

    @Test
    public void isBlackKingsideCastlingPossible() throws Exception {
    }

    @Test
    public void getOnMove() throws Exception {
    }

    @Test
    public void getPieceAsByte() throws Exception {
    }

    @Test
    public void getPiece() throws Exception {
    }

    @Test
    public void getEnpassantPawnPosition() throws Exception {
    }

}