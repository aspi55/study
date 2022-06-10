package Luzin.Alexey.Race;

public class Human extends Entity implements Description{
    @Override
    public int getP() {
        p = 1;
        return super.getP();
    }

    @Override
    public int getT() {

        t = 3;
        return super.getT();
    }

    @Override
    public int getW() {
        w = 2;
        return super.getW();
    }

    @Override
    public int getS() {
        s = 5;
        return s;
    }

    @Override
    public String getRace() {
        Race = "Human";
        return super.getRace();
    }

}

