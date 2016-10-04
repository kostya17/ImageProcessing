/**
 * Created by kostya on 9/29/16.
 */
public class SphericalPoint {

    private int r;
    private int fi;
    private int tetha;

    public SphericalPoint(int r, int fi, int tetha) {
        this.r = r;
        this.fi = fi;
        this.tetha = tetha;
    }

    public int getR() {
        return r;
    }

    public int getFi() {
        return fi;
    }

    public int getTetha() {
        return tetha;
    }

    @Override
    public String toString() {
        return "SphericalPoint [r = " + getR() + ", fi = " + getFi() + ", tetha = " + getTetha() + "]";
    }
}
