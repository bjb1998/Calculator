/**
 * A repository of Tests for the HexCalc class.
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexCalcTest {

    private HexCalc hCalc = new HexCalc();

    @Test
    void isHex() {
        assertTrue(hCalc.isHex("A"));
        assertTrue(hCalc.isHex("1"));
        assertTrue(hCalc.isHex("34"));
        assertTrue(hCalc.isHex("BB"));
        assertTrue(hCalc.isHex("4A"));
        assertTrue(hCalc.isHex("FF"));
        assertTrue(hCalc.isHex("FFFF"));
        assertFalse(hCalc.isHex("fish"));
        assertFalse(hCalc.isHex("!"));
        assertFalse(hCalc.isHex("Hello"));
        assertFalse(hCalc.isHex(".!@#$"));
    }

    @Test
    void checkIfValidHexEquation() {
        String[] s = {"A","+","A"};
        assertTrue(hCalc.checkIfValidHexEquation(s));
        s = new String[]{"4", "-", "A"};
        assertTrue(hCalc.checkIfValidHexEquation(s));
        s = new String[]{"F4", "*", "A6"};
        assertTrue(hCalc.checkIfValidHexEquation(s));
        s = new String[]{"1", "/", "1"};
        assertTrue(hCalc.checkIfValidHexEquation(s));
        s = new String[]{"F4", "fish", "A6"};
        assertFalse(hCalc.checkIfValidHexEquation(s));
        s = new String[]{"twelve", "+", "A6"};
        assertFalse(hCalc.checkIfValidHexEquation(s));
        s = new String[]{"F4", "-", "!"};
        assertFalse(hCalc.checkIfValidHexEquation(s));
    }

    @Test
    void toDecimal() {
        assertEquals("0", hCalc.toDecimal("0"));
        assertEquals("45", hCalc.toDecimal("2D"));
        assertEquals("1285", hCalc.toDecimal("505"));
        assertEquals("4852", hCalc.toDecimal("12F4"));
        assertEquals("12", hCalc.toDecimal("C"));
        assertEquals("15", hCalc.toDecimal("F"));
        assertEquals("94", hCalc.toDecimal("5E"));
        assertEquals("782", hCalc.toDecimal("30E"));
        assertEquals("154", hCalc.toDecimal("9A"));
        assertEquals("8888", hCalc.toDecimal("22B8"));
    }

    @Test
    void toHex() {
        assertEquals("E", hCalc.toHex(14));
        assertEquals("1", hCalc.toHex(1));
        assertEquals("0", hCalc.toHex(0));
        assertEquals("36", hCalc.toHex(54));
        assertEquals("400", hCalc.toHex(1024));
        assertEquals("12F4", hCalc.toHex(4852));
        assertEquals("F", hCalc.toHex(15));
        assertEquals("40", hCalc.toHex(64));
        assertEquals("4E", hCalc.toHex(78));
        assertEquals("1A4", hCalc.toHex(420));
    }

    @Test
    void calcResult() {
        assertEquals("2", hCalc.calcResult("1", "+", "1"));
        assertEquals("1A98", hCalc.calcResult("74", "*", "92"));
        assertEquals("14", hCalc.calcResult("244", "/", "12"));
        assertEquals("22", hCalc.calcResult("205", "-", "171"));
    }
}