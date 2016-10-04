import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by kostya on 9/29/16.
 */
public class Converter {

    public static Point3D convert(SphericalPoint point) {
        int x = (int) (point.getR() * Math.sin(point.getTetha()) * Math.cos(point.getFi()));
        int y = (int) (point.getR() * Math.sin(point.getTetha()) * Math.sin(point.getFi()));
        int z = (int) (point.getR() * Math.cos(point.getTetha()));
        return new Point3D(x, y, z);
    }

    public static SphericalPoint convert(Point3D point) {
        int r = (int) Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2) + Math.pow(point.getZ(), 2));
        int theta = (int) Math.acos(point.getZ() / r);
        int fi = (int) Math.atan(point.getY() / point.getX());
        return new SphericalPoint(r, fi, theta);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}