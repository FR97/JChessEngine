package fr97.jchess.core.move;

import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.piece.Piece;
import fr97.jchess.core.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 12/2/2017.
 */
public class MoveGenerator {

    private static final int[] mailbox = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, 0, 1, 2, 3, 4, 5, 6, 7, -1,
        -1, 8, 9, 10, 11, 12, 13, 14, 15, -1,
        -1, 16, 17, 18, 19, 20, 21, 22, 23, -1,
        -1, 24, 25, 26, 27, 28, 29, 30, 31, -1,
        -1, 32, 33, 34, 35, 36, 37, 38, 39, -1,
        -1, 40, 41, 42, 43, 44, 45, 46, 47, -1,
        -1, 48, 49, 50, 51, 52, 53, 54, 55, -1,
        -1, 56, 57, 58, 59, 60, 61, 62, 63, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    private static final int[] mailbox64 = {
        21, 22, 23, 24, 25, 26, 27, 28,
        31, 32, 33, 34, 35, 36, 37, 38,
        41, 42, 43, 44, 45, 46, 47, 48,
        51, 52, 53, 54, 55, 56, 57, 58,
        61, 62, 63, 64, 65, 66, 67, 68,
        71, 72, 73, 74, 75, 76, 77, 78,
        81, 82, 83, 84, 85, 86, 87, 88,
        91, 92, 93, 94, 95, 96, 97, 98
    };

    private static final int[][] offsets = {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {-11, -10, -9, -1, 1, 9, 10, 11},
        {-21, -19, -12, -8, 8, 12, 19, 21},
        {-10, -1, 1, 10, 0, 0, 0, 0},
        {-11, -9, 9, 11, 0, 0, 0, 0},
        {-11, -10, -9, -1, 1, 9, 10, 11},

    };

    public static List<Move> findPossibleMoves(final Piece piece, final Chessboard chessboard) {
        if (piece.type == PieceType.PAWN) {

            return findPossibleMovesForPawn(piece, chessboard);
        }

        int id = piece.type.id();
        List<Move> moves = new ArrayList<>();

        for (int i = 0; (i < 8 && offsets[id][i] != 0); i++) {

            int possible = mailbox[mailbox64[piece.position] + offsets[id][i]];

            if (id > 2) {
                while (possible != -1) {

                    Piece toEat = chessboard.getPiece(possible);

                    if (toEat == null) {
                        moves.add(new Move(chessboard, piece, possible));
                    } else if (piece.color != toEat.color) {
                        moves.add(new CaptureMove(chessboard, piece, toEat));
                        break;
                    } else {
                        break;
                    }
                    possible = mailbox[mailbox64[possible] + offsets[id][i]];
                }
            } else {
                if (possible != -1) {
                    Piece toEat = chessboard.getPiece(possible);
                    if (toEat == null) {
                        moves.add(new Move(chessboard, piece, possible));
                    } else if (piece.color != toEat.color) {
                        moves.add(new CaptureMove(chessboard, piece, toEat));
                    }
                }
            }
        }

        if (piece.type == PieceType.KING)
            addPossibleCastlings(piece, chessboard, moves);

        return moves;
    }

    private static List<Move> addPossibleCastlings(final Piece king, final Chessboard chessboard, final List<Move> moves) {

        if (!king.moved) {
            if (king.color.isWhite()) {
                Piece rook = chessboard.getPiece(63);
                if (rook != null && !rook.moved) {
                    if (chessboard.getPiece(62) == null && chessboard.getPiece(61) == null)
                        moves.add(new CastlingMove(chessboard, king, 62, rook, 61));
                }
                rook = chessboard.getPiece(56);
                if (rook != null && !rook.moved) {
                    if (chessboard.getPiece(57) == null && chessboard.getPiece(58) == null && chessboard.getPiece(59) == null)
                        moves.add(new CastlingMove(chessboard, king, 58, rook, 59));
                }
            } else {
                Piece rook = chessboard.getPiece(7);
                if (rook != null && !rook.moved) {
                    if (chessboard.getPiece(5) == null && chessboard.getPiece(6) == null)
                        moves.add(new CastlingMove(chessboard, king, 6, rook, 5));
                }
                rook = chessboard.getPiece(0);
                if (rook != null && !rook.moved) {
                    if (chessboard.getPiece(1) == null && chessboard.getPiece(2) == null && chessboard.getPiece(3) == null)
                        moves.add(new CastlingMove(chessboard, king, 2, rook, 3));
                }
            }
        }

        return moves;
    }

    private static List<Move> findPossibleMovesForPawn(final Piece pawn, final Chessboard chessboard) {
        List<Move> moves = new ArrayList<>();

        int direction = pawn.color.value() * -1;

        int possible = mailbox[mailbox64[pawn.position] + 10 * direction];
        if (possible != -1) {
            if (chessboard.getPiece(possible) == null) {
                moves.add(new Move(chessboard, pawn, possible));
                // Doubli skok
                possible = mailbox[mailbox64[possible] + 10 * direction];
                if (chessboard.getPiece(possible) == null && !pawn.moved)
                    moves.add(new Move(chessboard, pawn, possible, MoveType.PAWN_DOUBLE_JUMP));
            }
        }

        possible = mailbox[mailbox64[pawn.position] + 10 * direction - 1];
        if (possible != -1) {
            Piece toEat = chessboard.getPiece(possible);
            if (toEat != null && pawn.color != toEat.color) {
                moves.add(new CaptureMove(chessboard, pawn, toEat));
            }
        }
        possible = mailbox[mailbox64[pawn.position] + 10 * direction + 1];
        if (possible != -1) {
            Piece toEat = chessboard.getPiece(possible);
            if (toEat != null && pawn.color != toEat.color) {
                moves.add(new CaptureMove(chessboard, pawn, toEat));
            }
        }

        if (chessboard.isEnpassantPossible()) {
            int enpassantPawnPosition = mailbox[mailbox64[chessboard.getEnpassantPawnPosition()]];
            if (mailbox[mailbox64[pawn.position - 1]] == enpassantPawnPosition) {
                moves.add(new EnpassantMove(chessboard, pawn, mailbox[mailbox64[pawn.position - 1] + 10 * direction],
                    chessboard.getPiece(chessboard.getEnpassantPawnPosition())));
            } else if (mailbox[mailbox64[pawn.position + 1]] == enpassantPawnPosition) {
                moves.add(new EnpassantMove(chessboard, pawn, mailbox[mailbox64[pawn.position + 1] + 10 * direction],
                    chessboard.getPiece(chessboard.getEnpassantPawnPosition())));
            }
        }

        return moves;
    }

}
