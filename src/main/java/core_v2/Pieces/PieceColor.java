package core_v2.Pieces;

import java.io.Serializable;

/**
 * Created by Filip on 11/21/2017.
 */
public enum PieceColor implements Serializable{
    WHITE(1) {
        @Override
        public boolean isWhite() {
            return true;
        }
    },
    BLACK(-1) {
        @Override
        public boolean isWhite() {
            return false;
        }
    };

    private final int value;

    PieceColor(int value) {
        this.value = value;
    }

    public int value(){
        return this.value;
    }
    public abstract boolean isWhite();
}