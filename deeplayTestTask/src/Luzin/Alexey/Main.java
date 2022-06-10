package Luzin.Alexey;
import static Luzin.Alexey.Race.Solution.getResult;

public class Main {

    //  private final static String playField = "STWSWTPPTPTTPWPP";
    // private final static String Race = "SWAMPER";


    public static void main(String[] args) {

        Dialog dialog = new Dialog();
        dialog.setRaceSet();
        boolean isStart = true;
        while (isStart) {
            dialog.start();
            getResult(dialog.getField(), dialog.getRace());
            isStart = dialog.isStartAgain();
        }
    }
}

