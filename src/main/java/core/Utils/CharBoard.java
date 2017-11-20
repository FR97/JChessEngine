package core.Utils;

/**
 * Created by Filip on 11/19/2017.
 */
public class CharBoard {

    /**
     *  Matrica koja predstavlja sahovsku tablu<br>
     *  Omogucuje proveru mogucih poteza<br>
     *  Znacenje: 0-Prazno polje | 1,7-Pioni | 2,8-Topovi | 3,9-Skakac | 4,10 - Lovac | 5,11 - Kraljica | 6,12 - Kralj
    */

    private char [][]chessBoard = {
        {'R','N', 'B', 'Q','K' ,'B', 'N', 'R'},
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {'p', 'p', 'p','p', 'p', 'p', 'p', 'p'},
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}};


    public CharBoard(){

    }
    public CharBoard(char[][] chessBoard){
        this.chessBoard = chessBoard;
    }

    public char getPiece(final int x,final int y){
        return chessBoard[y][x];
    }

}
