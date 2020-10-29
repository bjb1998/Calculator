package Controller; /**
 * Created by Brandon Brassfield, 10/22/2020
 *
 * A collection of Controller.Generic methods that is used across both types of
 * Controller.Generic classes, AKA GenericEquation, and Controller.GenericBit
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Generic {

    //printWriter initialization
    PrintWriter pw;
    FileWriter fw;

    /**@return void Initializes basic stuff like FileWriters, and PrintWriters
     * @param console the scanner thar reads user input*/
    void initGeneric(Scanner console){
        fw = null;
        try {
            fw = new FileWriter("Calculations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        pw = new PrintWriter(fw);
        pw.println("Output file created Successfully!");
    }

    /**@return void print the phrase "invalid input", used when exceptions are found*/
    protected void printError(){
        System.out.println("Invalid input.");
    }

    /**@return void gives the user the option to save the calculation given, using y/n as options
     * @param result the calculation that was passed from printAndSave*/
    protected void printToTxt(String result){
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

    /**@return void higher-level method to print the results, and then give the user to save the problem
     * @param result the calculation that was just completed*/
    protected void printAndSave(String result){
        printResult(result);
        printToTxt(result);
    }

    /**@return void print the results to printed out in the command line
     * @param result the string passed on from printAndSave*/
    void printResult(String result){
        System.out.println("Result: " + result);
    }

}
