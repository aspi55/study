package Luzin.Alexey;

import Luzin.Alexey.Race.Entity;

import java.util.LinkedList;

public class TestClass {
    public static void main(String[] args) {
        //testAgain();
        test1();

    }
    protected static void test1 (){ // проверка конвертации символов в штрафное поле
        final  String field = "STWSWTPPTPTTPWPP";
        final  String Race = "Human";
        Field fielder = new Field();
        fielder.convert(field, Race);
        LinkedList<Integer> graph = new LinkedList<>();
        for (int i = 0; i < field.length(); i++) {
            graph.add(fielder.convert(field, Race)[i]);
            System.out.print(fielder.convert(field,Race)[i]);
        }
    }

    protected static void  testAgain(){
        Dialog dialog = new Dialog();
        dialog.isStartAgain();
    }

    protected static void testInputRace(){
        Dialog dialog = new Dialog();
        dialog.inputRace();
    }

    protected static void testDialog (){
        Dialog dialog = new Dialog();
        dialog.start();
    }
}
