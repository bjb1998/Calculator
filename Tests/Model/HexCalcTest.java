package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Brandon Brassfield, 10/25/2020
 */
class HexCalcTest {

    HexCalc hCalc = new HexCalc();

    @Test
    void isType() {
        assertTrue(hCalc.isType("0"));
        assertTrue(hCalc.isType("1"));
        assertTrue(hCalc.isType("4A"));
        assertTrue(hCalc.isType("1C5A"));
        assertTrue(hCalc.isType("5C"));
        assertTrue(hCalc.isType("A"));
        assertTrue(hCalc.isType("9A"));
        assertTrue(hCalc.isType("7"));
        assertFalse(hCalc.isType("T5"));
        assertFalse(hCalc.isType("Z"));
        assertFalse(hCalc.isType("banana"));
        assertFalse(hCalc.isType("$@#$@#"));
        assertFalse(hCalc.isType("WRG"));
        assertFalse(hCalc.isType("5W"));
    }

    @Test
    void toType() {
        assertEquals("0",hCalc.toType(0));
        assertEquals("1",hCalc.toType(1));
        assertEquals("F",hCalc.toType(15));
        assertEquals("89",hCalc.toType(137));
        assertEquals("38",hCalc.toType(56));
        assertEquals("5E",hCalc.toType(94));
        assertEquals("400",hCalc.toType(1024));
        assertEquals("61C",hCalc.toType(1564));
        assertEquals("2676",hCalc.toType(9846));
        assertEquals("FFFF",hCalc.toType(65535));
    }

    @Test
    void toDecimal() {
        assertEquals("0",hCalc.toDecimal("0"));
        assertEquals("1",hCalc.toDecimal("1"));
        assertEquals("15",hCalc.toDecimal("F"));
        assertEquals("137",hCalc.toDecimal("89"));
        assertEquals("56",hCalc.toDecimal("38"));
        assertEquals("94",hCalc.toDecimal("5E"));
        assertEquals("1024",hCalc.toDecimal("400"));
        assertEquals("1564",hCalc.toDecimal("61C"));
        assertEquals("9846",hCalc.toDecimal("2676"));
        assertEquals("65535",hCalc.toDecimal("FFFF"));
    }
}