import java.util.Scanner;

public class CustomerInfoPortal {
    CustomerInfoPortal(Scanner sc,Customer[] customers){
        this.login(customers, sc);
    }
    
    private void login(Customer[] customers,Scanner sc){
        boolean login = false;
        boolean exit = false;
        while(!login && !exit){
            System.out.println("Customer Info Portal");
            System.out.println("Login\nUserName:");
            String customerId = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();
            Customer customer = new Customer(customerId, 0, password, 0, 0);
            for (Customer c:customers){
                if ((c.customerId.compareTo(customerId)==0) && (password.compareTo(c.password)==0)){
                    System.out.println("Logged in");
                    login=true;customer=c;
                    break;
                }
            }
            if(!login){
                System.out.println("Incorrect username/password");
                System.out.println("try again? y/n");
                String option = sc.nextLine();
                if (option.compareTo("y")==0){
                    exit=true;
                }
            }else{
                this.menu(customer,sc);
            }
        }
    }
    private void menu(Customer c,Scanner sc){
        //add option to pay and just use credit card(can't pay in cash to a machine)
        //should also close the person's ticket after payment
    } 
    

}
