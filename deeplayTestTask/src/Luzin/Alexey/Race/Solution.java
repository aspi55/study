package Luzin.Alexey.Race;

import Luzin.Alexey.Graph;

public class Solution {

    public static int getResult(String playField, String Race){

        Graph graph = new Graph();

        int result= graph.resultGraph(playField, Race);
        System.out.println("Самый оптимальный маршрут для класса " +Race+ " составит "+ result + " единиц");
        return result;
    }

}
