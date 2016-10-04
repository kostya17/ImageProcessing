import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;


/**
 * Created by kostya on 9/14/16.
 */
public class Lab1 implements GLEventListener {

    private GLU glu;

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        glu = new GLU();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        setCamera(gl, glu, 500);
        gl.glPointSize(1.0f);
        gl.glBegin(GL.GL_POINTS);
        Line line = new Line(new Point(127, -59), new Point(-98, 11));
        line.draw(gl);
        gl.glEnd();
    }

    private void setCamera(GL2 gl, GLU glu, float distance) {
        // Change to projection matrix.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        glu.gluPerspective(45, 1, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private static GLCanvas initCanvas() {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.setSize(800, 600);

        return glcanvas;
    }

    public static void main(String[] args) {
        GLCanvas canvas = initCanvas();
        Lab1 l = new Lab1();
        canvas.addGLEventListener(l);

//        creating frame
        final JFrame frame = new JFrame("Line");

        //adding canvas to frame
        frame.getContentPane().add(canvas);
        initViews(frame);
        frame.setSize(800, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void initViews(JFrame frame) {
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton convertToCartesian = new JButton("to Cartesian");
        convertToCartesian.setSize(100, 30);
        JButton convertToSpherical = new JButton("to Spherical");
        convertToSpherical.setSize(100, 30);

        JLabel cartesianLabel = new JLabel("Cartesian: ");
        JLabel sphericalLabel = new JLabel("Spherical: ");
        JTextField x = new JTextField(4);
        JTextField y = new JTextField(4);
        JTextField z = new JTextField(4);
        JTextField r = new JTextField(4);
        JTextField fi = new JTextField(4);
        JTextField tetha = new JTextField(4);

        convertToSpherical.addActionListener(e -> {
            Point3D point3D = new Point3D(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()), Integer.parseInt(z.getText()));
            SphericalPoint sphericalPoint = Converter.convert(point3D);
            r.setText(String.valueOf(sphericalPoint.getR()));
            fi.setText(String.valueOf(sphericalPoint.getFi()));
            tetha.setText(String.valueOf(sphericalPoint.getTetha()));
        });

        convertToCartesian.addActionListener(e -> {
            SphericalPoint sphericalPoint = new SphericalPoint(Integer.parseInt(r.getText()), Integer.parseInt(fi.getText()), Integer.parseInt(tetha.getText()));
            Point3D point3D = Converter.convert(sphericalPoint);
            x.setText(String.valueOf(point3D.getX()));
            y.setText(String.valueOf(point3D.getY()));
            z.setText(String.valueOf(point3D.getZ()));
        });

        panel.add(cartesianLabel, BorderLayout.WEST);
        panel.add(x, BorderLayout.WEST);
        panel.add(y, BorderLayout.WEST);
        panel.add(z, BorderLayout.WEST);
        panel.add(convertToSpherical, BorderLayout.WEST);
        panel.add(convertToCartesian, BorderLayout.WEST);
        panel.add(sphericalLabel, BorderLayout.WEST);
        panel.add(r, BorderLayout.WEST);
        panel.add(fi, BorderLayout.WEST);
        panel.add(tetha, BorderLayout.WEST);

        frame.add(panel, BorderLayout.SOUTH);
    }
}