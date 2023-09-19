import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer[] customerData = new Customer[3];
        // data
        customerData[0].customerId="";// add some id
        customerData[0].creditcardNumber=0;
        customerData[0].accountBalance=0;
        customerData[0].password="";// add password
        customerData[0].address="";
        customerData[0].phoneNumber=0;

        customerData[1].customerId="";// add some id
        customerData[1].creditcardNumber=0;
        customerData[1].accountBalance=0;
        customerData[1].password="";// add password
        customerData[1].address="";
        customerData[1].phoneNumber=0;
        
        customerData[2].customerId="";// add some id
        customerData[2].creditcardNumber=0;
        customerData[2].accountBalance=0;
        customerData[2].password="";// add password
        customerData[2].address="";
        customerData[2].phoneNumber=0;

        CustomerInfoPortal cip = new CustomerInfoPortal(sc,customerData);

        sc.close();
    }
}