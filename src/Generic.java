/**
 * Generic abstract class, contains base methods that are used throughout all calculators
 * Initialization sets up the output file, and the main menu that leads to the calculator
 * the enum ProblemType determines the calculation type, this is not used in BitrateCalc
 */
import java.io.*;
import java.util.*;
abstract class Generic {

    //ProblemType based off the 4 types in Binary/hex
    enum ProblemType{
        CALC_DEC, CALC_OTHER, CONV_DEC, CONV_OTHER
    }

    //printWriter initialization
    private PrintWriter pw;

    //Initialize the Generic
    void init(){
       Scanner console = new Scanner(System.in);
       String input;

        FileWriter fw = null;
       try {
           fw = new FileWriter("Calculations.txt");
       } catch (IOException e) {
           e.printStackTrace();
       }

       //Put PrintWriter to FileWriter
       assert fw != null;
       pw = new PrintWriter(fw);
       pw.println("Output file created Successfully!");

       //Main menu of both Binary Calc and hex Calc, each has their own corresponding menu text
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

    /**@return String based on the operand and what is being calculated (Binary, Hex, or decimal)*/
    abstract String calcResult(String num1, String operand, String num2);

    abstract void calculate(ProblemType type);

    abstract void printMenuText();

    //-----TEXT/CALCULATIONS-----//

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

    void printError(){
        System.out.println("Invalid input.");
    }

    /**@return String[] Split the string by the operand (+,-,*,/)*/
    String[] generateInputs(Scanner console){
        System.out.print("Input Value(s): ");
        String input = console.nextLine();
        return input.split("(?<=[-+*/])|(?=[-+*/])"); // by to be in its own position in the array. It uses a
        // bitwise inclusive OR to look behind and ahead of the splitter
        //character to make it its own position
    }

    //-----BOOLEAN-----//

    /**@return boolean Determine if the string is a decimal number*/
    boolean isDecimal(String number){
        for(int i = 0; i < number.length(); i++){
            char currentChar = number.charAt(i);
            if(Character.getNumericValue(currentChar) > 9){
                return false;
            }
        }
        return true;
    }

    boolean checkIfValidDecimalEquation(String[] inputs){
        return (isDecimal(inputs[0]) && isOperand(inputs[1]) && isDecimal(inputs[2]));
    }

    /**@return boolean if the string is an operand or not (+, -, *, /)*/
    boolean isOperand(String s){
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }


}
