package fr97.jchess.core.util;

/**
 * Created by Filip on 11/18/2017.
 */
public final class Position implements Comparable<Position>{

    /**
     * All possible positions on chessboard
     */
    private static final Position[][] positions =
            {{new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0),
                    new Position(4, 0), new Position(5, 0), new Position(6, 0), new Position(7, 0)},

                    {new Position(0, 1), new Position(1, 1), new Position(2, 1), new Position(3, 1),
                            new Position(4, 1), new Position(5, 1), new Position(6, 1), new Position(7, 1)},

                    {new Position(0, 2), new Position(1, 2), new Position(2, 2), new Position(3, 2),
                            new Position(4, 2), new Position(5, 2), new Position(6, 2), new Position(7, 2)},

                    {new Position(0, 3), new Position(1, 3), new Position(2, 3), new Position(3, 3),
                            new Position(4, 3), new Position(5, 3), new Position(6, 3), new Position(7, 3)},

                    {new Position(0, 4), new Position(1, 4), new Position(2, 4), new Position(3, 4),
                            new Position(4, 4), new Position(5, 4), new Position(6, 4), new Position(7, 4)},

                    {new Position(0, 5), new Position(1, 5), new Position(2, 5), new Position(3, 5),
                            new Position(4, 5), new Position(5, 5), new Position(6, 5), new Position(7, 5)},

                    {new Position(0, 6), new Position(1, 6), new Position(2, 6), new Position(3, 6),
                            new Position(4, 6), new Position(5, 6), new Position(6, 6), new Position(7, 6)},

                    {new Position(0, 7), new Position(1, 7), new Position(2, 7), new Position(3, 7),
                            new Position(4, 7), new Position(5, 7), new Position(6, 7), new Position(7, 7)}};


    public static final Position[] KNIGHT_OFFSETS =
            {new Position(-2, -1), new Position(-2, 1), new Position(2, -1), new Position(2, 1),
                    new Position(-1, -2), new Position(-1, 2), new Position(1, -2), new Position(1, 2), };

    public static final Position[] KING_OFFSETS =
            {new Position(-1, -1), new Position(-1, 1), new Position(1, -1), new Position(1, 1),
                    new Position(0, -1), new Position(0, 1), new Position(-1, 0), new Position(1, 0) };

    public final int X;
    public final int Y;

    private Position(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public byte to1D(){
        return (byte)(this.X + this.Y*8);
    }

    /**
     * Checks if passed coordinates are valid
     * @return true if x and y are between 0 and 7
     */
    private static boolean isValid(int x, int y) {
        if (x< 0 || x > 7 || y < 0 || y > 7)
            return false;

        return true;
    }

    public static Position from1D(int position){

        return get(position%8, position/8);
    }

    /**
     * Returns position for desired coordinates(if such exist)
     * Otherwise returns null
     * @param x - X coordinate
     * @param y - Y coordinate
     * @return Position || null
     */
    public static Position get(int x, int y){
        if(isValid(x,y))
            return positions[y][x];
        else
            return null;
    }

    public static Position withOffsets(Position position, int x, int y){

        return get(position.X+x,position.Y+ y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (X != position.X) return false;
        return Y == position.Y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    @Override
    public int hashCode() {
        int result = X;
        result = 31 * result + Y;
        return result;
    }


    public int compareTo(Position o) {
        // Konvertovanje iz 2D pozicije u 1D
        int thisSum = X * 8 + Y;
        int otherSum = o.X * 8 + o.Y;
        if(thisSum == otherSum)
            return 0;
        if(thisSum < otherSum)
            return -1;

        return 1;
    }
}
