/*
 * Implementing Ticket as a class,
 * This class is used to generate a ticket object.
 * Constructor overloading is done to achieve ticket generation in two senarios
 * Case 1) Customer is registered and uses his customer ID
 * Case 2) Customer is not registered and uses his phone number   
 */
import java.util.ArrayList;
import java.util.UUID; // TO create unique id for tickets
public class Ticket {
    String ticketId;
    String customerId;//null if customer is not registered
    String phoneNumber;// if customer is not registered then phone number shall be used
    int parkingType; // 1,2,3,4 corresponding to the choice in the menu
    long entryTime;
    
    Ticket(ArrayList<Ticket> Tickets,String customerId,int parkingType,ArrayList<Customer> customers){

        this.ticketId = UUID.randomUUID().toString().substring(0, 7);
        this.customerId=customerId;
        this.entryTime = System.currentTimeMillis();
        this.parkingType=parkingType;
        Tickets.add(this);

        for(Customer c:customers){
            if(customerId.compareTo(c.customerId)==0){
                c.latestTicketId=this.ticketId; // Adding the latest ticket into customer 
                // We are assuming that people always pay when they leave and don't cheat
                // Futher we assume that a person won't come and park multiple vehicles at the same time 
            }
        }
    }
    Ticket(String phoneNumber,ArrayList<Ticket> Tickets,int parkingType){
        this.ticketId = UUID.randomUUID().toString().substring(0, 7);
        this.phoneNumber=phoneNumber;
        this.entryTime= System.currentTimeMillis();
        this.parkingType=parkingType;
        Tickets.add(this);
    }
}
