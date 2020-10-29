package Model;

import Controller.GenericBit;

import java.util.Scanner;

/**
 * Created by Brandon Brassfield, 10/22/2020
 */
public class BitType extends Controller.GenericBit {

    //The three types of inputs for this application
    enum Type {SIZE, TIME, RAW_UNIT}

    //Types have been added to help generify the types.
    sizeType mySizeType;
    timeType myTimeType;
    protected Scanner console = new Scanner(System.in);

    protected double rawInput;
    protected double rawValueBits;
    protected double rawValueBytes;

    public BitType(Type myType) {
        switch (myType) {
            case SIZE:
                getSizeData(console);
                break;
            case TIME:
                getTime(console);
                break;
            case RAW_UNIT:
                getSizeUnit(console);
                break;
        }
    }

    public BitType(Type myType, int n, String s){
        if(myType == Type.TIME){
            this.rawInput = n;
            this.myTimeType = getTimeTypeFromString(s);
        }else if(myType == Type.SIZE){
            this.rawInput = n;
            this.mySizeType = getSizeTypeFromString(s);
            this.rawValueBytes = toByte(n, mySizeType);
            this.rawValueBits = rawValueBytes / 8;
        }else{ //If RAW_UNIT
            this.mySizeType = getSizeTypeFromString(s);
        }
    }

    /**@return SizeType get it based on console input
     * @param console the console to get user input*/
     void getSizeData(Scanner console) {
        System.out.println("Enter Data Number:");
        rawInput = console.nextDouble();
        getSizeUnit(console);
        rawValueBytes = toByte(rawInput, mySizeType);
        rawValueBits = rawValueBytes * 8;

    }

    /**@return void simply just the size unit, used for some BitrateCalc methods
     * @param console the console to get user input*/
     void getSizeUnit(Scanner console){
        int selection;
        System.out.println("Enter Unit: \n" +
                "1 - Bit\n" +
                "2 - Kilobit\n" +
                "3 - Megabit\n" +
                "4 - Gigabit\n" +
                "5 - Terabit\n" +
                "6 - Byte\n" +
                "7 - Kilobyte\n" +
                "8 - Megabyte\n" +
                "9 - Gigabyte\n" +
                "0 - Terabyte");
        selection = console.nextInt();
        switch (selection) {
            case 1:
                mySizeType = sizeType.BIT;
                break;
            case 2:
                mySizeType = sizeType.KILOBIT;
                break;
            case 3:
                mySizeType = sizeType.MEGABIT;
                break;
            case 4:
                mySizeType = sizeType.GIGABIT;
                break;
            case 5:
                mySizeType = sizeType.TERABIT;
                break;
            case 6:
                mySizeType = sizeType.BYTE;
                break;
            case 7:
                mySizeType = sizeType.KILOBYTE;
                break;
            case 8:
                mySizeType = sizeType.MEGABYTE;
                break;
            case 9:
                mySizeType = sizeType.GIGABYTE;
                break;
            case 0:
                mySizeType = sizeType.TERABYTE;
                break;
            default:
                mySizeType = sizeType.UNKNOWN;
                break;
        }
    }

    /**@return timeType get it based on console input
     * @param console the console to get user input*/
    void getTime(Scanner console) {
        int selection;

        System.out.println("Enter Time Value: ");
        rawInput = console.nextDouble();

        System.out.println("Enter Time Unit: \n" +
                "1 - Per Second\n" +
                "2 - Per Minute\n" +
                "3 - Per Hour\n" +
                "4 - Per Day\n" +
                "5 - Per Week\n" +
                "6 - Per Month\n" +
                "7 - Per Year");
        selection = console.nextInt();

        switch (selection) {
            case 1: myTimeType = timeType.SECOND;
                break;
            case 2: myTimeType = timeType.MINUTE;
                break;
            case 3: myTimeType = timeType.HOUR;
                break;
            case 4: myTimeType = timeType.DAY;
                break;
            case 5: myTimeType = timeType.WEEK;
                break;
            case 6: myTimeType = timeType.MONTH;
                break;
            case 7: myTimeType = timeType.YEAR;
                break;
            default: myTimeType = timeType.UNKNOWN;
                break;
        }
    }

    /**@return sizeType the current objects sizeType*/
    sizeType getMySizeType(){
        return mySizeType;
    }

    /**@return double convert a number from bytes to a given unit type
     * @param byteNum the number to convert in bytes
     * @param resultType the desired unit to convert to*/
    protected double convertFromByte(double byteNum, GenericBit.sizeType resultType){
        double bitNum = byteNum * 8;
        switch(resultType){
            case BIT:
                return bitNum;
            case KILOBIT:
                return (bitNum / BYTE_SCALES[0]);
            case MEGABIT:
                return (bitNum / BYTE_SCALES[1]);
            case GIGABIT:
                return (bitNum / BYTE_SCALES[2]);
            case TERABIT:
                return (bitNum / BYTE_SCALES[3]);
            case BYTE:
                return byteNum;
            case KILOBYTE:
                return (byteNum / BYTE_SCALES[0]);
            case MEGABYTE:
                return (byteNum / BYTE_SCALES[1]);
            case GIGABYTE:
                return (byteNum / BYTE_SCALES[2]);
            case TERABYTE:
                return (byteNum / BYTE_SCALES[3]);
            default:
                return 0;
        }
    }

    /**@return double convert a number from one time frequency to per month
     * @param time the time frequency to convert from
     * @param currentType the desired unit to convert from*/
    protected double toPerMonth(double time, GenericBit.timeType currentType){
        switch(currentType){
            case SECOND:
                return time * 2679264.0;
            case MINUTE:
                return time * 44654.4;
            case HOUR:
                return time * 744.24;
            case DAY:
                return time * 31.01;
            case WEEK:
                return time * 4.43; //4.43 is the average amount of weeks/month
            case MONTH:
                return time;
            case YEAR:
                return time / 12.0;
            default:
                return 0.0;
        }
    }

    /**@return double convert an associated unit to bytes, the base unit
     * @param dataNum the raw input to generate the final byte number
     * @param dataType the data unit to convert from*/
    double toByte(double dataNum, sizeType dataType) {
        switch (dataType) {
            case BIT:
                return dataNum / 8;
            case KILOBIT:
                return ((dataNum / 8) * BYTE_SCALES[0]);
            case MEGABIT:
                return ((dataNum / 8) * BYTE_SCALES[1]);
            case GIGABIT:
                return ((dataNum / 8) * BYTE_SCALES[2]);
            case TERABIT:
                return ((dataNum / 8) * BYTE_SCALES[3]);
            case BYTE:
                return dataNum;
            case KILOBYTE:
                return (dataNum * BYTE_SCALES[0]);
            case MEGABYTE:
                return (dataNum * BYTE_SCALES[1]);
            case GIGABYTE:
                return (dataNum * BYTE_SCALES[2]);
            case TERABYTE:
                return (dataNum * BYTE_SCALES[3]);
            default:
                return 0;
        }

    }

    //This could have been refactored better, these methods are just useless, but are needed
    //for the inheritance
    @Override
    protected void printMenuText() {}

    @Override
    protected void calculate(ProblemType type) {}

}
