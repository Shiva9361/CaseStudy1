public class Floor {
    int capacity;
    char[] entryPoints;
    char[] exitPoints;
    boolean[] parkingSpaceCompact;
    boolean[] parkingSpaceLarge;
    boolean[] parkingSpacehandicapped;
    boolean[] parkingSpaceMotercycle;

    int floorId;
    Floor(int capacity,char[] entryPoints,char[] exitPoints,int parkingSpaceCompact,int parkingSpaceLarge,int parkingSpacehandicapped,int parkingSpace,int parkingSpaceMotercycle,int floorId){
        this.capacity=capacity;
        this.entryPoints=entryPoints;
        this.exitPoints=exitPoints;
        this.parkingSpaceCompact=new boolean[parkingSpaceCompact];
        this.parkingSpaceLarge=new boolean[parkingSpaceLarge];
        this.parkingSpaceMotercycle=new boolean[parkingSpaceMotercycle];
        this.parkingSpacehandicapped=new boolean[parkingSpacehandicapped];
        this.floorId=floorId;
    }

}
