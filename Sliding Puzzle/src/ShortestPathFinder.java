/*
    Student ID : 20212053 / w1871503
    Name : Chamoth Mendis
 */

import java.util.*;

public class ShortestPathFinder {
    static int width;
    static int height;
    static List<String> findPath(MazeGraph mazeGraph, char[][] layout, int start, int end) {

        height = layout.length;
        width = mazeGraph.getWidth();

        int vertices = mazeGraph.getVertices();

        //store distances of every node
        int[] distances = new int[vertices];

        //Track previous node
        int[] previous = new int[vertices];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(vertices, Comparator.comparingInt(i -> distances[i] + heuristic(i, end, width)));

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            int currentNode = priorityQueue.poll();

            if (currentNode == end) {
                break;
            }

            //Getting every neighbour
            for (int neighbor : mazeGraph.getNeighbors(currentNode)) {
                int neighborX = neighbor / width; //Column
                int neighborY = neighbor % width; //Row

                //Slide much as it can into the same direction and get the last node
                int[] finalPosition = slide(neighborX, neighborY, mazeGraph.getDeltaX(currentNode, neighbor), mazeGraph.getDeltaY(currentNode, neighbor), width, height, layout, end);
                int slideStopX = finalPosition[0];
                int slideStopY = finalPosition[1];
                int slideStopNode = slideStopX * width + slideStopY;

                //check the position is valid and new distance is low or not. If it is less than existing one update it to the new distance.
                //Also update the previous array and priority queue
                if (isValidSlide(slideStopX, slideStopY, layout) && isShorterPath(distances, currentNode, slideStopNode)) {
                    distances[slideStopNode] = distances[currentNode] + 1;
                    previous[slideStopNode] = currentNode;
                    priorityQueue.add(slideStopNode);
                }
            }

        }

        List<String> path = new ArrayList<>();
        int node = end;
        while (node != start) {
            path.add(getMoveDirection(previous[node], node, width));
            node = previous[node];
        }

        String startNodePos = "(" + ((start / width) + 1) + ", " + ((start % width) + 1) + ")";

        path.add("Start from " + startNodePos);

        Collections.reverse(path);
        path.add("Done!");

        List<String> numberedPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            numberedPath.add((i + 1) + ". " + path.get(i));
        }
        return numberedPath;
    }


    //Check the slide is valid or not.
    private static boolean isValidSlide(int slideStopX, int slideStopY, char[][] layout) {
        return slideStopX >= 0 && slideStopX < width && slideStopY >= 0 && slideStopY < height && layout[slideStopY][slideStopX] != '0';
    }

    //check the new distance with existing distance. If new distance is less than existing one return true.
    private static boolean isShorterPath(int[] distances, int currentNode, int slideStopNode) {
        return distances[currentNode] + 1 < distances[slideStopNode];
    }


    private static int heuristic(int current, int end, int width) {
        int currentX = current / width;
        int currentY = current % width;

        int endX = end / width;
        int endY = end % width;

        return Math.abs(currentX - endX) + Math.abs(currentY - endY);
    }

    //Getting neighbours x and y coordinates and movements and go until last valid node to the chosen direction
    private static int[] slide(int desiredX, int desiredY, int dx, int dy, int width, int height, char[][] layout, int end) {
        int lastValidX = desiredX;
        int lastValidY = desiredY;

        //check position is valid
        while (isValidPosition(desiredX, desiredY, width, height, layout)) {
            lastValidX = desiredX;
            lastValidY = desiredY;

            if (desiredX * width + desiredY == end) {
                return new int[]{desiredX, desiredY};
            }

            //move to the same direction
            desiredX += dx;
            desiredY += dy;
        }

        return new int[]{lastValidX, lastValidY};
    }

    //Check the position is valid node or not to move.
    private static boolean isValidPosition(int x, int y, int width, int height, char[][] layout) {
        return x >= 0 && x < width && y >= 0 && y < height && layout[y][x] != '0';
    }


    private static String getMoveDirection(int from, int to, int width) {
        int fromX = from / width;
        int fromY = from % width;

        int toX = to / width;
        int toY = to % width;

        if (toX < fromX){
            return "Move left to (" + (toX + 1) + "," + (toY + 1) + ")";
        }
        if (toX > fromX) {
            return "Move right to (" + (toX + 1) + "," + (toY + 1) + ")";
        }
        if (toY < fromY) {
            return "Move up to (" + (toX + 1) + "," + (toY + 1) + ")";
        }
        if (toY > fromY) {
            return "Move down to (" + (toX + 1) + "," + (toY + 1) + ")";
        }
        return "";
    }


}
