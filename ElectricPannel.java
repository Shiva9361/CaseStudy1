/*
 * Implemmenting Electric pannel as a class which is intantiated as an object of each floor
 */
import java.util.Scanner;
public class ElectricPannel {
    //Parameterised constructor 
    ElectricPannel(Scanner sc){
        System.out.println("Electric Pannel");
        menu(sc);
    }
    /*
     * Only one option is available which is to pay and refill
     * If given more time, Can implement cutomer id integration 
     */
    private void menu(Scanner sc){
        boolean quit = false;
        while(!quit){
            System.out.println();
            System.out.println("1:Pay and refill\n0:Quit");
            String choice = sc.nextLine();
            if (choice.compareTo("1")==0){
                refill(sc);
            }
            else if (choice.compareTo("0")==0){
                quit=true;
            }
            else{
                System.out.println("Invalid Choice");
            }
            
        }
    }private void refill(Scanner sc){
        System.out.println("Enter credit card number: ");
        long creditCardNumber=sc.nextLong();sc.nextLine();
        if (creditCardNumber!=0){
            System.out.println("Enter Amount to refill in Rupees\n(Standard Electricity converion rates apply)");
            int amount = sc.nextInt();sc.nextLine();
            // assuming credit card is valid and people don't cheat
            // assuming payment is being handled by a backend software
            System.out.println("Done recharging for "+amount);
        }
        else{
            System.out.println("No credit card number given..");
        }
    }
}
