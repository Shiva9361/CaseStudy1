/*
 * Implementing Customer Info Portal as a class which can be instantiated as a object of each floor.
 * Considering that each floor uses one Customer Info Portal, can be scaled easily to add more.
 * All functions implemented are made private for safety.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID; // for customer id generation

public class CustomerInfoPortal {
    //Parameterised constructor
    CustomerInfoPortal(Scanner sc,ArrayList<Customer> customers,ArrayList<Ticket> tickets){
        System.out.println("Customer Info Portal");
        System.out.println("Existing User?(y/n)");
        String customerId=sc.nextLine();
        if (customerId.compareTo("n")==0){
            this.signUp(customers,sc,tickets);
        }
        else{
            this.login(customers, sc,tickets);
        }
    }
    // The login method is the entry point of the program, Only after login,We can access the menu
    private void login(ArrayList<Customer> customers,Scanner sc,ArrayList<Ticket> tickets){
        boolean login = false;
        boolean exit = false;
        while(!login && !exit){
            System.out.println("Login\nUserName:");
            String customerId = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();
            Customer customer = new Customer(customerId, "","", 0, 0,password);
            for (Customer c:customers){
                if ((c.customerId.compareTo(customerId)==0) && (password.compareTo(c.password)==0)){
                    System.out.println("Logged in");
                    login=true;customer=c;
                    break;
                }
            }
            if(!login){
                System.out.println("Incorrect username/password");
                System.out.println("Try again? y/n");
                String option = sc.nextLine();
                if (option.compareTo("n")==0){
                    exit=true;
                }
            }else{
                this.menu(customer,sc,tickets);
            }
        }
    }
    /*
     * Everyone is given the option to sign up, Basic details are retrieved from the user to save the customer as a object.
     * An Unique user Id is generated for the customer and the cutomer can use this id to further login and use the portal  
     */
    private void signUp(ArrayList<Customer> customers,Scanner sc,ArrayList<Ticket>tickets){
        System.out.println("Enter phone number");
        String phoneNumber=sc.nextLine();
        System.out.println("Enter your Address");
        String address=sc.nextLine();
        System.out.println("Enter your Credit Card Number");
        long creditCardNumber=sc.nextLong();sc.nextLine();
        System.out.println("Enter a new password");
        String password = sc.nextLine();
        String customerId = UUID.randomUUID().toString().substring(0, 7);
        Customer tempCustomer = new Customer(customerId, phoneNumber, address, 0, creditCardNumber, password);
        customers.add(tempCustomer);
        System.out.println("Account Created !!\nUserid: "+tempCustomer.customerId);
        System.out.println("");
        login(customers, sc,tickets);
    }
    private void menu(Customer c,Scanner sc,ArrayList<Ticket> tickets){
        System.out.println("1:Pay pending payment\n0:Quit");
        boolean quit = false;
        while(!quit){
            String choice = sc.nextLine();
            if (choice.compareTo("1")==0){
                payment(sc, c.creditCardNumber, c.latestTicketId,tickets);
            }
            else if (choice.compareTo("0")==0){
                quit=true;
            }
            else{
                System.out.println("Invalid Choice");
            }
            System.out.println();
            System.out.println("1:Pay pending payment\n0:Quit");
        }
    } 
    private int costCalculation(Ticket ticket){
        float timeSpent = (float)((ticket.entryTime-System.currentTimeMillis())/2);// half an hour per millis
        int cost=0;
        if (timeSpent<=1.0){
            cost=10;
        }
        else if(timeSpent<=2){
            cost=20;
        }
        else if(timeSpent<=3){
            cost=30;
        }
        else{
            cost=10+2*10+(int)(timeSpent-3)*3;// cost is a bit less for registered cutomers
        }
        return cost;
    }
    protected void payment(Scanner sc,long creditCardNumber,String ticketId,ArrayList<Ticket>tickets){
        // Again assuming credit card works
        boolean ispresent=false;
        for(Ticket t:tickets){
            if(t.ticketId.compareTo(ticketId)==0){
                tickets.remove(t);
                System.out.println("Cost: "+costCalculation(t));
                System.out.println("Payment successful");
                ispresent=true;
                break;
            }
        }if(!ispresent){
            System.out.println("No pending Payments");
        }
    }
    

}
