package core;

import core.Moves.Move;
import core.Moves.MoveType;
import core.Utils.Position;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.*;

/**
 * Created by Filip on 11/19/2017.
 */
public class GameTest {

    @Test
    public void testCastling(){
        /*
        Game g = new Game();

        g.makeMove(Position.get(0,1), Position.get(0,3), MoveType.NORMAL);
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



        //g.getPossibleMoves(Position.get(3,6));
    }

    @Test
    public void testAi(){

        Game g = new Game();

        Move m = g.getBestMove(1);
        g.makeMove(m);

        System.out.println("WITH DEPTH 1");
        g.printBestMove();

        m = g.getBestMove(1);
        System.out.println(m);
        g.makeMove(m);
        m = g.getBestMove(1);
        System.out.println(m);
        g.makeMove(m);
        m = g.getBestMove(1);
        System.out.println(m);
        g.makeMove(m);

        System.out.println("WITH DEPTH 3");

        m = g.getBestMove(3);
        System.out.println(m);
        g.makeMove(m);

        m = g.getBestMove(3);
        System.out.println(m);
        g.makeMove(m);

        System.out.println("WITH DEPTH 5");
        Instant start = Instant.now();
        m = g.getBestMove(5);
        System.out.println(m);
        g.makeMove(m);
        Instant end = Instant.now();
        System.out.println("Time to calculate: " +Duration.between(start, end));

        /*
        System.out.println("WITH DEPTH 5");
        Instant start1 = Instant.now();
        m = g.getBestMove(5);
        System.out.println(m);
        g.makeMove(m);
        Instant end1 = Instant.now();
        System.out.println("Time to calculate: " +Duration.between(start1, end1));*/



    }


}