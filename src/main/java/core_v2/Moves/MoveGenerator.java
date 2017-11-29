package core_v2.Moves;

import core_v2.Chessboards.Chessboard;
import core_v2.Game;
import core_v2.Pieces.*;
import core_v2.Utils.Position;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Filip on 11/19/2017.
 */
public class MoveGenerator {



    public MoveGenerator(){

    }

    public static List<Move> getAllPossibleMoves(Chessboard chessboard, boolean activePieces){
        List<Move> moves = new ArrayList<>();
        if(activePieces){
            for (Piece piece : chessboard.getActivePieces()){
                moves.addAll(getPossibleMovesForPiece(piece,chessboard));
            }
        }else {
            for (Piece piece : chessboard.getTargetPieces()){
                moves.addAll(getPossibleMovesForPiece(piece,chessboard));
            }
        }
        for(Iterator<Move> i = moves.iterator(); i.hasNext();){
            Move m = i.next();
            if(isMoveDangerous(m)){
                i.remove();
            }
        }

        return moves;
    }

    public static boolean isKingInCheck(Chessboard chessboard){
        List<Move> moves = new ArrayList<>();
        for (Piece piece : chessboard.getTargetPieces()){
            for(Move move: getPossibleMovesForPiece(piece,chessboard)){
               if( move.type == MoveType.CHECKMATE){
                   return true;
               }
            }
        }
        return false;
    }

