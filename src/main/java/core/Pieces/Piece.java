package core.Pieces;

/**
 * Created by Filip on 11/18/2017.
 */
public final class Piece {
    public final PieceColor COLOR;
    public final PieceType TYPE;


    private Piece(PieceType type,PieceColor color) {

        this.COLOR = color;
        this.TYPE = type;

    }

    public static final Piece WhitePawn = new Piece(PieceType.PAWN, PieceColor.WHITE);
    public static final Piece WhiteRook= new Piece(PieceType.ROOK, PieceColor.WHITE);
    public static final Piece WhiteKnight = new Piece(PieceType.KNIGHT, PieceColor.WHITE);
    public static final Piece WhiteBishop = new Piece(PieceType.BISHOP, PieceColor.WHITE);
    public static final Piece WhiteQueen = new Piece(PieceType.QUEEN, PieceColor.WHITE);
    public static final Piece WhiteKing = new Piece(PieceType.KING, PieceColor.WHITE);

    public static final Piece BlackPawn = new Piece(PieceType.PAWN, PieceColor.BLACK);
    public static final Piece BlackRook = new Piece(PieceType.ROOK, PieceColor.BLACK);
    public static final Piece BlackKnight = new Piece(PieceType.KNIGHT, PieceColor.BLACK);
    public static final Piece BlackBishop = new Piece(PieceType.BISHOP, PieceColor.BLACK);
    public static final Piece BlackQueen = new Piece(PieceType.QUEEN, PieceColor.BLACK);
    public static final Piece BlackKing = new Piece(PieceType.KING, PieceColor.BLACK);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (COLOR != piece.COLOR) return false;
        return TYPE == piece.TYPE;
    }

    @Override
    public int hashCode() {
        int result = COLOR != null ? COLOR.hashCode() : 0;
        result = 31 * result + (TYPE != null ? TYPE.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return COLOR == PieceColor.WHITE ? TYPE.toString().toUpperCase() : TYPE.toString().toLowerCase();
    }
}
