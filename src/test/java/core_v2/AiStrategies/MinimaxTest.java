package core_v2.AiStrategies;

import core_v2.Chessboards.Chessboard;
import core_v2.Evaluators.BasicEvaluator;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Filip on 12/3/2017.
 */
public class MinimaxTest {
    @Test
    public void getBestMove() throws Exception {

       /* Instant start = Instant.now();
        int sum = 0;
        for (int i = 0; i < 5000000; i++){

            Piece[] pieces = new Piece[32];
            for(int j = 0; j < 32; j++){
                pieces[j*2] = new Piece(j*2, PieceType.KING, PieceColor.WHITE);
            }
            for(int j = 0; j < 32; j++){
                if(pieces[j] != null)
                    sum+=pieces[j].position;
            }


        }

        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
        System.out.println(sum);*/
     /*   PieceList whitePieces;
        PieceList.PieceListBuilder builder = new PieceList.PieceListBuilder(16);
        builder.put(new Piece(63, PieceType.ROOK, PieceColor.WHITE));
        builder.put( new Piece(62, PieceType.KNIGHT, PieceColor.WHITE));
        builder.put(new Piece(61, PieceType.BISHOP, PieceColor.WHITE));
        builder.put(new Piece(60, PieceType.KING, PieceColor.WHITE));
        builder.put(new Piece(59, PieceType.QUEEN, PieceColor.WHITE));
        builder.put(new Piece(58, PieceType.BISHOP, PieceColor.WHITE));
        builder.put(new Piece(57, PieceType.KNIGHT, PieceColor.WHITE));
        builder.put(new Piece(56, PieceType.ROOK, PieceColor.WHITE));
        for (int i = 55; i > 47; i--)
            builder.put(new Piece(i, PieceType.PAWN, PieceColor.WHITE));
        whitePieces = builder.build();


       IntHashMap blackPieces = new IntHashMap(16, 1.0f);
        blackPieces.put(0, new Piece(0, PieceType.ROOK, PieceColor.BLACK));
        blackPieces.put(1, new Piece(1, PieceType.KNIGHT, PieceColor.BLACK));
        blackPieces.put(2, new Piece(2, PieceType.BISHOP, PieceColor.BLACK));
        blackPieces.put(3, new Piece(3, PieceType.QUEEN, PieceColor.BLACK));
        blackPieces.put(4, new Piece(4, PieceType.KING, PieceColor.BLACK));
        blackPieces.put(5, new Piece(5, PieceType.BISHOP, PieceColor.BLACK));
        blackPieces.put(6, new Piece(6, PieceType.KNIGHT, PieceColor.BLACK));
        blackPieces.put(7, new Piece(7, PieceType.ROOK, PieceColor.BLACK));
        for (int i = 8; i < 16; i++)
            blackPieces.put(i, new Piece(i, PieceType.PAWN, PieceColor.BLACK));
        int sum = 0;
        for(int j = 0; j < 5000000; j++){


           // sum+=blackPieces.get(5).position + blackPieces.get(14).position;
            /*for(Piece p : blackPieces.values())
                sum+=  p.position-200000000;

           for(Piece p : whitePieces)
               sum+=  p.position-200000000;
        }
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println(Duration.between(start,end).toMillis());

*/

        Chessboard chessboard = new Chessboard();

        Minimax minimax = new Minimax(new BasicEvaluator());

        chessboard =  chessboard.getCurrent().getLegalMoves().get(0).execute();
        chessboard =  chessboard.getCurrent().getLegalMoves().get(0).execute();
        chessboard =  chessboard.getCurrent().getLegalMoves().get(0).execute();

        chessboard.print();

        System.out.println(minimax.getBestMove(chessboard,4));


    }

}