package core_v2.Chessboards;

import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Chessboard implements Serializable {


    //private Piece chessboard[] = new Piece[64];

    private int enpassantPawnPosition;
    private boolean enpassantPossible;

    private boolean whiteQueensideCastling;
    private boolean whiteKingsideCastling;
    private boolean blackQueensideCastling;
    private boolean blackKingsideCastling;
    private final byte[] chessboard;

    private PieceColor onMove;

    private byte whitePiecesCount;
    private byte blackPiecesCount;

    private boolean castled;

    private Chessboard() {

        chessboard = new byte[64];

        chessboard[0] = -2;
        chessboard[1] = -3;
        chessboard[2] = -4;
        chessboard[3] = -5;
        chessboard[4] = -6;
        chessboard[5] = -4;
        chessboard[6] = -3;
        chessboard[7] = -2;

        chessboard[8] = -1;
        chessboard[9] = -1;
        chessboard[10] = -1;
        chessboard[11] = -1;
        chessboard[12] = -1;
        chessboard[13] = -1;
        chessboard[14] = -1;
        chessboard[15] = -1;

        chessboard[55] = 1;
        chessboard[54] = 1;
        chessboard[53] = 1;
        chessboard[52] = 1;
        chessboard[51] = 1;
        chessboard[50] = 1;
        chessboard[49] = 1;
        chessboard[48] = 1;

        chessboard[63] = 2;
        chessboard[62] = 3;
        chessboard[61] = 4;
        chessboard[60] = 6;
        chessboard[59] = 5;
        chessboard[58] = 4;
        chessboard[57] = 3;
        chessboard[56] = 2;

        whitePiecesCount = 16;
        blackPiecesCount = 16;

        onMove = PieceColor.WHITE;

        enpassantPossible = false;
        enpassantPawnPosition = -1;

        whiteKingsideCastling = true;
        whiteQueensideCastling = true;

        blackKingsideCastling = true;
        blackQueensideCastling = true;

        castled = false;

    }

    public Chessboard(BoardBuilder builder) {

        this.chessboard = builder.pieces;

        whitePiecesCount = builder.whitePiecesCount;
        blackPiecesCount = builder.blackPiecesCount;

        onMove = builder.onMove;

        enpassantPossible = builder.enpassantPossible;
        enpassantPawnPosition = builder.enpassantPosition;

        whiteKingsideCastling = builder.whiteKingsideCastling;
        whiteQueensideCastling = builder.whiteQueensideCastling;

        blackKingsideCastling = builder.blackKingsideCastling;
        blackQueensideCastling = builder.blackQueensideCastling;

        castled = builder.castled;

    }

    public List<Piece> getActivePieces() {
        List<Piece> pieces = new ArrayList<>(16);
        byte counter = 0;
        byte i = 0;
        if (onMove == PieceColor.WHITE) {
            while (counter < whitePiecesCount) {
                if (chessboard[i] > 0) {
                    pieces.add(getPiece(i));
                    counter++;
                }
                i++;
            }
        } else {
            while (counter < blackPiecesCount) {
                if (chessboard[i] < 0) {
                    pieces.add(getPiece(i));
                    counter++;
                }
                i++;
            }
        }
        return pieces;
    }

    public List<Piece> getTargetPieces() {
        List<Piece> pieces = new ArrayList<>(16);
        byte counter = 0;
        byte i = 0;
        if (onMove == PieceColor.BLACK) {
            while (counter < whitePiecesCount  && i < 64) {
                if (chessboard[i] > 0) {
                    pieces.add(getPiece(i));
                    counter++;
                }
                i++;
            }
        } else {
            while (counter < blackPiecesCount && i < 64) {
                if (chessboard[i] < 0) {
                    pieces.add(getPiece(i));
                    counter++;
                }
                i++;
            }
        }
        return pieces;
    }

    public boolean isEnpassantPossible() {
        return enpassantPossible;
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

    public PieceColor getOnMove() {
        return onMove;
    }

    public byte getPieceAsByte(int position) {
        return chessboard[position];
    }

    public Piece getPiece(int position) {
        return Piece.getPieceByIdAndPosition(position, chessboard[position]);
    }

    public void printChessboard() {
        String s = "\n-------------------------------------\n          CHESSBOARD STATE\n\n   ";
        char c = 'A';
        for (int i = 0; i < 8; i++) {
            s += " " + c++ + "  ";
        }
        s += "\t\n  ---------------------------------\n";
        for (int i = 1; i < 65; i++) {
            int j = i / 8;
            if ((i - 1) % 8 == 0)
                s += 8 - j + " ";
            s += "| ";
            //Piece p = getPiece(i-1);
            if (chessboard[i - 1] != 0)
                s += getPiece(i - 1);
            else
                s += " ";
            s += " ";

            if (j != 0 && i % 8 == 0)
                s += "|\t\n  ---------------------------------\n";
            else if (j == 0 && i % 8 == 0)
                s += "\t\n  ---------------------------------\n";
        }

        s += "\nCurrently on move: " + onMove;
        s += "\nEnpassant possible: " + enpassantPossible;
        s += "\nWhite castlings: queenside- " + whiteQueensideCastling + ", kingside- " + whiteKingsideCastling;
        s += "\nBlack castlings: queenside- " + blackQueensideCastling + ", kingside- " + blackKingsideCastling;
        System.out.println(s);
    }

    public String[][] to2DString(){
        String[][] boardAsArray = new String[8][8];
        for (int i = 0; i < 64; i++) {
            if(chessboard[i] != 0){
                boardAsArray[i%8][i/8] = getPiece(i).forArray();
            }else
                boardAsArray[i%8][i/8] = "";

        }
        return boardAsArray;


    }

    public boolean isCastled() {
        return castled;
    }

    public int getEnpassantPawnPosition() {
        return enpassantPawnPosition;
    }

    public static class BoardBuilder {

        private byte[] pieces;
        private byte whitePiecesCount;
        private byte blackPiecesCount;
        private boolean enpassantPossible;
        private int enpassantPosition;

        private boolean whiteQueensideCastling;
        private boolean whiteKingsideCastling;
        private boolean blackQueensideCastling;
        private boolean blackKingsideCastling;

        private boolean castled;

        private PieceColor onMove;

        public BoardBuilder() {

            reset();

        }

        public BoardBuilder(Chessboard from) {
            this.pieces = new byte[64];
            this.castled = from.castled;
            this.onMove = from.getOnMove();

            this.enpassantPossible = from.isEnpassantPossible();
            this.enpassantPosition = from.getEnpassantPawnPosition();

            this.whiteKingsideCastling = from.isWhiteKingsideCastlingPossible();
            this.whiteQueensideCastling = from.isWhiteQueensideCastlingPossible();

            this.blackKingsideCastling = from.isBlackKingsideCastlingPossible();
            this.blackQueensideCastling = from.isBlackQueensideCastlingPossible();
        }

        public void setOnMove(PieceColor onMove) {
            this.onMove = onMove;
        }

        public void setEnpassantPossible() {
            this.enpassantPossible = true;
        }

        public void setEnpassantPosition(int enpassantPosition) {
            this.enpassantPossible = true;
            this.enpassantPosition = enpassantPosition;
        }

        public void setWhiteQueensideCastling(boolean whiteQueensideCastling) {
            this.whiteQueensideCastling = whiteQueensideCastling;
        }

        public void setWhiteKingsideCastling(boolean whiteKingsideCastling) {
            this.whiteKingsideCastling = whiteKingsideCastling;
        }

        public void setBlackQueensideCastling(boolean blackQueensideCastling) {
            this.blackQueensideCastling = blackQueensideCastling;
        }

        public void setBlackKingsideCastling(boolean blackKingsideCastling) {
            this.blackKingsideCastling = blackKingsideCastling;
        }

        public void setCastled(boolean castled) {
            this.castled = castled;
        }

        public void setPiece(byte position, byte piece) {
            if (piece > 0) {
                whitePiecesCount++;
            } else if (piece < 0) {
                blackPiecesCount++;
            }
            pieces[position] = piece;
        }

        public void setPiece(Piece piece) {
            setPiece(piece.position.to1D(), (byte) (piece.color.value() * piece.type.id()));
        }

        public void reset() {
            pieces = new byte[64];
            this.castled = false;
            onMove = PieceColor.WHITE;

            whitePiecesCount = 0;
            blackPiecesCount = 0;

            enpassantPossible = false;
            enpassantPosition = -1;

            whiteKingsideCastling = true;
            whiteQueensideCastling = true;

            blackKingsideCastling = true;
            blackQueensideCastling = true;
        }

        public Chessboard buildStartingBoard(){
            return new Chessboard();
        }

        public Chessboard build() {

            return new Chessboard(this);

        }
    }

}
