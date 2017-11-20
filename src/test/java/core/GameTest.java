package core;

import core.Moves.MoveType;
import core.Utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Filip on 11/19/2017.
 */
public class GameTest {

    @Test
    public void testCastling(){
        //Game g = new Game();
        /*
        g.makeMove(Position.get(4,6), Position.get(4,5), MoveType.NORMAL);
        g.printPossibleMoves(Position.get(6,7));
        g.makeMove(Position.get(6,7), Position.get(7,5), MoveType.NORMAL);
        g.printPossibleMoves(Position.get(5,7));
        g.makeMove(Position.get(5,7), Position.get(3,3), MoveType.NORMAL);
        g.makeMove(Position.get(1,7), Position.get(0,5), MoveType.NORMAL);
        g.makeMove(Position.get(2,7), Position.get(4,4), MoveType.NORMAL);
        g.makeMove(Position.get(3,7), Position.get(4,6), MoveType.NORMAL);

        g.printPossibleMoves(Position.get(4,7));
        g.makeMove(Position.get(4,7), Position.get(6,7),MoveType.CASTLING);*/
    }

    @Test
    public void testKingInDanger(){
        Game g = new Game();

        g.makeMove(Position.get(4,6), Position.get(4,5), MoveType.NORMAL);
        g.makeMove(Position.get(2,1), Position.get(2,2), MoveType.NORMAL);
        g.makeMove(Position.get(7,6), Position.get(7,5), MoveType.NORMAL);
        g.makeMove(Position.get(3,0), Position.get(0,3), MoveType.NORMAL);
        g.getPossibleMoves(Position.get(3,6));
    }



}