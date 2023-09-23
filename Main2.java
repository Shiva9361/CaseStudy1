/*
 * This main class has been defined to give a more menu orinted picture of our solution
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customerData = new ArrayList<Customer>();
        // customer data of already existing customers
        customerData.add(new Customer("a", "9999999999", null, 0, 0,"aa"));
        customerData.add(new Customer("b", "8888888888", null, 0, 0,"bb"));
        customerData.add(new Customer("c", "0000000000", null, 0, 0,"cc"));
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        ArrayList<Floor> floors = new ArrayList<Floor>();
           // For the purpose of demonstration two floors have been added
        floors.add(new Floor(2, 3,200,0));
        floors.add(new Floor(2, 3,200,1));
        Floor f1 = floors.get(0);
        
        /*
         * A menu driven Program of sorts
         */
        System.out.println("Do you want to manage or use the parling lot?");
        System.out.println("1.Manage 2.User");
        int in=sc.nextInt();
        if(in==1){
            // Due to time constranint did't implement login for admin
            boolean quit=false;
            while(!quit){
                Admin a=new Admin("John","1");
                System.out.println("Welcome Admin");
                System.out.println("What do you want to do");
                System.out.println("1.display all the floors structure\n2.change the slot\n3.add floor \n4.to quit");
                
                int in1=sc.nextInt();sc.nextLine();
                if(in1==1){
                    Floor.displayallStructure(floors); 
                }
                else if(in1==2) {
                    //ArrayList<Employee> employees=new ArrayList<Employee>();
                    //Not using the array of employee as the functionality was dropped due to time constraint
                    // Showing the ability to change slots
                    f1.displayID();
                    a.changeSlots(f1.Allotment, sc, f1);
                    f1.displayID();

                }
                else if(in1==3){
                    a.addFloor(floors, sc);
                }
                else if(in1==4){
                    quit=true;
                }
                else{
                    System.out.println("Invalid input");
                }
            }
        }
        else{
            boolean quit = false;
            while(!quit){
                System.out.println("You are at??");
                System.out.println("1.entry\n2.exit\n3.use customerInfoPortal\n4.use electricpannel");
                System.out.println("5.quit");
                int in2=sc.nextInt();sc.nextLine();
                if(in2==1){
                       Floor.EntryPoint en1 = f1.entryPoints[0];
                       System.out.println("Your ticket ID: "+en1.generateTicket(sc, tickets,customerData));

                }
                else if(in2==2){

                    Floor.ExitPoint E1 = f1.exitPoints[0]; 
                    //Displaying the structure of each floor including the free spaces
                    // Simulating payment in one exit
                    System.out.print("");
                    E1.payment(sc,0000000000000000, tickets);

                }
                else if(in2==3){
                   //Simulating customer info portal
                    f1.customerInfoPortal= new CustomerInfoPortal(sc,customerData,tickets);
                }
                else if(in2==4){
                    f1.electricPannel=new ElectricPannel(sc);
                     //Simlating Electric Pannel
                }
                else if(in2==5){
                    quit=true;
                }
                else{
                    System.out.println("Invalid");
                }
            }
        }
     sc.close();
    }
}