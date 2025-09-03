package robot;
import org.omg.CORBA.INTERNAL;

import kareltherobot.*;
import java.util.Scanner;

public class RoomCleaner implements Directions{

    public static void main(String[] args) {
        World.readWorld("basicRoom.wld");
        World.setVisible(true);
        // user input define
        // for world 1 start is y=11 and x=6
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the start x value: ");
        int x = scanner.nextInt();
        System.out.println("Enter the start y value: ");
        int y = scanner.nextInt();
        // direction
        // the start direction for wrld 1 is South
        System.out.println("Enter a direction:  ");
        String directionInput = reader.nextLine();
        
        Direction startDirection;

        // --- Validate input and determine direction ---
        switch (directionInput.toLowerCase()) {
            case "north":
                startDirection = North;
                break;
            case "east":
                startDirection = East;
                break;
            case "south":
                startDirection = South;
                break;
            case "west":
                startDirection = West;
                break;
            default:
                System.out.println("Invalid direction entered. Defaulting to North.");
                startDirection = South;
                break;
        }
        
        scanner.close();
        reader.close();
        int x_start = x;
        int y_start = y;
        int total_num_beepers = 0;
        
        // int pass = 0;
        // World.setVisible(true);// allows us to see the run output
        // the bigger the street, the farther north
        Robot stop = new Robot(y,x,startDirection,0);
        Robot rob = new Robot(y,x,startDirection,total_num_beepers);


        World.setSize(20,20);
        stop.turnLeft();
        while (stop.frontIsClear() == true){
            stop.move();
        }

        if (rob.nextToABeeper() == true){
            while (rob.nextToABeeper() == true) {
                rob.pickBeeper();
                total_num_beepers = total_num_beepers +1;
            }
            
        }
        while (true){

            if (rob.nextToABeeper() == true){
                while (rob.nextToABeeper() == true) {
                    rob.pickBeeper();
                    total_num_beepers = total_num_beepers +1;
                }
                
            }

            if (rob.nextToARobot() == true){
                rob.turnOff();
                System.out.println("total number of beepers is " + total_num_beepers);
                
                // int length = x - x_start;
                // int width = y-y_start;
                // System.out.println("Area of room:" + length * width);
                break;
            }



            if (rob.frontIsClear() == false && rob.facingSouth()) {

                rob.turnLeft();
                if (rob.frontIsClear() == false) {
                    while (rob.frontIsClear() == false) {
                        rob.turnLeft();
                        
                    }
                    rob.turnLeft();
                }
                rob.move();
                while (rob.nextToABeeper() == true) {
                    rob.pickBeeper();
                    total_num_beepers = total_num_beepers +1;
                }
                rob.turnLeft();
                
            }



            if (rob.frontIsClear() == false && rob.facingNorth()) {

                rob.turnLeft();
                rob.turnLeft();
                rob.turnLeft();
                
                rob.move();
                while (rob.nextToABeeper() == true) {
                    rob.pickBeeper();
                    total_num_beepers = total_num_beepers +1;
                }
                rob.turnLeft();
                rob.turnLeft();
                rob.turnLeft();
            }



            else if (rob.frontIsClear() == true){
            
                rob.move();

            }
        
        
        }

        // Main while ended above   

        
        
    }


    
}
