import java.util.Scanner;
public class ElectricPannel {
    ElectricPannel(Scanner sc){
        System.out.println("Electric Pannel");
        menu(sc);
    }
    private void menu(Scanner sc){
        System.out.println("1:Pay and refill\n0:Quit");
        boolean quit = false;
        while(!quit){
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
            System.out.println();
            System.out.println("1:Pay and refill\n0:Quit");
        }
    }private void refill(Scanner sc){
        System.out.println("Enter credit card number: ");
        System.out.println("Enter Amount to refill in Rupees\n(Standard Electricity converion rates apply)");
        int amount = sc.nextInt();sc.nextLine();
        //  assuming credit card is valid and people don't cheat
        // assuming payment is being handled by a backend software
        System.out.println("Done recharging for "+amount);
    }
}
