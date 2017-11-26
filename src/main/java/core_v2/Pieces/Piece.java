package core_v2.Pieces;

import core_v2.Moves.Move;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Piece implements Serializable{

    private static Piece[] pieces=
            {   new Piece(PieceType.PAWN, PieceColor.WHITE), new Piece(PieceType.ROOK, PieceColor.WHITE),
                    new Piece(PieceType.KNIGHT, PieceColor.WHITE), new Piece(PieceType.BISHOP, PieceColor.WHITE),
                    new Piece(PieceType.QUEEN, PieceColor.WHITE), new Piece(PieceType.KING, PieceColor.WHITE),
                    new Piece(PieceType.PAWN, PieceColor.BLACK), new Piece(PieceType.ROOK, PieceColor.BLACK),
                    new Piece(PieceType.KNIGHT, PieceColor.BLACK), new Piece(PieceType.BISHOP, PieceColor.BLACK),
                    new Piece(PieceType.QUEEN, PieceColor.BLACK), new Piece(PieceType.KING, PieceColor.BLACK),
            };

    public final PieceColor color;
    public final PieceType type;

    private Piece(final PieceType type, final PieceColor color) {
        this.color = color;
        this.type = type;
    }

    public static Piece getPieceById(int id) {

        if(id > 0 && id < 7)
            return pieces[id-1];
        else if(id < 0 && id > -7){
            return pieces[-id +5];
        }


        return null;
        //throw new  IllegalArgumentException("No piece with such id");
    }

    @Override
    public String toString() {
        return color == PieceColor.WHITE ? type.toString().toUpperCase() : type.toString().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (color != piece.color) return false;
        return type == piece.type;
    }

    @Override
    public int hashCode() {
        int result =  color.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }


}
