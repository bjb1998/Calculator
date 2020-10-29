package Model;

import java.util.Scanner;

/**
 * Binary calculator, adds, subtracts, multiples, divides binary numbers, and decimals outputting in binary
 * conversions are supported between the two integer types as well
 */

public class BinaryCalc extends Controller.GenericEquation {


    /**@return void Initalize the Binary Calculator menu*/
    public void init() {
        printMenuText();
        super.init();
    }

    //-----MENUS-----//

    @Override
    protected void calculate(ProblemType type) {
        String result; //The result string to always be seen as output
        Scanner console = new Scanner(System.in);
        String[] inputs = generateInputs(console);
        String numberToConvert = getConvNumber(inputs);
        int decimalNum;

        try {
            switch (type) {
                case CALC_OTHER:
                    if (isValidTypeEquation(inputs)) {
                        result = calcResult(inputs);
                        printAndSave(result);
                    } else {
                        printError();
                    }
                    break;
                case CALC_DEC:
                    if (IsValidDecimalEquation(inputs)) {
                        result = calcResult(inputs);
                        printAndSave(result);
                    } else {
                        printError();
                    }
                    break;
                case CONV_OTHER:
                    if (isType(numberToConvert)) {
                        result = toDecimal(numberToConvert);
                        printAndSave(result);
                    } else {
                        printError();
                    }
                    break;
                case CONV_DEC:
                    if (isDecimal(numberToConvert)) {
                        decimalNum = Integer.parseInt(numberToConvert);
                        result = toType(decimalNum);
                        printAndSave(result);
                    } else {
                        printError();
                    }
                    break;
            }

        } catch (Exception e) {
            printError(); //If any invalid input is detected
        }
    }

    @Override
    protected void printMenuText() {
        System.out.println("Input E to calculate binary, R to calculate Decimals \n" +
                "Input C to convert Binary to Decimal, V to convert Decimal to Binary\n" +
                "Input 0 to quit: ");
    }

    //-----BOOLEANS-----//

    protected boolean isType(String num) {
        if(num.length() == 0) return false;
        for (int i = 0; i < num.length(); i++) {
            char currentChar = num.charAt(i);
            if (currentChar > '1') {
                return false;
            }
        }
        return true;
    }

    //-----STRINGS/CALCULATIONS-----//

    protected String toType(int numToConvert) {
        StringBuilder sb = new StringBuilder();
        int[] binary = new int[64]; //Limit is set to 64 because why not
        numToConvert = Math.abs(numToConvert);
        int size = 0;
        sb.append("0"); //base zero in case the number is 0

        while (numToConvert > 0) {
            binary[size++] = numToConvert % 2; //the remainder is either 0, or 1, this is the base for the binary
            numToConvert /= 2;
        }
        for (int i = size - 1; i >= 0; i--) {
            sb.append(binary[i]);
        }

        return sb.toString();
    }

    public String toDecimal(String binaryNum) {
        if(!isDecimal(binaryNum)){ return binaryNum;} //Checker to see if a binaryNum is being passed

        StringBuilder sb = new StringBuilder();
        int decimalNum = 0;
        int factor = 0;
        for (int i = binaryNum.length() - 1; i >= 0; i--) {
            if (binaryNum.charAt(i) == '1') {
                decimalNum += Math.pow(2, factor);
            }
            factor++; //If the number is 1, then add to the corresponding power of 2
        }

        if (binaryNum.charAt(0) == '-') {
            sb.append("-");
        } //If it's ever negative
        sb.append(decimalNum);

        return sb.toString();
    }



}
