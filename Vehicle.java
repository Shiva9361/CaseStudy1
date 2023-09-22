public class Vehicle {
    String id;
    String typeofVehicle="";
    String typeofSpace="";

}

class ElectricVehicle extends Vehicle {
    ElectricVehicle(){
        this.typeofVehicle="electric";
        this.typeofSpace="e";
    }
    int charge;
}
class Car extends Vehicle{
    Car(){
        this.typeofVehicle="car";
        this.typeofSpace="c";
    }

}
class Bus extends Vehicle{
    Bus(){
        this.typeofVehicle="bus";
        this.typeofSpace="l";
   }
}
class Truck extends Vehicle{
    Truck(){
        this.typeofVehicle="truck";
        this.typeofSpace="l";

    }
}
class Bike extends Vehicle{
    Bike(){
        this.typeofVehicle="bike";
        this.typeofSpace="m";
    }
}
class Handicapvehicle extends Vehicle{
    Handicapvehicle(){
        this.typeofVehicle="handicap";
        this.typeofSpace="h";
    }
}
