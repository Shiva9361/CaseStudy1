import java.util.ArrayList;
import java.util.UUID; // TO create unique id for tickets
public class Ticket {
    UUID ticketId;
    String customerId;//null if customer is not registered
    String phoneNumber;// if customer is not registered then phone number shall be used 
    long entryTime;
    
    Ticket(String customerId,String phoneNumber,ArrayList<Ticket> Tickets){
        this.ticketId = UUID.randomUUID();
        this.customerId=customerId;
        this.phoneNumber=phoneNumber;
        this.entryTime = System.currentTimeMillis();
        Tickets.add(this);
    }
    Ticket(String phoneNumber,ArrayList<Ticket> Tickets){
        this.ticketId = UUID.randomUUID();
        this.phoneNumber=phoneNumber;
        this.entryTime= System.currentTimeMillis();
        Tickets.add(this);
    }
}
