import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customerData = new ArrayList<Customer>();
        // data
        customerData.add(new Customer("a", "9999999999", null, 0, 0,"aa"));
        customerData.add(new Customer("b", "8888888888", null, 0, 0,"bb"));
        customerData.add(new Customer("c", "0000000000", null, 0, 0,"cc"));
        /*
        customerData[0] = new Customer("a", "9999999999", null, 0, 0,"aa");
        customerData[1] = new Customer("b", "8888888888", null, 0, 0,"bb");
        customerData[2] = new Customer("c", "0000000000", null, 0, 0,"cc");// add some id
        */
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        //CustomerInfoPortal cip = new CustomerInfoPortal(sc,customerData);
        Floor f1 = new Floor(2, 3, 6, 7, 7, 0, 1);
        sc.close();
    }
}