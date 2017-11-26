package core_v2.Evaluators;



import core_v2.Game;
import core_v2.Pieces.Piece;

import java.util.List;

/**
 * Created by Filip on 11/20/2017.
 */
public class BasicEvaluator{

    private int lastPieceCount;

    public BasicEvaluator(){
        lastPieceCount = 0;
    }

    /**
     * Sabira vrednost svih polja sa belim figurama pa od toga oduzima vrednost svih sa crnim
     * Tako da ako trazimo najbolji potez za crnog onda trazimo sto manji broj
     * A ako trazimo za belog onda gledamo sto veci
     * @param chessboard - trenutna sahovska tabla
     * @return odnos vrednosti crnih i belih polja(0 - jednaki, <0 vodi crni, >0 vodi beli
     */


}
