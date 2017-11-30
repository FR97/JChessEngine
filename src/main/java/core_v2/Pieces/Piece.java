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
public class Piece implements Serializable{

    public final byte position;
    public final PieceColor color;
    public final PieceType type;
    protected List<Move> possibleMoves;

    public Piece(final byte position, final PieceType type, final PieceColor color) {
        this.position = position;
        this.color = color;
        this.type = type;
        this.possibleMoves = new ArrayList<>();
    }

    public Piece(Position position, final PieceType type, final PieceColor color) {
        this.position = position.to1D();
        this.color = color;
        this.type = type;
        this.possibleMoves = new ArrayList<>();
    }

    public Piece(final PieceType type, final PieceColor color){
        this.position = -1;
        this.type =type;
        this.color = color;
    }

    public Piece withPosition(Position position){
        return new Piece(position, this.type, this.color);
    }

    public Piece withPosition(byte position){
        return new Piece(position, this.type, this.color);
    }

    public static Piece getPieceByIdAndPosition(byte position, int id){
        switch (id){
            case 1:
                return new Piece(position, PieceType.PAWN, PieceColor.WHITE);
            case 2:
                return new Piece(position, PieceType.ROOK, PieceColor.WHITE);
            case 3:
                return new Piece(position, PieceType.KNIGHT, PieceColor.WHITE);
            case 4:
                return new Piece(position, PieceType.BISHOP, PieceColor.WHITE);
            case 5:
                return new Piece(position, PieceType.QUEEN, PieceColor.WHITE);
            case 6:
                return new Piece(position, PieceType.KING, PieceColor.WHITE);
            case -1:
                return new Piece(position, PieceType.PAWN, PieceColor.BLACK);
            case -2:
                return new Piece(position, PieceType.ROOK, PieceColor.BLACK);
            case -3:
                return new Piece(position, PieceType.KNIGHT, PieceColor.BLACK);
            case -4:
                return new Piece(position, PieceType.BISHOP, PieceColor.BLACK);
            case -5:
                return new Piece(position, PieceType.QUEEN, PieceColor.BLACK);
            case -6:
                return new Piece(position, PieceType.KING, PieceColor.BLACK);

            default:
                return null;

        }
    }


    @Override
    public String toString() {
        return color == PieceColor.WHITE ? type.toString().toUpperCase() : type.toString().toLowerCase();
    }

    public String forArray(){
        return color.toString().substring(0,1) + type.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (position != piece.position) return false;
        if (color != piece.color) return false;
        return type == piece.type;
    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + color.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
