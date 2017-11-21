package core;

import core.Moves.Move;
import core.Moves.MoveType;
import core.Pieces.Piece;
import core.Pieces.PieceColor;
import core.Pieces.PieceType;
import core.Utils.Position;

import java.util.*;

/**
 * Created by Filip on 11/19/2017.
 */
public class Chessboard {

    private HashMap<Position, Piece> PIECES;

    private Position whiteKingPosition;
    private Position blackKingPosition;
    private Position enpassantPawnPosition;

    private boolean enpassantPossible;
    private boolean whiteQueensideCastling;
    private boolean whiteKingsideCastling;
    private boolean blackQueensideCastling;
    private boolean blackKingsideCastling;

    private PieceColor onMove;

    public Chessboard() {

        PIECES = new HashMap<>();

        PIECES.put(Position.get(0, 0), Piece.BlackRook);
        PIECES.put(Position.get(7, 0), Piece.BlackRook);
        PIECES.put(Position.get(1, 0), Piece.BlackKnight);
        PIECES.put(Position.get(6, 0), Piece.BlackKnight);
        PIECES.put(Position.get(2, 0), Piece.BlackBishop);
        PIECES.put(Position.get(5, 0), Piece.BlackBishop);
        PIECES.put(Position.get(3, 0), Piece.BlackQueen);
        PIECES.put(Position.get(4, 0), Piece.BlackKing);
        for (int i = 0; i < 8; i++) {
            PIECES.put(Position.get(i, 1), Piece.BlackPawn);
        }

        PIECES.put(Position.get(0, 7), Piece.WhiteRook);
        PIECES.put(Position.get(7, 7), Piece.WhiteRook);
        PIECES.put(Position.get(1, 7), Piece.WhiteKnight);
        PIECES.put(Position.get(6, 7), Piece.WhiteKnight);
        PIECES.put(Position.get(2, 7), Piece.WhiteBishop);
        PIECES.put(Position.get(5, 7), Piece.WhiteBishop);
        PIECES.put(Position.get(3, 7), Piece.WhiteQueen);
        PIECES.put(Position.get(4, 7), Piece.WhiteKing);
        for (int i = 0; i < 8; i++) {
            PIECES.put(Position.get(i, 6), Piece.WhitePawn);
        }

        whiteKingPosition = Position.get(4,7);
        blackKingPosition = Position.get(4,0);


        onMove = PieceColor.WHITE;

        enpassantPossible =false;

        whiteQueensideCastling = true;
        whiteKingsideCastling = true;
        blackQueensideCastling = true;
        blackKingsideCastling = true;

    }

    /**
     * Copy constructor
     * @param chessboard - chessboard to copy
     */
    public Chessboard(Chessboard chessboard){

        this.PIECES = new HashMap<>();

        TreeSet<Position> positions = chessboard.getAllPositionsForColor(PieceColor.WHITE);
        positions.forEach(position -> {
            this.PIECES.put(position, chessboard.getPiece(position));
        });
        positions = chessboard.getAllPositionsForColor(PieceColor.BLACK);
        positions.forEach(position -> {
            this.PIECES.put(position, chessboard.getPiece(position));
        });

        this.onMove = chessboard.getOnMove();

        this.blackKingPosition = chessboard.getKingPosition(PieceColor.BLACK);
        this.whiteKingPosition = chessboard.getKingPosition(PieceColor.WHITE);

        this.blackKingsideCastling = chessboard.isBlackKingsideCastling();
        this.blackQueensideCastling = chessboard.isBlackQueensideCastling();

        this.whiteKingsideCastling = chessboard.isWhiteKingsideCastling();
        this.whiteQueensideCastling = chessboard.isWhiteQueensideCastling();

        this.enpassantPossible = chessboard.isEnpassantPossible();
    }


    public boolean pieceExist(Position p) {
        if (PIECES.containsKey(p))
            return true;

        return false;
    }

    public Piece getPiece(Position p) {

        return PIECES.getOrDefault(p, null);
    }

    public Piece getPiece(int x, int y) {

        return PIECES.getOrDefault(Position.get(x,y), null);
    }

