package core_v2.Chessboards;

import core_v2.Moves.Move;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Filip on 11/23/2017.
 */
public class IntBoard {

    private Stack<Move> moveHistory;
    private PieceColor onMove = PieceColor.WHITE;
    private boolean enpassantPossible;
    private Position enpassantPosition;

    private boolean whiteQueensideCastling;
    private boolean whiteKingsideCastling;
    private boolean blackQueensideCastling;
    private boolean blackKingsideCastling;


    private int chessboard[][] = {
        {-2, -3, -4, -5, -6, -4, -3, -2},
        {-1, -1, -1, -1, -1, -1, -1, -1},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1},
        {2, 3, 4, 5, 6, 4, 3, 2}};

    public IntBoard(){

        moveHistory = new Stack<>();

        enpassantPossible = false;
        enpassantPosition = null;

        whiteKingsideCastling = true;
        whiteQueensideCastling = true;
        blackKingsideCastling = true;
        blackQueensideCastling = true;

    }

    public IntBoard(IntBoard chessboard){

        this.moveHistory = chessboard.getMoveHistory();

        this.chessboard = new int[8][8];
        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 8; i++){
                this.chessboard[j][i] = chessboard.getPiece(Position.get(i,j));
            }
        }

        this.enpassantPossible = chessboard.isEnpassantPossible();
        this.enpassantPosition = chessboard.getEnpassantPosition();

        this.whiteKingsideCastling = chessboard.isWhiteKingsideCastlingPossible();
        this.whiteQueensideCastling = chessboard.isWhiteQueensideCastlingPossible();
        this.blackKingsideCastling = chessboard.isBlackKingsideCastlingPossible();
        this.blackQueensideCastling = chessboard.isBlackQueensideCastlingPossible();

    }

    public int getPiece(Position position){
        return chessboard[position.Y][position.X];
    }

    public boolean makeMove(Move move){

        if(chessboard[move.from.Y][move.from.X] == 0 || move.movingPiece * onMove.value() < 0)
            return false;

        if(whiteKingsideCastling || whiteQueensideCastling){
            if(move.movingPiece == 6){
                whiteQueensideCastling = false;
                whiteKingsideCastling = false;
            }else if(move.movingPiece == 2 && move.from.X == 0){
                whiteQueensideCastling = false;
            }else if(move.movingPiece == 2 && move.from.X == 7){
                whiteKingsideCastling = false;
            }
        }

        if(blackKingsideCastling || blackQueensideCastling){
            if(move.movingPiece == -6){
                blackQueensideCastling = false;
                blackKingsideCastling = false;
            }else if(move.movingPiece == -2 && move.from.X == 0){
                blackQueensideCastling = false;
            }else if(move.movingPiece == -2 && move.from.X == 7){
                blackKingsideCastling = false;
            }
        }


        enpassantPossible = false;
        enpassantPosition = null;
        switch (move.type){

            case NORMAL:
                makeNormalMove(move);
                break;
            case PAWN_DOUBLE_JUMP:
                enpassantPossible = true;
                enpassantPosition = Position.get(move.to.X, move.from.Y);
                makeNormalMove(move);
                break;
            case ENPASSANT:
                makeEnpassant(move);
                break;
            case CASTLING:
                makeCastling(move);
                break;
            default:
                return false;
        }

        moveHistory.push(move);

        toggleOnMove();

        return true;
    }

    private void makeNormalMove(Move move){
        chessboard[move.to.Y][move.to.X] = move.movingPiece;
        chessboard[move.from.Y][move.from.X] = 0;
    }

    private void makeEnpassant(Move move){
        chessboard[move.to.Y][move.to.X] = move.movingPiece;
        chessboard[move.from.Y][move.to.X] = 0;
    }

    private void makeCastling(Move move){

        chessboard[move.to.Y][move.to.X] = move.movingPiece;
        chessboard[move.from.Y][move.from.X] = 0;
        if(move.to.X == 2){
            chessboard[move.to.Y][3] = chessboard[move.to.Y][0];
            chessboard[move.to.Y][0] = 0;
        }else{
            chessboard[move.to.Y][5] = chessboard[move.to.Y][7];
            chessboard[move.to.Y][7] = 0;
        }
    }

    public void undoMove(){
        Move lastMove = moveHistory.pop();
        switch (lastMove.type){

            case NORMAL:
                undoStandardMove(lastMove);
                break;
            case PAWN_DOUBLE_JUMP:
                enpassantPossible = false;
                undoStandardMove(lastMove);
                break;
            case ENPASSANT:
                enpassantPossible = true;
                enpassantPosition = Position.get(lastMove.to.X, lastMove.from.Y);
                undoEnpassant(lastMove);
                break;
            case CASTLING:
                undoCastling(lastMove);
                break;
        }
        toggleOnMove();
    }

    private void undoStandardMove(Move move){
        chessboard[move.from.Y][move.from.X] = move.movingPiece;
        chessboard[move.to.Y][move.to.X] = move.eatenPiece;

    }

    private void undoCastling(Move move){
        chessboard[move.from.Y][move.from.X] = move.movingPiece;
        if(move.to.X == 2){
            chessboard[move.from.Y][0] = chessboard[move.from.Y][3];
            chessboard[move.from.Y][3] = 0;
        }else {
            chessboard[move.from.Y][7] = chessboard[move.from.Y][5];
            chessboard[move.from.Y][5] = 0;
        }
    }

    private void undoEnpassant(Move move){
        chessboard[move.from.Y][move.from.X] = move.movingPiece;
        chessboard[move.from.Y][move.to.X] = move.eatenPiece;
    }


    public void toggleOnMove(){
        onMove = (onMove == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public PieceColor getOnMove(){
        return this.onMove;
    }

    public Stack<Move> getMoveHistory() {
        return (Stack)this.moveHistory.clone();
    }

    public boolean isEnpassantPossible() {
        return enpassantPossible;
    }

    public Position getEnpassantPosition() {
        return enpassantPosition;
    }

    public boolean isWhiteQueensideCastlingPossible() {
        return whiteQueensideCastling;
    }

    public boolean isWhiteKingsideCastlingPossible() {
        return whiteKingsideCastling;
    }

    public boolean isBlackQueensideCastlingPossible() {
        return blackQueensideCastling;
    }

    public boolean isBlackKingsideCastlingPossible() {
        return blackKingsideCastling;
    }

    public void printChessboard(boolean full){
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
                    if(j > 0){
                        Piece p = Piece.getPieceById(chessboard[j-1][i]);
                        if(p != null)
                            s+=p;
                        else
                            s+=" ";
                    }

                    s+=" ";
                }

            }

            if(j !=0)
                s+="|\t\n  ---------------------------------\n";
            else
                s+="\t\n  ---------------------------------\n";


        }

        s+="\nCurrently on move: "+onMove+"\n";
        if(!full){
            System.out.print(s);
            return;
        }
        s+= "Enpassant possible: "+enpassantPossible+"\n";
        s+= "White castlings: Kingside - " + whiteKingsideCastling + ", Queenside: " + whiteQueensideCastling +
                "\nBlack castlings: Kingside - " + blackKingsideCastling + ", Queenside: " + blackQueensideCastling;
        System.out.println(s);


    }


}
