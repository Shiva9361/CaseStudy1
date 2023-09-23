/*
 * Floor is implemented as a class, This class can be instantiated to create an instance of floor
 * Floor has two subclasses namely entry and exit which derive from a parent abstract class and implenet an interface
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Floor {
    public static int floorCounter=0;// Used to keep track of the number of floors created.
    private int capacity;
    // Diffent floors might have different number of entries and exits hence they are implemented as an array 
    // Moreover Each point of entry/exit has to supply tickets and genreate bills. hence each entry/exit point is an object 
    EntryPoint[] entryPoints;
    ExitPoint[] exitPoints;
    static String[] parkingTypes={"m","l","c","h","e"};// These are the various parking types under the hood.
    /*
     * Parking spaces are considered as a two dimensional array
     */
    public String[][] Allotment;
    // Physically the spaces are not the same size but for representation in code, one array element is allotted for each type
    // IN practice, the motor spaces are physically much smaller.
    protected long bankbalance=0;
    // Each floor has it's own customer info portal.
    CustomerInfoPortal customerInfoPortal;
    // Electric pannel is made available for users of electric vehicles
    ElectricPannel electricPannel;
    // Floor number is used to keep track of the floor internally
    public int floorNumber; 
    int swappedSlots;// indicates the number of slots the admin has swapped from a differnt type
    //Parameterised constructor for floor
    Floor(int entryPoints,int exitPoints,int capacity,int floorNumber){
        floorCounter+=1;
        this.entryPoints=new EntryPoint[entryPoints];
        this.exitPoints=new ExitPoint[exitPoints];
        this.capacity=capacity;
        this.floorNumber=floorNumber;
        int numberOfRows=(int)Math.sqrt(capacity)+1;
        Allotment = new String[numberOfRows][numberOfRows];
        /*
         * Of all the spaces available some fraction is made availabe to each type of parking 
         * The selection of this fraction is in accordance to data acquired from previous establishments
         */
        float parkingSpaceCompactFraction=.2f;
        float parkingSpaceLargeFraction=.2f;
        float parkingSpaceMotercycleFraction=.4f;
        float parkingSpacehandicappedFraction=.1f;
        float parkingSpaceElectricVehicleFraction=.1f;
        // Assigning number of parking spaces to each category
        int parkingSpaceCompact=(int)(this.capacity*parkingSpaceCompactFraction);
        int parkingSpaceLarge=(int)(this.capacity*parkingSpaceLargeFraction);
        int parkingSpaceMotercycle=(int)(this.capacity*parkingSpaceMotercycleFraction);
        int parkingSpacehandicapped=(int)(this.capacity*parkingSpacehandicappedFraction);
        int parkingSpaceElectricVehicle=(int)(this.capacity*parkingSpaceElectricVehicleFraction);

        // Initializing the allotment array by giving unique id to each space available 
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
        /*
         * Entry and exit is named in alphabetical order 
         * Theoratically there can be a maximum of 26 entry (In small alphabet) and 
         * 26 exit in large alphabet
         * Each Entry and exit point is statergically placed to reduce congestion. 
         */
        char entryId='a';
        for(int i=0;i<entryPoints;i++){
            this.entryPoints[i]=new EntryPoint((char)(entryId),this.floorNumber);
            entryId = (char)(entryId+1);
        }

        char exitId='A';
        for(int i=0;i<exitPoints;i++){
            this.exitPoints[i]=new ExitPoint((char)(exitId),this.floorNumber);
            exitId = (char)(exitId+1);
        }
    }
    //A Ststic function which can be called to diplay the structure fo all floors and also the occuption details
    public static void displayallStructure(ArrayList<Floor> floors){
        System.out.println("Structure of floors");
        for(Floor floor:floors){
            System.out.println("Floor: "+floor.floorNumber);
            floor.displayOccupation();
        }
    }
    // A method which can be used to get the amount of free space availabe
    public int freespaceGetter(String parkingType){
        int n=this.Allotment.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if ((Allotment[i][j].substring(1, 2).compareTo(parkingType)==0) && (Allotment[i][j].split(" ")[1].compareTo("false")==0)){
                    if (Allotment[i][j].split(" ")[0].length()==4 ){
                        if (Allotment[i][j].split(" ")[0].substring(2, 3).compareTo("x")==0){
                            continue;
                        }
                        return Integer.parseInt(Allotment[i][j].split(" ")[0].substring(2, 4));
                    }
                    return Integer.parseInt(Allotment[i][j].split(" ")[0].substring(2, 3));
                }
            }
        }
        return -1;
    }
    // A ststic method which can be used to find the number of vacant spots of each type
    // Does not include extra space created by admin
    // This is because that allotment was just temporary
    public static void displayOccuption(ArrayList<Floor> floors){
        for(Floor floor:floors){
            System.out.println("Floor: "+floor.floorNumber+"\n");
            for(String parkingType:Floor.parkingTypes){
                int count = floor.freespaceGetter(parkingType);
                if(parkingType.compareTo("m")==0){
                    System.out.println("MotorCycle Space Remaining: "+count);
                }
                else if(parkingType.compareTo("l")==0){
                    System.out.println("Large Space Remaining: "+count);
                }
                else if(parkingType.compareTo("c")==0){
                    System.out.println("Compact Space Remaining: "+count);
                }
                else if(parkingType.compareTo("e")==0){
                    System.out.println("Electric vehicle Space Remaining: "+count);
                }
                else if(parkingType.compareTo("h")==0){
                    System.out.println("Handicap Space Remaining: "+count);
                } 
            }System.out.println();
        }
    }
    // Display method used to display the slots in 2D space
    void displayID(){
        System.out.println("ID of various parking spaces");
        int n = this.Allotment.length;
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (this.Allotment[i][j].split(" ")[0].length()==3){
                    System.out.print(this.Allotment[i][j].split(" ")[0]+"  ");
                }
                else{
                    System.out.print(this.Allotment[i][j].split(" ")[0]+" ");
                }
            }
            System.out.println();
        }
    }
    // Display method used to display the occuptaion of floor in 2D space.
    void displayOccupation(){
        int n = this.Allotment.length;
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if(this.Allotment[i][j].compareTo("")!=0){
                    if (this.Allotment[i][j].split(" ")[1].length()==4){
                        System.out.print("full  ");
                    }
                    else{
                        System.out.print("empty ");
                    }
                }
            }
            System.out.println();
        }
    }
    // Method used to check if one parking type is full
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
    // Method used to find free parking space of a specific type and return it to be alloted for the customer
    String freeSpace(String parkingType){
        int n = this.Allotment.length;
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
    /*
     * Points is an abstract class which is extended by to child classes namely Entry and exit points
     * Entry class overrides the generate ticket method of points
     */
    abstract class Points{
        char id;
        int floorNumber;
        protected String generateTicket(Scanner sc,ArrayList<Ticket> tickets,ArrayList<Customer> customers){
            return "";
        }

    }
    //Child class which extends abstract class points
    class EntryPoint extends Points{
        //Parametrised Constructor
        EntryPoint(char id,int floorNumber){
            this.id = id;
            this.floorNumber=floorNumber;
        }
        // Method overrided from parent abstract class
        protected String generateTicket(Scanner sc,ArrayList<Ticket> tickets,ArrayList<Customer> customers){
            boolean correctInfo =false;
            boolean alloted = false;
            String customerId="";
            String phoneNumber="";
            //Checking if atleast either a valid customer ID or phone number is given else asking for details again
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
                        String allotedSpace = freeSpace("e");
                        System.out.println("Alloted space id: "+allotedSpace);
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
                        String allotedSpace = freeSpace("c");
                        System.out.println("Alloted space id: "+allotedSpace);
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
                        String allotedSpace = freeSpace("l");
                        System.out.println("Alloted space id: "+allotedSpace);
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
                        String allotedSpace = freeSpace("l");
                        System.out.println("Alloted space id: "+allotedSpace);
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
                        String allotedSpace = freeSpace("m");
                        System.out.println("Alloted space id: "+allotedSpace);
                        alloted=true;
                    }
                    else{
                         System.out.println("Floor full");
                    }
                }
                /*
                 * If the user inputs a vehicle that's not defined in our vehicle class,
                 * We give an option for them to pick between compact and large spaces
                 */
                else{
                    System.out.println("Vehicle not found in our database ..");
                    System.out.println("Choose between these types");
                    while(true){
                        System.out.println("1:Compact\n2:Large");
                        type=sc.nextLine();
                    
                        if (type.compareTo("1")==0){
                            if (!isfull("c",Allotment)){
                                String allotedSpace = freeSpace("c");
                                System.out.println("Alloted space id: "+allotedSpace);
                            }
                            else{
                                System.out.println("Floor full");
                            }
                            break;
                        }
                        else if (type.compareTo("2")==0){
                            if (!isfull("l",Allotment)){
                                String allotedSpace = freeSpace("l");
                                System.out.println("Alloted space id: "+allotedSpace);
                            }
                            else{
                                System.out.println("Floor full");
                            }
                            break;
                        }
                    }
                }
            }   
            /*
             * If the person is handicapped, alloting handicap spots
             */
            else{
                if (!isfull("h",Allotment)){
                    String allotedSpace = freeSpace("h");
                    System.out.println("Alloted space id: "+allotedSpace);
                    alloted=true;
                }
                else{
                    System.out.println("Floor full");
                }
            }
            /*
             * If for some reason no parking spot is alloted, we don't need to generate tickets   
             */
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
    /*
     * Class exit point extends the abstract class point and implemenents the payment interface
     */
    class ExitPoint extends Points implements Payments{
        ExitPoint(char id,int floorNumber){
            this.id = id;
            this.floorNumber=floorNumber;
        }
        //Cost calculation is done using the following logic 
        //A simple hour based system is employed
        //For simulating purposes, the time is scaled.
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
        /*
         * Payment method is impelmeted using method overloading 
         * A differnt method is called depending upon whether the payment is by cash or credit card 
         */
        public int payment(Scanner sc,long creditCardNumber,ArrayList<Ticket> tickets){
            
            // We are assuming that credit card is in working condition and the payment is being handeled by some module
            System.out.println("Enter ticket id");
            String ticketId = sc.nextLine();
            for(Ticket t:tickets){
                if(t.ticketId.compareTo(ticketId)==0){
                    System.out.println("Cost: "+costCalculation(t));
                    System.out.println("Payment successful");
                    //As soon as the payment is done, the ticket is removed from the pending payments buffer.
                     bankbalance+=costCalculation(t);
                    tickets.remove(t);
                    return 0;
                }
            }
            System.out.println("Ticket id not valid");
            return -1; 
        }
        public int payment(Scanner sc,ArrayList<Ticket> tickets){
            System.out.println("Enter ticket id"); 
            String ticketId = sc.nextLine();
            for(Ticket t:tickets){
                    if(t.ticketId.compareTo(ticketId)==0){
                        int cost = costCalculation(t);
                        System.out.println("Amount to be paid: "+cost);
                        System.out.println("Amount deposited : ");
                        int cash = sc.nextInt();sc.nextLine();
                        if (cash>=cost){
                            System.out.println("Return amount: "+(cash-cost));
                            System.out.println("Payment successful");
                             bankbalance+=costCalculation(t);
                            tickets.remove(t);//As soon as the payment is done, the ticket is removed from the pending payments buffer.
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
