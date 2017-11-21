package core_v2.Chessboards;

import core.Pieces.*;
import core_v2.Game;
import core_v2.Moves.CastlingMove;
import core_v2.Moves.EnpassantMove;
import core_v2.Moves.Move;
import core_v2.Moves.MoveType;
import core_v2.Pieces.*;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Pieces.PieceType;
import core_v2.Players.Player;
import core_v2.Utils.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Chessboard implements IBoard<Piece>, Serializable{


    private HashMap<Position, Piece> whitePieces;
    private HashMap<Position, Piece> blackPieces;

    private Position enpassantPawnPosition;

    private boolean enpassantPossible;
    private boolean whiteQueensideCastling;
    private boolean whiteKingsideCastling;
    private boolean blackQueensideCastling;
    private boolean blackKingsideCastling;


    private PieceColor onMove;

    public Chessboard(){

        whitePieces = new HashMap<>();
        blackPieces = new HashMap<>();
        onMove = PieceColor.WHITE;

        enpassantPossible =false;

        whiteQueensideCastling = true;
        whiteKingsideCastling = true;
        blackQueensideCastling = true;
        blackKingsideCastling = true;

        initializeBoard();
    }

    public Chessboard(Chessboard chessboard){

        whitePieces = new HashMap<>();
        blackPieces = new HashMap<>();

        for (Piece p: chessboard.getWhitePieces()) {
            Piece newP = p.withPosition(p.position);
            whitePieces.put(newP.position, newP);
        }
        for (Piece p: chessboard.getBlackPieces()) {
            Piece newP = p.withPosition(p.position);
            blackPieces.put(newP.position, newP);
        }

        onMove = chessboard.getOnMove();

        enpassantPossible = chessboard.isEnpassantPossible();

        whiteQueensideCastling = chessboard.isWhiteQueenSideCastlingPossible();
        whiteKingsideCastling = chessboard.isWhiteKingSideCastlingPossible();
        blackQueensideCastling = chessboard.isBlackQueenSideCastlingPossible();
        blackKingsideCastling = chessboard.isBlackKingSideCastlingPossible();


    }

    @Override
    public Piece getPiece(int x, int y) {
        return getPiece(Position.get(x,y));
    }

    @Override
    public Piece getPiece(Position position) {

        if(whitePieces.containsKey(position)){
            return whitePieces.get(position);
        }
        if(blackPieces.containsKey(position)){
            return blackPieces.get(position);
        }
        return null;
    }


    @Override
    public void initializeBoard() {

        blackPieces.put(Position.get(0, 0), new Rook(Position.get(0, 0), PieceColor.BLACK));
        blackPieces.put(Position.get(7, 0), new Rook(Position.get(0, 0), PieceColor.BLACK));

        blackPieces.put(Position.get(1, 0), new Knight(Position.get(1, 0), PieceColor.BLACK));
        blackPieces.put(Position.get(6, 0), new Knight(Position.get(6, 0), PieceColor.BLACK));

        blackPieces.put(Position.get(2, 0), new Bishop(Position.get(2, 0), PieceColor.BLACK));
        blackPieces.put(Position.get(5, 0), new Bishop(Position.get(5, 0), PieceColor.BLACK));

        blackPieces.put(Position.get(3, 0), new Queen(Position.get(3, 0), PieceColor.BLACK));
        blackPieces.put(Position.get(4, 0), new King(Position.get(4, 0), PieceColor.BLACK));

        for (int i = 0; i < 8; i++) {
            blackPieces.put(Position.get(i, 1), new Pawn(Position.get(i, 1), PieceColor.BLACK));
        }

        whitePieces.put(Position.get(0, 7), new Rook(Position.get(0, 7), PieceColor.WHITE));
        whitePieces.put(Position.get(7, 7), new Rook(Position.get(7, 7), PieceColor.WHITE));

        whitePieces.put(Position.get(1, 7), new Knight(Position.get(1, 7), PieceColor.WHITE));
        whitePieces.put(Position.get(6, 7), new Knight(Position.get(6, 7), PieceColor.WHITE));

        whitePieces.put(Position.get(2, 7), new Bishop(Position.get(2, 7), PieceColor.WHITE));
        whitePieces.put(Position.get(5, 7), new Bishop(Position.get(5, 7), PieceColor.WHITE));

        whitePieces.put(Position.get(3, 7), new Queen(Position.get(3, 7), PieceColor.WHITE));
        whitePieces.put(Position.get(4, 7), new King(Position.get(4, 7), PieceColor.WHITE));


        for (int i = 0; i < 8; i++) {
            whitePieces.put(Position.get(i, 6), new Pawn(Position.get(i, 6), PieceColor.WHITE));
        }

        updatePossibleMoves();
    }

    private void updatePossibleMoves(){

        if(onMove == PieceColor.WHITE){
            for (Piece p: whitePieces.values()) {
                p.loadPossibleMoves(this);
            }
        }else{
            for (Piece p: blackPieces.values()) {
                p.loadPossibleMoves(this);
            }
        }


    }

    @Override
    public List<Piece> getWhitePieces() {
        //List<Piece> pieces = new ArrayList<>(whitePieces.values());
        return Collections.unmodifiableList(new ArrayList<>(whitePieces.values()));
    }

    @Override
    public List<Piece> getBlackPieces() {

        return Collections.unmodifiableList(new ArrayList<>(blackPieces.values()));
    }

    @Override
    public PieceColor getOnMove() {
        return onMove;
    }

    @Override
    public boolean makeMove(Move move) {

        if(move == null || move.getMovingPiece() == null || move.getMovingPiece().color != onMove){
            System.out.println("IMPOSSIBLE");
            return false;
        }

        /*
        Check if piece can actually perform that move
        if(!move.getMovingPiece().getPossibleMoves().contains(move)){
            System.err.println("This piece doesn't have such move available");
            return false;
        }*/
        if(onMove == PieceColor.BLACK ){
            if(blackKingsideCastling){
               if(move.getMovingPiece().type == PieceType.KING || move.getMovingPiece().position.equals(Position.get(7,0))){
                   blackKingsideCastling = false;
               }
            }
            if(blackQueensideCastling){
                if(move.getMovingPiece().type == PieceType.KING || move.getMovingPiece().position.equals(Position.get(0,0))){
                    blackQueensideCastling = false;
                }

            }
        }else{
            if(whiteKingsideCastling){
                if(move.getMovingPiece().type == PieceType.KING || move.getMovingPiece().position.equals(Position.get(7,7)))
                    whiteKingsideCastling = false;
            }
            if(whiteQueensideCastling){
                if(move.getMovingPiece().type == PieceType.KING || move.getMovingPiece().position.equals(Position.get(0,7)))
                   whiteQueensideCastling = false;
            }
        }

        switch (move.type) {
            case PAWN_DOUBLE_JUMP:
                enpassantPossible = true;
                enpassantPawnPosition = move.toPosition;
                makeNormalMove(move);
                break;
            case NORMAL:
                makeNormalMove(move);
                break;
            case ATTACK:
                makeAttackMove(move);
                break;
            case CASTLING:
                makeCastlingMove((CastlingMove) move);
                break;
            case ENPASSANT:
                makeEnpassantMove((EnpassantMove) move);
                break;
            default:
                return false;
        }

        toggleOnMove();

        if(Game.DEBUG_MODE){
            System.out.println("Move made: " + move);
            printChessboard();
        }

        updatePossibleMoves();

        return true;
    }

    private void makeNormalMove(Move move){

        if(onMove == PieceColor.WHITE){
            Piece movingPiece = whitePieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            whitePieces.put(move.toPosition, movingPiece);
        }else{
            Piece movingPiece = blackPieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            blackPieces.put(move.toPosition, movingPiece);
        }

    }

    private void makeAttackMove(Move move){
        if(onMove == PieceColor.WHITE){
            Piece movingPiece = whitePieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            whitePieces.put(move.toPosition, movingPiece);
            blackPieces.remove(move.toPosition);
        }else{
            Piece movingPiece = blackPieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            blackPieces.put(move.toPosition, movingPiece);
            whitePieces.remove(move.toPosition);

        }
    }

    private void makeCastlingMove(CastlingMove move){
        if(onMove == PieceColor.WHITE){
            Piece movingKing = whitePieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            Piece movingRook = whitePieces.remove(move.getMovingRook().position).withPosition(move.rookTo);
            whitePieces.put(move.toPosition, movingKing);
            whitePieces.put(move.rookTo, movingRook);
        }else{
            Piece movingKing = blackPieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            Piece movingRook = blackPieces.remove(move.getMovingRook().position).withPosition(move.rookTo);
            blackPieces.put(move.toPosition, movingKing);
            blackPieces.put(move.rookTo, movingRook);
        }
    }

    private void makeEnpassantMove(EnpassantMove move){
        if(onMove == PieceColor.WHITE){
            Piece movingPawn = whitePieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            blackPieces.remove(move.getEatenPawn().position);
            whitePieces.put(move.toPosition, movingPawn);
        }else{
            Piece movingPawn = blackPieces.remove(move.getMovingPiece().position).withPosition(move.toPosition);
            whitePieces.remove(move.getEatenPawn().position);
            blackPieces.put(move.toPosition, movingPawn);
        }
    }

    @Override
    public void toggleOnMove() {
        onMove = (onMove == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    @Override
    public Player getWhitePlayer() {
        return null;
    }

    @Override
    public Player getBlackPlayer() {
        return null;
    }

    @Override
    public boolean tryConvertFromFen(String fenString) throws IllegalArgumentException {
        return false;
    }

    @Override
    public String convertToFen() {
        return null;
    }

    @Override
    public String[][] convertTo2DArray() {
        return new String[0][];
    }

    @Override
    public int fullMovesCount() {
        return 0;
    }

    @Override
    public int halfMovesClock() {
        return 0;
    }

    @Override
    public boolean isEnpassantPossible() {
        return enpassantPossible;
    }

    @Override
    public boolean isWhiteQueenSideCastlingPossible() {
        return whiteQueensideCastling;
    }

    @Override
    public boolean isWhiteKingSideCastlingPossible() {
        return whiteKingsideCastling;
    }

    @Override
    public boolean isBlackQueenSideCastlingPossible() {
        return blackQueensideCastling;
    }

    @Override
    public boolean isBlackKingSideCastlingPossible() { return blackKingsideCastling; }

    @Override
    public void printChessboard() {
        String s ="\n-------------------------------------\n          CHESSBOARD STATE\n\n   ";
        char c = 'A';
        for (int j = 0; j < 9; j++){

            for (int i = 0; i < 8; i++){
                if(j ==0)
                   s+=" "+ c++ +"  ";
                else {
                    if(i==0 && j != 0)
                        s+=j+" ";
                    s+="| ";
                    Piece p = getPiece(Position.get(i,j-1));
                    if(p != null)
                        s+=p.toString();
                    else
                        s+=" ";
                    s+=" ";
                }

            }

            if(j !=0)
                s+="|\t\n  ---------------------------------\n";
            else
            s+="\t\n  ---------------------------------\n";


        }

        s+="\nCurrently on move: "+onMove+"\n\n";
        System.out.println(s);
    }

    public Position getEnpassantPawnPosition() {
        return enpassantPawnPosition;
    }

}
