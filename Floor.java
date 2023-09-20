import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Floor {
    private int capacity;
    private EntryPoint[] entryPoints;
    private ExitPoint[] exitPoints;
    private boolean[] parkingSpaceCompact;
    private boolean[] parkingSpaceLarge;
    private boolean[] parkingSpacehandicapped;
    private boolean[] parkingSpaceMotercycle;
    private int floorNumber;

    Floor(int entryPoints,int exitPoints,int parkingSpaceCompact,int parkingSpaceLarge,int parkingSpacehandicapped,int parkingSpaceMotercycle,int floorNumber){
        this.entryPoints=new EntryPoint[entryPoints];
        this.exitPoints=new ExitPoint[exitPoints];
        
        this.parkingSpaceCompact=new boolean[parkingSpaceCompact];
        Arrays.fill(this.parkingSpaceCompact,false);
        
        this.parkingSpaceLarge=new boolean[parkingSpaceLarge];
        Arrays.fill(this.parkingSpaceLarge,false);
        
        this.parkingSpaceMotercycle=new boolean[parkingSpaceMotercycle];
        Arrays.fill(this.parkingSpaceMotercycle,false);
        
        this.parkingSpacehandicapped=new boolean[parkingSpacehandicapped];
        Arrays.fill(this.parkingSpacehandicapped,false);
        
        this.floorNumber=floorNumber;
        this.capacity=parkingSpaceCompact+parkingSpaceLarge+parkingSpaceMotercycle+parkingSpacehandicapped;

        char entryId='a';
        for(int i=0;i<entryPoints;i++){
            this.entryPoints[i]=new EntryPoint((char)(entryId));
            entryId = (char)(entryId+1);
        }

        char exitId='A';
        for(int i=0;i<exitPoints;i++){
            this.exitPoints[i]=new ExitPoint((char)(exitId));
            exitId = (char)(exitId+1);
        }
    }
    
    boolean isfull(boolean[] parkingType){
        for (boolean occupied:parkingType){
            if (!occupied){
                return false;
            }
        }
        return true;
    }
    
    abstract class Points{
        char id;
        protected void generateTicket(){}
        protected void payment(String a){}
    }
    class EntryPoint extends Points{
        EntryPoint(char id){
            this.id = id;
        }
        protected void generateTicket(Scanner sc,ArrayList<Ticket> tickets){

            System.out.println("Enter Customer ID (Leave blank if not registered): ");
            String customerId= sc.nextLine();
            System.out.println("Enter phone number: ");
            String phoneNumber= sc.nextLine();

            // If customer id is not given, use different constructor 
            if (customerId.compareTo("")==0){
                new Ticket(phoneNumber, tickets);
            }
            else{
                new Ticket(customerId, phoneNumber, tickets);
            }
            
        }
    }
    class ExitPoint extends Points{
        ExitPoint(char id){
            this.id = id;
        }
        protected int costCalculation(Ticket ticket){
            float timeSpent = (float)((ticket.entryTime-System.currentTimeMillis())/2);// half an hour per millis
            int cost=0;
            if (timeSpent<=1.0){
                cost=20;
            }
            else if(timeSpent<=2){
                cost=30;
            }
            else if(timeSpent<=3){
                cost=40;
            }
            else{
                cost=20+2*10+(int)(timeSpent-3)*5;
            }
            return cost;
        }
        protected void payment(String creditCardNumber,Ticket ticket,ArrayList<Ticket> tickets){
            //int cost = costCalculation(ticket);
            // We are assuming that credit card is in working condition and the payment is being handeled by some module
                System.out.println("Payment successful");
                tickets.remove(ticket);
            
        }
        protected void payment(int cash,Ticket ticket,ArrayList<Ticket> tickets){
            int cost = costCalculation(ticket);
            if (cash>=cost){
                System.out.println("Return amount: "+(cash-cost));
                System.out.println("Payment successful");
                tickets.remove(ticket);
            }
            else{
                System.out.println("Given cash is not enough!!");
            }
            //Simulate, if more given, return the payment
        }
    }


}
