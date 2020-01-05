import java.util.*;
import java.io.*;

public class Day12 {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Chelsea\\Downloads\\input12.txt");
        Scanner scanner = new Scanner(new FileReader(file));
        ArrayList<Moon> moonList = new ArrayList<Moon>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            line = line.replace(">", "");
            String[] s = line.split(", ");
            Moon moon = new Moon(Integer.parseInt(s[0].substring(s[0].indexOf('=') + 1)),
                                Integer.parseInt(s[1].substring(s[1].indexOf('=') + 1)),
                                Integer.parseInt(s[2].substring(s[2].indexOf('=') + 1))
                                );
            //moon.printMoon();
            moonList.add(moon);
        }
        scanner.close();
        //part1findTotalEnergy(moonList);
        part2findNumOfSteps(moonList);
    }

    public static void part2findNumOfSteps(ArrayList<Moon> moonList) {
        // Each clone resets state of universe since finding num of steps is modifying the positions
        ArrayList<Moon> moonListCopy = (ArrayList<Moon>) moonList.clone();
        long x = findNumOfStepsByPosition(moonListCopy, 0);
        moonListCopy = (ArrayList<Moon>) moonList.clone();
        long y = findNumOfStepsByPosition(moonListCopy, 1);
        moonListCopy = (ArrayList<Moon>) moonList.clone();
        long z = findNumOfStepsByPosition(moonListCopy, 2);
        long numOfSteps = lcm(lcm(x,y),z);
        System.out.println("Number of steps: " + numOfSteps);
    }

    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absHigherNumber = Math.max(Math.abs(number1), Math.abs(number2));
        long absLowerNumber = Math.min(Math.abs(number1), Math.abs(number2));
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    public static long findNumOfStepsByPosition(ArrayList<Moon> moonList, int pos) {
        long numOfSteps = 0;
        HashSet<List<Integer>> positionSet = new HashSet<List<Integer>>();
        while (true) {
            // Apply gravity to each pair of moons
            for (int m = 0; m < moonList.size(); m++) {
                for (int n = m+1; n < moonList.size(); n++) {
                    applyGravity(moonList.get(m), moonList.get(n));
                }
            }
            List<Integer> positionList = new ArrayList<Integer>();
            // Apply velocity
            for (Moon moon : moonList) {
                moon.applyVelocity();
                // x mode
                if (pos == 0) {
                    positionList.add(moon.xPos);
                    positionList.add(moon.xVel);
                }
                // y mode
                else if (pos == 1) {
                    positionList.add(moon.yPos);
                    positionList.add(moon.yVel);
                }
                // z mode
                else if (pos == 2) {
                    positionList.add(moon.zPos);
                    positionList.add(moon.zVel);
                }
                //moon.printMoon();
            }
            if (!positionSet.contains(positionList)) {
                positionSet.add(positionList);
            }
            else {
                break;
            }
            numOfSteps++;
        }
        return numOfSteps;
    }

    public static void part1findTotalEnergy(ArrayList<Moon> moonList) {
        //System.out.println("=============================================================================================================================");
        for (int i = 0; i < 1000; i++) {
            // Apply gravity to each pair of moons
            for (int m = 0; m < moonList.size(); m++) {
                for (int n = m+1; n < moonList.size(); n++) {
                    applyGravity(moonList.get(m), moonList.get(n));
                }
            }
            // Apply velocity
            for (Moon moon : moonList) {
                moon.applyVelocity();
                //moon.printMoon();
            }
            //System.out.println("=============================================================================================================================");
        }

        int totalEnergy = 0;
        for (Moon moon : moonList) {
            totalEnergy += moon.calculateEnergy();
        }
        System.out.println("Total Energy: " + totalEnergy);
    }

    public static void applyGravity(Moon moonA, Moon moonB) {
        if (moonA.xPos > moonB.xPos) {
            moonA.xVel--;
            moonB.xVel++;
        }
        else if (moonA.xPos < moonB.xPos) {
            moonA.xVel++;
            moonB.xVel--;
        }
        if (moonA.yPos > moonB.yPos) {
            moonA.yVel--;
            moonB.yVel++;
        }
        else if (moonA.yPos < moonB.yPos) {
            moonA.yVel++;
            moonB.yVel--;
        }
        if (moonA.zPos > moonB.zPos) {
            moonA.zVel--;
            moonB.zVel++;
        }
        else if (moonA.zPos < moonB.zPos) {
            moonA.zVel++;
            moonB.zVel--;
        }
    }
}

class Moon {
    int xPos;
    int yPos;
    int zPos;
    int xVel;
    int yVel;
    int zVel;

    public Moon(int xPos, int yPos, int zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        xVel = 0;
        yVel = 0;
        zVel = 0;
    }

    public void applyVelocity() {
        xPos += xVel;
        yPos += yVel;
        zPos += zVel;
    }

    public int calculateEnergy() {
        int potentialEnergy = Math.abs(xPos) + Math.abs(yPos) + Math.abs(zPos);
        int kineticEnergy = Math.abs(xVel) + Math.abs(yVel) + Math.abs(zVel);
        int energy = potentialEnergy * kineticEnergy;
        return energy;
    }

    public void printMoon() {
        System.out.println("xPos: " + xPos + " yPos: " + yPos + " zPos: " + zPos + " xVel: " + xVel + " yVel: " + yVel + " zVel: " + zVel);
    }
}