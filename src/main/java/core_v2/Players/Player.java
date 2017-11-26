package core_v2.Players;


import core.Chessboard;
import core_v2.Moves.Move;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;

import java.util.*;

/**
 * Created by Filip on 11/21/2017.
 */
public class Player {

    public final PieceColor color;
    private Map<Position,Piece> pieces;
    private List<Move> possibleMoves;

    public Player(PieceColor color){
        this.color = color;
        pieces = new HashMap<>(16);
        if(color == PieceColor.WHITE){

            pieces.put(Position.get(0, 7), Piece.WhiteRook);
            pieces.put(Position.get(7, 7), Piece.WhiteRook);
            pieces.put(Position.get(1, 7), Piece.WhiteKnight);
            pieces.put(Position.get(6, 7), Piece.WhiteKnight);
            pieces.put(Position.get(2, 7), Piece.WhiteBishop);
            pieces.put(Position.get(5, 7), Piece.WhiteBishop);
            pieces.put(Position.get(3, 7), Piece.WhiteQueen);
            pieces.put(Position.get(4, 7), Piece.WhiteKing);
            for (int i = 0; i < 8; i++) {
                pieces.put(Position.get(i, 6), Piece.WhitePawn);
            }
        }else{
            pieces.put(Position.get(0, 0), Piece.BlackRook);
            pieces.put(Position.get(7, 0), Piece.BlackRook);
            pieces.put(Position.get(1, 0), Piece.BlackKnight);
            pieces.put(Position.get(6, 0), Piece.BlackKnight);
            pieces.put(Position.get(2, 0), Piece.BlackBishop);
            pieces.put(Position.get(5, 0), Piece.BlackBishop);
            pieces.put(Position.get(3, 0), Piece.BlackQueen);
            pieces.put(Position.get(4, 0), Piece.BlackKing);
            for (int i = 0; i < 8; i++) {
                pieces.put(Position.get(i, 1), Piece.BlackPawn);
            }
        }

        possibleMoves = new ArrayList<>();
    }


    public boolean makeMove(Move move, Chessboard chessboard){



        return true;
    }

    public Set<Position> getPiecePositions(){
        return Collections.unmodifiableSet(pieces.keySet());
    }

    public Map<Position,Piece> getPieces(){
        return Collections.unmodifiableMap(pieces);
    }

    public void removePiece(Position position){
        Piece remove = pieces.remove(position);
        System.out.println("Removed " + remove + " at position " + position);
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

}
