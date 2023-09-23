/*
 * Implementing customer as a class, Whenever a customer registers themselves in the customer info portal,
 * A customer object is created and this object is pushed into the customer array list.
 */
import java.util.ArrayList;

public class Customer {
    String customerId;
    private String phoneNumber;
    private String address;
    private String password;
    private String latestTicketId="";
    private int accountBalance;
    private long creditCardNumber=0;
    Customer(String customerId,String phoneNumber,String address,int accountBalance,long creditCardNumber,String password){
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.accountBalance=accountBalance;
        this.creditCardNumber=creditCardNumber;
        this.password=password;
    }
    //For a given customer ID, check if it is valid
    static boolean customerExists(String customerId,ArrayList<Customer>customers){
        for(Customer customer:customers ){
            if(customer.customerId.compareTo(customerId)==0){
                return true;
            }
        }
        return false;
    }
    //As we don't want customer info to be manipulted by external forces, Everything is made private and getter methods are used
    String phonenumberGetter(){
        return phoneNumber;
    }
    int accountBalanceGetter(){
        return accountBalance;
    }
    String addressGetter(){
        return address;
    }
    String passwordGetter(){
        return this.password;
    }
    long creditCardNumberGetter(){
        return this.creditCardNumber;
    }
    String latestTicketIdGetter(){
        return this.latestTicketId;
    }
    //Setter method is used to change the lastest tickey ID
    void latestTicketIdSetter(String latestTicketId){
        this.latestTicketId=latestTicketId;
    }
}
