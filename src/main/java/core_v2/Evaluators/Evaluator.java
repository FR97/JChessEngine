package core_v2.Evaluators;

/**
 * Created by Filip on 11/20/2017.
 */
public interface Evaluator<T> {

    double evaluate(T chessboard, int depth);
}
