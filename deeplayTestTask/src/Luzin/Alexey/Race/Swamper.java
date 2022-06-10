package Luzin.Alexey.Race;

public class Swamper extends Entity implements Description{
    @Override
    public int getW() {
        w = 2;
        return super.getW();
    }

    @Override
    public int getT() {
        t = 5;
        return super.getT();
    }

    @Override
    public int getP() {
        p = 2;
        return super.getP();
    }

    @Override
    public int getS() {
        s = 2;
        return super.getS();
    }

    @Override
    public String getRace() {
        Race = "Swamper";
        return super.getRace();
    }
}
