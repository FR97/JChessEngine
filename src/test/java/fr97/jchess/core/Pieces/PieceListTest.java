package fr97.jchess.core.Pieces;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Filip on 12/2/2017.
 */
public class PieceListTest {
    @Test
    public void add() throws Exception {
        PieceList pieces = new PieceList();

        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(34, PieceType.PAWN, PieceColor.BLACK));
        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(34, PieceType.PAWN, PieceColor.BLACK));
        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(34, PieceType.PAWN, PieceColor.BLACK));
        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.BLACK));
        pieces.add(new Piece(34, PieceType.PAWN, PieceColor.BLACK));
        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(34, PieceType.PAWN, PieceColor.BLACK));
        pieces.add(new Piece(34, PieceType.PAWN, PieceColor.BLACK));
        String error = "";

        try{
            pieces.add(new Piece(0, PieceType.QUEEN, PieceColor.WHITE));
        }
        catch(RuntimeException e)
        {
            error = e.getMessage();
        }
        assertEquals("You can't have more than 16 pieces", error);

    }

    @Test
    public void getKing() throws Exception {
        PieceList pieces = new PieceList();

        Piece king = new Piece(5, PieceType.KING, PieceColor.WHITE);
        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(king);
        pieces.add(new Piece(12, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(32, PieceType.BISHOP, PieceColor.WHITE));

        assertEquals(king, pieces.getKing());

    }

    @Test
    public void remove() throws Exception {
        PieceList pieces = new PieceList();

        Piece pawn = new Piece(12, PieceType.PAWN, PieceColor.BLACK);
        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(pawn);
        pieces.add(new Piece(22, PieceType.QUEEN, PieceColor.WHITE));

        pieces.remove(pawn);

        assertEquals(3, pieces.size());
    }

    @Test
    public void size() throws Exception {

        PieceList pieces = new PieceList();


        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(62, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(22, PieceType.QUEEN, PieceColor.WHITE));
        pieces.add(new Piece(12, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(32, PieceType.BISHOP, PieceColor.WHITE));

        assertEquals(5, pieces.size());

    }

    @Test
    public void iterator() throws Exception {

        PieceList pieces = new PieceList();


        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(5, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(10, PieceType.QUEEN, PieceColor.WHITE));
        pieces.add(new Piece(15, PieceType.KNIGHT, PieceColor.WHITE));
        pieces.add(new Piece(20, PieceType.BISHOP, PieceColor.WHITE));
/*
        Iterator<Piece> pieceIterator = pieces.iterator();
        while (pieceIterator.hasNext()){
            Piece p =pieceIterator.next();
            p = p.withPosition(p.position);
        }

        assertTrue(pieces.getPieceWithPosition(0).isMoved());
        assertTrue(pieces.getPieceWithPosition(5).isMoved());
        assertTrue(pieces.getPieceWithPosition(10).isMoved());
        assertTrue(pieces.getPieceWithPosition(15).isMoved());
        assertTrue(pieces.getPieceWithPosition(20).isMoved());
*/
    }

    @Test
    public void forEach() throws Exception {
        PieceList pieces = new PieceList();


        pieces.add(new Piece(0, PieceType.ROOK, PieceColor.WHITE));
        pieces.add(new Piece(5, PieceType.BISHOP, PieceColor.WHITE));
        pieces.add(new Piece(10, PieceType.QUEEN, PieceColor.WHITE));
        pieces.add(new Piece(15, PieceType.KNIGHT, PieceColor.WHITE));
        pieces.add(new Piece(20, PieceType.BISHOP, PieceColor.WHITE));
/*
        pieces.forEach( p -> {
            p = p.withPosition(p.position+1);
        });

        assertNotNull(pieces.getPieceWithPosition(1));
        assertNotNull(pieces.getPieceWithPosition(6));
        assertNotNull(pieces.getPieceWithPosition(11));
        assertNotNull(pieces.getPieceWithPosition(16));
        assertNotNull(pieces.getPieceWithPosition(21));
*/
    }

}