import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Floor {
    private int capacity;
    // Diffent floors might have different number of entries and exists hence they are implemented as an array 
    // Moreover Each point of entry/exit has to supply tickets and genreate bills hene
    EntryPoint[] entryPoints;
    ExitPoint[] exitPoints;
    /*
     * Parking space is divided based on the type and each is implemented as a boolean array
     */
    private boolean[] parkingSpaceCompact;
    private boolean[] parkingSpaceLarge;
    private boolean[] parkingSpacehandicapped;
    private boolean[] parkingSpaceMotercycle;
    private boolean[] parkingSpaceElectricVehicle;
    
    //private int floorNumber; seems to be of no use

    Floor(int entryPoints,int exitPoints,int parkingSpaceCompact,int parkingSpaceLarge,int parkingSpacehandicapped,int parkingSpaceMotercycle,int parkingSpaceElectricVehicle,int floorNumber){
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
        
        this.parkingSpaceElectricVehicle =new boolean[parkingSpaceElectricVehicle];
        Arrays.fill(this.parkingSpaceElectricVehicle,false);
        
        //this.floorNumber=floorNumber;
        //this.capacity=parkingSpaceCompact+parkingSpaceLarge+parkingSpaceMotercycle+parkingSpacehandicapped;

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

    /*void printLayout(int capacity,int parkingSpaceCompact,int parkingSpaceLarge,int parkingSpacehandicapped,int parkingSpaceMotercycle,int parkingSpaceElectricVehicle){
        for()
    }*/
    
    boolean isfull(boolean[] parkingType){
        for (boolean occupied:parkingType){
            if (!occupied){
                return false;
            }
        }
        return true;
    }
    int freeSpace(boolean[] parkingType){
        for(int i=0;i<parkingType.length;i++){
            if(!parkingType[i]){
                return i;
            }
        }
        return -1;
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
        protected String generateTicket(Scanner sc,ArrayList<Ticket> tickets,ArrayList<Customer> customers){

            System.out.println("Enter Customer ID (Leave blank if not registered): ");
            String customerId= sc.nextLine();
            System.out.println("Enter phone number (Leave blank if customer id is given): ");
            String phoneNumber= sc.nextLine();
            
            String type="";
            while(true){
                System.out.println("Pick a parking type\n1:Compact\n2:Large\n3:Handicapped\n4:Motercycle\n5:Electric");
                type=sc.nextLine();
            
                if (type.compareTo("1")==0){
                    if (!isfull(parkingSpaceCompact)){
                        int allotedSpace = freeSpace(parkingSpaceCompact);
                        parkingSpaceCompact[allotedSpace]=true;
                    
                    }
                    else{
                        System.out.println("Floor full");
                    }
                    break;
                }
                else if (type.compareTo("2")==0){
                    if (!isfull(parkingSpaceLarge)){
                        int allotedSpace = freeSpace(parkingSpaceLarge);
                        parkingSpaceLarge[allotedSpace]=true;
    
                    }
                    else{
                        System.out.println("Floor full");
                    }
                    break;
                }
                else if (type.compareTo("4")==0){
                    if (!isfull(parkingSpaceMotercycle)){
                        int allotedSpace = freeSpace(parkingSpaceMotercycle);
                        parkingSpaceMotercycle[allotedSpace]=true;
                    }
                    else{
                        System.out.println("Floor full");
                    }
                    break;
                }
                else if (type.compareTo("3")==0){
                    if (!isfull(parkingSpacehandicapped)){
                        int allotedSpace = freeSpace(parkingSpacehandicapped);
                        parkingSpacehandicapped[allotedSpace]=true;
                    }
                    else{
                        System.out.println("Floor full");
                    }
                    break;
                }
                else if (type.compareTo("5")==0){
                    if (!isfull(parkingSpaceElectricVehicle)){
                        int allotedSpace = freeSpace(parkingSpaceElectricVehicle);
                        parkingSpaceElectricVehicle[allotedSpace]=true;
                    }
                    else{
                        System.out.println("Floor full");
                    }
                    break;
                }
                else{
                    System.out.println("Invalid choice");
                }
            }
            
            Ticket tempTicket; 
            if(customerId.compareTo("")==0 && phoneNumber.compareTo("")==0){
                System.out.println("No data given.. Ticket generation failed");
                return "";
            }
            // If customer id is not given, use different constructor 
            if (customerId.compareTo("")==0){
                tempTicket = new Ticket(phoneNumber, tickets,Integer.parseInt(type));
            }
            else{
                tempTicket = new Ticket(tickets,customerId,Integer.parseInt(type),customers); //
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
            
            // We are assuming that credit card is in working condition and the payment is being handeled by some module
            System.out.println("Enter ticket id");
            String ticketId = sc.nextLine();
            for(Ticket t:tickets){
                if(t.ticketId.compareTo(ticketId)==0){
                    System.out.println("Cost: "+costCalculation(t));
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
