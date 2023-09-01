
/*
 * Eiji Arkady Achondo
 * 2Y BSCS
 * 8/23/23
 */

package CH1B1_AchondoEAA;
import java.util.Scanner;

public class CH1B1_AchondoEAA{

    public static Scanner scanner = new Scanner(System.in);

    public static double[][] inputCoords(){

        //Array to store coords
        double[][] coords = new double[3][2];

        //Loop through all Coords array fields
        for (int row = 0; row < 3; row++) {

                System.out.println("Coordinate " + (row+1));
                System.out.print("Enter X coordinate: ");
                coords[row][0] = scanner.nextDouble();
                System.out.print("Enter Y coordinate: ");
                coords[row][1] = scanner.nextDouble();
                System.out.println();

                //Check if input is 0,0
                if(coords[row][0] == 0 && coords[row][1] == 0){
                    System.out.println("Error: Coordinates cannot be 0,0");
                    System.exit(0);
                }

                //Check if input is duplicate
                Coords.checkDuplicate(coords, coords[row][0], coords[row][1], row);

        }

        return coords;

    }

    public static void main(String[] args){

        //Input Coordinates
        double[][] coords = inputCoords();

        //spacing
        System.out.println("////////////////////////");
        System.out.println();
        
        //Output coordinates info along with Quardrants
        Coords.outputCoords(coords);
        
        //spacing
        System.out.println("////////////////////////");
        System.out.println();

        
        //Output Farthest from origin
        PointDistance.findPoint(coords, "farthest");
        System.out.println();

        //Output Closest from origin
        PointDistance.findPoint(coords, "closest");

    }

}

class Coords{
    
    public static void outputCoords(double[][] coords){
        for (int row = 0; row < coords.length; row++) {
            
            System.out.println("Coordinate " + (row+1));
            System.out.println("X: " + coords[row][0]);
            System.out.println("Y: " + coords[row][1]);
            Coords.findQuadrant(coords[row][0], coords[row][1]);
            System.out.println("Distance from origin: " + String.format("%.2f",PointDistance.calculateDistance(coords[row][0], coords[row][1])));
            System.out.println();

        }
    }
    
    public static void findQuadrant(double x, double y){
        //Initialize string
        String quadrant = "";

        if(x == 0 || y == 0){ //detect if point is on an axis
            if(x==0 && y != 0){
                quadrant = "X axis";
            } else if(x != 0 && y == 0){
                quadrant = "Y Axis";
            }
        } else if(x > 0 && y > 0){ //detect which quadrant if point is not on axis
            quadrant = "Quadrant I";
        } else if(x < 0 && y > 0){
            quadrant = "Quadrant II";
        } else if(x < 0 && y < 0){
            quadrant = "Quadrant III";
        } else if(x > 0 && y < 0){
            quadrant = "Quadrant IV";
        }

        System.out.println(quadrant);
    }

    public static void checkDuplicate(double[][] coords, double x, double y, int rowCurrent){

        //Checks if input is duplicate
        switch (rowCurrent) {
            case 0:
                //ignores 1st input as there is no other input to compare it to
                return;
            case 1:
                if(coords[0][0] == x && coords[0][1] == y){
                    outputDuplicate();
                }
                break;
            case 2:
                if(coords[0][0] == x && coords[0][1] == y){
                    outputDuplicate();
                }
                if(coords[1][0] == x && coords[1][1] == y){
                    outputDuplicate();
                }
                break;
            default:
                break;
        }
    }

    public static void outputDuplicate(){
        System.out.println("Error: Cannot input duplicate coordinates");
        System.exit(0);
        //exits program if duplicate found
    }
    
}

class PointDistance{
    
    public static double calculateDistance(double x, double y){
        //Pythagorean theorem
        //Shorter than the normal distance formula sqrt[(x1+x2)^2+(y1+y2)^2]
        //We can remove x2 and y2 since they are equal to 0
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
        return distance;
    }
    
    public static void findPoint(double[][] coords, String function) {

        //Get each coordinate's distance
        double coords1 = calculateDistance(coords[0][0], coords[0][1]);
        double coords2 = calculateDistance(coords[1][0], coords[1][1]);
        double coords3 = calculateDistance(coords[2][0], coords[2][1]);
        
        //Separates the method depending on which is needed to be used
        switch (function){
            case "farthest":
                findFarthest(coords1, coords2, coords3);
                break;
            case "closest":
                findClosest(coords1, coords2, coords3);
                break;
        }

    }

    public static void findFarthest(double coords1, double coords2, double coords3) {

        //Checks which coordinate distance is the farthest
        double maxDistance = Math.max(coords1, Math.max(coords2, coords3));

        System.out.println("Farthest from origin: ");
        if (coords1 == maxDistance) {
            System.out.println("Coordinate 1");
        }
        if (coords2 == maxDistance) {
            System.out.println("Coordinate 2");
        }
        if (coords3 == maxDistance) {
            System.out.println("Coordinate 3");
        }
        
        System.out.println("Distance from origin: " + String.format("%.2f", maxDistance) + " units");
    }

    public static void findClosest(double coords1, double coords2, double coords3) {
        
        //Checks which coordinate distance is the closest
        double minDistance = Math.min(coords1, Math.min(coords2, coords3));

        System.out.println("Closest from origin: ");
        if (coords1 == minDistance) {
            System.out.println("Coordinate 1");
        }
        if (coords2 == minDistance) {
            System.out.println("Coordinate 2");
        }
        if (coords3 == minDistance) {
            System.out.println("Coordinate 3");
        }

        System.out.println("Distance from origin: " + String.format("%.2f", minDistance) + " units");
    }
    
}