package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link primitives.Double3}.
 */
public class Double3Tests {

    /**
     * Test method for {@link primitives.Double3#Double3(double, double, double)}.
     */
    @Test
    void testConstructors() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test creating Double3 with different values
        Double3 double3 = new Double3(1.0, 2.0, 3.0);
        assertEquals(1.0, double3.d1, "Constructor or getter d1 failed");
        assertEquals(2.0, double3.d2, "Constructor or getter d2 failed");
        assertEquals(3.0, double3.d3, "Constructor or getter d3 failed");

        // TC02: Test creating Double3 with same values
        Double3 singleValueDouble3 = new Double3(4.0);
        assertEquals(4.0, singleValueDouble3.d1, "Constructor or getter d1 failed with single value");
        assertEquals(4.0, singleValueDouble3.d2, "Constructor or getter d2 failed with single value");
        assertEquals(4.0, singleValueDouble3.d3, "Constructor or getter d3 failed with single value");
    }

    /**
     * Test method for {@link primitives.Double3#equals(Object)}.
     */
    @Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test equality of two identical Double3 objects
        Double3 double3_1 = new Double3(1.0, 2.0, 3.0);
        Double3 double3_2 = new Double3(1.0, 2.0, 3.0);
        assertEquals(double3_1, double3_2, "equals() failed for identical objects");

        // TC02: Test equality of two different Double3 objects
        Double3 double3_3 = new Double3(3.0, 2.0, 1.0);
        assertNotEquals(double3_1, double3_3, "equals() failed for different objects");
    }

    /**
     * Test method for {@link primitives.Double3#hashCode()}.
     */
    @Test
    void testHashCode() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test hash code for identical Double3 objects
        Double3 double3_1 = new Double3(1.0, 2.0, 3.0);
        Double3 double3_2 = new Double3(1.0, 2.0, 3.0);
        assertEquals(double3_1.hashCode(), double3_2.hashCode(), "hashCode() failed for identical objects");
    }

    /**
     * Test method for {@link primitives.Double3#toString()}.
     */
    @Test
    void testToString() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test string representation of Double3
        Double3 double3 = new Double3(1.0, 2.0, 3.0);
        assertEquals("(1.0,2.0,3.0)", double3.toString(), "toString() failed");
    }

    /**
     * Test method for {@link primitives.Double3#add(Double3)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test addition of two Double3 objects
        Double3 double3_1 = new Double3(1.0, 2.0, 3.0);
        Double3 double3_2 = new Double3(4.0, 5.0, 6.0);
        Double3 result = double3_1.add(double3_2);
        assertEquals(new Double3(5.0, 7.0, 9.0), result, "add() failed");
    }

    /**
     * Test method for {@link primitives.Double3#subtract(Double3)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test subtraction of two Double3 objects
        Double3 double3_1 = new Double3(4.0, 5.0, 6.0);
        Double3 double3_2 = new Double3(1.0, 2.0, 3.0);
        Double3 result = double3_1.subtract(double3_2);
        assertEquals(new Double3(3.0, 3.0, 3.0), result, "subtract() failed");
    }

    /**
     * Test method for {@link primitives.Double3#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test scaling of Double3 by a scalar
        Double3 double3 = new Double3(1.0, 2.0, 3.0);
        Double3 result = double3.scale(2.0);
        assertEquals(new Double3(2.0, 4.0, 6.0), result, "scale() failed");
    }

    /**
     * Test method for {@link primitives.Double3#reduce(double)}.
     */
    @Test
    void testReduce() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test reduction of Double3 by a scalar
        Double3 double3 = new Double3(2.0, 4.0, 6.0);
        Double3 result = double3.reduce(2.0);
        assertEquals(new Double3(1.0, 2.0, 3.0), result, "reduce() failed");
    }

    /**
     * Test method for {@link primitives.Double3#product(Double3)}.
     */
    @Test
    void testProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test product of two Double3 objects
        Double3 double3_1 = new Double3(1.0, 2.0, 3.0);
        Double3 double3_2 = new Double3(4.0, 5.0, 6.0);
        Double3 result = double3_1.product(double3_2);
        assertEquals(new Double3(4.0, 10.0, 18.0), result, "product() failed");
    }

    /**
     * Test method for {@link primitives.Double3#lowerThan(double)}.
     */
    @Test
    void testLowerThanDouble() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test lowerThan with a scalar value
        Double3 double3 = new Double3(1.0, 2.0, 3.0);
        assertTrue(double3.lowerThan(4.0), "lowerThan() failed when all values are less than scalar");
        assertFalse(double3.lowerThan(2.5), "lowerThan() failed when not all values are less than scalar");
    }

    /**
     * Test method for {@link primitives.Double3#lowerThan(Double3)}.
     */
    @Test
    void testLowerThanDouble3() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test lowerThan with another Double3 object
        Double3 double3_1 = new Double3(1.0, 2.0, 3.0);
        Double3 double3_2 = new Double3(4.0, 5.0, 6.0);
        assertTrue(double3_1.lowerThan(double3_2), "lowerThan() failed when all values are less than other Double3");
        assertFalse(double3_2.lowerThan(double3_1), "lowerThan() failed when not all values are less than other Double3");
    }
}
