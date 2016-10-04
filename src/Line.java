import com.jogamp.opengl.GL2;

import static java.lang.Math.abs;

/**
 * Created by kostya on 10/4/16.
 */
public class Line {

    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void draw(GL2 gl) {
        int dx = abs(end.getX() - start.getX());
        int dy = abs(end.getY() - start.getY());
        int sx = end.getX() >= start.getX() ? 1 : -1;
        int sy = end.getY() >= start.getY() ? 1 : -1;

        if (dy <= dx) {
            int d = (dy << 1) - dx;
            int d1 = dy << 1;
            int d2 = (dy - dx) << 1;
            gl.glVertex2i(start.getX(), start.getY());
            for (int x = start.getX() + sx, y = start.getY(), i = 1; i <= dx; i++, x += sx) {
                if (d > 0) {
                    d += d2;
                    y += sy;
                } else
                    d += d1;
                gl.glVertex2i(x, y);
            }
        } else {
            int d = (dx << 1) - dy;
            int d1 = dx << 1;
            int d2 = (dx - dy) << 1;
            gl.glVertex2i(start.getX(), start.getY());
            for (int y = start.getY() + sy, x = start.getX(), i = 1; i <= dy; i++, y += sy) {
                if (d > 0) {
                    d += d2;
                    x += sx;
                } else
                    d += d1;
                gl.glVertex2i(start.getX(), start.getY());
            }
        }
    }
}
