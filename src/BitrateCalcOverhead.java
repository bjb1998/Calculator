/**BitrateCalcOverhead.java
 * Contains the initialization and generic methods for the Bitrate Calculator.
 * Enums are used for the problem type and speed/size type for conversions
 * Many of the methods are carried over from the Generic.java class, but some massive changes needed to be made
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Scanner;

public abstract class BitrateCalcOverhead {

    //-----PARAMETERS-----//

    //UNKNOWN is for default cases
    enum sizeType {BIT, KILOBIT, MEGABIT, GIGABIT, TERABIT, BYTE, KILOBYTE, MEGABYTE, GIGABYTE, TERABYTE, UNKNOWN}
    enum speedType {BIT, KBIT, MBIT, GBIT, TBIT, UNKNOWN}
    enum timeType {SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR, UNKNOWN}

    //amount of seconds in a month, day, etc... used for the hosting bandwidth calculator since the one we're working
    // off of only deals in months
    final double SECONDS_IN_MONTH = 2.628e+6;
    final double[] BYTE_SCALES = {10e3,10e6,10e9,10e12}; //For small conversions between kilo, mega, giga, etc...

    //Used for the Data conversions, prints them in set order
    static final LinkedHashMap<Integer, sizeType> unitScales = new LinkedHashMap<Integer, sizeType>(){{
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

    enum ProblemType{
        CONV_DATA, UP_TIME, WEB_BAND, HOST_BAND;
    }

    //init PrintWriter
    private PrintWriter pw;

    //Initialization
    void init(){
        Scanner console = new Scanner(System.in);
        String input;

        FileWriter fw = null;
        try {
            fw = new FileWriter("Calculations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        pw = new PrintWriter(fw);
        pw.println("Output file created Successfully!");

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

    //Save output String to text
    void printToTxt(String result){
        Scanner console = new Scanner(System.in);
        String input;

        System.out.print("Save result? (Y/N) ");

        while(console.hasNext()) {
            input = console.next();

            if (input.equalsIgnoreCase("Y")) {
                pw.println(result);
                pw.flush();
                System.out.println("Save successful");
                break;
            }else if(input.equalsIgnoreCase("N")){
                break;
            }else{
                System.out.println("Try again");
            }
        }
    }

    //Menu Text
    abstract void printMenuText();

    //If any error is detected in an exception
    void printError(){
        System.out.println("Invalid input.");
    }

    abstract void calculate(ProblemType type);

    //-----GET ENUMS/OTHERS FROM INPUT-----//

    /**@return speedType get it based on console input*/
    speedType getSpeedType(Scanner console){
        System.out.println("Enter Unit: \n" +
                "1 - Bit/s\n" +
                "2 - Kilobit/s\n" +
                "3 - Megabit/s\n" +
                "4 - Gigabit/s\n" +
                "5 - Terabit/s");
        int n = getInt(console);

        switch (n) {
            case 1:
                return speedType.BIT;
            case 2:
                return speedType.KBIT;
            case 3:
                return speedType.MBIT;
            case 4:
                return speedType.GBIT;
            case 5:
                return speedType.TBIT;
            default:
                return speedType.UNKNOWN;
        }
    }

    /**@return SizeType get it based on console input*/
    sizeType getSizeType(Scanner console){
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
        int n = getInt(console);
        switch(n){
            case 1:
                return sizeType.BIT;
            case 2:
                return sizeType.KILOBIT;
            case 3:
                return sizeType.MEGABIT;
            case 4:
                return sizeType.GIGABIT;
            case 5:
                return sizeType.TERABIT;
            case 6:
                return sizeType.BYTE;
            case 7:
                return sizeType.KILOBYTE;
            case 8:
                return sizeType.MEGABYTE;
            case 9:
                return sizeType.GIGABYTE;
            case 0:
                return sizeType.TERABYTE;
            default:
                return sizeType.UNKNOWN;
        }
    }

    /**@return timeType get it based on console input*/
    timeType getTime(Scanner console){
        System.out.println("Enter Time Unit: \n" +
                "1 - Per Second\n" +
                "2 - Per Minute\n" +
                "3 - Per Hour\n" +
                "4 - Per Day\n" +
                "5 - Per Week\n" +
                "6 - Per Month\n" +
                "7 - Per Year");
        int n = getInt(console);

        switch(n) {
            case 1:
                return timeType.SECOND;
            case 2:
                return timeType.MINUTE;
            case 3:
                return timeType.HOUR;
            case 4:
                return timeType.DAY;
            case 5:
                return timeType.WEEK;
            case 6:
                return timeType.MONTH;
            case 7:
                return timeType.YEAR;
            default:
                return timeType.UNKNOWN;
        }
    }

    /**@return int baned off console input*/
    int getInt(Scanner console){
        return console.nextInt();
    }

    boolean getSizeToSpeed(Scanner console) {
        System.out.println("1 - Usage to Bandwidth \n" +
                "2 - Bandwidth to Usage");

        while (console.hasNextInt()) {
            int n = getInt(console);
            if (n == 1) {
                return true;
            } else if (n == 2) {
                return false;
            } else {
                System.out.println("Try again");
            }
        }
        return false;
    }

    //-----GET ENUM FROM STRING-----//
    //-----USED IN FILE IO ONLY-----//

    /**@return speedType get it based on String input*/
    speedType getSpeedTypeFromString(String s){
        switch(s){
            case "BIT/S":
                return speedType.BIT;
            case "KBIT/S":
                return speedType.KBIT;
            case "MBIT/S":
                return speedType.MBIT;
            case "GBIT/S":
                return speedType.GBIT;
            case "TBIT/S":
                return speedType.TBIT;
            default:
                return speedType.UNKNOWN;

        }
    }

    /**@return sizeType get it based on String input*/
    sizeType getSizeTypeFromString(String s){
        switch(s){
            case "BITS":
                return sizeType.BIT;
            case "KILOBITS":
                return sizeType.KILOBIT;
            case "MEGABITS":
                return sizeType.MEGABIT;
            case "GIGABITS":
                return sizeType.GIGABIT;
            case "TERABITS":
                return sizeType.TERABIT;
            case "BYTES":
                return sizeType.BYTE;
            case "KILOBYTES":
                return sizeType.KILOBYTE;
            case "MEGABYTES":
                return sizeType.MEGABYTE;
            case "GIGABYTES":
                return sizeType.GIGABYTE;
            case "TERABYTES":
                return sizeType.TERABYTE;
            default:
                return sizeType.UNKNOWN;
        }
    }

    /**@return timeType get it based on String input*/
    timeType getTimeTypeFromString(String s){
        switch(s){
            case "SECOND":
                return timeType.SECOND;
            case "MINUTE":
                return timeType.MINUTE;
            case "HOUR":
                return timeType.HOUR;
            case "DAY":
                return timeType.DAY;
            case "WEEK":
                return timeType.WEEK;
            case "MONTH":
                return timeType.MONTH;
            case "YEAR":
                return timeType.YEAR;
            default:
                return timeType.UNKNOWN;
        }
    }
}
