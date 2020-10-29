package Controller;

import Model.BitrateCalc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Brandon Brassfield, 10/25/2020
 */
class GenericBitTest {

    BitrateCalc bitCalc = new BitrateCalc();

    @Test
    void getSizeTypeFromString() {
        assertEquals(GenericBit.sizeType.BIT, bitCalc.getSizeTypeFromString("BITS"));
        assertEquals(GenericBit.sizeType.BIT, bitCalc.getSizeTypeFromString("BIT/S"));
        assertEquals(GenericBit.sizeType.KILOBIT, bitCalc.getSizeTypeFromString("KILOBITS"));
        assertEquals(GenericBit.sizeType.KILOBIT, bitCalc.getSizeTypeFromString("KBIT/S"));
        assertEquals(GenericBit.sizeType.MEGABIT, bitCalc.getSizeTypeFromString("MEGABITS"));
        assertEquals(GenericBit.sizeType.MEGABIT, bitCalc.getSizeTypeFromString("MBIT/S"));
        assertEquals(GenericBit.sizeType.GIGABIT, bitCalc.getSizeTypeFromString("GIGABITS"));
        assertEquals(GenericBit.sizeType.GIGABIT, bitCalc.getSizeTypeFromString("GBIT/S"));
        assertEquals(GenericBit.sizeType.TERABIT, bitCalc.getSizeTypeFromString("TERABITS"));
        assertEquals(GenericBit.sizeType.TERABIT, bitCalc.getSizeTypeFromString("TBIT/S"));
        assertEquals(GenericBit.sizeType.BYTE, bitCalc.getSizeTypeFromString("BYTES"));
        assertEquals(GenericBit.sizeType.BYTE, bitCalc.getSizeTypeFromString("B/S"));
        assertEquals(GenericBit.sizeType.KILOBYTE, bitCalc.getSizeTypeFromString("KILOBYTES"));
        assertEquals(GenericBit.sizeType.KILOBYTE, bitCalc.getSizeTypeFromString("KB/S"));
        assertEquals(GenericBit.sizeType.MEGABYTE, bitCalc.getSizeTypeFromString("MEGABYTES"));
        assertEquals(GenericBit.sizeType.MEGABYTE, bitCalc.getSizeTypeFromString("MB/S"));
        assertEquals(GenericBit.sizeType.GIGABYTE, bitCalc.getSizeTypeFromString("GIGABYTES"));
        assertEquals(GenericBit.sizeType.GIGABYTE, bitCalc.getSizeTypeFromString("GB/S"));
        assertEquals(GenericBit.sizeType.TERABYTE, bitCalc.getSizeTypeFromString("TERABYTES"));
        assertEquals(GenericBit.sizeType.TERABYTE, bitCalc.getSizeTypeFromString("TB/S"));
        assertEquals(GenericBit.sizeType.UNKNOWN, bitCalc.getSizeTypeFromString("BANANA"));
    }

    @Test
    void getTimeTypeFromString() {
        assertEquals(GenericBit.timeType.SECOND, bitCalc.getTimeTypeFromString("SECOND"));
        assertEquals(GenericBit.timeType.MINUTE, bitCalc.getTimeTypeFromString("MINUTE"));
        assertEquals(GenericBit.timeType.HOUR, bitCalc.getTimeTypeFromString("HOUR"));
        assertEquals(GenericBit.timeType.DAY, bitCalc.getTimeTypeFromString("DAY"));
        assertEquals(GenericBit.timeType.WEEK, bitCalc.getTimeTypeFromString("WEEK"));
        assertEquals(GenericBit.timeType.MONTH, bitCalc.getTimeTypeFromString("MONTH"));
        assertEquals(GenericBit.timeType.YEAR, bitCalc.getTimeTypeFromString("YEAR"));
        assertEquals(GenericBit.timeType.UNKNOWN, bitCalc.getTimeTypeFromString("BANANA"));
    }
}