import java.util.ArrayList;
import java.util.Scanner;

public class Floor {
    private int capacity;
    // Diffent floors might have different number of entries and exits hence they are implemented as an array 
    // Moreover Each point of entry/exit has to supply tickets and genreate bills hene
    EntryPoint[] entryPoints;
    ExitPoint[] exitPoints;
    /*
     * Parking space is divided based on the type and each is implemented as a boolean array
     */
    private String[][] Allotment;
    CustomerInfoPortal customerInfoPortal;
    ElectricPannel electricPannel;
    private int floorNumber; 

    Floor(int entryPoints,int exitPoints,int capacity,int floorNumber){
        this.entryPoints=new EntryPoint[entryPoints];
        this.exitPoints=new ExitPoint[exitPoints];
        this.capacity=capacity;
        this.floorNumber=floorNumber;
        int numberOfRows=(int)Math.sqrt(capacity)+1;
        Allotment = new String[numberOfRows][numberOfRows];

        float parkingSpaceCompactFraction=.2f;
        float parkingSpaceLargeFraction=.2f;
        float parkingSpaceMotercycleFraction=.4f;
        float parkingSpacehandicappedFraction=.1f;
        float parkingSpaceElectricVehicleFraction=.1f;

        int parkingSpaceCompact=(int)(this.capacity*parkingSpaceCompactFraction);
        int parkingSpaceLarge=(int)(this.capacity*parkingSpaceLargeFraction);
        int parkingSpaceMotercycle=(int)(this.capacity*parkingSpaceMotercycleFraction);
        int parkingSpacehandicapped=(int)(this.capacity*parkingSpacehandicappedFraction);
        int parkingSpaceElectricVehicle=(int)(this.capacity*parkingSpaceElectricVehicleFraction);

        for (int i=0;i<numberOfRows;i++){
            for (int j=0;j<numberOfRows;j++){
                if (parkingSpaceCompact>0){
                    Allotment[i][j] = this.floorNumber+"c"+(parkingSpaceCompact)+" "+"false";
                    parkingSpaceCompact--;
                }
                else if (parkingSpaceLarge>0){
                    Allotment[i][j] = this.floorNumber+"l"+(parkingSpaceLarge)+" "+"false";
                    parkingSpaceLarge--;
                }
                else if (parkingSpaceMotercycle>0){
                    Allotment[i][j] = this.floorNumber+"m"+(parkingSpaceMotercycle)+" "+"false";
                    parkingSpaceMotercycle--;
                }
                else if (parkingSpacehandicapped>0){
                    Allotment[i][j] = this.floorNumber+"h"+(parkingSpacehandicapped)+" "+"false";
                    parkingSpacehandicapped--;
                }
                else if (parkingSpaceElectricVehicle>0){
                    Allotment[i][j] = this.floorNumber+"e"+(parkingSpaceElectricVehicle)+" "+"false";
                    parkingSpaceElectricVehicle--;
                }
                else{
                    Allotment[i][j]="";
                }
            }
        }

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
    boolean isfull(String parkingType,String[][] Allotment){
        int n = Allotment.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                 if (Allotment[i][j].substring(1, 2).compareTo(parkingType)==0){
                    return false;
                }
            }
        }
        return true;
    }
    String freeSpace(String parkingType,String[][] Allotment){
        int n = Allotment.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                 if ((Allotment[i][j].substring(1, 2).compareTo(parkingType)==0) && (Allotment[i][j].split(" ")[1].compareTo("false")==0)){
                    Allotment[i][j] = Allotment[i][j].split(" ")[0]+" "+"true";
                    return Allotment[i][j].split(" ")[0];
                }
            }
        }
        return "";
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
            boolean correctInfo =false;
            boolean alloted = false;
            String customerId="";
            String phoneNumber="";
            while(!correctInfo){
                System.out.println("Enter Customer ID (Leave blank if not registered): ");
                customerId= sc.nextLine();
                System.out.println("Enter phone number (Leave blank if customer id is given): ");
                phoneNumber= sc.nextLine();
                if (customerId.compareTo("")==0 && phoneNumber.compareTo("")==0){
                    System.out.println("No data entered\nTry again");
                }
                else if(!(customerId.compareTo("")==0) && !Customer.customerExists(customerId,customers)){
                    System.out.println("Ur ID id incorrect\nTry again");
                }
                else{
                    correctInfo=true;
                }
            }
            System.out.println("Are you handicapped?(y/n)");
            String choice = sc.nextLine();
            
            String typeOfVehicle="";
            Vehicle vehicle = new Vehicle();

            if(choice.compareTo("n")==0){
                System.out.println("Enter vehicle type: ");
                String type=sc.nextLine();
                
                if (type.compareTo("electric")==0){
                    vehicle = new ElectricVehicle();
                    typeOfVehicle="e";
                    if (!isfull("e",Allotment)){
                        String allotedSpace = freeSpace("e",Allotment);
                        System.out.println(allotedSpace);
                        alloted=true;
                    }
                    else{
                         System.out.println("Floor full");
                    }
                }
                else if (type.compareTo("car")==0){
                    vehicle = new Car();
                    typeOfVehicle="c";
                    if (!isfull("c",Allotment)){
                        String allotedSpace = freeSpace("c",Allotment);
                        System.out.println(allotedSpace);
                        alloted=true;
                    }
                    else{
                         System.out.println("Floor full");
                    }
                }
                else if (type.compareTo("bus")==0){
                    vehicle = new Bus();
                    typeOfVehicle="l";
                    if (!isfull("l",Allotment)){
                        String allotedSpace = freeSpace("l",Allotment);
                        System.out.println(allotedSpace);
                        alloted=true;
                    }
                    else{
                         System.out.println("Floor full");
                    }
                }
                else if (type.compareTo("truck")==0){
                    vehicle = new Truck();
                    typeOfVehicle="l";
                    if (!isfull("l",Allotment)){
                        String allotedSpace = freeSpace("l",Allotment);
                        System.out.println(allotedSpace);
                        alloted=true;
                    }
                    else{
                         System.out.println("Floor full");
                    }
                }
                else if (type.compareTo("bike")==0){
                    vehicle = new Bike();
                    typeOfVehicle="m";
                    if (!isfull("m",Allotment)){
                        String allotedSpace = freeSpace("m",Allotment);
                        System.out.println(allotedSpace);
                        alloted=true;
                    }
                    else{
                         System.out.println("Floor full");
                    }
                }
                else{
                    System.out.println("Vehicle not found in our database ..");
                    System.out.println("Choose between these types");
                    while(true){
                        System.out.println("1:Compact\n2:Large");
                        type=sc.nextLine();
                    
                        if (type.compareTo("1")==0){
                            if (!isfull("c",Allotment)){
                                String allotedSpace = freeSpace("c",Allotment);
                                System.out.println(allotedSpace);
                            }
                            else{
                                System.out.println("Floor full");
                            }
                            break;
                        }
                        else if (type.compareTo("2")==0){
                            if (!isfull("l",Allotment)){
                                String allotedSpace = freeSpace("l",Allotment);
                                System.out.println(allotedSpace);
                            }
                            else{
                                System.out.println("Floor full");
                            }
                            break;
                        }
                    }
                }
            }   
            else{
                if (!isfull("h",Allotment)){
                    String allotedSpace = freeSpace("h",Allotment);
                    System.out.println(allotedSpace);
                }
                else{
                    System.out.println("Floor full");
                }
            }
            if(alloted){
                Ticket tempTicket; 
                if(customerId.compareTo("")==0 && phoneNumber.compareTo("")==0){
                    System.out.println("No data given.. Ticket generation failed");
                    return "";
                }
                // If customer id is not given, use different constructor 
                if (customerId.compareTo("")==0){
                    tempTicket = new Ticket(phoneNumber, tickets,typeOfVehicle,vehicle);
                }
                else{
                    tempTicket = new Ticket(tickets,customerId,typeOfVehicle,customers,vehicle); //
                }
                return tempTicket.ticketId;
            }
            return "";
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
