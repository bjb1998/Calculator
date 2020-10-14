/**
 * A repository of Tests for the Generic class, the and BinaryCalc class.
 */
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class BinaryCalcTest {
    String[] inputTestBin = {"0101010101","+","01010101"};
    String[] inputTestDecimal = {"50", "+", "50"};
    String inputBinary = "0101010101";
    int inputDecimal = 42;
    private BinaryCalc bCalc = new BinaryCalc();

    @Test
    void isNegative() {
        assertTrue(bCalc.isNegative(-1));
        assertFalse(bCalc.isNegative(1));
        assertFalse(bCalc.isNegative(0));
    }

    @Test
    void checkIfValidDecimalEquation() {
        assertTrue(bCalc.checkIfValidDecimalEquation(inputTestDecimal));
    }

    @Test
    void isOperand() {
        assertTrue(bCalc.isOperand("+"));
        assertTrue(bCalc.isOperand("-"));
        assertTrue(bCalc.isOperand("*"));
        assertTrue(bCalc.isOperand("/"));
        assertFalse(bCalc.isOperand("a"));
        assertFalse(bCalc.isOperand("4"));
        assertFalse(bCalc.isOperand("fish"));
        assertFalse(bCalc.isOperand("!"));
    }

    @Test
    void isNegativeString() {
        assertTrue(bCalc.isNegativeString("-010101010100"));
        assertTrue(bCalc.isNegativeString("-42"));
        assertFalse(bCalc.isNegativeString("42"));
        assertFalse(bCalc.isNegativeString("10101010101"));
    }

    @Test
    void checkIfValidBinaryEquation() {
        assertTrue(bCalc.checkIfValidBinaryEquation(inputTestBin));
    }

    @Test
    void isBinary() {
        assertTrue(bCalc.isBinary("010100101101010"));
        assertTrue(bCalc.isBinary("11101110"));
        assertTrue(bCalc.isBinary("10"));
        assertTrue(bCalc.isBinary("1"));
        assertTrue(bCalc.isBinary("0"));
        assertFalse(bCalc.isBinary("2"));
        assertFalse(bCalc.isBinary("15"));
        assertFalse(bCalc.isBinary("54975"));
        assertFalse(bCalc.isBinary("fish"));
    }

    @Test
    void toBinary() {
        assertEquals("01000", bCalc.toBinary(8, false));
        assertEquals("01100", bCalc.toBinary(12, false));
    }

    @Test
    void toDecimal() {
        assertEquals("100", bCalc.toDecimal("01100100"));
        assertEquals("12", bCalc.toDecimal("1100"));
    }

    @Test
    void calcResult() {
        assertEquals("0110101010", bCalc.calcResult(inputTestBin[0], inputTestBin[1], inputTestBin[2]));
        assertEquals("01100100", bCalc.calcResult(bCalc.toBinary(50, false), "+",
                bCalc.toBinary(50, false)));
    }
}