    public void move(Move move) {

        //System.out.println("Move made:" + move);

        if(whiteKingsideCastling || whiteQueensideCastling || blackKingsideCastling || blackQueensideCastling)
            updateCastling(move);

        if(move.MOVING_PIECE.equals(Piece.BlackKing))
            blackKingPosition = Position.get(move.TO.X, move.TO.Y);

        if(move.MOVING_PIECE.equals(Piece.WhiteKing))
            whiteKingPosition = Position.get(move.TO.X, move.TO.Y);


        if(move.TYPE == MoveType.NORMAL){
            PIECES.put(move.TO, PIECES.remove(move.FROM));
        }else if(move.TYPE == MoveType.CASTLING){
            if(onMove == PieceColor.WHITE){
                PIECES.put(move.TO, PIECES.remove(move.FROM));
                if(move.TO.X == 2)
                    PIECES.put(Position.withOffsets(move.FROM,-1,0), PIECES.remove(Position.get(0,7)));
                else
                    PIECES.put(Position.withOffsets(move.FROM,1,0), PIECES.remove(Position.get(7,7)));
            }
        }else {
            PIECES.put(move.TO, PIECES.remove(move.FROM));
            PIECES.remove(Position.get(move.TO.X, move.FROM.Y));
        }

       // moveHistory.addMove(move);
        toggleOnMove();
    }

    public void checkEnpassant(Move move){
        enpassantPossible = false;
        enpassantPawnPosition = null;
        if((move.MOVING_PIECE.equals(Piece.BlackPawn) || move.MOVING_PIECE.equals(Piece.WhitePawn)) && Math.abs(move.FROM.Y - move.TO.Y) == 2){
            enpassantPossible = true;
            enpassantPawnPosition = Position.get(move.TO.X, move.TO.Y);
        }
    }

    public void move(Position from, Position to){
        move(new Move(from, to, getPiece(from), getPiece(to), MoveType.NORMAL));
    }

    private void updateCastling(Move m){
        if(m.MOVING_PIECE.equals(Piece.BlackKing)){
            blackKingsideCastling = false;
            blackQueensideCastling = false;
        }
        if(m.MOVING_PIECE.equals(Piece.WhiteKing)){
            whiteKingsideCastling = false;
            whiteQueensideCastling = false;
        }

        if(m.MOVING_PIECE.equals(Piece.BlackRook) && m.FROM.X == 0)
            blackQueensideCastling = false;


        if(m.MOVING_PIECE.equals(Piece.BlackRook) && m.FROM.X == 7)
            blackKingsideCastling = false;


        if(m.MOVING_PIECE.equals(Piece.WhiteRook) && m.FROM.X == 1)
            whiteQueensideCastling = false;


        if(m.MOVING_PIECE.equals(Piece.WhiteRook) && m.FROM.X == 7)
            whiteKingsideCastling = false;

    }

    public int getPieceCount(){
        return this.PIECES.size();
    }

    public PieceColor getOnMove(){
        return this.onMove;
    }

    public Position getKingPosition(PieceColor kingColor){
        if(kingColor == PieceColor.WHITE)
            return whiteKingPosition;

        return blackKingPosition;
    }

    public TreeSet<Position> getAllPositionsForColor(PieceColor color){
        //System.out.println("Number of pieces - " + PIECES.keySet().size());
        TreeSet<Position> positions = new TreeSet<>();
        for (Position p: PIECES.keySet()) {
            if(PIECES.get(p).COLOR == color){
               // System.out.println("PIECES: " +PIECES.get(p) + " AT " + p);
                positions.add(p);
            }
        }
        return positions;
    }

    public Position getEnpassantPawnPosition(){
        return this.enpassantPawnPosition;
    }


    public boolean isEnpassantPossible() {
        return enpassantPossible;
    }

    public boolean isWhiteQueensideCastling() {
        return whiteQueensideCastling;
    }

    public boolean isWhiteKingsideCastling() {
        return whiteKingsideCastling;
    }

    public boolean isBlackQueensideCastling() {
        return blackQueensideCastling;
    }

    public boolean isBlackKingsideCastling() {
        return blackKingsideCastling;
    }


    public String[][] to2DArray(){
        String[][] boardAsArray = new String[8][8];
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Piece p = PIECES.get(Position.get(i,j));
                if(p != null)
                    boardAsArray[i][j] = p.toString().toLowerCase();
                else
                    boardAsArray[i][j] = "";
            }
        }
        return boardAsArray;
    }



    private void toggleOnMove() {
        onMove = (onMove == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public void printChessboard(){

        System.out.print("\n---------------------------------------------\n              CHESSBOARD STATE\n\n    ");
        char c = 'A';
        for (int j = 0; j < 9; j++){

            for (int i = 0; i < 8; i++){
                if(j ==0)
                    System.out.print(" "+ c++ +"   ");
                else {
                    if(i==0 && j != 0)
                        System.out.print(j+" ");
                    System.out.print("| ");
                    Piece p = PIECES.get(Position.get(i,j-1));
                    if(p != null)
                        System.out.print(p);
                    else
                        System.out.print("  ");
                    System.out.print(" ");
                }

            }

            if(j !=0)
                System.out.print("|\t\n  -----------------------------------------\n");
            else
                System.out.print("\t\n  -----------------------------------------\n");


        }

        System.out.print("\nCurrently on move: "+onMove+"\n\n");
    }

}
