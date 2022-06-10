package Luzin.Alexey;

import Luzin.Alexey.Race.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class Field {

    Map<String, Entity> entityMap;

    public Field() {
        entityMap = new HashMap<>();
        entityMap.put("human", new Human());
        entityMap.put("swamper", new Swamper());
        entityMap.put("woodman", new Woodman());
    }

    public int [] convert(String field, String race) {
        Entity entity =  entityMap.get(race.toLowerCase(Locale.ROOT));
        int s, w, t, p;
        s = entity.getS();
        w = entity.getW();
        t = entity.getT();
        p = entity.getP();
        int[] fieldInteger = new int[field.length()];

        field = field.replaceAll("S", Integer.toString(s));
        field = field.replaceAll("W", Integer.toString(w));
        field = field.replaceAll("T", Integer.toString(t));
        field = field.replaceAll("P", Integer.toString(p));
        String str[] = field.split("");
        for (int i = 0; i < field.length(); i++) {
            fieldInteger[i] = Integer.parseInt(str[i]);
          //  System.out.println(str[i]);
        }
        return fieldInteger;
    }
}