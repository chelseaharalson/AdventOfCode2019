import java.io.*;
import java.util.*;

public class Day24 {
    
	public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Integer>> bugGrid = new ArrayList<ArrayList<Integer>>();
        HashSet<ArrayList<ArrayList<Integer>>> hSet = new HashSet<ArrayList<ArrayList<Integer>>>();
        File file = new File("C:\\Users\\Chelsea\\Downloads\\input24.txt");
        Scanner scanner = new Scanner(new FileReader(file));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] strArr = line.split(" ");
            for (String s : strArr) {
                System.out.print(s);
            }
            System.out.println();
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].equals(".")) {
                    row.add(0);
                }
                else if (strArr[i].equals("#")) {
                    row.add(1);
                }
            }
            bugGrid.add(row);
        }
        scanner.close();
        /*System.out.println("Printing original grid: ");
        printGrid(bugGrid);
        int cn = countNeighboringBugs(bugGrid, 4, 0);
        System.out.println(cn);
        bugGrid = updateGrid(bugGrid);
        System.out.println("Printing updated grid: ");
        printGrid(bugGrid);*/
        while (true) {
            if (!hSet.contains(bugGrid)) {
                hSet.add(bugGrid);
            }
            else {
                int score = calculateBiodiversityScore(bugGrid);
                System.out.println("Biodiversity Score: " + score);
                break;
            }
            bugGrid = updateGrid(bugGrid);
        }
    }

    public static int countNeighboringBugs(ArrayList<ArrayList<Integer>> bugGrid, int x, int y) {
        int bugCount = 0;
        // Right
        if (x < bugGrid.get(0).size()-1) {
            bugCount += bugGrid.get(y).get(x+1);
        }
        // Left
        if (x > 0) {
            bugCount += bugGrid.get(y).get(x-1);
        }
        // Up
        if (y > 0) {
            bugCount += bugGrid.get(y-1).get(x);
        }
        // Down
        if (y < bugGrid.size()-1) {
            bugCount += bugGrid.get(y+1).get(x);
        }
        return bugCount;
    }

    public static ArrayList<ArrayList<Integer>> updateGrid(ArrayList<ArrayList<Integer>> bugGrid) {
        ArrayList<ArrayList<Integer>> resultGrid = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < bugGrid.size(); i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < bugGrid.get(i).size(); j++) {
                int cnb = countNeighboringBugs(bugGrid, j, i);
                // Bug dies unless there is exactly one bug adjacent to it
                if ((cnb < 1 || cnb > 1) && bugGrid.get(i).get(j) == 1) {
                    row.add(0);
                }
                // Empty space becomes infested with bug if exactly 1 or 2 bugs are adjacent to it
                else if ((cnb == 1 || cnb == 2) && bugGrid.get(i).get(j) == 0) {
                    row.add(1);
                }
                else {
                    row.add(bugGrid.get(i).get(j));
                }
            }
            resultGrid.add(row);
        }
        return resultGrid;
    }

    public static int calculateBiodiversityScore(ArrayList<ArrayList<Integer>> bugGrid) {
        int score = 0;
        int power = 1;
        for (int i = 0; i < bugGrid.size(); i++) {
            for (int j = 0; j < bugGrid.get(i).size(); j++) {
                if (bugGrid.get(i).get(j) == 1) {
                    score += power;
                }
                power *= 2;
            }
        }
        return score;
    }

    public static void printGrid(ArrayList<ArrayList<Integer>> bugGrid) {
        for (int i = 0; i < bugGrid.size(); i++) {
            for (int j = 0; j < bugGrid.get(i).size(); j++) {
                System.out.print(bugGrid.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    
}