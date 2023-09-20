import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer[] customerData = new Customer[3];
        // data
        customerData[0] = new Customer("a", "9999999999", null, 0, 0,"aa");
        customerData[1] = new Customer("b", "8888888888", null, 0, 0,"bb");
        customerData[2] = new Customer("c", "0000000000", null, 0, 0,"cc");// add some id

        CustomerInfoPortal cip = new CustomerInfoPortal(sc,customerData);
        
        sc.close();
    }
}