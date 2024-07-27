/*
    Student ID : 20212053 / w1871503
    Name : Chamoth Mendis
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeParser {

    public static MazeGraph parseLayout(String filePath) throws IOException {
        String line;
        int width ;
        int height;
        int startX = -1, startY = -1, endX = -1, endY = -1;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        List<String> layoutLines = new ArrayList<>();

        for (int y = 0; (line = reader.readLine()) != null; y++) {
            layoutLines.add(line);

            int startIndex = line.indexOf('S');
            if (startIndex != -1) {
                startX = startIndex;
                startY = y;
            }

            int endIndex = line.indexOf('F');
            if (endIndex != -1) {
                endX = endIndex;
                endY = y;
            }
        }

        reader.close();
        width = layoutLines.get(0).length();
        height = layoutLines.size();

        char[][] layout = new char[height][];
        for (int i = 0; i < layoutLines.size(); i++) {
            layout[i] = layoutLines.get(i).toCharArray();
        }

        int startNode = startX * width + startY;
        int endNode = endX * width + endY;


        MazeGraph mazeGraph = new MazeGraph(height * width, startNode, endNode, width, layout);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //current Node index
                int currentIndex = i * width + j;

                // Connect left cell
                if (j > 0) {
                    mazeGraph.addEdge(currentIndex, currentIndex - 1);
                }

                // Connect right cell
                if (j < width - 1) {
                    mazeGraph.addEdge(currentIndex, currentIndex + 1);
                }

                // Connect up cell
                if (i > 0) {
                    mazeGraph.addEdge(currentIndex, currentIndex - width);
                }

                // Connect down cell
                if (i < height - 1) {
                    mazeGraph.addEdge(currentIndex, currentIndex + width);
                }
            }
        }


        return mazeGraph;
    }
}
