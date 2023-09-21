import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID; // for customer id generation

public class CustomerInfoPortal {
    CustomerInfoPortal(Scanner sc,ArrayList<Customer> customers){
        System.out.println("Customer Info Portal");
        System.out.println("Existing User?(y/n)");
        String customerId=sc.nextLine();
        if (customerId.compareTo("n")==0){
            this.signUp(customers,sc);
        }
        else{
            this.login(customers, sc);

        }
    }
    
    private void login(ArrayList<Customer> customers,Scanner sc){
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
                this.menu(customer,sc);
            }
        }
    }
    private void signUp(ArrayList<Customer> customers,Scanner sc){
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
        
        login(customers, sc);
    }
    private void menu(Customer c,Scanner sc){
        System.out.println("Menu");
        //add option to pay and just use credit card(can't pay in cash to a machine)
        //should also close the person's ticket after payment
    } 
    

}
