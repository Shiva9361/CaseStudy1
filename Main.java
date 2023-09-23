import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customerData = new ArrayList<Customer>();
        // data
        customerData.add(new Customer("a", "9999999999", null, 0, 0,"aa"));
        customerData.add(new Customer("b", "8888888888", null, 0, 0,"bb"));
        customerData.add(new Customer("c", "0000000000", null, 0, 0,"cc"));
        
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        ArrayList<Floor> floors = new ArrayList<Floor>();
        
        floors.add(new Floor(2, 3,200,0));
        floors.add(new Floor(2, 3,200,0));
        Floor f1 = floors.get(0);
        
        Floor.EntryPoint e1 = f1.entryPoints[0];
        Floor.EntryPoint e2 = f1.entryPoints[1];  
        Floor.ExitPoint E1 = f1.exitPoints[0]; 
          Employee e1=new Employee("ram",1);
          Employee e2=new Employee("sam",2);
          Employee e3=new Employee("sita",3);
          Employee e4=new Employee("ravi",4);
          Employee e5=new Employee("Lorentz",5);
          Admin a=new Admin();
          ArrayList<Employee> employees=new ArrayList<Employee>();
          employees.add(e1);
          employees.add(e2);
          employees.add(e3);
          employees.add(e4);
          employees.add(e5);
      for(Employee e:employees){
        System.out.print(e.name);
      }

        // Simulating generation of ticket in 2 entry points
        System.out.println("Your ticket ID: "+e1.generateTicket(sc, tickets,customerData));
        System.out.println("Your ticket ID: "+e2.generateTicket(sc, tickets,customerData));
        f1.displayOccupation();
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
