package core_v2.Pieces;

import core_v2.Chessboards.Chessboard;
import core_v2.Moves.Move;
import core_v2.Utils.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public abstract class Piece implements Serializable{

    public final Position position;
    public final PieceColor color;
    public final PieceType type;
    protected List<Move> possibleMoves;

    public Piece(final Position position, final PieceType type, final PieceColor color) {
        this.position = position;
        this.color = color;
        this.type = type;
        this.possibleMoves = new ArrayList<>();
    }


    //public abstract PieceType type();

    public abstract Piece withPosition(final Position position);

    public abstract void loadPossibleMoves(final Chessboard chessboard);

    public abstract void updatePossibleMoves(final Chessboard chessboard);

    public abstract List<Move> getPossibleMoves();

    @Override
    public String toString() {
        return color == PieceColor.WHITE ? type.toString().toUpperCase() : type.toString().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (!position.equals(piece.position)) return false;
        if (color != piece.color) return false;
        return type == piece.type;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
