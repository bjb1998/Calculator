package Model;

import Controller.GenericBit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Brandon Brassfield, 10/25/2020
 */
class BitTypeTest {

    BitType bt = new BitType(BitType.Type.SIZE, 0, "UNKNOWN");

    @Test
    void convertFromByte() {
        assertEquals(5, bt.convertFromByte(5, GenericBit.sizeType.BYTE));
        assertEquals(5, bt.convertFromByte(5e3, GenericBit.sizeType.KILOBYTE));
        assertEquals(5, bt.convertFromByte(5e6, GenericBit.sizeType.MEGABYTE));
        assertEquals(5, bt.convertFromByte(5e9, GenericBit.sizeType.GIGABYTE));
        assertEquals(5, bt.convertFromByte(5e12, GenericBit.sizeType.TERABYTE));
        assertEquals(5, bt.convertFromByte(0.625, GenericBit.sizeType.BIT));
        assertEquals(5, bt.convertFromByte(625, GenericBit.sizeType.KILOBIT));
        assertEquals(5, bt.convertFromByte(625000, GenericBit.sizeType.MEGABIT));
        assertEquals(5, bt.convertFromByte(625000000, GenericBit.sizeType.GIGABIT));
        assertEquals(5, bt.convertFromByte(0.625e12, GenericBit.sizeType.TERABIT));
    }

    @Test
    void toPerMonth() {
        assertEquals(13396320, bt.toPerMonth(5, GenericBit.timeType.SECOND));
        assertEquals(223272, bt.toPerMonth(5, GenericBit.timeType.MINUTE));
        assertEquals(3721.2, bt.toPerMonth(5, GenericBit.timeType.HOUR));
        assertEquals(155.05, bt.toPerMonth(5, GenericBit.timeType.DAY));
        assertEquals(22.15, bt.toPerMonth(5, GenericBit.timeType.WEEK));
        assertEquals(5, bt.toPerMonth(5, GenericBit.timeType.MONTH));
        assertEquals(1, bt.toPerMonth(12, GenericBit.timeType.YEAR));
    }

    @Test
    void toByte() {
        assertEquals(5, bt.toByte(5, GenericBit.sizeType.BYTE));
        assertEquals(5e3, bt.toByte(5, GenericBit.sizeType.KILOBYTE));
        assertEquals(5e6, bt.toByte(5, GenericBit.sizeType.MEGABYTE));
        assertEquals(5e9, bt.toByte(5, GenericBit.sizeType.GIGABYTE));
        assertEquals(5e12, bt.toByte(5, GenericBit.sizeType.TERABYTE));
        assertEquals(0.625, bt.toByte(5, GenericBit.sizeType.BIT));
        assertEquals(625, bt.toByte(5, GenericBit.sizeType.KILOBIT));
        assertEquals(625000, bt.toByte(5, GenericBit.sizeType.MEGABIT));
        assertEquals(625000000, bt.toByte(5, GenericBit.sizeType.GIGABIT));
        assertEquals(0.625e12, bt.toByte(5, GenericBit.sizeType.TERABIT));
    }
}