import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customerData = new ArrayList<Customer>();
        // customer data of  already existing customers
        customerData.add(new Customer("a", "9999999999", null, 0, 0,"aa"));
        customerData.add(new Customer("b", "8888888888", null, 0, 0,"bb"));
        customerData.add(new Customer("c", "0000000000", null, 0, 0,"cc"));
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        ArrayList<Floor> floors = new ArrayList<Floor>();
           // For the purpose of demonstration two floors have been added
        floors.add(new Floor(2, 3,200,0));
        floors.add(new Floor(2, 3,200,1));
        Floor f1 = floors.get(0);
        System.out.println("Do you want to manage or use the parling lot?");
        System.out.println("1.Manage 2.User");
        int in=sc.nextInt();
        if(in==1){
            Admin a=new Admin("John","1");
            System.out.println("Welcome Admin");
            System.out.println("What do you want to do");
            System.out.println("1.display all the floors structure\n
                               2.change the slot");
            
            int in1=sc.nextInt();
            if(in==1){
                Floor.displayallStructure(floors); 
            }
            else {
                  //ArrayList<Employee> employees=new ArrayList<Employee>();
            //Not using the array of employee as the functionality was dropped due to time constraint
            // Showing the ability to change slots
            f1.displayID();
            a.changeSlots(f1.Allotment, sc, f1);
            f1.displayID();
                
            }
            }
        else{
         System.out.println("You are at??");
         System.out.println(1.entry 2.exit 3.use customerInfoPortal 4.use electricpanel);
         int in2=sc.nextInt();
            if(in2==1){
                   Floor.EntryPoint en1 = f1.entryPoints[0];
                   Floor.EntryPoint en2 = f1.entryPoints[1];
                // Simulating generation of ticket in 2 entry points
                 //when ticket is generated,you will only get to see ticketids
                 // These two commands could be run on two different threads to make them run parallely
                   System.out.println("Your ticket ID: "+en1.generateTicket(sc, tickets,customerData));
                   System.out.println("Your ticket ID: "+en2.generateTicket(sc, tickets,customerData));
                    
            }
            else if(in2==2){
                
            Floor.ExitPoint E1 = f1.exitPoints[0]; 
        //Displaying the structure of each floor including the free spaces
        // Simulating payment in one exit
                   E1.payment(sc,0000000000000000, tickets);

            }
           else if(in2==3){
               //Simulating customer info portal
           f1.customerInfoPortal= new CustomerInfoPortal(sc,customerData,tickets);
           }
            else {
                 f1.electricPannel=new ElectricPannel(sc);
                 //Simlating Electric Pannel
            }
              // Display based on the occupation of the foors
             Floor.displayOccuption(floors);
            }
     
     sc.close();
    }
}
