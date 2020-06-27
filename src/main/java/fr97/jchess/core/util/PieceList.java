package fr97.jchess.core.util;

import fr97.jchess.core.piece.Piece;
import fr97.jchess.core.piece.PieceColor;
import fr97.jchess.core.piece.PieceType;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class PieceList implements Iterable<Piece> {

    private static final int MAX_SIZE = 16;

    private final Piece[] pieces;
    private final int size;
    private final int kingPosition;


    private PieceList(final int size, final Piece[] pieces, final int kingPosition) {
        this.size = size;
        this.pieces = pieces;
        this.kingPosition = kingPosition;

    }

    private PieceList(final PieceList list, final Piece without) throws IllegalArgumentException{
        this.size = list.size - 1;
        this.pieces = new Piece[this.size];
        int counter = 0;
        int kingPos = -1;
        for (int i = 0; i < list.pieces.length; i++) {
            if (!list.pieces[i].equals(without)) {
                if(list.pieces[i].type == PieceType.KING)
                    kingPos = counter;
                this.pieces[counter] = list.pieces[i];

                counter++;
            }
        }
        if (kingPos < 0)
            throw new IllegalArgumentException("PieceList must contain king");
        this.kingPosition = kingPos;
    }

    private PieceList(final PieceList list,final Piece oldPiece,  final Piece newPiece) {
        this.size = list.size;
        this.pieces = new Piece[this.size];
        this.kingPosition = list.kingPosition;
        for (int i = 0; i < list.pieces.length; i++) {
            if (!list.pieces[i].equals(oldPiece)) {
                this.pieces[i] = list.pieces[i];
            } else {
                this.pieces[i] = newPiece;
            }
        }

    }

    public PieceList insteadOf(Piece oldPiece, Piece newPiece) {
        return new PieceList(this, oldPiece, newPiece);
    }

    public PieceList without(Piece removedPiece) {
        return new PieceList(this, removedPiece);
    }

    public static PieceList of(final Piece[] pieces) throws IllegalArgumentException {
        if (pieces.length > MAX_SIZE)
            throw new IllegalArgumentException("PieceList can't have more than 16 pieces");
        int kingPos = -1;
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].type == PieceType.KING) {
                kingPos = i;
                break;
            }
        }
        if (kingPos < 0)
            throw new IllegalArgumentException("PieceList must contain king");

        return new PieceList(pieces.length, pieces, kingPos);
    }

    public Piece getPiece(int position) {
        for (int i = 0; i < size; i++) {
            if (pieces[i].position == position)
                return pieces[i];
        }
        return null;
    }

    public Piece getByIndex(int index) {
        return pieces[index];
    }

    public Piece get(int position) {
        if (position < 32) {
            for (int i = 0; i < size; i++) {
                if (pieces[i].position == position)
                    return pieces[i];
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (pieces[i].position == position)
                return pieces[i];
        }
        return null;
    }

    public Piece getKing() {
        return this.pieces[kingPosition];
    }

    /**
     * @param piece
     * @return index if piece is in list || -1;
     */
    public int contains(Piece piece) {
        if (piece == null)
            return -1;
        for (int i = 0; i < this.size; i++) {
            if (pieces[i].equals(piece))
                return i;
        }
        return -1;
    }

    public int containsOnPosition(int position) {
        if (position < 0 || position > 63)
            return -1;
        for (int i = 0; i < this.size; i++) {
            if (pieces[i].position == position)
                return i;
        }
        return -1;
    }

    public int size() {
        return this.size;
    }

    public static PieceList getStartingWhiteList() {
        final Piece[] pieces = new Piece[16];
        pieces[0] = new Piece(60, PieceType.KING, PieceColor.WHITE);
        for (int i = 1; i < 9; i++) {
            pieces[i] = new Piece(i + 47, PieceType.PAWN, PieceColor.WHITE);
        }
        pieces[9] = new Piece(56, PieceType.ROOK, PieceColor.WHITE);
        pieces[10] = new Piece(57, PieceType.KNIGHT, PieceColor.WHITE);
        pieces[11] = new Piece(58, PieceType.BISHOP, PieceColor.WHITE);
        pieces[12] = new Piece(59, PieceType.QUEEN, PieceColor.WHITE);
        pieces[13] = new Piece(61, PieceType.BISHOP, PieceColor.WHITE);
        pieces[14] = new Piece(62, PieceType.KNIGHT, PieceColor.WHITE);
        pieces[15] = new Piece(63, PieceType.ROOK, PieceColor.WHITE);

        return of(pieces);
    }

    public static PieceList getStartingBlackList() {
        final Piece[] pieces = new Piece[16];
        pieces[0] = new Piece(4, PieceType.KING, PieceColor.BLACK);

        pieces[1] = new Piece(0, PieceType.ROOK, PieceColor.BLACK);
        pieces[2] = new Piece(1, PieceType.KNIGHT, PieceColor.BLACK);
        pieces[3] = new Piece(2, PieceType.BISHOP, PieceColor.BLACK);
        pieces[4] = new Piece(3, PieceType.QUEEN, PieceColor.BLACK);
        pieces[5] = new Piece(5, PieceType.BISHOP, PieceColor.BLACK);
        pieces[6] = new Piece(6, PieceType.KNIGHT, PieceColor.BLACK);
        pieces[7] = new Piece(7, PieceType.ROOK, PieceColor.BLACK);
        for (int i = 8; i < 16; i++) {
            pieces[i] = new Piece(i, PieceType.PAWN, PieceColor.BLACK);
        }

        return of(pieces);
    }


    @Override
    public Iterator<Piece> iterator() {
        return new PieceIterator(this);
    }

    @Override
    public void forEach(Consumer<? super Piece> action) {
        Objects.requireNonNull(action);
        Piece last = null;
        for (Piece p : this) {
            last = p;
        }
        action.accept(last);
    }

    private static class PieceIterator implements Iterator<Piece> {

        private int current;
        private final PieceList pieceList;

        private PieceIterator(PieceList pieceList) {
            current = 0;
            this.pieceList = pieceList;
        }

        public boolean hasNext() {
            return current < pieceList.size;
        }

        public Piece next() {
            if (hasNext()) {
                return pieceList.pieces[current++];
            }


            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


}
