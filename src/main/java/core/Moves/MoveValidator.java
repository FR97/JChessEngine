package core.Moves;

import core.Chessboard;
import core.Game;
import core.Pieces.PieceColor;
import core.Pieces.PieceType;
import core.Utils.Position;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Filip on 11/19/2017.
 */
public class MoveValidator {

    public static boolean isKingInDanger(Chessboard chessboard){
        List<Move> moves = MoveGenerator.getAllPossibleMoves(chessboard);
        for (Move m: moves) {
            if(m.EATEN_PIECE != null && m.EATEN_PIECE.TYPE == PieceType.KING) {
                if(Game.DEBUG_MODE)
                    System.err.println("KING IN DANGER  -  "+ m);
                return true;
            }
        }
        return false;
    }

    public static List<Move> removeDangerousMoves(List<Move> moves,final Chessboard currentChessboard){
        for (Iterator<Move> i = moves.iterator(); i.hasNext(); ) {
            Move m = i.next();
            Chessboard futureChessboard = new Chessboard(currentChessboard);
            futureChessboard.move(m);
            if (MoveValidator.isKingInDanger(futureChessboard)) {
                if(Game.DEBUG_MODE)
                    System.err.println("Removed dangerous move  -- "+ m);
                i.remove();
            }
        }
        return moves;
    }
}
