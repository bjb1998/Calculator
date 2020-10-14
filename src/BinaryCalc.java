/**
 * Binary calculator, adds, subtracts, multiples, divides binary numbers, and decimals outputting in binary
 * conversions are supported between the two integer types as well
 */
import java.util.*;
public class BinaryCalc extends Generic{


    //initialize the BinaryCalc
    public void init(){
        printMenuText();
        super.init();
    }

    //-----MENUS-----//

    public void calculate(ProblemType type) {
        String result; //The result string to always be seen as output
        Scanner console = new Scanner(System.in);
        String[] inputs = generateInputs(console);
        int n1, n2;

        try {
            switch (type) {
                case CALC_OTHER:
                    if (checkIfValidBinaryEquation(inputs)) {
                        result = calcResult(inputs[0], inputs[1], inputs[2]);
                        System.out.println("Result: " + result + " (" + toDecimal(result) + ")"); //Display binary adn Decimal result
                        printToTxt(result); //Give option to save the output to txt
                    } else {
                        printError();
                    }

                    break;
                case CALC_DEC:
                    if (checkIfValidDecimalEquation(inputs)) {
                        n1 = Integer.parseInt(inputs[0]); //Convert Strings to int, lack of foresight in implementation :(
                        n2 = Integer.parseInt(inputs[2]);
                        result = calcResult(toBinary(n1, isNegative(n1)), inputs[1], toBinary(n2, isNegative(n2)));
                        System.out.println("Result: " + result + " (" + toDecimal(result) + ")");
                        printToTxt(result);
                    } else {
                        printError();
                    }

                    break;
                case CONV_OTHER:
                    if (isNegativeString(inputs[0])) {
                        result = toDecimal(inputs[1]);
                        System.out.println("Converted Binary Number: -" + result);
                        printToTxt(result);
                    } else {
                        result = toDecimal(inputs[0]);
                        System.out.println("Converted Binary Number: " + result);
                        printToTxt(result);
                    }

                    break;
                case CONV_DEC:
                    if (isNegativeString(inputs[0])) {
                        n1 = Integer.parseInt(inputs[1]);
                        result = toBinary(n1, isNegative(n1));
                        System.out.println("Converted Decimal Number: -" + result);
                        printToTxt(result);
                    } else {
                        n1 = Integer.parseInt(inputs[0]);
                        result = toBinary(n1, isNegative(n1));
                        System.out.println("Converted Decimal Number: " + result);
                        printToTxt(result);
                    }

                    break;
            }

        } catch (Exception e) {
            printError(); //If any invalid input is detected
        }
    }

    //Menu text
    void printMenuText(){
        System.out.println("Input E to calculate binary, R to calculate Decimals \n" +
                "Input C to convert Binary to Decimal, V to convert Decimal to Binary\n" +
                "Input 0 to quit: ");
    }

    //-----BOOLEANS-----//

    //If all the strings are what they need to be
    protected boolean checkIfValidBinaryEquation(String[] inputs){
        return (isBinary(inputs[0]) && isOperand(inputs[1]) && isBinary(inputs[2]));
    }

    //If all the chars are 0 or 1, then its binary
    protected boolean isBinary(String number){
        for(int i = 0; i < number.length(); i++){
            char currentChar = number.charAt(i);
            if(Character.getNumericValue(currentChar) > 1 && currentChar != '1'){
                return false;
            }
        }
        return true;
    }

    //-----STRINGS/CALCULATIONS-----//

    //Decimal to Binary
    protected String toBinary(int decimalNum, boolean isNegative){
        StringBuilder sb = new StringBuilder();
        int[] binary = new int[64]; //Limit is set to 64 because why not
        decimalNum = Math.abs(decimalNum);
        int size = 0;
        sb.append("0"); //base zero in case the number is 0
        if(isNegative) {sb.insert(0, "-");} //Negative numbers are somewhat implemented

        while(decimalNum > 0){
            binary[size++] = decimalNum % 2; //the remainder is either 0, or 1, this is the base for the binary
            decimalNum /= 2;
        }
        for(int i = size - 1; i >= 0; i--){
            sb.append(binary[i]);
        }

        return sb.toString();
    }

    //Binary to Decimal
    protected String toDecimal(String binaryNum){
        StringBuilder sb = new StringBuilder();
        int decimalNum = 0;
        int factor = 0;
        for(int i = binaryNum.length() - 1; i >= 0; i--){
            if(binaryNum.charAt(i) == '1') {decimalNum += Math.pow(2, factor);}
            factor++; //If the number is 1, then add to the corresponding power of 2
        }

        if (binaryNum.charAt(0) == '-') {sb.append("-");} //If it's ever negative
        sb.append(decimalNum);

        return sb.toString();
    }

    //Calculate two binary numbers by calculating them as decimals
    String calcResult(String num1, String operand, String num2){
        //This inputs binary by default, changes them to decimals
        int dec1 = Integer.parseInt(toDecimal(num1));
        int dec2 = Integer.parseInt(toDecimal(num2));

        //return the binary number
        switch(operand){
            case "+":
                return toBinary(dec1 + dec2, isNegative(dec1 + dec2));
            case "-":
                return toBinary(dec1 - dec2, isNegative(dec1 - dec2));
            case "*":
                return toBinary(dec1 * dec2, isNegative(dec1 * dec2));
            case "/":
                return toBinary(dec1 / dec2, isNegative(dec1 / dec2));
            default:
                return "Invalid Operand";
        }
    }

}
