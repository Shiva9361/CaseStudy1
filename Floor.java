public class Floor {
    private int capacity;
    private char[] entryPoints;
    private char[] exitPoints;
    private boolean[] parkingSpaceCompact;
    private boolean[] parkingSpaceLarge;
    private boolean[] parkingSpacehandicapped;
    private boolean[] parkingSpaceMotercycle;

    int floorId;
    Floor(int capacity,int entryPoints,int exitPoints,int parkingSpaceCompact,int parkingSpaceLarge,int parkingSpacehandicapped,int parkingSpace,int parkingSpaceMotercycle,int floorId){
        this.capacity=capacity;
        this.entryPoints=new char[entryPoints];
        this.exitPoints=new char[exitPoints];
        this.parkingSpaceCompact=new boolean[parkingSpaceCompact];
        this.parkingSpaceLarge=new boolean[parkingSpaceLarge];
        this.parkingSpaceMotercycle=new boolean[parkingSpaceMotercycle];
        this.parkingSpacehandicapped=new boolean[parkingSpacehandicapped];
        this.floorId=floorId;
    }
    abstract class Points{
        String id;
        abstract protected void generateTicket();
        abstract protected void payment(String a);
    }
    class EntryPoint extends Points{
        EntryPoint(String id){
            this.id = id;
        }
        protected void generateTicket(){
            
        }
        protected void payment(String creditCardNumber){
            //Simulate payment
        }
        protected void payment(int cash){
            //Simulate, if more given, return the payment
        }
    }
    class ExitPoint extends Points{
        ExitPoint(String id){
            this.id = id;
        }
        protected void generateTicket(){
            
        }
        protected void payment(String creditCardNumber){
            //Simulate payment
        }
        protected void payment(int cash){
            //Simulate, if more given, return the payment
        }
    }

}
