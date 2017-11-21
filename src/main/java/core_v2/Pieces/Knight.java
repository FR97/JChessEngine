package core_v2.Pieces;

import core_v2.Chessboards.Chessboard;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Utils.Position;

import java.util.Collections;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Knight extends Piece{

    public Knight(Position position, PieceColor color){
        super(position, PieceType.KNIGHT, color);

    }

    @Override
    public Piece withPosition(Position position) {
        return new Knight(position, this.color);
    }

    @Override
    public void loadPossibleMoves(Chessboard chessboard){
        possibleMoves.clear();
        possibleMoves.addAll(MoveGenerator.getPossibleMovesForOffsets(this, chessboard, Position.KNIGHT_OFFSETS));
    }

    @Override
    public void updatePossibleMoves(Chessboard chessboard) {

    }

    @Override
    public List<Move> getPossibleMoves() {
        return Collections.unmodifiableList(possibleMoves);
    }


}
