package Model; /**Model.BitrateCalc.java
 * Contains the conversion methods, menu methods, and the calculation strings
 * depending on the type of problem. the type of interface had to be changes massively compared to the other two
 * calculators :(
 */

import Controller.GenericBit;

import java.util.*;
public class BitrateCalc extends Controller.GenericBit {

    //-----MENU-----//

    //Initialization
    public void init(){
        printMenuText();
        super.init();
    }

    @Override
    protected void calculate(GenericBit.ProblemType type) {
        String result; //Final output String
        Scanner console = new Scanner(System.in);
        BitType firstBit;
        BitType secondBit;

        try {
            switch (type) {
                case CONV_DATA:
                    firstBit = new BitType(BitType.Type.SIZE);
                    result = calcDataUnits(firstBit);
                    System.out.println(result);
                    printToTxt(result);
                    break;
                case UP_TIME:
                    System.out.println("Enter Size: ");
                    firstBit = new BitType(BitType.Type.SIZE);
                    System.out.println("Enter Speed: ");
                    secondBit = new BitType(BitType.Type.SIZE);
                    result = calcUpload(firstBit, secondBit);
                    System.out.println(result);
                    printToTxt(result);
                    break;
                case WEB_BAND:
                    System.out.println("Enter Average Page Size: ");
                    firstBit = new BitType(BitType.Type.SIZE);
                    System.out.println("Enter Average Page Views: ");
                    secondBit = new BitType(BitType.Type.TIME);
                    System.out.print("Enter Redundancy Factor:");
                    double redundancy = console.nextDouble();
                    result = calcWebsite(firstBit, secondBit, redundancy);
                    System.out.println(result);
                    printToTxt(result);
                    break;
                case HOST_BAND:
                    System.out.println("Enter 0 on the associated item for size to speed, or speed to size");
                    firstBit = new BitType(BitType.Type.SIZE);
                    secondBit = new BitType(BitType.Type.SIZE);
                    result = calcHosting(firstBit, secondBit);
                    System.out.println(result);
                    printToTxt(result);
                    break;
            }
        }catch(Exception E){
            printError();
        }

    }

    //-----MENU + INPUT / OUTPUT TEXT-----//

    /**@return the data conversions for all data types, like in the given calculator
     * @param results the pre-calculated conversions from calcDataUnits()*/
    private String dataUnitResult(String[] results){
        return ( results[0] + " Bits (b)" + "\n" +
                 results[1] + " Kilobits(kb)" + "\n" +
                 results[2] + " Megabits(mb)" + "\n" +
                 results[3] + " Gigabits(gb)" + "\n" +
                 results[4] + " Terabits(gb)" + "\n" +
                 results[5] + " Bytes(B)" + "\n" +
                 results[6] + " Kilobytes(KB)" + "\n" +
                 results[7] + " Megabytes(MB)" + "\n" +
                 results[8] + " Gigabytes(GB)" + "\n" +
                 results[9] + " Terabytes(TB)");
    }

    protected void printMenuText(){
        System.out.println("Input E to convert Data Units, R to calculate Download/Upload Time \n" +
                "Input C to calculate Website Bandwidth, V to calculate Hosting Bandwidth\n" +
                "Input 0 to quit: ");
    }

    //-----CALCULATIONS-----//

    /**@return String the monthly hosting usage based on it was size to speed, or speed to size
     * , as in the calculator given
     * @param monthlyUsage The monthly data usage given by the user, if 0, goes from speed to size.
     *                     Need a better implementation of this once the GUI part rolls around :(
     * @param monthlySpeed The monthly speed usage given by user*/
    String calcHosting(BitType monthlyUsage, BitType monthlySpeed){
        double monthlyBytes = monthlyUsage.rawValueBytes;
        double speedBytes = monthlySpeed.rawValueBytes;

        if(monthlyUsage.rawInput != 0){
            double totalByteSpeed = monthlyBytes / SECONDS_IN_MONTH;
            double finalSpeed = monthlySpeed.convertFromByte(totalByteSpeed, monthlySpeed.mySizeType);
            return ( monthlyUsage.rawInput + " (" + monthlyUsage.getMySizeType() + ") per month is equivalent to "
                    + finalSpeed + " " + monthlySpeed.getMySizeType() + "/s");
        }else{
            double totalByteSize = speedBytes * SECONDS_IN_MONTH;
            double finalSize = monthlyUsage.convertFromByte(totalByteSize, monthlyUsage.mySizeType);
            return ( finalSize + " (" + monthlyUsage.getMySizeType() + ") per month is equivalent to "
                    + monthlySpeed.rawInput + " " + monthlySpeed.getMySizeType() + "/s");
        }
    }

    /**@return String the data units after converting the data unit to bytes, utilizes dataUnitResult for final string
     * @param byteNum the unit type, and number given by the user.*/
    String calcDataUnits(BitType byteNum){
        String[] results = new String[GenericBit.unitScales.size()];

        for(int i = 0; i < results.length; i++){
            results[i] = Double.toString(byteNum.convertFromByte(byteNum.rawValueBytes, GenericBit.unitScales.get(i)));
        }

        return dataUnitResult(results);
    }

    /**@return String the download time based on the speed given, and file size.
     * Minutes / seconds supported only because im lazy
     * @param data The size of a file specified by the user.
     * @param speed The speed given by the user (X amount per [Time interval])*/
    String calcUpload(BitType data, BitType speed){
        double totalSeconds = data.rawValueBytes / speed.rawValueBytes;
        int minutes = (int)totalSeconds / 60;
        int seconds = (int)totalSeconds % 60;

        return ("Download/Upload time needed is: ~" + minutes + " minutes " + seconds + " seconds");

    }

    /*Results here can only be approximated based on the original calculator, but they are close enough to me
    Results always end in Gigabytes so they hard coded to be this way, like in the original calculator*/
    /**@return String the approximate speed and bandwidth needed for website based off user input
     * @param averageSize the average size of a webpage, in its associated unit.
     * @param time Time and frequency defined by user.
     * @param redundancyFactor a simple double to count the redundancy factor*/
    String calcWebsite(BitType averageSize, BitType time, double redundancyFactor){
        double resultBytes = time.toPerMonth(time.rawInput, time.myTimeType) * averageSize.rawValueBytes * redundancyFactor;
        double resultMegabits = (resultBytes * 8.0) / BYTE_SCALES[1];
        double resultMegaBitsPerSecond = resultMegabits / SECONDS_IN_MONTH;
        double resultGigabytes = resultBytes / BYTE_SCALES[2];
        return "The bandwidth needed is " + resultMegaBitsPerSecond + " Mbit/s or " + resultGigabytes + " GB per month";
    }

}
