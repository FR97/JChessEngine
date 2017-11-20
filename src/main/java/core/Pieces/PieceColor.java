package core.Pieces;

/**
 * Created by Filip on 11/18/2017.
 */
public enum PieceColor {
    WHITE(1),
    BLACK(-1);

    private final int value;

    PieceColor(int value) {
        this.value = value;
    }

    public int value(){
        return this.value;
    }
}
