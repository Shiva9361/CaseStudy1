public class Ticket {
    String customerId;//null if customer is not registered
    String phoneNumber;// if customer is not registered then phone number shall be used 
    long entryTime;
    
    Ticket(String customerId,String phoneNumber){
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.entryTime = System.currentTimeMillis();
        
    }
    Ticket(String phoneNumber){
        this.phoneNumber=phoneNumber;
        this.entryTime= System.currentTimeMillis();
    }
}
