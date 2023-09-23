import java.util.ArrayList;
import java.util.Scanner;
/*
 * Implementing Payments as an interface which can be implemnted by all classes which need to use payments
 * Payments has to methods, for cash payment and credit card payment
 */
public interface Payments {
    // Two differnt types of payment methods.
    // Implentation using method overloading
    public int payment(Scanner sc,long creditCardNumber,ArrayList<Ticket> tickets);
    //Payment method used for credit card payments
    public int payment(Scanner sc,ArrayList<Ticket> tickets);
    //Payment method used for cash payments
}
