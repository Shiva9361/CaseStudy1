/*
 * Implementing customer as a class, Whenever a customer registers themselves in the customer info portal,
 * A customer object is created and this object is pushed into the customer array list.
 */
public class Customer {
    String customerId;
    String phoneNumber;
    String address;
    String password;
    String latestTicketId="";
    int accountBalance;
    long creditCardNumber;
    Customer(String customerId,String phoneNumber,String address,int accountBalance,long creditCardNumber,String password){
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.accountBalance=accountBalance;
        this.creditCardNumber=creditCardNumber;
        this.password=password;
    }
}
