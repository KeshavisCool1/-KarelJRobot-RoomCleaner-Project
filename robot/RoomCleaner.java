package robot;
import kareltherobot.*;


public class RoomCleaner implements Directions{

    public static void main(String[] args) {
        World.readWorld("basicRoom.wld");

        World.setVisible(true);// allows us to see the run output
        // the bigger the street, the farther north
        Robot rob = new Robot(11,6,South,0);
        World.setSize(20,20);
        
        while (true){
            

            if (rob.nextToABeeper() == true){

                rob.pickBeeper();
            }

            if (rob.frontIsClear() == false && rob.facingSouth()) {

                rob.turnLeft();
                rob.move();
                rob.turnLeft();
            }
            if (rob.frontIsClear() == false && rob.facingNorth()) {

                rob.turnLeft();
                rob.turnLeft();
                rob.turnLeft();
                rob.move();
                rob.turnLeft();
                rob.turnLeft();
                rob.turnLeft();
            }
            if (rob.frontIsClear() == true){

                rob.move();

            }


        }


        

    }
}
