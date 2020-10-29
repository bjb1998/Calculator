package Model; /**
 * Hexadecimal calculator, adds, subtracts, multiples, divides hex numbers, and decimals outputting in hex
 * conversions are supported between the two integer types as well
 */

import java.util.*;

public class HexCalc extends Controller.GenericEquation {

    //All possible Hex digits
    private Character[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    //-----MENU-----//

    /**@return void Initalize the Hexadecimal Calculator menu*/
    public void init() {
        printMenuText();
        super.init();
    }

    protected void calculate(ProblemType type) {
        String result;
        Scanner console = new Scanner(System.in);
        String[] inputs = generateInputs(console);
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
                    if (isType(inputs[0])) {
                        result = toDecimal(inputs[0]);
                        printAndSave(result);
                    } else {
                        printError();
                    }
                    break;
                case CONV_DEC:
                    if (isDecimal(inputs[0])) {
                        decimalNum = Integer.parseInt(inputs[0]);
                        result = toType(decimalNum);
                        printAndSave(result);
                    } else {
                        printError();
                    }
                    break;
            }
        } catch (Exception E) {
            printError();
        }
    }

    protected void printMenuText() {
        System.out.println("Input E to calculate hexadecimal, R to calculate Decimals \n" +
                "Input C to convert hexadecimal to Decimal, V to convert Decimal to hexadecimal\n" +
                "Input 0 to quit: ");
    }

    //-----BOOLEANS-----//

    /**@return boolean if the String is a hexadecimal number or not*/
    protected boolean isType(String num) {
        if(num.length() == 0) return false;
        for (int i = 0; i < num.length(); i++) {
            if (!Arrays.asList(hex).contains(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //-----CALCULATION/CONVERSIONS-----//

    /**@return String the number in hexadecimal format*/
    @Override
    protected String toType(int num) {
        StringBuilder sb = new StringBuilder();
        String result = "";

        if (num <= 9) {
            sb.append(num);
        } else {
            while (num > 0) {
                int remainder = num % 16;
                sb.insert(0, hex[remainder]);
                num = num / 16;
            }
        }

        result = sb.toString();
        return result;
    }

    /**@return String the Hexadecimal Number as a string*/
    @Override
    public String toDecimal(String hexNum) {
        String decimalNum = "";
        decimalNum = decimalNum.concat("" + Integer.parseInt(hexNum, 16));
        return decimalNum;
    }

}
