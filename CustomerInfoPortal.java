import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID; // for customer id generation

public class CustomerInfoPortal {
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
    private void signUp(ArrayList<Customer> customers,Scanner sc,ArrayList<Ticket>tickets){
        System.out.println("Enter phone number");
        String phoneNumber=sc.nextLine();
        System.out.println("Enter your Address");
        String address=sc.nextLine();
        System.out.println("Enter your Credit Card Number");
        long creditCardNumber=sc.nextLong();sc.nextLine();
        System.out.println("Enter a new password");
        String password = sc.nextLine();
        String customerId = UUID.randomUUID().toString();
        Customer tempCustomer = new Customer(customerId, phoneNumber, address, 0, creditCardNumber, password);
        customers.add(tempCustomer);
        System.out.println("Account Created !!\nUserid: "+tempCustomer.customerId);
        
        login(customers, sc,tickets);
    }
    private void menu(Customer c,Scanner sc,ArrayList<Ticket> tickets){
        System.out.println("1:Pay pending payment\n0:Quit");
        String choice = sc.nextLine();
        boolean quit = false;
        while(!quit){
            if (choice.compareTo("1")==0){
                payment(sc, c.creditCardNumber, c.latestTicketId,tickets);
            }
            else if (choice.compareTo("0")==0){
                quit=true;
            }
            else{
                System.out.println("Invalid Choice");
            }
        }
        //add option to pay and just use credit card(can't pay in cash to a machine)
        //should also close the person's ticket after payment
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
        for(Ticket t:tickets){
            if(t.ticketId.compareTo(ticketId)==0){
                tickets.remove(t);
                System.out.println("Cost: "+costCalculation(t));
                System.out.println("Payment successful");
            }
        }
    }
    

}
