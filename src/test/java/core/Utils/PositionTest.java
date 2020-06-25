package core.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Filip on 11/28/2017.
 */
public class PositionTest {
    @Test
    public void to1D() throws Exception {

        int expected = 11;
        int actual = Position.get(3,1).to1D();


        int expected1 = 62;
        int actual1 = Position.get(6,7).to1D();

        int expected2 = 0;
        int actual2 = Position.get(0,0).to1D();

        assertEquals(expected,actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void from1D() throws Exception {

        Position expected = Position.get(3,1);
        Position actual = Position.from1D(11);

        Position expected1 = Position.get(6,7);
        Position actual1 = Position.from1D(62);

        Position expected2 = Position.get(0,0);
        Position actual2 = Position.from1D(0);

        assertEquals(expected,actual);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);

    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void withOffsets() throws Exception {
    }

}