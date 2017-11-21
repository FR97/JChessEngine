package core_v2.Chessboards;


import core_v2.Moves.Move;
import core_v2.Pieces.PieceColor;
import core_v2.Players.Player;
import core_v2.Utils.Position;

import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public interface IBoard<E>{



    /**
     *  Returns piece at passed position if it exists
     * @param x - column
     * @param y - row
     * @return Piece at that position || null
     */
    E getPiece(int x, int y);

    /**
     * Returns piece at passed position if it exists
     * @param position  desired position
     * @return Piece at that position || null
     */
    E getPiece(Position position);

    /**
     *
     * @return all white pieces on board
     */
    List<E> getWhitePieces();

    /**
     *
     * @return all black pieces on board
     */
    List<E> getBlackPieces();

    /**
     *
     * @return Color currently on move
     */
    PieceColor getOnMove();

    /**
     *  Checks passed move if it is valid,
     *  if it is it makes move and return true
     *  otherwise returns false
     * @param move to make
     * @return true if move succeeded || false
     */
    boolean makeMove(Move move);

    /**
     * Toggles color that is currently on move
     * Should be called only AFTER move is made
     */
    void toggleOnMove();

    /**
     * @return Player
     */
    Player getWhitePlayer();

    /**
     * @return Player
     */
    Player getBlackPlayer();

    /**
     * Must be implemented in such way to put pieces at their starting positions
     */
    void initializeBoard();

    /**
     * Creates chessboard from FEN string passed
     * if string is valid
     * @param fenString
     * @return true if it succeeds  || false
     * @throws IllegalArgumentException if fenString is invalid
     */
    boolean tryConvertFromFen(String fenString) throws IllegalArgumentException;

    /**
     * Converts board to FEN(Forsyth–Edwards Notation)
     * @return string representing current board state in FEN
     */
    String convertToFen();

    /**
     * Converts to 2D array with pieces in format - Color+PieceType
     * For example WP for white pawn or BN for black knight
     * Empty squares are marked empty string ""
     * @return
     */
    String[][] convertTo2DArray();

    /**
     * Starts at 1 and increments every time black makes a move
     * @return count of full moves from start of game
     */
    int fullMovesCount();

    /**
     *  Important for applying 50 moves rule
     * @return number of consecutive moves without capture or pawn move
     */
    int halfMovesClock();

    /**
     * Is enpassant possible in current chessboard state?
     * @return true only if last move was pawn double jump || false
     */
    boolean isEnpassantPossible();

    /**
     * Is white queenside castling possible in current chessboard state?
     * @return true || false
     */
    boolean isWhiteQueenSideCastlingPossible();

    /**
     * Is white kingside castling possible in current chessboard state?
     * @return true || false
     */
    boolean isWhiteKingSideCastlingPossible();

    /**
     * Is black queenside castling possible in current chessboard state?
     * @return true || false
     */
    boolean isBlackQueenSideCastlingPossible();

    /**
     * Is black kingside castling possible in current chessboard state?
     * @return true || false
     */
    boolean isBlackKingSideCastlingPossible();

    void printChessboard();

}
