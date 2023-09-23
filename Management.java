
    import java.util.ArrayList;
public class Management{
    //stats for the day
    //employee data can be stored by creating objects 
     
   //should be able to change the shifts of the day
  protected  void staff_info(ArrayList<Employee>Employees){
    System.out.println("Name"+" "+" " +"|"+" "+" "+ "Employeeid"+ " "+" "+ "|"+" "+" " +"Shiftfortoday" +" "+" "+"|" +" "+" "+"presence");
    for(Employee e:Employees){
      System.out.println(e.name+" "+" " +"|"+" "+" "+ e.id+ " "+" "+ "|"+" "+" " +e.shiftfortheday +" "+" "+"|" +" "+" "+e.presence);
    }
    
  }

  protected void stats_of_day(ArrayList<Ticket>Tickets){
    
   System.out.println("Here are the stats of the day");
   System.out.println("customerId"+" "+" " +"|"+" "+" "+ "phoneNumber"+ " "+" "+ "|"+" "+" " +"parkingType" +" "+" "+"|" +" "+" "+"entryTime");
   for(Ticket t:Tickets){
        System.out.println(t.customerId+" "+" " +"|"+" "+" "+ t.phoneNumber+ " "+" "+ "|"+" "+" " +t.parkingType +" "+" "+"|" +" "+" "+t.entryTime);
   }
   //should be able to find the allocation each floor
                
           
           

    }

   
}

