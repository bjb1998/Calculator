/**
 * A repository of Tests for the BitrateCalcOverhead class, the and BitrateCalc class.
 */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitrateCalcTest {

    BitrateCalc bitCalc = new BitrateCalc();

    @Test
    void getSpeedTypeFromString() {
        assertEquals(BitrateCalcOverhead.speedType.BIT, bitCalc.getSpeedTypeFromString("BIT/S"));
        assertEquals(BitrateCalcOverhead.speedType.KBIT, bitCalc.getSpeedTypeFromString("KBIT/S"));
        assertEquals(BitrateCalcOverhead.speedType.MBIT, bitCalc.getSpeedTypeFromString("MBIT/S"));
        assertEquals(BitrateCalcOverhead.speedType.GBIT, bitCalc.getSpeedTypeFromString("GBIT/S"));
        assertEquals(BitrateCalcOverhead.speedType.TBIT, bitCalc.getSpeedTypeFromString("TBIT/S"));
    }

    @Test
    void getSizeTypeFromString() {
        assertEquals(BitrateCalcOverhead.sizeType.BIT, bitCalc.getSizeTypeFromString("BITS"));
        assertEquals(BitrateCalcOverhead.sizeType.KILOBIT, bitCalc.getSizeTypeFromString("KILOBITS"));
        assertEquals(BitrateCalcOverhead.sizeType.MEGABIT, bitCalc.getSizeTypeFromString("MEGABITS"));
        assertEquals(BitrateCalcOverhead.sizeType.GIGABIT, bitCalc.getSizeTypeFromString("GIGABITS"));
        assertEquals(BitrateCalcOverhead.sizeType.TERABIT, bitCalc.getSizeTypeFromString("TERABITS"));
        assertEquals(BitrateCalcOverhead.sizeType.BYTE, bitCalc.getSizeTypeFromString("BYTES"));
        assertEquals(BitrateCalcOverhead.sizeType.KILOBYTE, bitCalc.getSizeTypeFromString("KILOBYTES"));
        assertEquals(BitrateCalcOverhead.sizeType.MEGABYTE, bitCalc.getSizeTypeFromString("MEGABYTES"));
        assertEquals(BitrateCalcOverhead.sizeType.GIGABYTE, bitCalc.getSizeTypeFromString("GIGABYTES"));
        assertEquals(BitrateCalcOverhead.sizeType.TERABYTE, bitCalc.getSizeTypeFromString("TERABYTES"));
    }

    @Test
    void getTimeTypeFromString() {
        assertEquals(BitrateCalcOverhead.timeType.SECOND, bitCalc.getTimeTypeFromString("SECOND"));
        assertEquals(BitrateCalcOverhead.timeType.MINUTE, bitCalc.getTimeTypeFromString("MINUTE"));
        assertEquals(BitrateCalcOverhead.timeType.HOUR, bitCalc.getTimeTypeFromString("HOUR"));
        assertEquals(BitrateCalcOverhead.timeType.DAY, bitCalc.getTimeTypeFromString("DAY"));
        assertEquals(BitrateCalcOverhead.timeType.WEEK, bitCalc.getTimeTypeFromString("WEEK"));
        assertEquals(BitrateCalcOverhead.timeType.MONTH, bitCalc.getTimeTypeFromString("MONTH"));
        assertEquals(BitrateCalcOverhead.timeType.YEAR, bitCalc.getTimeTypeFromString("YEAR"));
        assertEquals(BitrateCalcOverhead.timeType.UNKNOWN, bitCalc.getTimeTypeFromString("SOMETHING_ELSE"));
    }

    @Test
    void calcHosting() {
    }

    @Test
    void calcDataUnits() {
        String resultTest = "4.0E12 Bits (b)\n" +
                "4.0E8 Kilobits(kb)\n" +
                "400000.0 Megabits(mb)\n" +
                "400.0 Gigabits(gb)\n" +
                "0.4 Terabits(gb)\n" +
                "5.0E11 Bytes(B)\n" +
                "5.0E7 Kilobytes(KB)\n" +
                "50000.0 Megabytes(MB)\n" +
                "50.0 Gigabytes(GB)\n" +
                "0.05 Terabytes(TB)";

        assertEquals(resultTest, bitCalc.calcDataUnits(50, BitrateCalcOverhead.sizeType.GIGABYTE));
    }

    @Test
    void calcUpload() {
        assertEquals("Download/Upload time needed is: ~" + 133 + " minutes " + 20 + " seconds",
                bitCalc.calcUpload(500.0, BitrateCalcOverhead.sizeType.MEGABYTE,
                        500.0, BitrateCalcOverhead.speedType.KBIT));
    }

    @Test
    void calcWebsite() {
        assertEquals("The bandwidth needed is " + 0.35399543378995435 + " Mbit/s or " + 116.2875 + " GB per month",
                bitCalc.calcWebsite(5000, 500, 1.5,
                        BitrateCalcOverhead.timeType.DAY, BitrateCalc.sizeType.KILOBYTE));
    }
}