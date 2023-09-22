// dk if this is required 
abstract class vehicle {
   String id;
  String typeofVehicle;
  String typeofSpace;

}

class electricVehicle extends vehicle {
    electricVehicle(){
    this.id="";
    this.typeofVehicle="electric";
   this.typeofSpace="";
    }

    int charge;
}
class car extends vehicle{
    car(){
    typeofVehicle="car";
     id="";
    
    this.typeofSpace="";
    }

}
class bus extends vehicle{
   bus(){
     typeofVehicle="car";
    typeofVehicle="car";
     id="";
    this.typeofVehicle="electric";
    this.typeofSpace="";
   }
}
class truck extends vehicle{
    truck(){

    
     typeofVehicle="car";
     id="";
    this.typeofVehicle="electric";
    this.typeofSpace="";
    }
    
}
class bike extends vehicle{
bike(){

    
     typeofVehicle="car";
     id="";
    this.typeofVehicle="electric";
    this.typeofSpace="";
    }
}


