package core.Pieces;


import java.io.Serializable;


/**
 * Created by Filip on 11/21/2017.
 */
public class Piece implements Serializable {

    public final int position;
    public final boolean moved;
    public final PieceType type;
    public final PieceColor color;


    public Piece(int position, PieceType type, PieceColor color) {
        this(position, false, type, color);
    }

    public Piece(int position, boolean moved, PieceType type, PieceColor color) {
        this.position = position;
        this.moved = moved;
        this.type = type;
        this.color = color;
    }

    public Piece withPosition(int position) {
        return new Piece(position, true, this.type, this.color);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (position != piece.position) return false;
        if (moved != piece.moved) return false;
        if (type != piece.type) return false;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + (moved ? 1 : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    public String forPrint() {
        return color.isWhite() ? type.toString().toUpperCase() : type.toString().toLowerCase();
    }

    public String forArray() {
        return color.toString().substring(0, 1) + type.toString();
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", moved=" + moved +
                ", type=" + type +
                ", color=" + color +
                '}';
    }
}
