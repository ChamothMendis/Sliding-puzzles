/*
    Student ID : 20212053 / w1871503
    Name : Chamoth Mendis
 */

import java.util.*;

public class MazeGraph {

    private final int startNode;
    private final int endNode;
    private final char[][] layout;
    private final int vertices;
    private final int width;
    private final Map<Integer, LinkedList<Integer>> adjacencyList;


    public MazeGraph(int vertices, int startNode, int endNode, int width, char[][] layout) {
        this.vertices = vertices;
        this.startNode = startNode;
        this.endNode = endNode;
        this.width = width;
        this.layout = layout;

        adjacencyList = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new LinkedList<>());
        }
    }

    public List<Integer> getNeighbors(int node) {
        return adjacencyList.get(node);
    }


    public int getWidth() {
        return width;
    }

    public int getVertices() {
        return vertices;
    }


    //calculates the change in the x-coordinate (horizontal movement)
    public int getDeltaX(int currentNode, int neighborNode) {
        int deltaX = 0;
        int uX = currentNode / getWidth();
        int vX = neighborNode / getWidth();

        if (uX != vX) {
            deltaX = vX - uX;
        }
        return deltaX;
    }

    //calculates the change in the y-coordinate (vertical movement)
    public int getDeltaY(int currentNode, int neighborNode) {
        int deltaY = 0;
        int uY = currentNode % getWidth();
        int vY = neighborNode % getWidth();

        if (uY != vY) {
            deltaY = vY - uY;
        }
        return deltaY;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }


    public char[][] getLayout() {
        return layout;
    }

    //adds an edge (or connection) between two nodes
    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
    }
}

