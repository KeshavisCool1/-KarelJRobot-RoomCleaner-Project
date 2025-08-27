package robot;
import kareltherobot.*;


public class RoomCleaner implements Directions{

    public static void main(String[] args) {
        World.readWorld("basicRoom.wld");

        World.setVisible(true);// allows us to see the run output
        // the bigger the street, the farther north
        World.setSize(20,20);
        Robot rob = new Robot(15,2,South,9);

        

    }
}
