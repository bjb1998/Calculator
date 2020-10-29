package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Brandon Brassfield, 10/25/2020
 */
class BinaryCalcTest {

    BinaryCalc bCalc = new BinaryCalc();

    @Test
    void isType() {
        assertTrue(bCalc.isType("1010101011111"));
        assertTrue(bCalc.isType("1"));
        assertTrue(bCalc.isType("0"));
        assertTrue(bCalc.isType("11111111111"));
        assertTrue(bCalc.isType("0000000001"));
        assertFalse(bCalc.isType("42"));
        assertFalse(bCalc.isType("7"));
        assertFalse(bCalc.isType("banana"));
        assertFalse(bCalc.isType(""));
        assertFalse(bCalc.isType("!@#$%^"));
    }

    @Test
    void toType() {
        assertEquals("0101010", bCalc.toType(42));
        assertEquals("01", bCalc.toType(1));
        assertEquals("0", bCalc.toType(0));
        assertEquals("01011101", bCalc.toType(93));
        assertEquals("010000000", bCalc.toType(128));
        assertEquals("0111111", bCalc.toType(63));
        assertEquals("011000000111001", bCalc.toType(12345));
    }

    @Test
    void toDecimal() {
        assertEquals("42",bCalc.toDecimal("101010"));
        assertEquals("1",bCalc.toDecimal("1"));
        assertEquals("0",bCalc.toDecimal("0"));
        assertEquals("93",bCalc.toDecimal("1011101"));
        assertEquals("128",bCalc.toDecimal("10000000"));
        assertEquals("63",bCalc.toDecimal("111111"));
        assertEquals("12345",bCalc.toDecimal("11000000111001"));
    }
}