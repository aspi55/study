package Luzin.Alexey;

import java.util.LinkedList;

public class Graph {

    public int resultGraph(String playField, String Race) {

        PathAlgorithm PathAlgorithm = new PathAlgorithm();

        LinkedList<Integer> graph = new LinkedList<>();

        Field fielder = new Field();
        fielder.convert(playField.toUpperCase(), Race);
        for (int i = 0; i < playField.length(); i++) {
            graph.add(fielder.convert(playField, Race)[i]);
        }

        int bestpath = Integer.MAX_VALUE;
        System.out.println();
        bestpath = PathAlgorithm.findHalfPath(graph);
       return bestpath;
    }
}
