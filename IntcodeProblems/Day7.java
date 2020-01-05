package intcodecomputer;
import java.util.*;

public class Day7 {
    private static String input = "3,8,1001,8,10,8,105,1,0,0,21,42,51,76,101,118,199,280,361,442,99999,3,9,101,5,9,9,102,2,9,9,1001,9,4,9,102,2,9,9,4,9,99,3,9,1002,9,3,9,4,9,99,3,9,1002,9,4,9,1001,9,3,9,1002,9,5,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,101,4,9,9,1002,9,2,9,1001,9,3,9,1002,9,3,9,101,4,9,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99";

    public static void main(String[] args) {
        int[] arr = { 0, 1, 2, 3, 4 }; // PART 1
        int[] arr2 = { 5, 6, 7, 8, 9 }; // PART 2
        // List<List<Integer>> permutationList = permute(arr); // PART 1
        List<List<Integer>> permutationList = permute(arr2);
        long largestOutput = -1;
        int largestOutputIndex = -1;
        
        // PART 1
        /*for (int i = 0; i < permutationList.size(); i++) {
            long previousResult = 0;
            for (int j = 0; j < permutationList.get(0).size(); j++) {
                int inputVal = permutationList.get(i).get(j);
                IntcodeComputer ic = new IntcodeComputer(input, true, false);
                ic.setInputNumber(inputVal);
                ic.compute();
                ic.setInputNumber((int)previousResult);
                ic.compute();
                previousResult = ic.getOutput();
            }
            if (previousResult > largestOutput) {
                largestOutput = previousResult;
                largestOutputIndex = i;
            }
        }
        System.out.println("Largest Output: " + largestOutput + " and Largest Output Index: " + largestOutputIndex);*/
        
        // PART 2
        for (int i = 0; i < permutationList.size(); i++) {
            long previousResult = 0;
            IntcodeComputer icA = new IntcodeComputer(input, true, false);
            icA.setInputNumber(permutationList.get(i).get(0));
            icA.compute();
            icA.setInputNumber((int)previousResult);
            icA.compute();
            previousResult = icA.getOutput();
            
            IntcodeComputer icB = new IntcodeComputer(input, true, false);
            icB.setInputNumber(permutationList.get(i).get(1));
            icB.compute();
            icB.setInputNumber((int)previousResult);
            icB.compute();
            previousResult = icB.getOutput();
            
            IntcodeComputer icC = new IntcodeComputer(input, true, false);
            icC.setInputNumber(permutationList.get(i).get(2));
            icC.compute();
            icC.setInputNumber((int)previousResult);
            icC.compute();
            previousResult = icC.getOutput();
            
            IntcodeComputer icD = new IntcodeComputer(input, true, false);
            icD.setInputNumber(permutationList.get(i).get(3));
            icD.compute();
            icD.setInputNumber((int)previousResult);
            icD.compute();
            previousResult = icD.getOutput();
            
            IntcodeComputer icE = new IntcodeComputer(input, true, false);
            icE.setInputNumber(permutationList.get(i).get(4));
            icE.compute();
            icE.setInputNumber((int)previousResult);
            icE.compute();
            previousResult = icE.getOutput();
            
            icA.setInputNumber((int)previousResult);
            while (icE.isRunning()) {
                icA.compute();
                previousResult = icA.getOutput();
                icB.setInputNumber((int)previousResult);
                
                icB.compute();
                previousResult = icB.getOutput();
                icC.setInputNumber((int)previousResult);
                
                icC.compute();
                previousResult = icC.getOutput();
                icD.setInputNumber((int)previousResult);
                
                icD.compute();
                previousResult = icD.getOutput();
                icE.setInputNumber((int)previousResult);
                
                icE.compute();
                previousResult = icE.getOutput();
                icA.setInputNumber((int)previousResult);
                
                if (previousResult > largestOutput) {
                    largestOutput = previousResult;
                    largestOutputIndex = i;
                }
            }
        }
        System.out.println("Largest Output: " + largestOutput + " and Largest Output Index: " + largestOutputIndex);
    }
    
    public static List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), arr);
        return list;
    }
    
    public static void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr) {
        // Base case
        if (resultList.size() == arr.length) {
            list.add(new ArrayList<>(resultList));
        } 
        else {
            for (int i = 0; i < arr.length; i++) { 
                if (resultList.contains(arr[i])) {
                    // If element already exists in the list then skip
                    continue; 
                }
                // Choose element
                resultList.add(arr[i]);
                // Explore
                permuteHelper(list, resultList, arr);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }
    }
}
