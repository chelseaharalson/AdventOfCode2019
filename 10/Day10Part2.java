import java.util.*;
import java.io.*;

public class Day10Part2 {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Chelsea\\Downloads\\input10.txt");
        Scanner scanner = new Scanner(new FileReader(file));
        List<Asteroid> asteroids = getAsteroidFromInput(scanner);
        int maxAsteroids = findMaxNumOfAsteroids(asteroids);
        System.out.println(maxAsteroids);
    }

    public static List<Asteroid> getAsteroidFromInput(Scanner scanner){
        List<Asteroid> asteroids = new ArrayList<>();
        int yCount = 0;
        List<Integer> lineLengths = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            int xCount = 0;
            for (Character character : line.toCharArray())  {
                if (character == '#') {
                    asteroids.add(new Asteroid(xCount,yCount));
                }
                xCount++;
            }

            lineLengths.add(line.length());
            yCount++;
        }
        return asteroids;
    }

    public static int findMaxNumOfAsteroids(List<Asteroid> asteroids) {
        int maxAsteroids = 0;
        for (Asteroid a : asteroids) {  // From asteroid A, how many asteroids can it see?
            Set<Double> slopesLeft = new HashSet<Double>();
            Set<Double> slopesRight = new HashSet<Double>();
            int currentAsteroidCount = 0;
            boolean hasLeft = false;
            boolean hasRight = false;
            boolean hasUp = false;
            boolean hasDown = false;

            for (Asteroid b : asteroids) {  // Can asteroid A see asteroid B?
                if (a.x == b.x && a.y == b.y) { // If comparing to itself
                    continue;
                }
                if (a.y == b.y) {   // Slope is vertical
                    if (b.x < a.x) {
                        if (!hasLeft) {
                            hasLeft = true;
                            currentAsteroidCount++;
                        }
                        continue;
                    }
                    if (b.x > a.x) {
                        if (!hasRight) {
                            hasRight = true;
                            currentAsteroidCount++;
                        }
                        continue;
                    }
                }
                if (a.x == b.x) {   // Slope is horizontal
                    //System.out.println("Slope was horizontal!");
                    if (b.y < a.y) {
                        if (!hasDown) {
                            hasDown = true;
                            currentAsteroidCount++;
                        }
                        //System.out.println("Slope was horizontal! - Continuing");
                        continue;
                    }
                    if (b.y > a.y) {
                        if (!hasUp) {
                            hasUp = true;
                            currentAsteroidCount++;
                        }
                        //System.out.println("Slope was horizontal! - Continuing2");
                        continue;
                    }
                    //System.out.println("Slope was horizontal - OOPS!");
                }
                //System.out.println("a.x: " + a.x + " b.x: " + b.x + " a.y: " + a.y + " b.y: " + b.y);
                double slope = calculateSlope(a, b);
                if (b.x < a.x) {
                    if (!slopesLeft.contains(slope)) {
                        //System.out.println("Adding left slope: " + slope);
                        slopesLeft.add(slope);
                        currentAsteroidCount++;
                    }
                    else {
                        //System.out.println("Left slope already existed: " + slope);
                    }
                    
                }
                if (b.x > a.x) {
                    if (!slopesRight.contains(slope)) {
                        //System.out.println("Adding right slope: " + slope);
                        slopesRight.add(slope);
                        currentAsteroidCount++;
                    }
                    else {
                        //System.out.println("Right slope already existed: " + slope);
                    }

                }
            }
            //System.out.println("===================");
            //System.out.println("Done with asteroid!");
            //System.out.println("===================");
            if (currentAsteroidCount > maxAsteroids) {
                maxAsteroids = currentAsteroidCount;
            }
        }
        return maxAsteroids;
    }

    public static double calculateSlope(Asteroid a, Asteroid b) {
        double slope = (b.y - a.y) / (b.x - a.x);
        return slope;
    }

    public static double getAngle(Asteroid a, Asteroid b) {
        double angle = (double) Math.toDegrees(Math.atan2(a.y - b.y, a.x - b.x));
    
        if (angle < 0){
            angle += 360;
        }
    
        return angle;
    }

}

class Asteroid {
    double x;
    double y;

    Asteroid(double x, double y) {
        this.x = x;
        this.y = y;
    }
}