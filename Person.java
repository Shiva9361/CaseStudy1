import java.util.Scanner;
import java.util.ArrayList;
/*
 * Person is an abstract class from which Employee and admin inherit
 * Admin has the option to change the type of a parking space
 */
abstract class Person{
    String  id;
    String name;
    int Salary;

}
// Employee class extends person, If more time was given, more functionality could have been added
class Employee extends Person{
    int shiftfortheday;
    boolean presence=true;
    
    Employee(String name,String id,int shiftfortheday){
        this. shiftfortheday=shiftfortheday;
        this.id=id;
        this.name=name;
    }
}
/*
 * Admin class extends the abstract class person
 * Admin has the ability to perfrom changeSlots
 * When a slot of a particular type is filled, 
 * the admin can modify any other available slot to convert it to a slot of the desired type
 * 
 * The admib can also add floor data
 */
class Admin extends Person{
    Admin(String name,String id){
        this.name=name;
        this.id=id;
    }
    //The funtion which allows the admin to change slots
    void addFloor(ArrayList<Floor> floors,Scanner sc){
        System.out.println("Number of Entries: ");
        int entryPoints=sc.nextInt();
        System.out.println("Number of Exits: ");
        int exitPoints=sc.nextInt();
        System.out.println("Capacity: ");
        int capacity=sc.nextInt();
        floors.add(new Floor(entryPoints,exitPoints,capacity,Floor.floorCounter+1));
    }
    
    void changeSlots(String Allotment[][],Scanner sc,Floor floor){
        /*
         * We are assuming that the admin knows about the slot codes as he is someone working int the company
         */
        String slot="";
        System.out.println("Enter the slot code for which you need more slots");
        slot=sc.nextLine();
        System.out.println("The available slots are: ");
        for (String parkingType:Floor.parkingTypes){
            if (!(floor.isfull(parkingType, Allotment))&& !(parkingType.compareTo(slot)==0)){
                System.out.println(parkingType);
            }
        }
        System.out.println("Pick from the above slots");
        String picked_slot=sc.nextLine();
        for (String parkingType:Floor.parkingTypes){
            if (parkingType.compareTo(picked_slot)==0 && parkingType.compareTo(slot)!=0){
                String space = floor.freeSpace(parkingType);
                int n = Allotment.length;
                for (int i=0;i<n;i++){
                    for (int j=0;j<n;j++){
                        if (Allotment[i][j].split(" ")[0].compareTo(space)==0){
                            floor.swappedSlots+=1;// This is a used to make sure that we can keep track of the number of swapping done
                            Allotment[i][j] = floor.floorNumber+slot+"x"+floor.swappedSlots+" "+"false";//x stands for extra
                        }
                    }
                }
            }
        
        }
    }
}
        
    
