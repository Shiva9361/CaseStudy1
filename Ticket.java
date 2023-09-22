import java.util.ArrayList;
import java.util.UUID; // TO create unique id for tickets
public class Ticket {
    String ticketId;
    String customerId;//null if customer is not registered
    String phoneNumber;// if customer is not registered then phone number shall be used
    String parkingType; // 1,2,3,4 corresponding to the choice in the menu
    long entryTime;
    Vehicle vehicle;
    
    Ticket(ArrayList<Ticket> Tickets,String customerId,String parkingType,ArrayList<Customer> customers,Vehicle vehicle){

        this.ticketId = UUID.randomUUID().toString().substring(0, 7);
        this.customerId=customerId;
        this.entryTime = System.currentTimeMillis();
        this.parkingType=parkingType;
        this.vehicle=vehicle;
        Tickets.add(this);

        for(Customer c:customers){
            if(customerId.compareTo(c.customerId)==0){
                c.latestTicketIdSetter(this.ticketId); // Adding the latest ticket into customer 
                // We are assuming that people always pay when they leave and don't cheat
                // Futher we assume that a person won't come and park multiple vehicles at the same time 
            }
        }
    }
    Ticket(String phoneNumber,ArrayList<Ticket> Tickets,String parkingType,Vehicle vehicle){
        this.ticketId = UUID.randomUUID().toString().substring(0, 7);
        this.phoneNumber=phoneNumber;
        this.entryTime= System.currentTimeMillis();
        this.parkingType=parkingType;
        this.vehicle=vehicle;
        Tickets.add(this);
    }
}
