package core.Moves;

import core.Chessboard;
import core.Game;
import core.Pieces.Piece;
import core.Pieces.PieceColor;
import core.Pieces.PieceType;
import core.Utils.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Filip on 11/19/2017.
 */
public class MoveGenerator {



    public MoveGenerator(){

    }

    public static List<Move> getAllPossibleMoves(Chessboard chessboard){
        if(Game.DEBUG_MODE)
            System.out.println("GENERATING ALL POSSIBLE MOVES FOR " + chessboard.getOnMove() +" ...");
        List<Move> moves = new ArrayList<>();
        TreeSet<Position> positions = chessboard.getAllPositionsForColor(chessboard.getOnMove());
        positions.forEach(position ->{
            moves.addAll(getPossibleMovesForPieceAt(position, chessboard));
        });
        if(Game.DEBUG_MODE)
            System.out.println("FINISHED GENERATING");
        return moves;
    }

    public static List<Move> getPossibleMovesForPieceAt(Position position, Chessboard chessboard){

        List<Move> moves = new ArrayList<>();
        PieceType type = chessboard.getPiece(position).TYPE;
        if(Game.DEBUG_MODE)
            System.out.println("Finding possible moves for " +chessboard.getPiece(position) + " at " + position);
        switch (type){
            case PAWN: moves.addAll(possibleMovesForPawn(position, chessboard));
                break;
            case ROOK: moves.addAll(possibleMovesForDirections(position, chessboard,
                    Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST));
                break;
            case KNIGHT: moves.addAll(possibleMovesBasedOnOffset(position, chessboard, Position.KNIGHT_OFFSETS));
                break;
            case BISHOP: moves.addAll(possibleMovesForDirections(position, chessboard,
                    Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.NORTH_WEST));
                break;
            case QUEEN: moves.addAll(possibleMovesForDirections(position, chessboard,
                    Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST,
                    Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.NORTH_WEST));
                break;
            case KING: moves.addAll(possibleMovesBasedOnOffset(position, chessboard, Position.KING_OFFSETS));
                        moves.addAll(getPossibleCastlings(position,chessboard));
                break;
        }

        return moves;

    }

    private static List<Move> possibleMovesBasedOnOffset(Position piecePosition, Chessboard chessboard, Position[] offsets){
        List<Move> moves = new ArrayList<>();
        for (Position offset: offsets) {
            Position possiblePosition = Position.withOffsets(piecePosition, offset.X, offset.Y);
            if(possiblePosition != null){
                if( chessboard.getPiece(possiblePosition) == null || chessboard.getPiece(possiblePosition).COLOR != chessboard.getPiece(piecePosition).COLOR){
                    Move possibleMove = new Move(piecePosition, possiblePosition,chessboard.getPiece(piecePosition), chessboard.getPiece(possiblePosition));
                    moves.add(possibleMove);
                }
            }
        }

        return moves;
    }


    private static List<Move> possibleMovesForDirections(Position piecePosition, Chessboard chessboard, Direction... directions){

        List<Move> moves = new ArrayList<>();
        for (Direction direction: directions) {
            moves.addAll(possibleMovesInDirection(piecePosition, chessboard, direction));
        }


        return moves;
    }

    private static List<Move> possibleMovesInDirection(Position piecePosition, Chessboard chessboard, Direction direction){

        List<Move> moves = new ArrayList<>();
        int x = 0,y = 0;
        switch (direction){

            case SOUTH: x = 0; y = 1;
                break;
            case NORTH: x = 0; y = -1;
                break;
            case WEST: x = -1; y = 0;
                break;
            case EAST: x = 1; y = 0;
                break;
            case SOUTH_EAST: x = 1; y = 1;
                break;
            case SOUTH_WEST: x = -1; y = 1;
                break;
            case NORTH_EAST: x = 1; y = -1;
                break;
            case NORTH_WEST: x = -1; y = -1;
                break;
        }
        int i = 1;

        while (i < 8){
            Position possiblePosition = Position.withOffsets(piecePosition, x *i, y*i);
            if(possiblePosition != null){
                if(chessboard.getPiece(possiblePosition) == null || chessboard.getPiece(possiblePosition).COLOR != chessboard.getPiece(piecePosition).COLOR ){
                    Move possibleMove = new Move(piecePosition, possiblePosition,chessboard.getPiece(piecePosition), chessboard.getPiece(possiblePosition));
                    moves.add(possibleMove);
                    if(chessboard.getPiece(possiblePosition) != null)
                        break;
                }else {
                    break;
                }


            }

            i++;
        }


        return moves;
    }

