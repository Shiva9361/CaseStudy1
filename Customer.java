public class Customer {
    String customerId;
    String phoneNumber;
    String address;
    String password;
    int accountBalance;
    int creditCardNumber;
    Customer(String customerId,String phoneNumber,String address,int accountBalance,int creditCardNumber,String password){
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.accountBalance=accountBalance;
        this.creditCardNumber=creditCardNumber;
        this.password=password;
    }
}
