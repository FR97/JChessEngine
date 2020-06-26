package fr97.jchess.core.piece;

/**
 * Created by Filip on 12/2/2017.
 */
public final class FinalPiece {

    public final int position;
    public final boolean moved;
    public final PieceType type;
    public final PieceColor color;

    protected FinalPiece(int position, boolean moved, PieceType type, PieceColor color) {
        this.position = position;
        this.moved = moved;
        this.type = type;
        this.color = color;
    }

    public Piece toRegularPiece(){
        return new Piece(this.position, this.moved, this.type, this.color);
    }

}
