package fr97.jchess.core.piece;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Filip on 12/2/2017.
 */
public class PieceList implements Iterable<Piece>{

    private Piece[] pieces;

    private final int MAX_SIZE;
    private int size;
    private int kingPosition;

    public PieceList(){
        this.MAX_SIZE = 16;
        this.pieces = new Piece[MAX_SIZE];
        this.size = 0;
        this.kingPosition = -1;
    }

    public PieceList(int size){
        this.MAX_SIZE = size;
        this.pieces = new Piece[MAX_SIZE];
        this.size = 0;
        this.kingPosition = -1;
    }

    public void add(Piece piece){
        if(this.size < MAX_SIZE){
            if(piece.type == PieceType.KING)
                this.kingPosition = size;
            this.pieces[size] = piece;
            this.size++;
            return;
        }
        throw new RuntimeException("You can't have more than 16 pieces");
    }

    public Piece getPieceWithPosition(int position){
        for(int i = 0; i < this.size; i++){
            if (position == pieces[i].position)
                return pieces[i];
        }
        return null;
    }


    public Piece get(int index){
        return pieces[index];
    }

    public Piece getKing(){
        return this.pieces[kingPosition];
    }

    public void remove(Piece piece){
        Piece[] newList = new Piece[16];
        int br = 0;
        //boolean kingRemoved = true;
        for(int i = 0; i < size; i++){
            if(!pieces[i].equals(piece)) {
                newList[br] = pieces[i];
                if(pieces[i].type == PieceType.KING){
                    this.kingPosition = br;
                   // kingRemoved = false;
                }
                br++;
            }
        }
        //if(kingRemoved){
            //throw new RuntimeException("You can't delete king");
       // }
        this.pieces = null;
        this.pieces = newList;
        this.size = br;
    }

    public boolean contains(Piece piece){
        if(piece == null)
            return false;
        for(int i = 0; i < this.size; i++){
            if(pieces[i].equals(piece))
                return true;
        }
        return false;
    }

    public int size() {
        return this.size;
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

    @Override
    public Spliterator<Piece> spliterator() {
        return null;
    }

    private static class PieceIterator implements Iterator<Piece> {

        private int current;
        private PieceList pieceList;

        private PieceIterator(PieceList pieceList){
            current = 0;
            this.pieceList = pieceList;
        }

        public boolean hasNext() {
            return current < pieceList.size;
        }

        public Piece next() {
            if(hasNext()){
                return pieceList.pieces[current++];
            }


            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
