package core_v2.Moves;

import core_v2.Chessboards.IntBoard;
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

    public static List<Move> getPossibleMovesForPieceAt(Position position, IntBoard chessboard){

        List<Move> moves = new ArrayList<>();
        PieceType type = Piece.getPieceById(chessboard.getPiece(position)).type;
        if(Game.DEBUG_MODE)
            System.out.println("Finding possible moves for " +chessboard.getPiece(position) + " at " + position);
        switch (type){
            case PAWN: moves.addAll(possibleMovesForPawn(position, chessboard));
                break;
            case ROOK:
                moves.addAll(possibleMovesInDirection(position, chessboard, 1,0));
                moves.addAll(possibleMovesInDirection(position, chessboard, -1,0));
                moves.addAll(possibleMovesInDirection(position, chessboard, 0,1));
                moves.addAll(possibleMovesInDirection(position, chessboard, 0,-1));
                break;
            case KNIGHT: moves.addAll(possibleMovesBasedOnOffset(position, chessboard, Position.KNIGHT_OFFSETS));
                break;
            case BISHOP:
                moves.addAll(possibleMovesInDirection(position, chessboard, 1,1));
                moves.addAll(possibleMovesInDirection(position, chessboard, 1,-1));
                moves.addAll(possibleMovesInDirection(position, chessboard, -1,1));
                moves.addAll(possibleMovesInDirection(position, chessboard, -1,-1));
                break;
            case QUEEN:
                moves.addAll(possibleMovesInDirection(position, chessboard, 1,0));
                moves.addAll(possibleMovesInDirection(position, chessboard, -1,0));
                moves.addAll(possibleMovesInDirection(position, chessboard, 0,1));
                moves.addAll(possibleMovesInDirection(position, chessboard, 0,-1));
                moves.addAll(possibleMovesInDirection(position, chessboard, 1,1));
                moves.addAll(possibleMovesInDirection(position, chessboard, 1,-1));
                moves.addAll(possibleMovesInDirection(position, chessboard, -1,1));
                moves.addAll(possibleMovesInDirection(position, chessboard, -1,-1));
                break;
            case KING:
                moves.addAll(possibleMovesBasedOnOffset(position, chessboard, Position.KING_OFFSETS));
                moves.addAll(getPossibleCastlings(position,chessboard));
                break;
        }

        return moves;

    }

    private static List<Move> possibleMovesBasedOnOffset(Position piecePosition, IntBoard chessboard, Position[] offsets){
        List<Move> moves = new ArrayList<>();
        int piece = chessboard.getPiece(piecePosition);
        for (Position offset: offsets) {
            Position possiblePosition = Position.withOffsets(piecePosition, offset.X, offset.Y);

            if(possiblePosition != null){
                int pieceToEat = chessboard.getPiece(possiblePosition);
                if( pieceToEat == 0){
                    Move possibleMove = new Move(piece, piecePosition, possiblePosition);
                    moves.add(possibleMove);
                }else if((piece < 0 && pieceToEat > 0) || (piece > 0 && pieceToEat < 0)){
                    Move possibleMove = new Move(piece, piecePosition, possiblePosition, pieceToEat);
                    moves.add(possibleMove);
                }
            }
        }

        return moves;
    }


    private static List<Move> possibleMovesInDirection(Position piecePosition, IntBoard chessboard,final int xDirection,final int yDirection){

        List<Move> moves = new ArrayList<>();

        int piece = chessboard.getPiece(piecePosition);

        int i = 1;

        while (i < 8){
            Position possiblePosition = Position.withOffsets(piecePosition, xDirection *i, yDirection*i);
            int pieceToEat = chessboard.getPiece(possiblePosition);
            if(possiblePosition != null){
                if(pieceToEat == 0){
                    Move possibleMove = new Move(piece, piecePosition, possiblePosition);
                    moves.add(possibleMove);
                }else if((piece < 0 && pieceToEat > 0) || (piece > 0 && pieceToEat < 0)){
                    Move possibleMove = new Move(piece, piecePosition, possiblePosition, pieceToEat);
                    moves.add(possibleMove);
                    break;
                }
                break;
            }

            i++;
        }


        return moves;
    }

    private static List<Move> possibleMovesForPawn(Position piecePosition,IntBoard chessboard){
        List<Move> moves = new ArrayList<>();
        int yOffset = 1;
        int pawn = chessboard.getPiece(piecePosition);
        if(pawn > 0){
            yOffset = -1;
        }
        Position possiblePosition = Position.get(piecePosition.X, piecePosition.Y+yOffset);
        // Da li moze napred
        if(possiblePosition != null && chessboard.getPiece(possiblePosition) == 0){
            moves.add(new Move(pawn, piecePosition, possiblePosition, MoveType.NORMAL));
            possiblePosition = Position.get(piecePosition.X, piecePosition.Y+yOffset*2);
            if((piecePosition.Y == 6 && yOffset<0 || piecePosition.Y == 1 && yOffset>0)
                    && possiblePosition != null && chessboard.getPiece(possiblePosition) == 0){
                moves.add(new Move(pawn, piecePosition, possiblePosition,MoveType.PAWN_DOUBLE_JUMP));
            }
        }

        // Da li moze dijagonalno u levo
        possiblePosition = Position.get(piecePosition.X-1, piecePosition.Y+yOffset);
        if(possiblePosition != null){
            int pieceToEat =  chessboard.getPiece(possiblePosition);
            if((pawn < 0 && pieceToEat > 0) || (pawn > 0 && pieceToEat < 0)){
                moves.add(new Move(pawn, piecePosition, possiblePosition,pieceToEat));
            }
        }

        // Da li moze dijagonalno u desno
        possiblePosition = Position.get(piecePosition.X+1, piecePosition.Y+yOffset);
        if(possiblePosition != null){
            int pieceToEat =  chessboard.getPiece(possiblePosition);
            if((pawn < 0 && pieceToEat > 0) || (pawn > 0 && pieceToEat < 0)){
                moves.add(new Move(pawn, piecePosition, possiblePosition,pieceToEat));
            }
        }

        // Da li je moguc enpassant potez
        if(chessboard.isEnpassantPossible()){
            Position pawnToEatPosition = chessboard.getEnpassantPosition();
            if(piecePosition.Y == pawnToEatPosition.Y){
                if(piecePosition.X-1 == pawnToEatPosition.X || piecePosition.X+1 == pawnToEatPosition.X){
                    moves.add(new Move(pawn, piecePosition, Position.get(pawnToEatPosition.X, pawnToEatPosition.Y + yOffset),
                            chessboard.getPiece(pawnToEatPosition), MoveType.ENPASSANT));
                }
            }
        }

        return moves;
    }

    private static List<Move> getPossibleCastlings(Position piecePosition, IntBoard chessboard){
        List<Move> moves = new ArrayList<>();
        int king = chessboard.getPiece(piecePosition);
        if(king == 6){
            if(king > 0){
                if(chessboard.isWhiteKingsideCastlingPossible()){
                    if(chessboard.getPiece(Position.get(5,7))==0 && chessboard.getPiece(Position.get(6,7))==0)
                        moves.add(new Move(king, piecePosition, Position.get(6,7), MoveType.CASTLING));
                }
                if(chessboard.isWhiteQueensideCastlingPossible()){
                    if(chessboard.getPiece(Position.get(2,7))==0 && chessboard.getPiece(Position.get(1,7))==0)
                        moves.add(new Move(king, piecePosition, Position.get(2,7), MoveType.CASTLING));
                }
            }else{
                if(chessboard.isBlackKingsideCastlingPossible()){
                    if(chessboard.getPiece(Position.get(2,0))==0 && chessboard.getPiece(Position.get(1,0))==0)
                        moves.add(new Move(king, piecePosition, Position.get(2,7), MoveType.CASTLING));
                }
                if(chessboard.isBlackQueensideCastlingPossible()){
                    if(chessboard.getPiece(Position.get(2,0))==0 && chessboard.getPiece(Position.get(1,0))==0)
                        moves.add(new Move(king, piecePosition, Position.get(2,0), MoveType.CASTLING));
                }
            }
        }
        return moves;
    }


}
