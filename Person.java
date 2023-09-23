import java.util.Scanner;

abstract class Person{
     int  id;
     String name;
     String address;

}

class Employee extends Person{
    int shiftfortheday=1;
    boolean presence=true;
    
    Employee(String name,int id){
        this.id=id;
        this.name=name;
    }
}

class Admin extends Person{
    Admin(){

    }
    void changeSlots(String Allotment[][],Scanner sc,Floor floor){
        boolean isvalidSlotcode=false;
        String slot="";
        while(!isvalidSlotcode){
            System.out.println("Enter the slot code for whatever slot you want to change");
            slot=sc.next();
            System.out.println("The available slots are ");

        }

        int n=Allotment.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(Allotment[i][j].split(" ")[1]=="false"){
                    Allotment[i][j]=floor.floorNumber+p+"temp"+" "+"true";
                    break;
                }
            }
        }
       

    };
}
