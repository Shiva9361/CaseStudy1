/*
 * Implementing Customer Info Portal as a class which can be instantiated as a object of each floor.
 * Considering that each floor uses one Customer Info Portal, can be scaled easily to add more.
 * All functions implemented are made private for safety.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID; // for customer id generation

public class CustomerInfoPortal implements Payments{
    //Parameterised Constructor 
    Customer customer;
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
            // Creating a temporary Object customer and instantiating it as we need the customer object for further manipulation
            this.customer = new Customer(customerId, "","", 0, 0,password);
            for (Customer c:customers){
                if ((c.customerId.compareTo(customerId)==0) && (password.compareTo(c.passwordGetter())==0)){
                    System.out.println("Logged in");
                    login=true;this.customer=c;
                    break;
                }
            }
            // If the above loop failed to login, it means the credentials given were incorrect. 
            // We are giving option for people to try again as much as they want.
            if(!login){
                System.out.println("Incorrect username/password");
                System.out.println("Try again? y/n");
                String option = sc.nextLine();
                if (option.compareTo("n")==0){
                    exit=true;
                }
            }else{
                // Once logged in, menu is launched
                this.menu(sc,tickets);
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
    private void menu(Scanner sc,ArrayList<Ticket> tickets){
        System.out.println("1:Pay pending payment\n0:Quit");
        boolean quit = false;
        while(!quit){
            String choice = sc.nextLine();
            if (choice.compareTo("1")==0){
                payment(sc, this.customer.creditCardNumberGetter(),tickets);
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
        //add option to pay and just use credit card(can't pay in cash to a machine)
        //should also close the person's ticket after payment
    } 
    /*
     * Cost is calculated on a hourly basis
     * To simulate timeflow, a millisecond is considered half an hour
     */
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
    public int payment(Scanner sc,long creditCardNumber,ArrayList<Ticket>tickets){
        String ticketId = this.customer.latestTicketIdGetter();
        // Assuming credit card works
        // Using the last ticket id associated with the customer to do payments
        // If that string is null or if the ticket isn't pre
        for(Ticket t:tickets){
            if(t.ticketId.compareTo(ticketId)==0){
                tickets.remove(t);
                System.out.println("Cost: "+costCalculation(t));
                System.out.println("Payment successful");
                return 0;
            }
        }System.out.println("No pending Payments");
        return 0;
    }
    /*
     * Not implementing cash payment in customer info portal, A fully digital system.
     */
    public int payment(Scanner sc,ArrayList<Ticket> tickets){ 
        return 0;
    }
}
