/**BitrateCalc.java
 * Contains the conversion methods, menu methods, and the calculation strings
 * depending on the type of problem. the type of interface had to be changes massively compared to the other two
 * calculators :(
 */

import java.util.*;
public class BitrateCalc extends BitrateCalcOverhead{

    enum getter {SIZE, SPEED, REDUNDANCY, PAGE_VIEWS, PAGE_SIZE}

    //-----MENU-----//

    //Initialization
    public void init(){
        printMenuText();
        super.init();
    }

    public void calculate(ProblemType type) {
        String result; //Final output String
        Scanner console = new Scanner(System.in);
        double currentSize, currentSpeed, currentPageViews, currentRedundancy; //Calculations here use doubles for precision
        boolean sizeToSpeed; //For the Hosting bandwidth data
        sizeType currentSizeType;
        speedType currentSpeedType;
        timeType currentTimeType;

        try {
            switch (type) {
                case CONV_DATA:
                    currentSize = getNum(console, getter.SIZE);
                    currentSizeType = getSizeType(console);
                    result = calcDataUnits(currentSize, currentSizeType);
                    System.out.println(result);
                    printToTxt(result);
                    break;
                case UP_TIME:
                    currentSize = getNum(console, getter.SIZE);
                    currentSizeType = getSizeType(console);
                    currentSpeed = getNum(console, getter.SPEED);
                    currentSpeedType = getSpeedType(console);
                    result = calcUpload(currentSize, currentSizeType, currentSpeed, currentSpeedType);
                    System.out.println(result);
                    printToTxt(result);
                    break;
                case WEB_BAND:
                    currentPageViews = getNum(console, getter.PAGE_VIEWS);
                    currentTimeType = getTime(console);
                    currentSize = getNum(console, getter.PAGE_SIZE);
                    currentSizeType = getSizeType(console);
                    currentRedundancy = getNum(console, getter.REDUNDANCY);
                    result = calcWebsite(currentPageViews, currentSize, currentRedundancy, currentTimeType, currentSizeType);
                    System.out.println(result);
                    printToTxt(result);
                    break;
                case HOST_BAND:
                    sizeToSpeed = getSizeToSpeed(console);
                    if(sizeToSpeed){
                        currentSize = getNum(console, getter.SIZE);
                        currentSizeType = getSizeType(console);
                        currentSpeedType = getSpeedType(console);
                        result = calcHosting(true, currentSize, currentSizeType, 0.0, currentSpeedType);
                    }else{
                        currentSpeed = getNum(console, getter.SPEED);
                        currentSpeedType = getSpeedType(console);
                        currentSizeType = getSizeType(console);
                        result = calcHosting(false, 0.0, currentSizeType, currentSpeed, currentSpeedType);
                    }
                    System.out.println(result);
                    printToTxt(result);
                    break;
            }
        }catch(Exception E){
            printError();
        }

    }

    //-----MENU + INPUT / OUTPUT TEXT-----//

    //For Data unit conversion
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

    //Menu Text
    void printMenuText(){
        System.out.println("Input E to convert Data Units, R to calculate Download/Upload Time \n" +
                "Input C to calculate Website Bandwidth, V to calculate Hosting Bandwidth\n" +
                "Input 0 to quit: ");
    }

    //Get the input and what type it is
    private double getNum(Scanner console, getter currentGetter){
        switch(currentGetter){
            case SIZE:
                System.out.println("Enter Size Number: ");
                return console.nextDouble();
            case SPEED:
                System.out.println("Enter Speed Number: ");
                return console.nextDouble();
            case REDUNDANCY:
                System.out.println("Enter Redundancy Number: ");
                return console.nextDouble();
            case PAGE_VIEWS:
                System.out.println("Enter Page Views Number: ");
                return console.nextDouble();
            case PAGE_SIZE:
                System.out.println("Enter Page Size Number: ");
                return console.nextDouble();
            default:
                return 0.0;
        }

    }

    //-----CONVERSIONS-----//

    /*Each one has its own special conversion type, using BYTE_SCALES for conversion
    * Basically each is attempted to convert to their base type (bytes, bits, etc.)
    * Then converted out from there, making one or two universal conversion functions was
    * attempted, but I just had to get things done the quick and messy way
     */

    private double toByte(double dataNum, sizeType dataType){
        switch (dataType){
            case BIT:
                return dataNum / 8;
            case KILOBIT:
                return (long)((dataNum / 8) * BYTE_SCALES[0]);
            case MEGABIT:
                return (long)((dataNum / 8) * BYTE_SCALES[1]);
            case GIGABIT:
                return (long)((dataNum / 8) * BYTE_SCALES[2]);
            case TERABIT:
                return (long)((dataNum / 8) * BYTE_SCALES[3]);
            case BYTE:
                return dataNum;
            case KILOBYTE:
                return (long)(dataNum * BYTE_SCALES[0]);
            case MEGABYTE:
                return (long)(dataNum * BYTE_SCALES[1]);
            case GIGABYTE:
                return (long)(dataNum * BYTE_SCALES[2]);
            case TERABYTE:
                return (long)(dataNum * BYTE_SCALES[3]);
            default:
                return 0;
        }
    }

