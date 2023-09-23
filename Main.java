import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customerData = new ArrayList<Customer>();
        // customer data
        customerData.add(new Customer("a", "9999999999", null, 0, 0,"aa"));
        customerData.add(new Customer("b", "8888888888", null, 0, 0,"bb"));
        customerData.add(new Customer("c", "0000000000", null, 0, 0,"cc"));
        
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        ArrayList<Floor> floors = new ArrayList<Floor>();
        // For the purpose of demonstration two floors have been added
        floors.add(new Floor(2, 3,200,0));
        floors.add(new Floor(2, 3,200,1));
        Floor f1 = floors.get(0);
        
        Floor.EntryPoint en1 = f1.entryPoints[0];
        Floor.EntryPoint en2 = f1.entryPoints[1];  
        Floor.ExitPoint E1 = f1.exitPoints[0]; 
        //Displaying the structure of each floor including the free spaces
        Floor.displayallStructure(floors);
        Admin a=new Admin("John","1");
        //ArrayList<Employee> employees=new ArrayList<Employee>();
        //Not using the array of employee as the functionality was dropped due to time constraint
        // Showing the ability to change slots
        f1.displayID();
        a.changeSlots(f1.Allotment, sc, f1);
        f1.displayID();
        
        
        // Simulating generation of ticket in 2 entry points
        // These two commands could be run on two different threads to make them run parallely
        System.out.println("Your ticket ID: "+en1.generateTicket(sc, tickets,customerData));
        System.out.println("Your ticket ID: "+en2.generateTicket(sc, tickets,customerData));
        // Display based on the occupation of the foors
        Floor.displayOccuption(floors);

        f1.displayID();
        
        // Simulating payment in one exit
        E1.payment(sc,0000000000000000, tickets);

        //Simulating customer info portal
        f1.customerInfoPortal= new CustomerInfoPortal(sc,customerData,tickets);
        //Simulating electric pannel
        f1.electricPannel=new ElectricPannel(sc);
        //Simlating Electric Pannel
        sc.close();
    }
}
