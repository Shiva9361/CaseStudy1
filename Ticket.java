/*
 * Implementing Ticket as a class,
 * This class is used to generate a ticket object.
 * Constructor overloading is done to achieve ticket generation in two senarios
 * Case 1) Customer is registered and uses his customer ID
 * Case 2) Customer is not registered and uses his phone number   
 */
import java.util.ArrayList;
import java.util.UUID; // To create unique id for tickets
public class Ticket {
    String ticketId;// Unique id for each ticket
    String customerId; // null if customer is not registered
    String phoneNumber;// if customer is not registered then phone number shall be used
    String parkingType; // refers to the four major types of parking, namely compact,large,electric and hadicap
    long entryTime;
    Vehicle vehicle;
    
    //Constructor for cutomer using their ID 
    Ticket(ArrayList<Ticket> Tickets,String customerId,String parkingType,ArrayList<Customer> customers,Vehicle vehicle){

        this.ticketId = UUID.randomUUID().toString().substring(0, 7);
        this.customerId=customerId;
        this.entryTime = System.currentTimeMillis();// Time is recorded at the time of generation of ticket
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
    // Constructor for Customer using just phone number
    Ticket(String phoneNumber,ArrayList<Ticket> Tickets,String parkingType,Vehicle vehicle){
        this.ticketId = UUID.randomUUID().toString().substring(0, 7);
        this.phoneNumber=phoneNumber;
        this.entryTime= System.currentTimeMillis();
        this.parkingType=parkingType;
        this.vehicle=vehicle;
        Tickets.add(this);
    }
}
