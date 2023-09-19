public class Customer {
    String customerId;
    int phoneNumber;
    String address;
    String password;
    int accountBalance;
    int creditcardNumber;
    Customer(String customerId,int phoneNumber,String address,int accountBalance,int creditcardNumber){
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.accountBalance=accountBalance;
        this.creditcardNumber=creditcardNumber;
    }
}
