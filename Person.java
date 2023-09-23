public class Person{
     int  id;
     String name;
     String address;

}
class Employee extends Person{
    Employee(String name,int id){
        this.id=id;
        this.name=name;
    }
        int shiftfortheday=1;
        boolean presence=true;

}
class Admin extends Person{
    void changeSlots(String Allotment[][],Scanner sc,int floornumber){
        System.out.println("Enter the slot code for whatever slot you want to change");
        String p=sc.next();
        System.out.println("the available slots are ");
       
      int n=Allotment.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                   if(Allotment[i][j].split(" ")[1]=="false"){
                         Allotment[i][j]=floornumber+p+"temp"+" "+"true";
                         break;
                   }
            }

        }
       

    };
    //to be able to change the slots

}
