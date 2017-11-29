package core_v2.Moves;

import core_v2.Chessboards.Chessboard;
import core_v2.Game;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Pieces.PieceType;
import core_v2.Utils.Position;


/**
 * Created by Filip on 11/21/2017.
 */
public class Move {

    public final Chessboard chessboard;
    public final Piece movingPiece;
    public final Position toPosition;
    public final MoveType type;

    public Move(Chessboard chessboard, Piece movingPiece, Position toPosition, MoveType type) {
        this.chessboard = chessboard;
        this.movingPiece = movingPiece;
        this.toPosition = toPosition;
        this.type = type;
    }

    public Move(Chessboard chessboard, Piece movingPiece, Position toPosition){
        this.chessboard = chessboard;
        this.movingPiece = movingPiece;
        this.toPosition = toPosition;
        this.type = MoveType.NORMAL;
    }


    public Chessboard make(){

        Chessboard.BoardBuilder builder = new Chessboard.BoardBuilder();

        for(Piece piece : this.chessboard.getActivePieces()){
            if(!this.movingPiece.equals(piece))
                builder.setPiece(piece);
            else{
                if(Game.DEBUG_MODE)
                    System.out.println("This piece will be moved " + piece + " from " + piece.position + " to " + this.toPosition);
            }

        }

        if(this.type == MoveType.PAWN_DOUBLE_JUMP){
            builder.setEnpassantPosition(this.toPosition.to1D());
        }else if(this.type == MoveType.CASTLING || this.movingPiece.type == PieceType.KING){
            if(chessboard.getOnMove() == PieceColor.WHITE){
                builder.setWhiteKingsideCastling(false);
                builder.setWhiteQueensideCastling(false);
            }else{
                builder.setBlackKingsideCastling(false);
                builder.setBlackQueensideCastling(false);
            }
        }


        for(Piece targetPieces : chessboard.getTargetPieces()){
            builder.setPiece(targetPieces);
        }

        updateCastlings(builder);

        builder.setPiece(this.movingPiece.withPosition(this.toPosition));

        builder.setOnMove(this.chessboard.getOnMove() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE);

        return builder.build();
    }

    protected void updateCastlings(Chessboard.BoardBuilder builder){

        if(chessboard.getOnMove() == PieceColor.WHITE){
            if(movingPiece.position.equals(Position.get(7,7)))
                builder.setWhiteKingsideCastling(false);
            else if(movingPiece.position.equals(Position.get(0,7))){
                builder.setWhiteQueensideCastling(false);
            }
        }else{
            if(movingPiece.position.equals(Position.get(7,0)))
                builder.setBlackKingsideCastling(false);
            else if(movingPiece.position.equals(Position.get(0,0))){
                builder.setBlackQueensideCastling(false);
            }
        }
    }

    public Chessboard undo(){
        return chessboard;
    }

    @Override
    public String toString() {
        return "Move{" +
                "movingPiece=" + movingPiece +
                ", toPosition=" + toPosition +
                ", type=" + type +
                '}';
    }

    @Override
    public int hashCode() {
        int result = movingPiece.hashCode();
        result = 31 * result + toPosition.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
