package core_v2.Players;

import core_v2.Chessboards.Chessboard;
import core_v2.Moves.Move;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Player {

    public final PieceColor color;
    private List<Piece> pieces;

    public Player(PieceColor color){
        this.color = color;
        pieces = new ArrayList<>();
    }

    public void setPieces(final Chessboard chessboard){
        this.pieces = chessboard.getPiecesForColor(this.color);
    }

    public List<Move> getAllMovesForPlayer(){
        List<Move> moves = new ArrayList<>();
        for (Piece p: pieces) {
            moves.addAll(p.getPossibleMoves());
        }
        return moves;
    }

    public List<Piece> getPieces(){
        return Collections.unmodifiableList(pieces);
    }

}
