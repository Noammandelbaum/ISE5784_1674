package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link primitives.Util}.
 */
public class UtilTests {

    /**
     * Test method for {@link primitives.Util#isZero(double)}.
     */
    @Test
    void testIsZero() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a number that is exactly zero
        assertTrue(Util.isZero(0.0), "isZero() failed for 0.0");

        // TC02: Test for a number that is very close to zero
        assertTrue(Util.isZero(1e-50), "isZero() failed for a very small positive number");

        // TC03: Test for a number that is not zero
        assertFalse(Util.isZero(1.0), "isZero() failed for 1.0");

        // =============== Boundary Values Tests ==================
        // TC10: Test for a number that is on the boundary of being considered zero
        assertTrue(Util.isZero(1e-40), "isZero() failed for a number on the boundary");
    }

    /**
     * Test method for {@link primitives.Util#alignZero(double)}.
     */
    @Test
    void testAlignZero() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for a number that is exactly zero
        assertEquals(0.0, Util.alignZero(0.0), "alignZero() failed for 0.0");

        // TC02: Test for a number that is very close to zero (less than threshold)
        assertEquals(0.0, Util.alignZero(1e-50), "alignZero() failed for a very small positive number");

        // TC03: Test for a number that is not zero
        assertEquals(1.0, Util.alignZero(1.0), "alignZero() failed for 1.0");

        // =============== Boundary Values Tests ==================
        // TC10: Test for a number that is on the boundary of being considered zero
        assertEquals(0.0, Util.alignZero(1e-40), "alignZero() failed for a number on the boundary");
    }

    /**
     * Test method for {@link primitives.Util#compareSign(double, double)}.
     */
    @Test
    void testCompareSign() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for two positive numbers
        assertTrue(Util.compareSign(1.0, 2.0), "compareSign() failed for two positive numbers");

        // TC02: Test for two negative numbers
        assertTrue(Util.compareSign(-1.0, -2.0), "compareSign() failed for two negative numbers");

        // TC03: Test for one positive and one negative number
        assertFalse(Util.compareSign(1.0, -2.0), "compareSign() failed for a positive and a negative number");

        // =============== Boundary Values Tests ==================
        // TC10: Test for one zero and one positive number
        assertFalse(Util.compareSign(0.0, 1.0), "compareSign() failed for zero and positive number");

        // TC11: Test for one zero and one negative number
        assertFalse(Util.compareSign(0.0, -1.0), "compareSign() failed for zero and negative number");
    }

    /**
     * Test method for {@link primitives.Util#random(double, double)}.
     */
    @Test
    void testRandom() {
        // ============ Equivalence Partitions Tests ==============
        double min = 1.0;
        double max = 10.0;

        // TC01: Test that the random number is within the range
        for (int i = 0; i < 100; i++) {
            double result = Util.random(min, max);
            assertTrue(result >= min && result < max, "random() generated a number out of range");
        }
    }
}
