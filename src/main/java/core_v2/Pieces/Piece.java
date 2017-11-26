package core_v2.Pieces;

import core_v2.Moves.Move;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Piece implements Serializable{

    public static final Piece WhitePawn = new Piece(PieceType.PAWN, PieceColor.WHITE);
    public static final Piece WhiteRook = new Piece(PieceType.ROOK, PieceColor.WHITE);
    public static final Piece WhiteKnight = new Piece(PieceType.KNIGHT, PieceColor.WHITE);
    public static final Piece WhiteBishop = new Piece(PieceType.BISHOP, PieceColor.WHITE);
    public static final Piece WhiteQueen = new Piece(PieceType.QUEEN, PieceColor.WHITE);
    public static final Piece WhiteKing = new Piece(PieceType.KING, PieceColor.WHITE);

    public static final Piece BlackPawn = new Piece(PieceType.PAWN, PieceColor.BLACK);
    public static final Piece BlackRook =new Piece(PieceType.ROOK, PieceColor.BLACK);
    public static final Piece BlackKnight = new Piece(PieceType.KNIGHT, PieceColor.BLACK);
    public static final Piece BlackBishop = new Piece(PieceType.BISHOP, PieceColor.BLACK);
    public static final Piece BlackQueen = new Piece(PieceType.QUEEN, PieceColor.BLACK);
    public static final Piece BlackKing = new Piece(PieceType.KING, PieceColor.BLACK);

    public final PieceColor color;
    public final PieceType type;

    private Piece(final PieceType type, final PieceColor color) {
        this.color = color;
        this.type = type;
    }

    public static Piece getPieceById(int id) {

        if(id > 0){
            switch (id){
                case 1:
                    return WhitePawn;
                case 2:
                    return WhiteRook;
                case 3:
                    return WhiteKnight;
                case 4:
                    return WhiteBishop;
                case 5:
                    return WhiteQueen;
                case 6:
                    return WhiteKing;
            }
        }
        switch (id){
            case -1:
                return BlackPawn;
            case -2:
                return BlackRook;
            case -3:
                return BlackKnight;
            case -4:
                return BlackBishop;
            case -5:
                return BlackQueen;
            case -6:
                return BlackKing;
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
