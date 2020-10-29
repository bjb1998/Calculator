package Model;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * Model.FileIO.java, is responsible for reading inputs from the file
 * Input.txt Line by line and outputting the corresponding calculation to
 * Calculations.txt
 */

public class FileIO{

    /**@param bCalc The binary Calculator
     * @param hCalc the Hexadecimal Calculator
     * @param bitCalc the Bitrate Calculator
     */

    //Calculator initialization
    private final BinaryCalc bCalc = new BinaryCalc();
    private final HexCalc hCalc = new HexCalc();
    private final BitrateCalc bitCalc = new BitrateCalc();

    //File output initialization
    private FileWriter fw;
    private List<String> calcList;

    //BitTypes for Data Calculation
    BitType type1;
    BitType type2;

    //initializing the Model.FileIO class
    public void init() {

        //Gather the lines from inputs.txt and turn into a list
        calcList = createListFromFile();
        fw = createFileWriter();
        PrintWriter pw = new PrintWriter(fw);

        //test output
        pw.println("Output file created Successfully!");
        pw.flush();

        try {
            //For each line in Inputs.txt
            for (String calculation : calcList) {
                String[] currentInput = createInputStrings(calculation);
                String baseInput = currentInput[0];
                if (calculation.startsWith("//")) {continue;}

                switch (baseInput) {
                    case "CALCULATE":
                        getCalcFromType(pw, currentInput, bCalc, hCalc, bitCalc);
                        break;
                    case "CONVERT":
                        getConvFromType(pw, currentInput, bCalc, hCalc, bitCalc);
                        break;
                    default:
                        pw.println("Invalid input on " + baseInput);
                        pw.flush();
                }
            }
        }catch (Exception e){
            pw.println("Invalid Input. Aborting!");
            pw.flush();
        }

    }

    //-----STRINGS-----//

    /**@return List a list of Strings from each line of user inputwithin a txt file*/
    private List<String> createListFromFile(){
        try {
            calcList = Files.readAllLines(Paths.get("Inputs.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return calcList;
    }

    /**@return FileWriter create a file writer for a specified text file*/
    private FileWriter createFileWriter(){
        try {
            fw = new FileWriter("Calculations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fw;
    }

    /**@return String[] gets rid of any weird CapITaLiZatIon
     * @param s the string to capitalize*/
    private String[] createInputStrings(String s){
        s = s.toUpperCase();
        return s.split(" ");
    }

    //-----CALCULATION ACCESSORS-----//

    /*Each Sentence has a predetermined structure based on the input document given, so using currentInput[someNum]
    is perfectly usable and is good at determining bad inputs
     */

    /**@return void one of the hierarchy functions to get the right type of calculation to calculate
     * multiple parameters are shared across the methods below.
     * @param pw the PrintWriter to write the result to txt file
     * @param bCalc Binary calculator
     * @param hCalc  hex calculator
     * @param bitCalc bitrte calculator
     * @param currentInput The string gathered from a certain line of text*/
    private void getCalcFromType(PrintWriter pw, String[] currentInput, BinaryCalc bCalc, HexCalc hCalc, BitrateCalc bitCalc){
        try {
            String unitInput = currentInput[1];
            switch (unitInput) {
                case "BINARY":
                    pw.println(bCalc.calcResult(new String[] {currentInput[3], currentInput[2], currentInput[4]}));
                    pw.flush();
                    break;
                case "HEXADECIMAL":
                    pw.print(hCalc.calcResult(new String[] {currentInput[3], currentInput[2], currentInput[4]}));
                    pw.println();
                    pw.flush();
                    break;
                case "DOWNLOAD/UPLOAD":
                    type1 = new BitType(BitType.Type.SIZE, Integer.parseInt(currentInput[3]), currentInput[4]);
                    type2 = new BitType(BitType.Type.SIZE, Integer.parseInt(currentInput[5]), currentInput[6]);
                    pw.println(bitCalc.calcUpload(type1, type2));
                    break;
                case "WEBSITE":
                    type1 = new BitType(BitType.Type.TIME, Integer.parseInt(currentInput[3]), currentInput[5]);
                    type2 = new BitType(BitType.Type.SIZE, Integer.parseInt(currentInput[6]), currentInput[7]);
                    pw.println(bitCalc.calcWebsite(type2, type1, Double.parseDouble(currentInput[8])));
                    break;
                default:
                    pw.println("Invalid input on " + unitInput);
                    pw.flush();
                    break;

            }
        }catch(Exception e){
            pw.println("Invalid Calculation Type on " + Arrays.toString(currentInput));
            pw.flush();
            System.out.println("Invalid Calculation Type on " + Arrays.toString(currentInput));
        }
    }

    /**@return void one of the hierarchy functions to get the right type of calculation to calculate*/
    private void getConvFromType(PrintWriter pw, String[] currentInput, BinaryCalc bCalc, HexCalc hCalc, BitrateCalc bitCalc){
        /** @param num1 Controller.Generic int entry depending on the conversion being done
         *  @param num2 Same as num1*/
        try {
            String unitInput = currentInput[1];
            switch (unitInput) {
                case "BINARY":
                    pw.println(bCalc.toDecimal(currentInput[4]));
                    pw.flush();
                    break;
                case "HEXADECIMAL":
                    pw.println(hCalc.toDecimal(currentInput[4]));
                    pw.flush();
                    break;
                case "DECIMAL":
                    getConvFromDec(pw, currentInput, bCalc, hCalc);
                    break;
                case "DATA":
                    type1 = new BitType(BitType.Type.SIZE, Integer.parseInt(currentInput[5]), currentInput[4]);
                    pw.println(bitCalc.calcDataUnits(type1));
                    pw.flush();
                    break;
                case "MONTHLY":
                    type1 = new BitType(BitType.Type.SIZE, Integer.parseInt(currentInput[5]), currentInput[6]);
                    type2 = new BitType(BitType.Type.SIZE, Integer.parseInt(currentInput[7]), currentInput[8]);
                    pw.println(bitCalc.calcHosting(type1, type2));
                    pw.flush();
                    break;
                default:
                    pw.println("Invalid input on " + unitInput);
                    pw.flush();
                    break;

            }
        }catch(Exception e){
            pw.println("Invalid Conversion Type on " + Arrays.toString(currentInput));
            pw.flush();
            System.out.println("Invalid Conversion Type on " + Arrays.toString(currentInput));
        }
    }

    /**@return void one of the hierarchy functions to get the right type of calculation to calculate*/
    private void getConvFromDec(PrintWriter pw, String[] currentInput, BinaryCalc bCalc, HexCalc hCalc){
        try {
            String input = currentInput[3];
            int decimalNum = Integer.parseInt(currentInput[4]);
            switch (input) {
                case "BINARY":
                    pw.println(bCalc.toType(decimalNum));
                    pw.flush();
                    break;
                case "HEXADECIMAL":
                    pw.println(hCalc.toType(decimalNum));
                    pw.flush();
                    break;
                default:
                    pw.println("Invalid input on " + input);
                    pw.flush();
            }
        }catch(Exception e){
            pw.println("Invalid Decimal Conversion Type on " + Arrays.toString(currentInput));
            pw.flush();
            System.out.println("Invalid Decimal Conversion Type on " + Arrays.toString(currentInput));
        }
    }


}
