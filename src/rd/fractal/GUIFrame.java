package rd.fractal;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * https://stackoverflow.com/questions/22573385/java-draw-circle-and-lines-on-swing
 */
public class GUIFrame extends JFrame {
	JPanel panel;

	public GUIFrame(int width, int height) {
		super();
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(Color.black);
		this.add(panel);
	}

	public void showGui() {
		this.setVisible(true);
	}

	double coordX(double xValue) {
		return this.WIDTH / 2 + xValue;
	}

	double coordY(double yValue) {
		return this.HEIGHT / 2 - yValue;
	}

	public void drawPoint(Point p) {
		double width = 5;
		double height = 5;
		Graphics graphics = panel.getGraphics();
//		panel.getGraphicsConfiguration()		
		graphics.setColor(Color.RED);

		int xCoord = (int) Math.round(coordX(p.x) - width / 2);
		int yCoord = (int) Math.round(coordY(p.y) - height / 2);
		graphics.drawOval(xCoord, yCoord, (int) width, (int) height);
		graphics.drawOval(10, 10, 100, 100);

	}

}
