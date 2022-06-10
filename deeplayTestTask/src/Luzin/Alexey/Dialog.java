package Luzin.Alexey;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Dialog {
    private String field;
    private String Race;

    public void start (){
        System.out.println("Введите координаты поля (16 символов):");
        System.out.println("S - Болото");
        System.out.println("W - Вода");
        System.out.println("T - Кусты");
        System.out.println("Р - Равнина");
        inputField();
        inputRace();
    }

    public String inputRace() {
        System.out.println("Выберете расу существа:");
        System.out.println("human - человек");
        System.out.println("swamper - болотник");
        System.out.println("woodman - лесник");
        Scanner input = new Scanner(System.in);
        String strRace = input.nextLine().toLowerCase();
        if(raceSet.contains(strRace))
            setRace(strRace);
        else {
            System.out.println("Нет такого существа, проверьте корректность ввода :)");
            inputRace();
        }
            return  strRace;
    }

    public String inputField () {
        Scanner input = new Scanner(System.in);
        boolean inputCorrect;
        String symbolsAllow = "STWP";
        String strField = input.nextLine().toUpperCase();
        inputCorrect = (strField.matches("["+symbolsAllow +"]*")&&strField.length()==16);
        if(inputCorrect)
            setField(strField);
        else {
            System.out.println("Вы где-то ошиблись :( Попробуйте еще раз");
            start();
        }
        return strField;
    }

    public boolean isStartAgain() {
        boolean isStart=false;
        System.out.println("Еще раз? (введите Yes/Y для подтверждения или любой другой текст для выхода)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().toUpperCase();
        if (answer.equals("YES") || answer.equals("Y")) {
            System.out.println("AGAIN!");
             return isStart = true;
        } else {
            System.out.println("Good Bye!");
            System.exit(0);
        }
        return isStart;
    }

    Set <String> raceSet = new HashSet<>();
    public void setRaceSet() {
        raceSet.add("human");
        raceSet.add("swamper");
        raceSet.add("woodman");
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getField() {
        return field;
    }

    public String getRace() {
        return Race;
    }

}
