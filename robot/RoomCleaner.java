package robot;
import org.omg.CORBA.INTERNAL;

import kareltherobot.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;


public class RoomCleaner implements Directions{

    public static void main(String[] args) {



        World.setVisible(true);
        // user input define
        // for world basic start is y=11 and x=6
        // test-1 x=11 and y = 52
        // test-2 x=6 y=27
        // final x= 101 y =199
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the world file name: ");
        String world = scanner.nextLine();
        File filename = new File(world);
        if (filename.exists()){
            World.readWorld(world);
        } else {
            System.out.println("Invalid world file entered. Defaulting to basicRoom.wld.");
                world = "basicRoom.wld";
                World.readWorld(world);
        }
        

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
        
        ArrayList <Integer> beeperslist =  new ArrayList<>();
        ArrayList<Integer> streets =  new ArrayList<>();
        ArrayList<Integer> avenues =  new ArrayList<>();
        
        int total_num_beepers = 0;
        
        // int pass = 0;
        // World.setVisible(true);// allows us to see the run output
        // the bigger the street, the farther north
        Robot stop = new Robot(y,x,startDirection,0);
        Robot rob = new Robot(y,x,startDirection,total_num_beepers);
        int y_ = 0;
        World.setDelay(1);
        World.setSize(200,200);
        stop.turnLeft();
        while (stop.frontIsClear() == true){
            stop.move();
        }

        stop.turnLeft();
        stop.turnLeft();
        stop.turnLeft();
        // finding the width
        while (stop.frontIsClear()== true) {
            stop.move();
            y_ = y_ +1;
            
        }
        stop.turnLeft();
        stop.turnLeft();

        for (int i = 0;i<y_;i++){
            stop.move();
        }

        // end of width find

        
        while (true){


            if (rob.nextToABeeper() == true){
                int beepers_count = 0;
                while (rob.nextToABeeper() == true) {
                    rob.pickBeeper();
                    total_num_beepers = total_num_beepers +1;
                    beepers_count = beepers_count + 1;
                }
                if (beepers_count > 0){
                    beeperslist.add(beepers_count);
                    streets.add(rob.street());
                    avenues.add(rob.avenue());
                }
            }


            if (rob.frontIsClear() == true){
            
                rob.move();
                if (rob.nextToABeeper() == true){
                    continue;
                }
            }

            
            if (rob.nextToARobot() == true){
                rob.turnOff();
                
                int length = rob.avenue() - x + 1;
                int width = y_ + 1;

                
                // finding piles and location
                int size = beeperslist.size();
                
                int count = 0;
                for (int index = 0; index<size; index++){
                    count = count + beeperslist.get(index);
                }
                int lastIndex = 0;
                int max = 0;
                for (int index = 0; index < beeperslist.size(); index++){
                    if (max < beeperslist.get(index)){
                        max = beeperslist.get(index);
                        lastIndex = index;
                    }
                }
                

                System.out.println("The area is " + length * width + " square units");
                System.out.println("The total number of piles: " + size);
                System.out.println("The total number of beepers is " + total_num_beepers);
                System.out.println("The largest pile of beepers has " + max + " beepers");
                System.out.println("The largest pile (From top left corner) is right " + Math.abs(x - avenues.get(lastIndex)) + " and down "+ Math.abs(y - streets.get(lastIndex)));
               
                System.out.println("The average pile size is " + (double)count/size );
                System.out.println("The percent dirty is " + (double)size/(length * width));
                
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
                int beepers_count = 0;
                while (rob.nextToABeeper() == true) {
                    rob.pickBeeper();
                    total_num_beepers = total_num_beepers +1;
                    beepers_count = beepers_count + 1;
                }
                if (beepers_count > 0){
                    beeperslist.add(beepers_count);
                    streets.add(rob.street());
                    avenues.add(rob.avenue());
                }
                
                rob.turnLeft();
                
            }

            if (rob.frontIsClear() == false && rob.facingNorth()) {

                rob.turnLeft();
                rob.turnLeft();
                rob.turnLeft();
                
                rob.move();
                int beepers_count = 0;
                while (rob.nextToABeeper() == true) {
                    rob.pickBeeper();
                    total_num_beepers = total_num_beepers +1;
                    beepers_count = beepers_count + 1;
                }
                if (beepers_count > 0){
                    beeperslist.add(beepers_count);
                    streets.add(rob.street());
                    avenues.add(rob.avenue());
                }
                rob.turnLeft();
                rob.turnLeft();
                rob.turnLeft();
            }
        }

        // Main while ended above   

    }
}