    public static List<Move> getPossibleMovesForPiece(Piece piece, Chessboard chessboard){

        List<Move> moves = new ArrayList<>();


        if(Game.DEBUG_MODE)
            System.out.println("Finding possible moves for " + piece + " at " + piece.position);

        switch (piece.type){
            case PAWN:
                moves.addAll(getPossibleMovesForPawn(piece, chessboard));
                break;
            case ROOK:
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 1, 0));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, -1, 0));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 0, 1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 0, -1));
                break;
            case KNIGHT:
                moves.addAll(getPossibleMovesForOffsets(piece, chessboard, Position.KNIGHT_OFFSETS));
                break;
            case BISHOP:
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 1, 1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 1, -1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, -1, 1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, -1, -1));
                break;
            case QUEEN:
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 1, 0));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, -1, 0));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 0, 1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 0, -1));

                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 1, 1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, 1, -1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, -1, 1));
                moves.addAll(getPossibleMovesInDirection(piece, chessboard, -1, -1));
                break;
            case KING:
                moves.addAll(getPossibleMovesForOffsets(piece, chessboard, Position.KING_OFFSETS));
                moves.addAll(getPossibleCastlings(piece,chessboard));
                break;
        }
        if(Game.DEBUG_MODE)
            System.out.println("Found " + moves.size() + " possible moves");
        return moves;

    }


    public static List<Move> getPossibleMovesForOffsets(Piece movingPiece, Chessboard chessboard, Position[] offsets) {

        List<Move> moves = new ArrayList<>();

        for (Position offset : offsets) {
            Position toPosition = Position.withOffsets(movingPiece.position, offset.X, offset.Y);
            if (toPosition != null) {
                Piece eatenPiece = chessboard.getPiece(toPosition.to1D());
                if (eatenPiece == null)
                    moves.add(new Move(chessboard, movingPiece, toPosition));
                else if (movingPiece.color != eatenPiece.color)
                    moves.add(new AttackMove(chessboard, movingPiece, eatenPiece));
            }
        }


        return moves;
    }

    public static List<Move> getPossibleMovesInDirection(Piece movingPiece, Chessboard chessboard, int xDirection, int yDirection) {

        List<Move> moves = new ArrayList<>();
        int x = xDirection, y = yDirection;

        int i = 1;

        while (i < 8) {
            Position toPosition = Position.withOffsets(movingPiece.position, x * i, y * i);
            if (toPosition != null) {
                Piece eatenPiece = chessboard.getPiece(toPosition.to1D());
                if (eatenPiece == null) {
                    moves.add(new Move(chessboard, movingPiece, toPosition));

                } else if (movingPiece.color != eatenPiece.color) {
                    moves.add(new AttackMove(chessboard, movingPiece, eatenPiece));
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
            i++;
        }

        return moves;
    }

    public static List<Move> getPossibleMovesForPawn(Piece pawn, Chessboard chessboard) {

        List<Move> moves = new ArrayList<>();
        int yOffset = -pawn.color.value();

        Position possibleTo = Position.get(pawn.position.X, pawn.position.Y + yOffset);
        // Da li moze napred
        if (possibleTo != null && chessboard.getPiece(possibleTo.to1D()) == null) {
            moves.add(new Move(chessboard, pawn, possibleTo));

            // da li moze dupli skok
            if (pawn.position.Y == 6 && yOffset < 0 || pawn.position.Y == 1 && yOffset > 0) {
                possibleTo = Position.get(pawn.position.X, pawn.position.Y + yOffset * 2);
                if (possibleTo != null && chessboard.getPiece(possibleTo.to1D()) == null)
                    moves.add(new Move(chessboard, pawn, possibleTo, MoveType.PAWN_DOUBLE_JUMP));
            }
        }

        // Da li moze dijagonalno u levo
        possibleTo = Position.get(pawn.position.X - 1, pawn.position.Y + yOffset);
        if (possibleTo != null) {
            Piece pieceToEat = chessboard.getPiece(possibleTo.to1D());
            if (pieceToEat != null && pawn.color != pieceToEat.color) {
                moves.add(new AttackMove(chessboard, pawn, pieceToEat));
            }
        }

        // Da li moze dijagonalno u desno
        possibleTo = Position.get(pawn.position.X + 1, pawn.position.Y + yOffset);
        if (possibleTo != null) {
            Piece pieceToEat = chessboard.getPiece(possibleTo.to1D());
            if (pieceToEat != null && pawn.color != pieceToEat.color) {
                moves.add(new AttackMove(chessboard, pawn, pieceToEat));
            }
        }

        // Da li moze enpassant
        if (chessboard.isEnpassantPossible()) {
            Position pawnToEatPosition = Position.from1D(chessboard.getEnpassantPawnPosition());
            if (pawn.position.Y == pawnToEatPosition.Y) {
                if (pawn.position.X - 1 == pawnToEatPosition.X || pawn.position.X + 1 == pawnToEatPosition.X) {
                    moves.add(new EnpassantMove(chessboard, pawn, Position.get(pawnToEatPosition.X, pawnToEatPosition.Y+yOffset), chessboard.getPiece(pawnToEatPosition.to1D())));
                }
            }
        }

        return moves;
    }

    public static List<Move> getPossibleCastlings(Piece king, Chessboard chessboard) {

        List<Move> moves = new ArrayList<>();
        if(king.color == PieceColor.WHITE){
            if(chessboard.isWhiteKingsideCastlingPossible() && chessboard.getPieceAsByte(63) == 2){
                if(chessboard.getPieceAsByte(62) == 0 && chessboard.getPieceAsByte(61) == 0)
                    moves.add(new CastlingMove(chessboard, king, Position.get(6,7), chessboard.getPiece(63), Position.get(5,7)));
            }else if(chessboard.isWhiteQueensideCastlingPossible()&& chessboard.getPieceAsByte(56) == 2){
                if(chessboard.getPieceAsByte(57) == 0 && chessboard.getPieceAsByte(58) == 0 && chessboard.getPieceAsByte(59) == 0)
                    moves.add(new CastlingMove(chessboard, king, Position.get(2,7), chessboard.getPiece(56), Position.get(3,7)));
            }
        }else{
            if(chessboard.isBlackKingsideCastlingPossible() && chessboard.getPieceAsByte(7) == -2){
                if(chessboard.getPieceAsByte(6) == 0 && chessboard.getPieceAsByte(5) == 0)
                    moves.add(new CastlingMove(chessboard, king, Position.get(6,0), chessboard.getPiece(7), Position.get(5,0)));
            }else if(chessboard.isBlackQueensideCastlingPossible() && chessboard.getPieceAsByte(0) == -2){
                if(chessboard.getPieceAsByte(1) == 0 && chessboard.getPieceAsByte(2) == 0 && chessboard.getPieceAsByte(3) == 0)
                    moves.add(new CastlingMove(chessboard, king, Position.get(2,0), chessboard.getPiece(0), Position.get(3,0)));
            }
        }

        return moves;
    }

    public static boolean isMoveDangerous(Move move){
        Chessboard chessboard = move.make();
        for (Piece piece : chessboard.getActivePieces()){
           for(Move m: getPossibleMovesForPiece(piece,chessboard)){
               if(m.type == MoveType.CHECKMATE){
                   return true;
               }

           }
        }

        return false;
    }



}
