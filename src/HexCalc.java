/**
 * Hexadecimal calculator, adds, subtracts, multiples, divides hex numbers, and decimals outputting in hex
 * conversions are supported between the two integer types as well
 */
import java.util.*;
public class HexCalc extends Generic{

    //All possible Hex digits
    Character[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    //initialize the HexCalc
    public void init(){
        printMenuText();
        super.init();
    }

    public void calculate(ProblemType type) {
        String result;
        Scanner console = new Scanner(System.in);
        String[] inputs = generateInputs(console);
        int decimalNum;

        try {
            switch (type) {
                case CALC_OTHER:
                    if (checkIfValidHexEquation(inputs)) {
                        result = calcResult(toDecimal(inputs[0]), inputs[1], toDecimal(inputs[2]));
                        System.out.println("Result: " + result + " (" + toDecimal(result) + ")");
                        printToTxt(result);
                    } else {
                        printError();
                    }

                    break;
                case CALC_DEC:
                    if (checkIfValidDecimalEquation(inputs)) {
                        result = calcResult(inputs[0], inputs[1], inputs[2]);
                        System.out.println("Result: " + result + " (" + toDecimal(result) + ")");
                        printToTxt(result);

                    } else {
                        printError();
                    }

                    break;
                case CONV_OTHER:
                    if (isNegativeString(inputs[0])) {
                        result = toDecimal(inputs[1]);
                        System.out.println("Converted Hexadecimal Number: -" + result);
                        printToTxt(result);

                    } else {
                        result = toDecimal(inputs[0]);
                        System.out.println("Converted Hexadecimal Number: " + result);
                        printToTxt(result);
                    }

                    break;
                case CONV_DEC:
                    if (isNegativeString(inputs[0])) {
                        decimalNum = Integer.parseInt(inputs[1]);
                        result = toHex(decimalNum);
                        System.out.println("Converted Decimal Number: -" + result);
                        printToTxt(result);
                    } else {
                        decimalNum = Integer.parseInt(inputs[0]);
                        result = toHex(decimalNum);
                        System.out.println("Converted Decimal Number: " + result);
                        printToTxt(result);
                    }

                    break;
            }
        }catch (Exception E){
            printError();
        }
    }

    public void printMenuText(){
        System.out.println("Input E to calculate hexadecimal, R to calculate Decimals \n" +
                "Input C to convert hexadecimal to Decimal, V to convert Decimal to hexadecimal\n" +
                "Input 0 to quit: ");
    }

    protected boolean isHex(String hexNum){
        for(int i = 0; i < hexNum.length(); i++){
            if(!Arrays.asList(hex).contains(hexNum.charAt(i))){
                return false;
            }
        }
        return true;
    }

    protected boolean checkIfValidHexEquation(String[] inputs){
        return (isHex(inputs[0]) && isOperand(inputs[1]) && isHex(inputs[2]));}

    protected String toDecimal(String hexNum){
        String decimalNum = "";
        decimalNum = decimalNum.concat("" + Integer.parseInt(hexNum, 16));
        return decimalNum;
    }

    protected String toHex(int num){
        StringBuilder sb = new StringBuilder();
        String result = "";

        if(num <= 9) {
            sb.append(num);
        }else{
            while(num > 0){
                int remainder = num % 16;
                sb.insert(0, hex[remainder]);
                num = num / 16;
            }
        }

        result = sb.toString();
        return result;
    }

    String calcResult(String num1, String operand, String num2){
        int dec1 = Integer.parseInt(num1);
        int dec2 = Integer.parseInt(num2);
        int n;
        switch(operand){
            case "+":
                n = dec1 + dec2;
                return toHex(n);
            case "-":
                n = dec1 - dec2;
                return toHex(n);
            case "*":
                n = dec1 * dec2;
                return toHex(n);
            case "/":
                n = dec1 / dec2;
                return toHex(n);
            default:
                return "Unknown Error";
        }
    }

}
