import java.util.Scanner;

public class CustomerInfoPortal {
    CustomerInfoPortal(Scanner sc,Customer[] customers){
        login(customers, sc);
    }
    
    public static void login(Customer[] customers,Scanner sc){
        boolean login = false;
        boolean exit = false;
        while(!login && !exit){
            System.out.println("Customer Info Portal");
            System.out.println("Login\nUserName:");
            String name = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();
            Customer customer;
            for (Customer c:customers){
                if ((c.customerId.compareTo(name)==0) && (password.compareTo(c.password)==0)){
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
                menu(c);
            }
        }
    }
    public 
    

}
