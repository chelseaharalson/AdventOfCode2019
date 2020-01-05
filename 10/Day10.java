import java.util.*;
import java.io.*;

public class Day10 {
    static int idTracker = 0;
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Chelsea\\Downloads\\input10.txt");
        Scanner scanner = new Scanner(new FileReader(file));
        List<Asteroid> asteroids = getAsteroidFromInput(scanner);
        Asteroid bestAsteroid = findBestAsteroid(asteroids);
        for (Asteroid a : asteroids) {
            a.calculateDistance(bestAsteroid);
        }
        TreeMap<Double, PriorityQueue<Asteroid>> asteroidMap = calculateAnglesFromOrigin(asteroids, bestAsteroid);
        int iterations = 0;
        boolean laserOriented = false;
        while (iterations < 200) {
            for (Map.Entry<Double, PriorityQueue<Asteroid>> entry : asteroidMap.entrySet()) {
                //System.out.println("Angle: " + entry.getKey() + " Size: " + entry.getValue().size());
                if (!laserOriented && entry.getKey() < 180) {
                    continue;
                }
                else if (!laserOriented && entry.getKey() >= 180) {
                    laserOriented = true;
                }
                if (iterations == 199) {
                    Asteroid a200 = entry.getValue().peek();
                    double result = (a200.x * 100) + a200.y;
                    System.out.println("Asteroid 200: " + result + " x: " + a200.x + " y: " + a200.y);
                    return;
                }
                if (entry.getValue().size() != 0) {
                    iterations++;
                    System.out.println("Size: " + entry.getValue().size());
                    System.out.println("Blowing up asteroid x: " + entry.getValue().peek().x + " y: " + entry.getValue().peek().y + " ID: " + entry.getValue().peek().id);
                    entry.getValue().poll();
                    System.out.println("Size after blowing up: " + entry.getValue().size());
                }
                else {
                    System.out.println("No asteroids remaining at angle: " + entry.getKey());
                }
            }
        }
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
                    asteroids.add(new Asteroid(xCount,yCount,idTracker));
                    idTracker++;
                }
                xCount++;
            }

            lineLengths.add(line.length());
            yCount++;
        }
        return asteroids;
    }

    public static TreeMap<Double, PriorityQueue<Asteroid>> calculateAnglesFromOrigin(List<Asteroid> asteroids, Asteroid origin) {
        TreeMap<Double, PriorityQueue<Asteroid>> asteroidMap = new TreeMap<Double, PriorityQueue<Asteroid>>();
        for (Asteroid a : asteroids) {
            if (a.equals(origin)) { // Skip self
                System.out.println("Skipping origin");
                continue;
            }
            double angle = getAngle(origin, a);
            //System.out.println("Angle: " + angle);
            if (!asteroidMap.containsKey(angle)) {
                PriorityQueue<Asteroid> currentAsteroidQueue = new PriorityQueue<Asteroid>(new AsteroidComparator());
                currentAsteroidQueue.offer(a);
                asteroidMap.put(angle, currentAsteroidQueue);
            }
            else {
                PriorityQueue<Asteroid> currentAsteroidQueue = asteroidMap.get(angle);
                currentAsteroidQueue.offer(a);
                asteroidMap.put(angle, currentAsteroidQueue);
            }
        }
        return asteroidMap;
    }

    public static Asteroid findBestAsteroid(List<Asteroid> asteroids) {
        int maxAsteroids = 0;
        Asteroid bestAsteroid = null;
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
                bestAsteroid = a;
            }
        }
        System.out.println("Max Num Of Asteroids: " + maxAsteroids);
        return bestAsteroid;
    }

    public static double calculateSlope(Asteroid a, Asteroid b) {
        double slope = (b.y - a.y) / (b.x - a.x);
        return slope;
    }

    public static double getAngle(Asteroid a, Asteroid b) {
        double angle = 90-Math.toDegrees(Math.atan2(b.y-a.y, a.x-b.x));
        if (angle < 0) {
            return angle + 360;
        }
        return angle;
    }

}

class Asteroid {
    double x;
    double y;
    double distance;
    int id;

    Asteroid(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void calculateDistance(Asteroid a) {
        distance = Math.abs(a.x-x) + Math.abs(a.y-y);
    }
}

class AsteroidComparator implements Comparator<Asteroid> { 
    public int compare(Asteroid a, Asteroid b) { 
        if (a.distance > b.distance) {
            return 1;
        }
        else if (a.distance < b.distance)  {
            return -1;
        }
        return 0; 
    } 
} 