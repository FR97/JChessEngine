import core.Game;
import core.Moves.Move;
import core.Moves.MoveType;
import core.Utils.CharBoard;
import core.Utils.Position;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by Filip on 11/18/2017.
 */



public class Main {

    public static void main(String[] args) {

        Game g = new Game();
        Move m;

        m = g.getBestMove(5);
        System.out.println(m);
        g.makeMove(m);
        m = g.getBestMove(5);
        System.out.println(m);
        g.makeMove(m);
        System.out.println("WITH DEPTH 6");
        Instant start1 = Instant.now();

        for (int i = 0; i < 25; i++){
            m = g.getBestMove(6);
            System.out.println(m);
            g.makeMove(m);
        }



        Instant end1 = Instant.now();
        System.out.println("Time to calculate: " + Duration.between(start1, end1));

    }


}
