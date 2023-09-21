import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Floor {
    private int capacity;
    EntryPoint[] entryPoints;
    ExitPoint[] exitPoints;
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
        protected String generateTicket(Scanner sc,ArrayList<Ticket> tickets){

            System.out.println("Enter Customer ID (Leave blank if not registered): ");
            String customerId= sc.nextLine();
            System.out.println("Enter phone number (Leave blank if customer id is given): ");
            String phoneNumber= sc.nextLine();
            Ticket tempTicket; 
            if(customerId.compareTo("")==0 && phoneNumber.compareTo("")==0){
                System.out.println("No data given.. Ticket generation failed");
                return "";
            }
            // If customer id is not given, use different constructor 
            if (customerId.compareTo("")==0){
                tempTicket = new Ticket(phoneNumber, tickets);
            }
            else{
                tempTicket = new Ticket(tickets,customerId); // change to have type detail
            }
            return tempTicket.ticketId;
        }
    }
    class ExitPoint extends Points{
        ExitPoint(char id){
            this.id = id;
        }
        private int costCalculation(Ticket ticket){
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
        protected int payment(Scanner sc,long creditCardNumber,ArrayList<Ticket> tickets){
            //int cost = costCalculation(ticket);
            // We are assuming that credit card is in working condition and the payment is being handeled by some module
            System.out.println("Enter ticket id");
            String ticketId = sc.nextLine();
            for(Ticket t:tickets){
                if(t.ticketId.compareTo(ticketId)==0){
                    System.out.println("Payment successful");
                    tickets.remove(t);
                    return 0;
                }
            }
            System.out.println("Ticket id not valid");
            return -1; 
        }
        protected int payment(int cash,Scanner sc,ArrayList<Ticket> tickets){
            System.out.println("Enter ticket id"); 
            String ticketId = sc.nextLine();
            for(Ticket t:tickets){
                    if(t.ticketId.compareTo(ticketId)==0){
                        int cost = costCalculation(t);
                        if (cash>=cost){
                            System.out.println("Return amount: "+(cash-cost));
                            System.out.println("Payment successful");
                            tickets.remove(t);
                        }
                        else{
                            System.out.println("Given cash is not enough!!");
                        }
                        return 0;
                    }
                }
            System.out.println("Ticket id not valid");
            return -1;    
        }
    }
}
