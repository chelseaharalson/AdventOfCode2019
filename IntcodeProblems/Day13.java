package intcodecomputer;
import java.util.*;

public class Day13 {
    private static String input = "1,380,379,385,1008,2235,768501,381,1005,381,12,99,109,2236,1102,0,1,383,1102,1,0,382,21002,382,1,1,20102,1,383,2,21102,1,37,0,1106,0,578,4,382,4,383,204,1,1001,382,1,382,1007,382,38,381,1005,381,22,1001,383,1,383,1007,383,21,381,1005,381,18,1006,385,69,99,104,-1,104,0,4,386,3,384,1007,384,0,381,1005,381,94,107,0,384,381,1005,381,108,1106,0,161,107,1,392,381,1006,381,161,1101,0,-1,384,1106,0,119,1007,392,36,381,1006,381,161,1101,0,1,384,20101,0,392,1,21102,1,19,2,21101,0,0,3,21102,138,1,0,1106,0,549,1,392,384,392,21002,392,1,1,21101,0,19,2,21101,3,0,3,21102,161,1,0,1106,0,549,1102,1,0,384,20001,388,390,1,21001,389,0,2,21101,180,0,0,1106,0,578,1206,1,213,1208,1,2,381,1006,381,205,20001,388,390,1,21001,389,0,2,21101,0,205,0,1106,0,393,1002,390,-1,390,1102,1,1,384,21002,388,1,1,20001,389,391,2,21101,0,228,0,1106,0,578,1206,1,261,1208,1,2,381,1006,381,253,21002,388,1,1,20001,389,391,2,21102,253,1,0,1106,0,393,1002,391,-1,391,1102,1,1,384,1005,384,161,20001,388,390,1,20001,389,391,2,21101,279,0,0,1105,1,578,1206,1,316,1208,1,2,381,1006,381,304,20001,388,390,1,20001,389,391,2,21101,0,304,0,1105,1,393,1002,390,-1,390,1002,391,-1,391,1102,1,1,384,1005,384,161,20102,1,388,1,20102,1,389,2,21101,0,0,3,21102,338,1,0,1105,1,549,1,388,390,388,1,389,391,389,21002,388,1,1,21002,389,1,2,21101,0,4,3,21101,0,365,0,1106,0,549,1007,389,20,381,1005,381,75,104,-1,104,0,104,0,99,0,1,0,0,0,0,0,0,320,17,16,1,1,19,109,3,21201,-2,0,1,22101,0,-1,2,21101,0,0,3,21102,414,1,0,1106,0,549,21202,-2,1,1,21201,-1,0,2,21102,1,429,0,1105,1,601,1201,1,0,435,1,386,0,386,104,-1,104,0,4,386,1001,387,-1,387,1005,387,451,99,109,-3,2106,0,0,109,8,22202,-7,-6,-3,22201,-3,-5,-3,21202,-4,64,-2,2207,-3,-2,381,1005,381,492,21202,-2,-1,-1,22201,-3,-1,-3,2207,-3,-2,381,1006,381,481,21202,-4,8,-2,2207,-3,-2,381,1005,381,518,21202,-2,-1,-1,22201,-3,-1,-3,2207,-3,-2,381,1006,381,507,2207,-3,-4,381,1005,381,540,21202,-4,-1,-1,22201,-3,-1,-3,2207,-3,-4,381,1006,381,529,22101,0,-3,-7,109,-8,2105,1,0,109,4,1202,-2,38,566,201,-3,566,566,101,639,566,566,1201,-1,0,0,204,-3,204,-2,204,-1,109,-4,2106,0,0,109,3,1202,-1,38,594,201,-2,594,594,101,639,594,594,20102,1,0,-2,109,-3,2106,0,0,109,3,22102,21,-2,1,22201,1,-1,1,21101,0,401,2,21102,1,733,3,21102,798,1,4,21102,1,630,0,1106,0,456,21201,1,1437,-2,109,-3,2105,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,2,2,2,2,2,2,2,2,2,2,2,0,2,0,2,2,2,0,2,2,2,2,2,2,0,2,2,0,2,2,2,2,2,2,0,1,1,0,2,2,0,2,0,2,2,2,2,0,2,2,2,2,2,0,2,0,2,2,0,2,2,2,2,0,0,2,2,2,0,2,0,2,0,1,1,0,0,2,2,2,0,0,2,0,2,2,0,2,2,2,2,0,0,2,0,2,2,2,2,2,2,2,0,2,0,2,2,2,0,2,0,1,1,0,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2,0,0,2,0,2,0,2,2,0,2,2,0,2,2,2,2,2,0,2,0,1,1,0,2,0,2,2,0,2,2,2,2,2,2,2,2,2,2,0,0,0,2,2,2,0,2,0,2,2,0,0,2,2,0,2,2,2,0,1,1,0,2,2,2,2,0,2,0,0,2,2,2,0,2,2,2,0,2,0,2,0,0,2,2,2,0,2,2,2,0,0,2,0,2,2,0,1,1,0,0,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2,2,0,2,0,2,2,2,0,2,2,2,0,0,1,1,0,2,2,0,2,0,0,2,2,2,0,0,0,2,0,2,2,2,0,2,2,0,2,2,2,2,2,2,2,0,0,0,2,0,0,0,1,1,0,0,2,2,0,0,2,2,2,2,0,2,2,2,0,0,2,0,2,2,0,2,2,2,2,2,2,2,2,2,0,0,0,2,2,0,1,1,0,2,0,0,2,2,2,2,2,2,2,0,2,0,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,0,0,2,0,1,1,0,2,2,0,2,2,2,0,2,2,0,2,0,2,0,0,0,0,2,2,2,2,2,2,2,2,2,2,0,0,2,2,0,2,2,0,1,1,0,2,2,0,2,2,2,2,2,0,2,0,2,2,2,2,2,0,0,2,2,2,2,0,2,2,0,2,2,2,2,0,2,0,2,0,1,1,0,2,2,0,2,0,2,2,2,2,2,2,0,2,2,2,2,2,2,0,2,2,0,2,2,2,0,2,2,2,2,2,2,2,2,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,25,47,80,47,44,8,60,70,40,60,9,9,88,82,66,91,81,11,3,95,72,53,39,7,33,48,69,45,31,75,6,61,77,72,25,57,70,2,92,78,46,43,88,74,45,27,94,73,90,43,68,90,22,64,2,5,3,40,98,62,25,95,74,1,35,2,1,54,76,68,88,75,30,77,35,40,43,49,85,55,53,12,77,94,89,9,55,8,50,82,7,89,21,85,37,48,4,33,1,28,97,62,95,41,14,22,52,24,72,2,25,51,32,55,36,73,84,22,66,69,36,1,57,97,50,21,98,41,36,59,56,6,80,46,2,86,14,67,77,59,77,5,13,97,98,83,83,42,10,62,64,86,97,17,90,37,27,54,40,39,61,38,11,67,40,65,13,6,85,71,9,93,69,9,28,48,7,93,67,95,90,15,29,90,88,8,75,64,36,42,29,92,24,28,19,4,19,60,16,1,97,43,50,13,10,82,30,19,86,32,93,46,32,66,94,91,44,39,57,51,48,41,92,17,97,16,92,41,92,58,31,94,82,68,25,10,32,98,24,77,17,5,82,30,16,40,82,67,14,16,33,3,96,72,90,83,97,4,55,69,8,30,29,2,47,8,47,52,41,2,14,67,7,57,14,4,94,44,47,9,81,54,91,50,85,41,84,45,65,33,66,28,35,98,89,92,57,81,49,89,89,39,11,8,97,77,12,30,33,77,12,46,64,37,2,84,34,11,54,23,33,57,40,27,95,47,3,17,8,43,15,13,13,41,80,62,93,68,45,82,86,17,44,49,51,29,9,92,67,14,81,16,97,5,65,6,85,46,35,19,50,88,51,23,90,35,44,74,33,36,13,20,44,42,71,51,32,60,22,29,13,26,1,64,26,75,86,43,78,4,43,41,52,67,16,20,63,37,60,2,1,53,37,75,55,3,40,66,36,1,69,18,55,33,81,38,1,81,24,80,31,25,79,30,84,83,71,72,11,94,62,6,35,15,9,63,29,27,76,33,62,77,47,12,61,84,13,38,73,11,32,49,87,6,25,57,87,4,35,91,67,19,30,72,59,79,46,64,66,14,21,15,85,25,22,45,87,96,90,28,83,72,29,71,58,14,50,71,48,19,50,78,63,65,3,41,64,82,50,64,74,77,93,21,52,55,24,34,19,61,19,13,44,80,38,53,36,41,96,17,77,9,84,87,79,51,77,35,5,55,82,23,63,20,66,68,23,92,81,10,2,57,97,4,24,44,28,25,56,4,22,61,11,35,60,75,63,96,60,94,3,65,93,63,28,54,21,10,20,12,46,15,84,93,43,83,71,90,52,48,33,47,32,75,22,22,38,8,62,42,30,95,66,15,75,14,73,17,10,94,64,70,29,51,70,14,68,56,60,57,9,84,16,77,37,17,44,37,22,88,60,85,59,61,52,3,21,15,19,23,90,33,47,36,48,44,30,33,16,22,37,93,78,16,43,18,65,18,61,67,71,51,13,33,7,48,40,70,9,66,12,59,49,67,34,23,51,75,48,23,30,47,23,81,21,42,6,66,34,4,67,45,21,90,34,48,47,43,11,3,54,9,10,35,60,78,19,17,68,1,9,26,92,7,86,66,13,12,31,12,71,55,43,78,39,54,70,62,38,93,25,89,83,37,37,2,60,87,84,48,98,43,1,78,86,97,67,41,85,66,17,23,32,9,35,91,4,18,89,71,4,88,66,50,32,92,9,44,10,23,31,17,53,36,46,94,33,93,68,44,85,73,72,14,34,69,66,77,43,93,23,24,14,17,79,27,63,7,44,95,66,55,83,8,90,58,43,768501";

    public static void main(String[] args) {
        IntcodeComputer ic = new IntcodeComputer(input,false,true);
        Map<Position, Integer> posMap = new HashMap<Position, Integer>();
        // PART 1
        while (ic.isRunning()) {
            ic.compute();
            int x = (int)ic.getOutput();
            ic.compute();
            int y = (int)ic.getOutput();
            ic.compute();
            int typeOfBlock = (int)ic.getOutput();
            Position pos = new Position(x,y);
            posMap.remove(pos);
            posMap.put(pos, typeOfBlock);
        }
        
        int numOfBlockTiles = 0;
        for (Position pos : posMap.keySet()) {
            if (posMap.get(pos) == 2) {
                numOfBlockTiles++;
            }
        }
        System.out.println("Number of block tiles: " + numOfBlockTiles);
    }
}
