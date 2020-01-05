import java.io.FileReader;
import java.util.*;

public class Day6 {
    public static String inputFile = "C:\\Users\\cmetcalf\\Downloads\\input_day6.txt";

    public static void main (String[] args) throws Exception {
        inputFile = inputFile.replace("\\", "/");
        Scanner scanner = new Scanner(new FileReader(inputFile));

        Tree tree = new Tree();
        HashMap<String, Node> nodeMap = new HashMap<String, Node>();
        while (scanner.hasNext()) {

            String[] orbitingStr = scanner.nextLine().split("\\)");
            String orbitee = orbitingStr[0];
            String orbiter = orbitingStr[1];

            Node orbiteeNode = new Node(orbitee);
            Node orbiterNode = new Node(orbiter);

            if (!nodeMap.containsKey(orbitee)) {
                nodeMap.put(orbitee, orbiteeNode);
            }
            else {
                orbiteeNode = nodeMap.get(orbitee);
            }
            orbiteeNode.getChildren().add(orbiterNode);

            if (!nodeMap.containsKey(orbiter)) {
                nodeMap.put(orbiter, orbiterNode);
            }
            else {
                orbiterNode = nodeMap.get(orbiter);
            }
            orbiterNode.setParent(orbiteeNode);

            if (!tree.hasRoot() || tree.isRoot(orbiterNode)) {
                tree.setRoot(orbiteeNode);
            }
        }

        System.out.println("Total number of orbits : " + calculateNumberOfOrbits(nodeMap));
        calculateHopsBetweenNodesXAndYOrbit(nodeMap, "YOU", "SAN");

        scanner.close();
    }

    public static Set<Node> findTotalOrbitSetOfANode(Node node) {
        Set<Node> set = new HashSet<>();
        while (node.parent != null) {
            set.add(node.parent);
            node = node.parent;
        }
        return set;
    }

    // Part 1
    public static int calculateNumberOfOrbits(HashMap<String, Node> nodes) {
        int total = 0;
        for (Node node : nodes.values()) {
            Node currentNode = node;
            while (currentNode.parent != null) {
                total++;
                currentNode = currentNode.parent;
            }
        }
        return total;
    }

    // Part 2
    public static void calculateHopsBetweenNodesXAndYOrbit(HashMap<String, Node> nodeMap, String x, String y) {
        Node node1 = nodeMap.get(x);
        Node node2 = nodeMap.get(y);

        if (node1 != null && node2 != null) {
            Set<Node> set1 = findTotalOrbitSetOfANode(node1);
            Set<Node> set2 = findTotalOrbitSetOfANode(node2);
            int sizeOfSet1 = set1.size();
            int sizeOfSet2 = set2.size();

            set1.retainAll(set2);
            
            int sizeOfIntersection = set1.size();
            int numOfHopsBetween = sizeOfSet1 + sizeOfSet2 - (2*sizeOfIntersection);

            System.out.println("Number of hops to unit " + x + " and " + y + ": " + numOfHopsBetween);
        }
        else {
            System.out.println("One node of : " + x + " or " + y + " Not Found");
        }
    }
}


class Tree {

    Node root;

    Tree () {

    }

    boolean hasRoot() { 
        return root != null; 
    }

    boolean isRoot (Node node) { 
        return node.data == root.data; 
    }

    void setRoot (Node rootData) { 
        root = rootData; 
    }
}

class Node {

    String data;
    Node parent;
    List<Node> children;

    public Node () {

    }

    public Node (String data) {
        this.children = new ArrayList<>();
        this.data = data;
    }

    public List<Node> getChildren() { 
        return children; 
    }

    public void setParent(Node node) { 
        parent = node; 
    }

    @Override
    public boolean equals(Object obj) {
        return this.data == ((Node) obj).data;
    }

    @Override
    public int hashCode() {
        return data.toString().length();
    }
}