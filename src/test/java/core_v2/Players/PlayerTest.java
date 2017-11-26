package core_v2.Players;

import core.Chessboard;
import core_v2.Chessboards.IntBoard;
import core_v2.Game;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.PieceColor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Filip on 11/24/2017.
 */
public class PlayerTest {
    @Test
    public void getPiecePositions() throws Exception {
    }

    @Test
    public void getPieces() throws Exception {
    }

    @Test
    public void removePiece() throws Exception {
    }

    @Test
    public void getPossibleMoves() throws Exception {

    }

    @Test
    public void setPossibleMoves() throws Exception {
       IntBoard chessboard = new IntBoard();
       Player player = new Player(PieceColor.WHITE);
       AiPlayer player1 = new AiPlayer(PieceColor.BLACK);

       chessboard.printChessboard(false);

        for(int i = 0; i < 25; i++){
            Move bestMove = player1.getBestMove(chessboard,3);
            chessboard.makeMove(bestMove);
            chessboard.printChessboard(false);
        }



       //System.out.println(player.getPossibleMoves());
       // System.out.println(player1.getBestMove(chessboard,3));
    }

}