    private static List<Move> possibleMovesForPawn(Position piecePosition,Chessboard chessboard){
        List<Move> moves = new ArrayList<>();
        int yOffset = 1;
        Piece pawn = chessboard.getPiece(piecePosition);
        if(pawn.COLOR == PieceColor.WHITE){
            yOffset = -1;
        }
        Position possiblePosition = Position.get(piecePosition.X, piecePosition.Y+yOffset);
        // Da li moze napred
        if(possiblePosition != null && chessboard.getPiece(possiblePosition) == null){
            moves.add(new Move(piecePosition, possiblePosition,pawn, chessboard.getPiece(possiblePosition)));
            possiblePosition = Position.get(piecePosition.X, piecePosition.Y+yOffset*2);
            if((piecePosition.Y == 6 && yOffset<0 || piecePosition.Y == 1 && yOffset>0)
                    && possiblePosition != null && chessboard.getPiece(possiblePosition) == null){
                moves.add(new Move(piecePosition, possiblePosition,pawn, chessboard.getPiece(possiblePosition)));
            }
        }

        // Da li moze dijagonalno u levo
        possiblePosition = Position.get(piecePosition.X-1, piecePosition.Y+yOffset);
        if(possiblePosition != null){
            Piece pieceToEat =  chessboard.getPiece(possiblePosition);
            if(pieceToEat != null && pieceToEat.COLOR != chessboard.getPiece(piecePosition).COLOR){
                moves.add(new Move(piecePosition, possiblePosition,pawn,pieceToEat));
            }
        }

        // Da li moze dijagonalno u desno
        possiblePosition = Position.get(piecePosition.X+1, piecePosition.Y+yOffset);
        if(possiblePosition != null){
            Piece pieceToEat =  chessboard.getPiece(possiblePosition);
            if(pieceToEat != null && pieceToEat.COLOR != chessboard.getPiece(piecePosition).COLOR){
                moves.add(new Move(piecePosition, possiblePosition,pawn,pieceToEat));
            }
        }

        // Da li je moguc enpassant potez
        if(chessboard.isEnpassantPossible()){
            Position pawnToEatPosition = chessboard.getEnpassantPawnPosition();
            if(piecePosition.Y == pawnToEatPosition.Y){
                if(piecePosition.X-1 == pawnToEatPosition.X || piecePosition.X+1 == pawnToEatPosition.X){
                    moves.add(new Move(piecePosition, Position.get(pawnToEatPosition.X, pawnToEatPosition.Y + yOffset),
                            pawn, chessboard.getPiece(pawnToEatPosition), MoveType.ENPASSANT));
                }
            }
        }

        return moves;
    }

    private static List<Move> getPossibleCastlings(Position piecePosition, Chessboard chessboard){
        List<Move> moves = new ArrayList<>();
        Piece king = chessboard.getPiece(piecePosition);
        if(king.TYPE == PieceType.KING){
            if(king.COLOR == PieceColor.WHITE){
                if(chessboard.isWhiteKingsideCastling()){
                    if(chessboard.getPiece(Position.get(5,7))==null && chessboard.getPiece(Position.get(6,7))==null)
                        moves.add(new Move(piecePosition, Position.get(6,7), king, MoveType.CASTLING));
                }
                if(chessboard.isWhiteQueensideCastling()){
                    if(chessboard.getPiece(Position.get(2,7))==null && chessboard.getPiece(Position.get(1,7))==null)
                        moves.add(new Move(piecePosition, Position.get(2,7), king, MoveType.CASTLING));
                }
            }else{
                if(chessboard.isBlackKingsideCastling()){
                    if(chessboard.getPiece(Position.get(2,0))==null && chessboard.getPiece(Position.get(1,0))==null)
                        moves.add(new Move(piecePosition, Position.get(2,7), king, MoveType.CASTLING));
                }
                if(chessboard.isBlackQueensideCastling()){
                    if(chessboard.getPiece(Position.get(2,0))==null && chessboard.getPiece(Position.get(1,0))==null)
                        moves.add(new Move(piecePosition, Position.get(2,0), king, MoveType.CASTLING));
                }
            }
        }
        return moves;
    }



    private enum Direction{
        SOUTH, NORTH, WEST, EAST, SOUTH_EAST, SOUTH_WEST, NORTH_EAST, NORTH_WEST
    }

}
