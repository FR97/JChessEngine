package core.Moves;

import core.Chessboard;
import core.Utils.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Filip on 12/2/2017.
 */
public class MoveGeneratorTest {
    @Test
    public void getAllPossibleMoves() throws Exception {
    }

    @Test
    public void getAllPossibleMoves1() throws Exception {
    }

    @Test
    public void getPossibleMovesForPieceAt() throws Exception {
        Chessboard cb = new Chessboard();
        cb.printChessboard();

        List<Move> moveList = new ArrayList<>();
        for(int i = 0; i < 500000; i++){
            moveList= MoveGenerator.getPossibleMovesForPieceAt(Position.get(7,7), cb);
        } // 220 MS AVG
        System.out.println(moveList);
    }

}