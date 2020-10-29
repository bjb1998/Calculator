package Controller;

import Model.BinaryCalc;
import Model.HexCalc;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Brandon Brassfield, 10/25/2020
 */
class GenericEquationTest {

    BinaryCalc bCalc = new BinaryCalc();
    HexCalc hCalc = new HexCalc();
    Scanner mockConsole = new Scanner(System.in);

    @Test
    void generateInputs() {
        mockConsole = new Scanner("4+4");
        assertArrayEquals(new String[] {"4","+","4"}, bCalc.generateInputs(mockConsole));
        mockConsole = new Scanner("01010101/11100000");
        assertArrayEquals(new String[] {"01010101","/","11100000"}, bCalc.generateInputs(mockConsole));
        mockConsole = new Scanner("4A-5C");
        assertArrayEquals(new String[] {"4A","-","5C"}, bCalc.generateInputs(mockConsole));
        mockConsole = new Scanner("number1*number2");
        assertArrayEquals(new String[] {"number1","*","number2"}, bCalc.generateInputs(mockConsole));
        mockConsole = new Scanner("4A+0101010111");
        assertArrayEquals(new String[] {"4A","+","0101010111"}, bCalc.generateInputs(mockConsole));
        mockConsole = new Scanner("4%4");
        assertArrayEquals(new String[] {"4%4"}, bCalc.generateInputs(mockConsole));
        mockConsole = new Scanner("banana$banana");
        assertArrayEquals(new String[] {"banana$banana"}, bCalc.generateInputs(mockConsole));
    }

    @Test
    void calcResult() {
        assertEquals("0101010", bCalc.calcResult(new String[] {"10010","+","11000"}));
        assertEquals("01111011", bCalc.calcResult(new String[] {"1111101","-","10"}));
        assertEquals("0110111", bCalc.calcResult(new String[] {"1011","*","101"}));
        assertEquals("0100", bCalc.calcResult(new String[] {"10000","/","100"}));
        assertEquals("A6",hCalc.calcResult(new String[] {"4A","+","5C"}));
        assertEquals("2",hCalc.calcResult(new String[] {"A6","-","A4"}));
        assertEquals("1A",hCalc.calcResult(new String[] {"2","*","D"}));
        assertEquals("D",hCalc.calcResult(new String[] {"1A","/","2"}));
    }

    @Test
    void isDecimal() {
        assertTrue(bCalc.isDecimal("42"));
        assertTrue(bCalc.isDecimal("1"));
        assertTrue(bCalc.isDecimal("100"));
        assertTrue(bCalc.isDecimal("12345"));
        assertTrue(bCalc.isDecimal("101101001")); //Technically its 101,101,001
        assertFalse(bCalc.isDecimal("hello"));
        assertFalse(bCalc.isDecimal("01110101011101"));
        assertFalse(bCalc.isDecimal("0123456789")); //Decimals typically aren't written with 0 in the front
        assertFalse(bCalc.isDecimal("4A"));
        assertFalse(bCalc.isDecimal(""));
        assertFalse(bCalc.isDecimal("4A5b%3Sabanana5##321"));
    }

    @Test
    void isValidDecimalEquation() {
        assertTrue(bCalc.IsValidDecimalEquation(new String[] {"5","+","1"}));
        assertTrue(bCalc.IsValidDecimalEquation(new String[] {"105","-","42"}));
        assertTrue(bCalc.IsValidDecimalEquation(new String[] {"111","*","222"}));
        assertTrue(bCalc.IsValidDecimalEquation(new String[] {"12","/","34"}));
        assertFalse(bCalc.IsValidDecimalEquation(new String[] {"010101010","+","4"}));
        assertFalse(bCalc.IsValidDecimalEquation(new String[] {"4A","*","8"}));
        assertFalse(bCalc.IsValidDecimalEquation(new String[] {"4","banana","4"}));
        assertFalse(bCalc.IsValidDecimalEquation(new String[] {"banana","+","banana"}));
    }

    @Test
    void isValidTypeEquation(){
        assertTrue(bCalc.isValidTypeEquation(new String[] {"11011011","+","01011"}));
        assertTrue(bCalc.isValidTypeEquation(new String[] {"1","-","0"}));
        assertTrue(bCalc.isValidTypeEquation(new String[] {"1011101","*","0011"}));
        assertTrue(bCalc.isValidTypeEquation(new String[] {"001101","/","110101"}));
        assertTrue(hCalc.isValidTypeEquation(new String[] {"1","+","3"}));
        assertTrue(hCalc.isValidTypeEquation(new String[] {"4A","-","5C"}));
        assertTrue(hCalc.isValidTypeEquation(new String[] {"B","*","12B"}));
        assertTrue(hCalc.isValidTypeEquation(new String[] {"D","/","4"}));
        assertFalse(bCalc.isValidTypeEquation( new String[] {"42","+","101011"}));
        assertFalse(bCalc.isValidTypeEquation( new String[] {"110110","/","125"}));
        assertFalse(bCalc.isValidTypeEquation( new String[] {"10101","banana","101011"}));
        assertFalse(hCalc.isValidTypeEquation( new String[] {"4A","!","5C"}));
    }

    @Test
    void isOperand() {
        assertTrue(bCalc.isOperand("+"));
        assertTrue(bCalc.isOperand("-"));
        assertTrue(bCalc.isOperand("*"));
        assertTrue(bCalc.isOperand("/"));
        assertFalse(bCalc.isOperand("$"));
        assertFalse(bCalc.isOperand("12345"));
        assertFalse(bCalc.isOperand("banana"));
        assertFalse(bCalc.isOperand("!"));
    }
}