    private double speedToByte(double speedNum, speedType type){
        double speedNumBit = speedNum / 8;
        switch(type){
            case BIT:
                return speedNumBit;
            case KBIT:
                return speedNumBit * BYTE_SCALES[0];
            case MBIT:
                return speedNumBit * BYTE_SCALES[1];
            case GBIT:
                return speedNumBit * BYTE_SCALES[2];
            case TBIT:
                return speedNumBit * BYTE_SCALES[3];
            default:
                return 0.0;
        }
    }
    
    private double convertFromByte(double byteNum, sizeType resultType){
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

    //Per month however is always used, so simply adding to this was necessary.
    private double toPerMonth(double n, timeType currentType){
        switch(currentType){
            case SECOND:
                return n * 2679264.0;
            case MINUTE:
                return n * 44654.4;
            case HOUR:
                return n * 744.24;
            case DAY:
                return n * 31.01;
            case WEEK:
                return n * 4.43; //4.43 is the average amount fo weeks/month
            case MONTH:
                return n;
            case YEAR:
                return n / 12.0;
            default:
                return 0.0;
        }
    }

    private double speedToBit(double n, speedType currentType){
        switch(currentType){
            case BIT:
                return n;
            case KBIT:
                return n / BYTE_SCALES[0];
            case MBIT:
                return n / BYTE_SCALES[1];
            case GBIT:
                return n / BYTE_SCALES[2];
            case TBIT:
                return n / BYTE_SCALES[3];
            default:
                return 0.0;
        }
    }

    //-----CALCULATIONS-----//

    //Calculate the monthly hosting usage based on it was size to speed, or speed to size, as in the calculator given
    String calcHosting(boolean sizeToSpeed, double monthlyUsage, sizeType monthlyUsageType, double bandwidth, speedType bandwidthType){
        if(sizeToSpeed){
            double monthlyUsageBits = toByte(monthlyUsage, monthlyUsageType) * 8;
            double monthlyUsageTargetBits = speedToBit(monthlyUsageBits, bandwidthType);
            bandwidth = monthlyUsageTargetBits / SECONDS_IN_MONTH;
        }else{
            double speedBytes = speedToByte(bandwidth, bandwidthType);
            double totalBytes = speedBytes * SECONDS_IN_MONTH;
            monthlyUsage = convertFromByte(totalBytes, monthlyUsageType);
        }
        return (monthlyUsage + " (" + monthlyUsageType + ") per month is equivalent to " + bandwidth + " " + bandwidthType  + "/s");
    }

    //Calculate the data units after converting the data unit to bytes
    String calcDataUnits(double dataNum, sizeType dataType){
        String[] results = new String[unitScales.size()];
        double _dataNumByte = toByte(dataNum, dataType);

        for(int i = 0; i < results.length; i++){
            results[i] = Double.toString(convertFromByte(_dataNumByte, unitScales.get(i)));
        }

        return dataUnitResult(results);
    }

    //Calculate the download time based on the speed given, and file size. Minutes / seconds supported only because im lazy
    String calcUpload(double fileNum, sizeType dataType, double speedNum, speedType speedType){
        double _speedNum = speedToByte(speedNum, speedType);
        double _fileNum = toByte(fileNum, dataType);

        double totalSeconds = _fileNum / _speedNum;
        int minutes = (int)totalSeconds / 60;
        int seconds = (int)totalSeconds % 60;

        return ("Download/Upload time needed is: ~" + minutes + " minutes " + seconds + " seconds");

    }

    /*Results here can only be approximated based on the original calculator, but they are close enough to me
    Results always end in Gigabytes so they hard coded to be this way, like in the original calculator*/
    String calcWebsite(double pageViews, double averageSize, double redundancyFactor, timeType currentTimeType, sizeType currentSizeType){
        double resultBytes = toPerMonth(pageViews, currentTimeType) * toByte(averageSize, currentSizeType) * redundancyFactor;
        double resultMegabits = (resultBytes * 8.0) / BYTE_SCALES[1];
        double resultMegaBitsPerSecond = resultMegabits / SECONDS_IN_MONTH;
        double resultGigabytes = resultBytes / BYTE_SCALES[2];
        return "The bandwidth needed is " + resultMegaBitsPerSecond + " Mbit/s or " + resultGigabytes + " GB per month";
    }

}
