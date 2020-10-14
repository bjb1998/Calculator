/**
 * FileIO.java, is responsible for reading inputs from the file
 * Input.txt Line by line and outputting the corresponding calculation to
 * Calculations.txt
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class FileIO{

    /**This is the main menu
     * @param bCalc The binary Calculator
     * @param hCalc the Hexadecimal Calculator
     * @param bitCalc the Bitrate Calculator
     */

    //Calculator initialization
    private BinaryCalc bCalc = new BinaryCalc();
    private HexCalc hCalc = new HexCalc();
    private BitrateCalc bitCalc = new BitrateCalc();

    //File output initialization
    private FileWriter fw;
    private List<String> calcList;

    //initializing the FileIO class
    void init() {

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
                //Is comment, ignore it
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

    //read all lines from input.txt, and add it list
    private List<String> createListFromFile(){
        try {
            calcList = Files.readAllLines(Paths.get("Inputs.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            File calcInputs = new File("Inputs.txt");
            e.printStackTrace();
        }

        return calcList;
    }

    //Make FileWriter to Calculations.txt
    private FileWriter createFileWriter(){
        try {
            fw = new FileWriter("Calculations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fw;
    }

    /**@return String[] gets rid of any weird CapITaLiZatIon*/
    private String[] createInputStrings(String s){
        s = s.toUpperCase();
        return s.split(" ");
    }

    //-----CALCULATION ACCESSORS-----//

    /*Each Sentence has a predetermined structure based on the input document given, so using currentInput[someNum]
    is perfectly usable and is good at determining bad inputs
     */

    //Get which type to calculate to/from, and exec the corresponding method
    private void getCalcFromType(PrintWriter pw, String[] currentInput, BinaryCalc bCalc, HexCalc hCalc, BitrateCalc bitCalc){
        try {
            String unitInput = currentInput[1];
            String operand = currentInput[2];
            double num1, num2, num3;
            switch (unitInput) {
                case "BINARY":
                    pw.println(bCalc.calcResult(currentInput[3], operand, currentInput[4]));
                    pw.flush();
                    break;
                case "HEXADECIMAL":
                    pw.println(hCalc.calcResult(hCalc.toDecimal(currentInput[3]), operand, hCalc.toDecimal(currentInput[4])));
                    pw.flush();
                    break;
                case "DOWNLOAD/UPLOAD":
                    num1 = Double.parseDouble(currentInput[3]);
                    num2 = Double.parseDouble(currentInput[5]);
                    pw.println(bitCalc.calcUpload(num1, bitCalc.getSizeTypeFromString(currentInput[4]),
                            num2, bitCalc.getSpeedTypeFromString(currentInput[6])));
                    pw.flush();
                    break;
                case "WEBSITE":
                    num1 = Double.parseDouble(currentInput[3]);
                    num2 = Double.parseDouble(currentInput[6]);
                    num3 = Double.parseDouble(currentInput[8]);
                    pw.println(bitCalc.calcWebsite(num1, num2, num3, bitCalc.getTimeTypeFromString(currentInput[5]),
                            bitCalc.getSizeTypeFromString(currentInput[7])));
                    pw.flush();
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

    //Get which type to convert to/from, and exec the corresponding method
    private void getConvFromType(PrintWriter pw, String[] currentInput, BinaryCalc bCalc, HexCalc hCalc, BitrateCalc bitCalc){
        /**@param num1 Generic int entry depending on the conversion being done*/
        try {
            String unitInput = currentInput[1];
            double num1;
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
                    num1 = Double.parseDouble(currentInput[5]);
                    pw.println(bitCalc.calcDataUnits(num1, bitCalc.getSizeTypeFromString(currentInput[4])));
                    pw.flush();
                    break;
                case "MONTHLY":
                    num1 = Double.parseDouble(currentInput[5]);
                    pw.println(bitCalc.calcHosting(true, num1,
                            bitCalc.getSizeTypeFromString(currentInput[6]),
                            0.0, BitrateCalcOverhead.speedType.MBIT));
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

    //Conversion from decimal is a special case, can be to either binary or hex
    private void getConvFromDec(PrintWriter pw, String[] currentInput, BinaryCalc bCalc, HexCalc hCalc){
        try {
            String input = currentInput[3];
            int decimalNum = Integer.parseInt(currentInput[4]);
            switch (input) {
                case "BINARY":
                    pw.println(bCalc.toBinary(decimalNum, bCalc.isNegative(decimalNum)));
                    pw.flush();
                    break;
                case "HEXADECIMAL":
                    pw.println(hCalc.toHex(decimalNum));
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
