import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Day22 {
    
	public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Chelsea\\Downloads\\input22.txt");
        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int i = 0; i < 10007; i++) {
            deck.add(i);
        }
        Scanner scanner = new Scanner(new FileReader(file));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("deal into new stack")) {
                //System.out.println("DEAL INTO NEW STACK");
                deck = dealIntoNewStack(deck);
                //printDeck(deck);
            }
            else if (line.startsWith("cut")) {
                String[] cutArr = line.split(" ");
                int n = Integer.parseInt(cutArr[1]);
                if (n > 0) {
                    //System.out.println("CUT POSITIVE");
                    deck = cut(deck, n);
                }
                else if (n < 0) {
                    //System.out.println("CUT NEGATIVE");
                    deck = cutReverse(deck, Math.abs(n));
                }
                //printDeck(deck);
            }
            else if (line.startsWith("deal with increment")) {
                //System.out.println("DEAL WITH INCREMENT");
                String[] dealArr = line.split(" ");
                int n = Integer.parseInt(dealArr[3]);
                deck = dealWithIncrement(deck, n);
                //printDeck(deck);
            }
            //System.out.println("Deck Size: " + deck.size());
        }
        scanner.close();
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == 2019) {
                System.out.println("Part 1: " + i);
                return;
            }
        }
    }
    
    public static ArrayList<Integer> dealIntoNewStack(ArrayList<Integer> inputList) {
        Collections.reverse(inputList);
        return inputList;
    }

    public static ArrayList<Integer> cut(ArrayList<Integer> inputList, int n) {
        //System.out.println("Input List Size: " + inputList.size() + " n: " + n);
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        List<Integer> head = inputList.subList(0, n);
        List<Integer> tail = inputList.subList(n, inputList.size());
        resultList.addAll(tail);
        resultList.addAll(head);
        return resultList;
    }

    public static ArrayList<Integer> cutReverse(ArrayList<Integer> inputList, int n) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        List<Integer> head = inputList.subList(0, inputList.size()-n);
        List<Integer> tail = inputList.subList(inputList.size()-n, inputList.size());
        resultList.addAll(tail);
        resultList.addAll(head);
        return resultList;
    }

    public static ArrayList<Integer> dealWithIncrement(ArrayList<Integer> inputList, int n) {
        ArrayList<Integer> resultList = new ArrayList<Integer>(Collections.nCopies(inputList.size(), 0));
        int idx = 0;
        for (int i = 0; i < inputList.size(); i++) {
            //System.out.println("i: " + i + " index: " + idx + " inputList.get(i): " + inputList.get(i));
            resultList.set(idx, inputList.get(i));
            idx += n;
            if (idx >= inputList.size()) {
                idx = idx % inputList.size();
            }
        }
        return resultList;
    }

    public static void printDeck(ArrayList<Integer> deck) {
        for (int i = 0; i < deck.size(); i++) {
            System.out.print(deck.get(i) + " ");
        }
    }

}
