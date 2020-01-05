/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2019;
import java.io.*;
import java.util.*;

public class Day3 {
    static byte[][] matrix = new byte[30000][30000];
    static HashMap<Integer, HashMap<Integer, Integer>> intersectionStepsMap = new HashMap<Integer, HashMap<Integer, Integer>>();

    public static void main(String[] args) throws IOException {
        String input1 = "R1000,D722,L887,D371,R430,D952,R168,D541,L972,D594,R377,U890,R544,U505,L629,U839,L680,D863,L315,D10,L482,U874,L291,U100,R770,D717,L749,U776,L869,D155,R250,U672,L195,D991,L556,D925,R358,U296,R647,D652,L790,D780,L865,U405,L400,D160,L460,U50,R515,D666,R306,U746,R754,U854,L332,U254,R673,U795,R560,U69,L507,U332,L328,U547,L717,U291,R626,U868,L583,D256,R371,U462,R793,U559,L571,U270,R738,U425,L231,U549,L465,U21,L647,U43,R847,U104,L699,U378,L549,D975,R13,D306,R532,D730,L566,U846,L903,D224,R448,D424,L727,D199,L626,D872,L541,D786,L304,U462,R347,U379,R29,D556,L775,D768,L284,D480,R654,D659,R818,D57,L77,U140,R619,D148,R686,D461,L910,U244,R115,D769,R968,U802,L737,U868,R399,D150,L791,U579,L856,D11,R115,U522,L443,D575,L133,U750,R437,U718,L79,D119,L97,U471,R817,U438,R157,U105,L219,U777,L965,U687,L906,D744,L983,D350,R664,D917,R431,D721,L153,U757,L665,U526,L49,U166,L59,D293,R962,D764,R538,U519,L24,U91,R11,U574,L647,U891,R44,D897,L715,U498,L624,D573,R287,U762,L613,D79,R122,U148,L849,D385,R792,D20,L512,D431,R818,U428,L10,D800,R773,D936,L594,D38,R824,D216,L220,U358,L463,U550,R968,D346,L658,U113,R813,U411,L730,D84,R479,U877,L730,D961,L839,D792,R424,U321,L105,D862,L815,D243,L521,D913,L1,D513,L269,U495,L27,U16,R904,D926,R640,U948,R346,D240,L273,U131,L296,U556,R347,D640,L261,D43,R136,U824,R126,U583,R736,U530,L734,U717,L256,U362,L86,U48,R851,U519,L610,D134,L554,D766,L179,U637,R71,D895,L21,D908,R486,D863,R31,U85,R420,D718,R466,D861,R655,D304,L701,D403,L860,D208,L595,U64,R999";
        String input2 = "L992,D463,R10,D791,R312,D146,R865,D244,L364,D189,R35,U328,R857,D683,L660,D707,L908,D277,R356,U369,R197,D35,R625,D862,L769,U705,L728,U999,R938,U233,L595,U266,L697,U966,L536,D543,L669,D829,R910,U693,R753,D389,L647,U603,L660,D787,L138,D119,L131,D266,R268,D917,R776,U290,R920,U904,L46,D139,L341,D19,R982,U790,L981,U791,L147,U30,L246,U677,R343,D492,R398,D234,R76,D423,L709,D392,R741,U408,R878,U29,R446,U36,R806,U78,L76,D813,R584,U682,L187,U666,L340,D301,L694,U15,R800,U276,L755,U558,R366,D309,R571,U976,L286,D833,R318,U365,L864,U408,L352,D61,R284,D272,R240,D845,L206,U721,R367,D541,R628,U581,L750,D680,R695,D30,R849,U743,L214,U605,R533,U493,R803,D783,R168,U877,L61,D726,L794,D116,R717,U44,R964,U674,L291,D372,L381,D523,L644,U438,R983,D390,R520,D471,R556,D693,L919,D863,R84,D629,L264,D429,R82,U64,R835,D801,R93,U770,R441,D152,L718,D788,L797,U699,L82,U206,L40,U952,R902,U570,L759,D655,L131,D901,L470,D804,L407,U458,L922,D21,L171,U841,L237,D301,R192,D293,R984,U243,R846,U139,L413,U162,R925,D235,L115,U443,L884,D910,R335,U274,L338,U160,R125,D775,R824,D821,R531,D761,L189,U822,L602,D732,R473,U149,L128,U30,R77,D957,R811,D154,L988,D237,R425,D855,R482,D571,R134,D731,L905,U869,R916,D689,L17,U24,R353,D996,R832,U855,L76,U659,R581,D483,R821,D145,R199,D344,R487,D436,L92,U491,R365,D909,L17,D148,R307,U57,R666,U660,R195,D767,R612,D902,L594,D299,R670,D881,L583,D793,R58,U89,L99,D355,R394,D350,R920,U544,R887,U564,L238,U979,L565,D914,L95,U150,R292,U495,R506,U475,R813,D308,L797,D484,R9";
        Direction[] dirInput1 = parseInput(input1);
        // PART 1
        /*mapWirePath(dirInput1, false);
        Direction[] dirInput2 = parseInput(input2);
        ArrayList<int[]> intersections = mapWirePath(dirInput2, true);
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < intersections.size(); i++) {
            System.out.println("intersections: " + intersections.get(i)[0] + ", " + intersections.get(i)[1] + " " + intersections.get(i)[2]);
            int manDist = calculateManhattanDistance(15000, 15000, intersections.get(i)[0], intersections.get(i)[1]);
            resultList.add(manDist);
        }
        Collections.sort(resultList);
        System.out.println(resultList);*/
        // PART 2
        mapWirePath(dirInput1, 1);
        Direction[] dirInput2 = parseInput(input2);
        mapWirePath(dirInput2, 2);
        mapWirePath(dirInput1, 3);
        
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        
        for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : intersectionStepsMap.entrySet()) {
            Map<Integer, Integer> childMap = entry.getValue();
            for (Map.Entry<Integer, Integer> entry2 : childMap.entrySet()) {
                Integer childValue = entry2.getValue();
                resultList.add(childValue);
            }
        }
        Collections.sort(resultList);
        System.out.println(resultList);
    }
    
    public static Direction[] parseInput(String input) {
        String[] wirePaths = input.split(",");
        Direction[] dirArr = new Direction[wirePaths.length];
        for (int i = 0; i < wirePaths.length; i++) {
            String d = wirePaths[i].substring(0,1);
            int dist = Integer.parseInt(wirePaths[i].substring(1,wirePaths[i].length()));
            Direction dir = new Direction(dist, d);
            dirArr[i] = dir;
        }
        return dirArr;
    }
    
    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2) {
        int distance = Math.abs(x1-x2) + Math.abs(y1-y2);
        return distance;
    }
    
    public static void mapWirePath(Direction[] wirePath, int pass) {
        int x = 15000;
        int y = 15000;
        int numSteps = 0;
        for (int i = 0; i < wirePath.length; i++) {
            if (wirePath[i].direction.equals("R")) {
                for (int j = 1; j <= wirePath[i].distance; j++) {
                    numSteps ++;
                    if (pass == 1) {
                        matrix[x+j][y] = 1;
                    }
                    else if (pass == 2 && matrix[x+j][y] == 1 && i > 0) {
                        matrix[x+j][y] = 2;
                        checkAddSteps(x+j, y, numSteps);
                    }
                    else if (pass == 3 && matrix[x+j][y] == 2 && i > 0) {
                        matrix[x+j][y] = 3;
                        checkAddSteps(x+j, y, numSteps);
                    }
                    
                }
                x += wirePath[i].distance;
            }
            else if (wirePath[i].direction.equals("L")) {
                for (int j = 1; j <= wirePath[i].distance; j++) {
                    numSteps ++;
                    if (pass == 1) {
                        matrix[x-j][y] = 1;
                    }
                    else if (pass == 2 && matrix[x-j][y] == 1 && i > 0) {
                        matrix[x-j][y] = 2;
                        checkAddSteps(x-j, y, numSteps);
                    }
                    else if (pass == 3 && matrix[x-j][y] == 2 && i > 0) {
                        matrix[x-j][y] = 3;
                        checkAddSteps(x-j, y, numSteps);
                    }
                }
                x -= wirePath[i].distance;
            }
            else if (wirePath[i].direction.equals("D")) {
                for (int j = 1; j <= wirePath[i].distance; j++) {
                    numSteps ++;
                    if (pass == 1) {
                        matrix[x][y-j] = 1;
                    }
                    else if (pass == 2 && matrix[x][y-j] == 1 && i > 0) {
                        matrix[x][y-j] = 2;
                        checkAddSteps(x, y-j, numSteps);
                    }
                    else if (pass == 3 && matrix[x][y-j] == 2 && i > 0) {
                        matrix[x][y-j] = 3;
                        checkAddSteps(x, y-j, numSteps);
                    }
                }
                y -= wirePath[i].distance;
            }
            else if (wirePath[i].direction.equals("U")) {
                for (int j = 1; j <= wirePath[i].distance; j++) {
                    numSteps ++;
                    if (pass == 1) {
                        matrix[x][y+j] = 1;
                    }
                    else if (pass == 2 && matrix[x][y+j] == 1 && i > 0) {
                        matrix[x][y+j] = 2;
                        checkAddSteps(x, y+j, numSteps);
                    }
                    else if (pass == 3 && matrix[x][y+j] == 2 && i > 0) {
                        matrix[x][y+j] = 3;
                        checkAddSteps(x, y+j, numSteps);
                    }
                }
                y += wirePath[i].distance;
            }
            //System.out.println("x: " + x + " y: " + y + " next: " + wirePath[i+1].direction + " " + wirePath[i+1].distance);
        }
    }
    
    public static void checkAddSteps(int x, int y, int steps) {
        HashMap<Integer, Integer> xMap = null;
        if (intersectionStepsMap.containsKey(x)) {
            xMap = intersectionStepsMap.get(x);
        }
        else {
            xMap = new HashMap<Integer, Integer>();
        }
        
        int currentSteps = 0;
        if (xMap.containsKey(y)) {
            currentSteps = xMap.get(y);
        }
        currentSteps += steps;
        xMap.put(y, currentSteps);
        intersectionStepsMap.put(x, xMap);
    }
    
    public static ArrayList<int[]> mapWirePath(Direction[] wirePath, boolean checkIfIntersect) {
        ArrayList<int[]> intersectionList = new ArrayList<int[]>();
        int x = 15000;
        int y = 15000;
        int numSteps = 0;
        for (int i = 0; i < wirePath.length; i++) {
            numSteps += wirePath[i].distance;
            if (wirePath[i].direction.equals("R")) {
                for (int j = 0; j < wirePath[i].distance; j++) {
                    if (checkIfIntersect && i > 0 && matrix[x+j][y] == 1) {
                        int[] resultArr = new int[3];
                        resultArr[0] = x+j;
                        resultArr[1] = y;
                        resultArr[2] = numSteps;
                        intersectionList.add(resultArr);
                    }
                    matrix[x+j][y] = 1;
                }
                x += wirePath[i].distance;
            }
            else if (wirePath[i].direction.equals("L")) {
                for (int j = 0; j < wirePath[i].distance; j++) {
                    if (checkIfIntersect && i > 0 && matrix[x-j][y] == 1) {
                        int[] resultArr = new int[3];
                        resultArr[0] = x-j;
                        resultArr[1] = y;
                        resultArr[2] = numSteps;
                        intersectionList.add(resultArr);
                    }
                    matrix[x-j][y] = 1;
                }
                x -= wirePath[i].distance;
            }
            else if (wirePath[i].direction.equals("D")) {
                for (int j = 0; j < wirePath[i].distance; j++) {
                    if (checkIfIntersect && i > 0 && matrix[x][y-j] == 1) {
                        int[] resultArr = new int[3];
                        resultArr[0] = x;
                        resultArr[1] = y-j;
                        resultArr[2] = numSteps;
                        intersectionList.add(resultArr);
                    }
                    matrix[x][y-j] = 1;
                }
                y -= wirePath[i].distance;
            }
            else if (wirePath[i].direction.equals("U")) {
                for (int j = 0; j < wirePath[i].distance; j++) {
                    if (checkIfIntersect && i > 0 && matrix[x][y+j] == 1) {
                        int[] resultArr = new int[3];
                        resultArr[0] = x;
                        resultArr[1] = y+j;
                        resultArr[2] = numSteps;
                        intersectionList.add(resultArr);
                    }
                    matrix[x][y+j] = 1;
                }
                y += wirePath[i].distance;
            }
            //System.out.println("x: " + x + " y: " + y + " next: " + wirePath[i+1].direction + " " + wirePath[i+1].distance);
        }
        return intersectionList;
    }
    
    static void writeMatrixToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (byte row[] : matrix) {
            writer.write(Arrays.toString(row));
        }
    }
}

class Direction {
    int distance;
    String direction;
    
    public Direction(int pDistance, String pDirection) {
        distance = pDistance;
        direction = pDirection;
    }
    
    public void printDirection() {
        System.out.print(direction + distance);
    }
}