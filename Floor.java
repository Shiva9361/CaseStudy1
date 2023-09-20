public class Floor {
    private int capacity;
    private EntryPoint[] entryPoints;
    private ExitPoint[] exitPoints;
    private boolean[] parkingSpaceCompact;
    private boolean[] parkingSpaceLarge;
    private boolean[] parkingSpacehandicapped;
    private boolean[] parkingSpaceMotercycle;
    private int floorNumber;

    Floor(int entryPoints,int exitPoints,int parkingSpaceCompact,int parkingSpaceLarge,int parkingSpacehandicapped,int parkingSpaceMotercycle,int floorNumber){
        this.entryPoints=new EntryPoint[entryPoints];
        this.exitPoints=new ExitPoint[exitPoints];
        this.parkingSpaceCompact=new boolean[parkingSpaceCompact];
        this.parkingSpaceLarge=new boolean[parkingSpaceLarge];
        this.parkingSpaceMotercycle=new boolean[parkingSpaceMotercycle];
        this.parkingSpacehandicapped=new boolean[parkingSpacehandicapped];
        this.floorNumber=floorNumber;
        this.capacity=parkingSpaceCompact+parkingSpaceLarge+parkingSpaceMotercycle+parkingSpacehandicapped;

        char entryId='a';
        for(int i=0;i<entryPoints;i++){
            this.entryPoints[i]=new EntryPoint((char)(entryId));
            entryId = (char)(entryId+1);
        }

        char exitId='A';
        for(int i=0;i<exitPoints;i++){
            this.exitPoints[i] = new ExitPoint((char)(exitId+1));
            exitId = (char)(exitId+1);
        }
    }
    boolean isfull(boolean[] parkingType){
        for (boolean occupied:parkingType){
            if (!occupied){
                return false;
            }
        }
        return true;
    }
    
    abstract class Points{
        char id;
        protected void generateTicket(){}
        protected void payment(String a){}
    }
    class EntryPoint extends Points{
        EntryPoint(char id){
            this.id = id;
        }
        protected void generateTicket(){
            
        }
    }
    class ExitPoint extends Points{
        ExitPoint(char id){
            this.id = id;
        }
        protected void payment(String creditCardNumber){
            //Simulate payment
        }
        protected void payment(int cash){
            //Simulate, if more given, return the payment
        }
    }


}
