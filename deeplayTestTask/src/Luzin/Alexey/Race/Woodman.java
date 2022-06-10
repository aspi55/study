package Luzin.Alexey.Race;

public class Woodman extends Entity implements Description  {
    @Override
    public int getS() {
        s = 3;
        return super.getS();
    }

    @Override
    public int getW() {
        w = 3;
        return super.getW();
    }

    @Override
    public int getT() {
        t = 2;
        return super.getT();
    }

    @Override
    public int getP() {
        p = 2;
        return super.getP();
    }

    @Override
    public String getRace() {
        Race = "Woodman";
        return super.getRace();
    }
}
