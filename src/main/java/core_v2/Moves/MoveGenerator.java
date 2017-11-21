package core_v2.Moves;

import core_v2.Chessboards.Chessboard;
import core_v2.Game;
import core_v2.Pieces.*;
import core_v2.Utils.Position;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Filip on 11/19/2017.
 */
public class MoveGenerator {



    public MoveGenerator(){

    }

    public static List<Move> getPossibleMovesForOffsets(Piece movingPiece, Chessboard chessboard, Position[] offsets){
        List<Move> moves = new ArrayList<>();

        for (Position offset : offsets) {
            Position toPosition = Position.withOffsets(movingPiece.position, offset.X, offset.Y);
            if(toPosition!= null){
                Piece eatenPiece = chessboard.getPiece(toPosition);
                if(eatenPiece == null)
                    moves.add(new Move(movingPiece,toPosition ));
                else if(movingPiece.color != eatenPiece.color)
                    moves.add(new AttackMove(movingPiece, eatenPiece));
            }
        }
        return moves;
    }

    public static List<Move> getPossibleMovesInDirection(Piece movingPiece, Chessboard chessboard, int xDirection, int yDirection){

        List<Move> moves = new ArrayList<>();
        int x = xDirection, y = yDirection;

        int i = 1;

        while (i < 8){
            Position positionTo = Position.withOffsets(movingPiece.position, x *i, y*i);
            Piece eatenPiece = chessboard.getPiece(positionTo);
            if( positionTo  != null){
                if(eatenPiece == null){
                    moves.add(new Move(movingPiece, positionTo));

                }else if( movingPiece.color != eatenPiece.color){
                    moves.add(new AttackMove(movingPiece, eatenPiece));
                    break;
                }
                else{
                    break;
                }
            }else{
                break;
            }
            i++;
        }

        return moves;
    }

    public static List<Move> getPossibleMovesForPawn(Pawn pawn, Chessboard chessboard){
        List<Move> moves = new ArrayList<>();
        int yOffset = -pawn.color.value();

        Position possibleTo= Position.get(pawn.position.X, pawn.position.Y+yOffset);
        // Da li moze napred
        if(possibleTo != null && chessboard.getPiece(possibleTo) == null){
            moves.add(new Move(pawn, possibleTo));

            // da li moze dupli skok
            if(pawn.position.Y == 6 && yOffset<0 || pawn.position.Y == 1 && yOffset>0){
                possibleTo = Position.get(pawn.position.X, pawn.position.Y+yOffset*2);
                if(possibleTo != null && chessboard.getPiece(possibleTo) == null)
                    moves.add(new Move(pawn, possibleTo, MoveType.PAWN_DOUBLE_JUMP));
            }
        }

        // Da li moze dijagonalno u levo
        possibleTo = Position.get(pawn.position.X-1,pawn.position.Y+yOffset);
        if(possibleTo  != null){
            Piece pieceToEat =  chessboard.getPiece(possibleTo );
            if(pieceToEat != null && pawn.color != pieceToEat.color ){
                moves.add(new AttackMove(pawn, pieceToEat));
            }
        }

        // Da li moze dijagonalno u desno
        possibleTo = Position.get(pawn.position.X+1,pawn.position.Y+yOffset);
        if(possibleTo  != null){
            Piece pieceToEat =  chessboard.getPiece(possibleTo);
            if(pieceToEat != null && pawn.color != pieceToEat.color ){
                moves.add(new AttackMove(pawn, pieceToEat));
            }
        }

        // Da li moze enpassant
        if(chessboard.isEnpassantPossible()){
            Position pawnToEatPosition = chessboard.getEnpassantPawnPosition();
            if(pawn.position.Y == pawnToEatPosition.Y){
                if(pawn.position.X-1 == pawnToEatPosition.X || pawn.position.X+1 == pawnToEatPosition.X){
                    moves.add(new EnpassantMove(pawn, (Pawn)chessboard.getPiece(pawnToEatPosition)));
                }
            }
        }

        return moves;
    }

    public static List<Move> getPossibleCastlings(King king, Chessboard chessboard){
        List<Move> moves = new ArrayList<>();
        if(king.color == PieceColor.WHITE){
            if(chessboard.isWhiteQueenSideCastlingPossible()){
                if(chessboard.getPiece(1,7) == null && chessboard.getPiece(2,7) == null && chessboard.getPiece(3,7) == null ){
                    moves.add(new CastlingMove(king, (Rook)chessboard.getPiece(0,7), Position.get(2,7), Position.get(3,7)));
                }
            }
            if(chessboard.isWhiteKingSideCastlingPossible()){
                if(chessboard.getPiece(5,7) == null && chessboard.getPiece(6,7) == null ){
                    moves.add(new CastlingMove(king, (Rook)chessboard.getPiece(7,7), Position.get(6,7), Position.get(5,7)));
                }
            }
        }else {

            if(chessboard.isBlackQueenSideCastlingPossible()){
                if(chessboard.getPiece(1,0) == null && chessboard.getPiece(2,0) == null && chessboard.getPiece(3,0) == null ){
                    moves.add(new CastlingMove(king, (Rook)chessboard.getPiece(0,0), Position.get(2,0), Position.get(3,0)));
                }
            }
            if(chessboard.isBlackKingSideCastlingPossible()){
                if(chessboard.getPiece(5,0) == null && chessboard.getPiece(6,0) == null ){
                    moves.add(new CastlingMove(king, (Rook)chessboard.getPiece(7,0), Position.get(6,0), Position.get(5,0)));
                }
            }
        }
        return moves;
    }


}
