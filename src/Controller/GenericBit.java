package Controller;
/**BitrateCalcOverhead.java
 * Contains the initialization and generic methods for the Bitrate Calculator.
 * Enums are used for the problem type and speed/size type for conversions
 * Many of the methods are carried over from the Controller.Generic.java class, but some massive changes needed to be made
 */

import java.util.LinkedHashMap;
import java.util.Scanner;

public abstract class GenericBit extends Generic{

    //-----PARAMETERS-----//
    Scanner console = new Scanner(System.in);

    //UNKNOWN is for default cases
    //size and Speed hae been merged. It adds compatibility between more data types at only minimal refactoring.
    public enum sizeType {BIT, KILOBIT, MEGABIT, GIGABIT, TERABIT, BYTE, KILOBYTE, MEGABYTE, GIGABYTE, TERABYTE, UNKNOWN}
    public enum timeType {SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR, UNKNOWN}
    public enum ProblemType{ CONV_DATA, UP_TIME, WEB_BAND, HOST_BAND }

    //amount of seconds in a month, day, etc... used for the hosting bandwidth calculator since the one we're working
    // off of only deals in months
    protected final double SECONDS_IN_MONTH = 2.628e+6;
    protected final double[] BYTE_SCALES = {10e2,10e5,10e8,10e11}; //For small conversions between kilo, mega, giga, etc.

    //Used for the Data conversions, prints them in set order
    protected static final LinkedHashMap<Integer, sizeType> unitScales = new LinkedHashMap<Integer, sizeType>(){{
        put(0, sizeType.BIT);
        put(1, sizeType.KILOBIT);
        put(2, sizeType.MEGABIT);
        put(3, sizeType.GIGABIT);
        put(4, sizeType.TERABIT);
        put(5, sizeType.BYTE);
        put(6, sizeType.KILOBYTE);
        put(7, sizeType.MEGABYTE);
        put(8, sizeType.GIGABYTE);
        put(9, sizeType.TERABYTE);
    }};

    /**@return void initializes the calculator for bitrate calculation.*/
    protected void init(){
        String input;
        initGeneric(console);

        mainLoop:
        while(console.hasNext()){
            input = console.next();

            switch(input){
                case "0":
                    break mainLoop;
                case "E":
                case "e":
                    calculate(ProblemType.CONV_DATA);
                    break;
                case "R":
                case "r":
                    calculate(ProblemType.UP_TIME);
                    break;
                case "C":
                case "c":
                    calculate(ProblemType.WEB_BAND);
                    break;
                case "V":
                case "v":
                    calculate(ProblemType.HOST_BAND);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    //-----TEXT/CALCULATIONS-----//

    /**@return void print the menu text of the associated calculator*/
    protected abstract void printMenuText();

    /**@return void Calculate the problem based on the enum type from the Controller.Generic class menu
     * @param type the type fo problem that is generated based off user input*/
    protected abstract void calculate(ProblemType type);

    //-----GET ENUM FROM STRING-----//
    //-----USED IN FILE IO ONLY-----//

    /**@return sizeType get it based on String input
     * @param input the string gathered from user input*/
    protected sizeType getSizeTypeFromString(String input){
        switch(input){
            case "BITS": case "BIT/S": return sizeType.BIT;
            case "KILOBITS": case "KBIT/S": return sizeType.KILOBIT;
            case "MEGABITS": case "MBIT/S": return sizeType.MEGABIT;
            case "GIGABITS": case "GBIT/S": return sizeType.GIGABIT;
            case "TERABITS": case "TBIT/S": return sizeType.TERABIT;
            case "BYTES": case "B/S": return sizeType.BYTE;
            case "KILOBYTES": case "KB/S": return sizeType.KILOBYTE;
            case "MEGABYTES": case "MB/S": return sizeType.MEGABYTE;
            case "GIGABYTES": case "GB/S": return sizeType.GIGABYTE;
            case "TERABYTES": case "TB/S": return sizeType.TERABYTE;
            default: return sizeType.UNKNOWN;
        }
    }

    /**@return timeType get it based on String input
     * @param input the string gathered from user input*/
    protected timeType getTimeTypeFromString(String input){
        switch(input){
            case "SECOND": return timeType.SECOND;
            case "MINUTE": return timeType.MINUTE;
            case "HOUR": return timeType.HOUR;
            case "DAY": return timeType.DAY;
            case "WEEK": return timeType.WEEK;
            case "MONTH": return timeType.MONTH;
            case "YEAR": return timeType.YEAR;
            default: return timeType.UNKNOWN;
        }
    }
}
