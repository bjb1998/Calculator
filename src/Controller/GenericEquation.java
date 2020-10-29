package Controller; /**
 * Controller.Generic abstract class, contains base methods that are used throughout all calculators
 * Initialization sets up the output file, and the main menu that leads to the calculator
 * the enum ProblemType determines the calculation type, this is not used in Model.BitrateCalc
 */

import Controller.Generic;

import java.util.*;
public abstract class GenericEquation extends Generic {

    Scanner console = new Scanner(System.in);

    //ProblemType based off the 4 types in Binary/hex
    public enum ProblemType{
        CALC_DEC, CALC_OTHER, CONV_DEC, CONV_OTHER
    }

    /**@return void Initialize the Controller.Generic class, also set menu based off input, enum is sent to subclass*/
    public void init(){
       String input;

       initGeneric(console);

       //Viewer.Main menu of both Binary Calc and hex Calc, each has their own corresponding menu text
       mainLoop:
       while(console.hasNext()){
           input = console.next();

           //set problemType enum depending on input
           switch(input){
               case "0":
               break mainLoop;
               case "E":
               case "e":
                   calculate(ProblemType.CALC_OTHER);
                   break;
               case "R":
               case "r":
                   calculate(ProblemType.CALC_DEC);
                   break;
               case "C":
               case "c":
                   calculate(ProblemType.CONV_OTHER);
                   break;
               case "V":
               case "v":
                   calculate(ProblemType.CONV_DEC);
                   break;
               default:
                   System.out.println("Invalid input");
                   break;
           }
       }
   }

    //-----ABSTRACT-----//

    /**@return String convert the given int to a binary/hex string
     * @param numToConvert the decimal number to convert to binary/hex*/
    protected abstract String toType(int numToConvert);

    /**@return void Calculate the problem based on the enum type from the Controller.Generic class menu
     * @return type the problemType made based off user input from the menu*/
    protected abstract void calculate(ProblemType type);

    /**@return void print the menu text of the associated calculator*/
    protected abstract void printMenuText();

    /**@return String convert the given binary/hex number as a string, to a decimal string.
     * @param num the binary/hex number that is to be converted to a decimal*/
    protected abstract String toDecimal(String num);

    /**@return boolean is the given string is a binayr number, or hex number.
     * @param num the string that is to be evaluated.*/
    protected abstract boolean isType(String num);

    //-----TEXT/CALCULATIONS-----//

    /**@return String[] Split the string by the operand (+,-,*,/)
     * @param console the scanner to gather user input*/
    protected String[] generateInputs(Scanner console){
        System.out.print("Input Value(s): ");
        String input = console.next();
        return input.split("(?<=[-+*/])|(?=[-+*/])"); // by to be in its own position in the array. It uses a
        // bitwise inclusive OR to look behind and ahead of the splitter character to make it its own position
    }

    /**@return String the number to convert as a string
     * @param inputs the tokenized strings of the given equation inputted by user*/
    protected String getConvNumber(String[] inputs){
        return inputs[0];
    }

    /**@return String based on the operand and what is being calculated (Binary, Hex, or decimal)
     * @param inputs the tokenized strings of the given equation inputted by user*/
    public String calcResult(String[] inputs) {
        //This inputs binary by default, changes them to decimals
        int dec1 = Integer.parseInt(toDecimal(inputs[0]));
        int dec2 = Integer.parseInt(toDecimal(inputs[2]));
        int n;
        String operand = inputs[1];
        //return the binary number
        switch (operand) {
            case "+":
                n = dec1 + dec2;
                return toType(n);
            case "-":
                n = dec1 - dec2;
                return toType(n);
            case "*":
                n = dec1 * dec2;
                return toType(n);
            case "/":
                n = dec1 / dec2;
                return toType(n);
            default:
                return "Invalid Operand";
        }
    }

    //-----BOOLEAN-----//

    /**@return if the equation is valid hex, or binary equation or not
     * @param inputs the tokenized strings of the given equation inputted by user*/
    protected boolean isValidTypeEquation(String[] inputs){
        return (isType(inputs[0]) && isOperand(inputs[1]) && isType(inputs[2]));
    }

    /**@return boolean Determine if the string is a decimal number
     * @param number the string gathered from user input.*/
    protected boolean isDecimal(String number){
        if(number.startsWith("0") || number.length() == 0) return false;

        for(int i = 0; i < number.length(); i++){
            char currentChar = number.charAt(i);
            if(Character.getNumericValue(currentChar) > 9){
                return false;
            }
        }
        return true;
    }

    /**@return boolean Checks if the array of tokenized strings is a valid decimal equation
     * @param inputs the tokenized strings of the given equation inputted by user*/
    protected boolean IsValidDecimalEquation(String[] inputs){
        return (isDecimal(inputs[0]) && isOperand(inputs[1]) && isDecimal(inputs[2]));
    }

    /**@return boolean if the string is an operand or not (+, -, *, /)
     * @param s the string gathered from tokenized strings in generateInputs()*/
    protected boolean isOperand(String s){
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }


}
