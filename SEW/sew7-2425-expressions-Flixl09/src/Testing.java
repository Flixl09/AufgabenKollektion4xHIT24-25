import org.junit.Test;
import org.junit.Before;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Testing.
 */
public class Testing {
    private Value a;
    private Value b;
    private Value c;
    private Value d;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        a = new Value(1);
        b = new Value(2);
        c = new Value(3);
        d = new Value(4);

    }

    /**
     * Test max.
     */
    @Test
    public void testMax() {
        Operation max = new Max(a, b, c, d);
        assertEquals(4, max.getValue(), 0.0);
    }

    /**
     * Test max to string.
     */
    @Test
    public void testMaxToString() {
        Operation max = new Max(a, b, c, d);
        assertEquals("max(1.0, 2.0, 3.0, 4.0)", max.toString());
    }

    /**
     * Test max equals.
     */
    @Test
    public void testMaxEquals() {
        Operation max1 = new Max(a, b, c, d);
        Operation max2 = new Max(a, b, c, d);
        assertTrue(max1.equals(max2));
    }

    /**
     * Test min.
     */
    @Test
    public void testMin() {
        Operation min = new Min(a, b, c, d);
        assertEquals(1, min.getValue(), 0.0);
    }

    /**
     * Test min to string.
     */
    @Test
    public void testMinToString() {
        Operation min = new Min(a, b, c, d);
        assertEquals("min(1.0, 2.0, 3.0, 4.0)", min.toString());
    }

    /**
     * Test min equals.
     */
    @Test
    public void testMinEquals() {
        Operation min1 = new Min(a, b, c, d);
        Operation min2 = new Min(a, b, c, d);
        assertTrue(min1.equals(min2));
    }

    /**
     * Test add.
     */
    @Test
    public void testAdd() {
        Operation add = new Add(a, b);
        assertEquals(3, add.getValue(), 0.0);
    }

    /**
     * Test add to string.
     */
    @Test
    public void testAddToString() {
        Operation add = new Add(a, b);
        assertEquals("(1.0 + 2.0)", add.toString());
    }

    /**
     * Test add equals.
     */
    @Test
    public void testAddEquals() {
        Operation add1 = new Add(a, b);
        Operation add2 = new Add(a, b);
        assertTrue(add1.equals(add2));
    }

    /**
     * Test sub.
     */
    @Test
    public void testSub() {
        Operation sub = new Subtract(a, b);
        assertEquals(-1, sub.getValue(), 0.0);
    }

    /**
     * Test sub to string.
     */
    @Test
    public void testSubToString() {
        Operation sub = new Subtract(a, b);
        assertEquals("(1.0 - 2.0)", sub.toString());
    }

    /**
     * Test sub equals.
     */
    @Test
    public void testSubEquals() {
        Operation sub1 = new Subtract(a, b);
        Operation sub2 = new Subtract(a, b);
        assertTrue(sub1.equals(sub2));
    }

    /**
     * Test mul.
     */
    @Test
    public void testMul() {
        Operation mul = new Multi(c, b);
        assertEquals(6, mul.getValue(), 0.0);
    }

    /**
     * Test mul to string.
     */
    @Test
    public void testMulToString() {
        Operation mul = new Multi(c, b);
        assertEquals("(3.0 * 2.0)", mul.toString());
    }

    /**
     * Test mul equals.
     */
    @Test
    public void testMulEquals() {
        Operation mul1 = new Multi(c, b);
        Operation mul2 = new Multi(c, b);
        assertTrue(mul1.equals(mul2));
    }

    /**
     * Test div.
     */
    @Test
    public void testDiv() {
        Operation div = new Div(c, b);
        assertEquals(1.5, div.getValue(), 0.0);
    }

    /**
     * Test div to string.
     */
    @Test
    public void testDivToString() {
        Operation div = new Div(c, b);
        assertEquals("(3.0 / 2.0)", div.toString());
    }

    /**
     * Test div equals.
     */
    @Test
    public void testDivEquals() {
        Operation div1 = new Div(c, b);
        Operation div2 = new Div(c, b);
        assertTrue(div1.equals(div2));
    }

    /**
     * Test pow.
     */
    @Test
    public void testPow() {
        Operation pow = new Potenz(c, b);
        assertEquals(9, pow.getValue(), 0.0);
    }


    /**
     * Test pow to string.
     */
    @Test
    public void testPowToString() {
        Operation pow = new Potenz(c, b);
        assertEquals("(3.0 ^ 2.0)", pow.toString());
    }

    /**
     * Test pow equals.
     */
    @Test
    public void testPowEquals() {
        Operation pow1 = new Potenz(c, b);
        Operation pow2 = new Potenz(c, b);
        assertTrue(pow1.equals(pow2));
    }

    /**
     * Test value.
     */
    @Test
    public void testValue() {
        Value v = new Value(5);
        assertEquals(5, v.getValue(), 0.0);
    }

    /**
     * Test value to string.
     */
    @Test
    public void testValueToString() {
        Value v = new Value(5);
        assertEquals("5.0", v.toString());
    }

    /**
     * Test value equals.
     */
    @Test
    public void testValueEquals() {
        Value v1 = new Value(5);
        Value v2 = new Value(5);
        assertTrue(v1.equals(v2));
    }

    /**
     * Test negate.
     */
    @Test
    public void testNegate() {
        Operation neg = new Negate(a);
        assertEquals(-1, neg.getValue(), 0.0);
    }

    /**
     * Test negate to string.
     */
    @Test
    public void testNegateToString() {
        Operation neg = new Negate(a);
        assertEquals("(-1.0)", neg.toString());
    }

    /**
     * Test recursive.
     */
    @Test
    public void testRecursive() {
        Operation mul = new Multi(a, b);
        Operation div = new Div(c, d);
        Operation add = new Add(mul, div);
        System.out.println(add.toString());
        assertEquals(2.75, add.getValue(), 0.0);
    }

}
