/**
 * I'm the IP Calculation class! I got axed but i'm left here so here I am!
 */
import java.util.Scanner;

public class IPCalc extends Generic{

    public void init(){
        printMenuText();
        super.init();
    }

    public void calculate(ProblemType type) {
        String result;
        Scanner console = new Scanner(System.in);
        String[] inputs = generateInputs(console);

        try {
            switch (type) {
                case CALC_OTHER:

                    break;
                case CALC_DEC:

                    break;
                case CONV_OTHER:

                    break;
                case CONV_DEC:

                    break;
            }
        } catch (Exception E) {
            printError();
        }
    }

    public void printMenuText(){
        System.out.println("Input two IP addresses, with the operand in the middle (0 to quit): ");
    }

    String calcResult(String s1, String s2, String s3){
        return "";
    };

    boolean isIPV4(String address){
        if(address.startsWith("2001") || address.startsWith("2002")){
            return true;
        }
        return false;
    }

    String resultIPV6(){
        return ("IP address: \n" +
                "Full IP Address: \n" +
                "Total IP Addresses: \n" +
                "Total /64 Networks: \n" +
                "Network: \n" +
                "IP Range: ");
    }

    String resultIPV4(){
        return ("IP address: \n" +
                "Network Address: \n" +
                "Usable Host IP Range: \n" +
                "Broadcast Address: \n" +
                "Total Number of Hosts: \n" +
                "Number of Usable hosts: \n" +
                "Subnet Mask: \n" +
                "Wildcard Mask: \n" +
                "Binary Subnet Mask: \n" +
                "IP Class: \n" +
                "CIDR Notation: \n" +
                "IP Type: Public \n" +
                "Short: \n" +
                "Binary ID: \n" +
                "Integer ID: \n" +
                "Hex ID: \n" +
                "in-addr.arpa: \n" +
                "IPV4 Mapped Addresses: \n" +
                "6t04 Prefix: \n");
    }

}
