package core.Moves;

import java.util.Stack;

/**
 * Created by Filip on 11/19/2017.
 */
public class MoveStack {

    private Stack<Move> moves;

    public MoveStack(){
        moves = new Stack<>();
    }

    public void pushMove(Move m){
        moves.add(m);
    }

    public Move popMove(){
        return moves.pop();
    }

    public Move peekMove(){
        return moves.peek();
    }

}
