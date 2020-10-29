package Viewer; /**
 * @author Brandon James Brassfield
 * @version 1.1
 * @since 2020-10-27
 * @name Calculator Project
 * -------------
 * The main class, this is whats first executed
 * Initializes all objects, and print main menu
 */

import Model.BinaryCalc;
import Model.BitrateCalc;
import Model.FileIO;
import Model.HexCalc;

import java.io.File;
import java.util.*;
public class Main {

    //main method. Is docroot being used right? :(
    /** {@docroot} */
    public static void main(String[] args) {
        mainMenu();
    }

    /**@return void the main menu of the whole applications*/
    private static void mainMenu() {
        /**This is the main menu
         * @param  bCalc The binary Calculator
         * @param hCalc the Hexadecimal Calculator
         * @param bitCalc the Bitrate Calculator
         * @param outputs The Model.FileIO Object to interact
         * with the calculators*/

        System.out.println(menuText());

        //Object/File initialization
        BinaryCalc bCalc = new BinaryCalc();
        HexCalc hCalc = new HexCalc();
        BitrateCalc bitCalc = new BitrateCalc();
        FileIO outputs = new FileIO();

        //input initialization
        String input;
        Scanner console = new Scanner(System.in);

        while (console.hasNext()) {
            input = console.next();
            switch (input) {
                case "1":
                    System.out.println("Launching Binary");
                    bCalc.init();
                    System.out.println(menuText());
                    break;
                case "2":
                    System.out.println("Launching Hex");
                    hCalc.init();
                    System.out.println(menuText());
                    break;
                case "3":
                    System.out.println("Launching Bitrate");
                    bitCalc.init();
                    System.out.println(menuText());
                    break;
                case "4":
                    outputs.init();
                    System.out.println("Output of Calculations successful.");
                    System.out.println(menuText());
                    break;
                case "0":
                    System.out.println("Quitting");
                    System.exit(0);
                default:
                    System.out.println("Try again.");
                    break;

            }
        }

    }

    //The main menu text
    /**@return String The main menu text*/
    private static String menuText(){
        return "Main Menu: \n" +
                "1 - Binary Calculator\n" +
                "2 - Hex Calculator\n" +
                "3 - Bitrate Calculator\n" +
                "4 - Output from file\n" +
                "0 - Quit";
    }

}
