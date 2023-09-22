import java.util.ArrayList;

public class Customer {
    String customerId;
    private String phoneNumber;
    private String address;
    private String password;
    private String latestTicketId="";
    private int accountBalance;
    private long creditCardNumber;
    Customer(String customerId,String phoneNumber,String address,int accountBalance,long creditCardNumber,String password){
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.accountBalance=accountBalance;
        this.creditCardNumber=creditCardNumber;
        this.password=password;
    }
    static boolean customerExists(String customerId,ArrayList<Customer>customers){
        for(Customer customer:customers ){
            if(customer.customerId.compareTo(customerId)==0){
                return true;
            }
        }
        return false;
    }
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
    void latestTicketIdSetter(String latestTicketId){
        this.latestTicketId=latestTicketId;
    }
}
