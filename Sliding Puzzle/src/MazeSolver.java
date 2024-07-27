/*
    Student ID : 20212053 / w1871503
    Name : Chamoth Mendis
 */

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MazeSolver {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the file name:");
        Scanner scanner = new Scanner(System.in);

        try {
            MazeGraph mazeGraph = MazeParser.parseLayout(scanner.nextLine());

            List<String> path = ShortestPathFinder.findPath(mazeGraph, mazeGraph.getLayout(), mazeGraph.getStartNode(), mazeGraph.getEndNode());
            if (path.isEmpty()) {
                System.out.println("No Path Found!");
            } else {
                for (String instruction : path) {
                    System.out.println(instruction);
                }
            }
        } catch (IOException e) {
            System.err.println("Error parsing maze file: " + e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
