package fr97.jchess.core.evaluator;

import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.piece.Piece;
import fr97.jchess.core.piece.PieceType;
import fr97.jchess.core.player.Player;
import fr97.jchess.core.util.PieceList;

public class AdvancedEvaluator implements Evaluator {

    private static final int CASTLING = 65;
    private static final int CHECK = 40;
    private static final int CHECK_MATE = 10000;
    private static final int ISOLATED_PAWN = -15;
    private static final int DOUBLE_BISHOP = 45;

    public AdvancedEvaluator() {

    }

    @Override
    public int evaluate(Chessboard chessboard, int depth) {

        return calculateScore(chessboard.getWhitePieces(), chessboard.getWhitePlayer(), chessboard.getBlackPlayer(), depth)
                - calculateScore(chessboard.getBlackPieces(), chessboard.getBlackPlayer(), chessboard.getWhitePlayer(), depth);
    }

    private int calculateScore(PieceList pieces, Player player, Player opponent, int depth) {

        return pieceValue(pieces) + castling(player) + doubleBishop(pieces) + opponentCheckMated(opponent, depth) + opponentInCheck(opponent) + mobility(player) + isolatedPawns(pieces);
    }

    private int doubleBishop(PieceList pieces){
        int bishopCounter = 0;
        for(Piece p : pieces){
            if(p.type == PieceType.BISHOP)
                bishopCounter++;
            if(bishopCounter == 2)
                return DOUBLE_BISHOP;
        }
        return 0;
    }


    private int isolatedPawns(PieceList pieces){

        int sum = 0;
        for(Piece p : pieces){
            if(p.type == PieceType.PAWN){
                Piece pawnSupport = pieces.get(p.position + p.color.value()*9);
                if(pawnSupport == null || pawnSupport.type != PieceType.PAWN)
                    sum += ISOLATED_PAWN;
                pawnSupport = pieces.get(p.position + p.color.value()*7);
                if(pawnSupport == null || pawnSupport.type != PieceType.PAWN)
                    sum += ISOLATED_PAWN;
            }
        }
        return sum;
    }

    private int pieceValue(PieceList pieces) {
        int sum = 0;
        for (Piece p : pieces) {
            sum += p.type.value()+PiecePositionAnalyzer.getPiecePositionValue(p);
        }
        return sum;
    }

    private int opponentInCheck(Player player) {
        return player.isInCheck() ? CHECK : 0;
    }

    private int opponentCheckMated(Player player, int depth) {
        return player.isCheckMated() ? CHECK_MATE * depthBonus(depth) : 0;
    }

    private int depthBonus(int depth) {
        return depth == 0 ? 1 : 100 * depth;
    }

    private int mobility(Player player) {
        return player.getLegalMoves().size();
    }

    private int castling(Player player) {

        return player.isCastled() ? CASTLING : 0;
    }

}
