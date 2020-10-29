package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Brandon Brassfield, 10/25/2020
 */
class BitrateCalcTest {

    BitrateCalc bitCalc = new BitrateCalc();
    BitType type1 = new BitType(BitType.Type.SIZE, 0, "UNKNOWN");
    BitType type2 = new BitType(BitType.Type.SIZE, 0, "UNKNOWN");

    String dataResult = "4.0E11 Bits (b)\n" +
            "4.0E8 Kilobits(kb)\n" +
            "400000.0 Megabits(mb)\n" +
            "400.0 Gigabits(gb)\n" +
            "0.4 Terabits(gb)\n" +
            "5.0E10 Bytes(B)\n" +
            "5.0E7 Kilobytes(KB)\n" +
            "50000.0 Megabytes(MB)\n" +
            "50.0 Gigabytes(GB)\n" +
            "0.05 Terabytes(TB)";


    String uploadResult = "Download/Upload time needed is: ~133 minutes 20 seconds";

    String webResults = "The bandwidth needed is 0.35399543378995435 Mbit/s or 116.2875 GB per month";

    String hostResultTrue = "500.0 (GIGABYTE) per month is equivalent to 1.5220700152207 MEGABIT/s";
    String hostResultFalse = "147.825 (GIGABYTE) per month is equivalent to 450.0 KILOBIT/s";

    @Test
    void calcHosting() {
        type1 = new BitType(BitType.Type.SIZE, 500, "GIGABYTES");
        type2 = new BitType(BitType.Type.SIZE, 0, "MEGABITS");
        assertEquals(hostResultTrue, bitCalc.calcHosting(type1, type2));
        type1 = new BitType(BitType.Type.SIZE, 0, "GIGABYTES");
        type2 = new BitType(BitType.Type.SIZE, 450, "KILOBITS");
        assertEquals(hostResultFalse, bitCalc.calcHosting(type1, type2));
    }

    @Test
    void calcDataUnits() {
        type1 = new BitType(BitType.Type.SIZE, 50, "GIGABYTES");
        assertEquals(dataResult, bitCalc.calcDataUnits(type1));
    }

    @Test
    void calcUpload() {
        type1 = new BitType(BitType.Type.SIZE, 500, "MEGABYTES");
        type2 = new BitType(BitType.Type.SIZE, 500, "KILOBITS");
        assertEquals(uploadResult, bitCalc.calcUpload(type1, type2));
    }

    @Test
    void calcWebsite() {
        type1 = new BitType(BitType.Type.TIME, 5000, "DAY");
        type2 = new BitType(BitType.Type.SIZE, 500, "KILOBYTES");
        assertEquals(webResults, bitCalc.calcWebsite(type2,type1, 1.5));
    }
}