package rd.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//https://stackoverflow.com/questions/29881808/how-to-rotate-a-line-based-on-a-given-number-of-degrees
public class RotateLine extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 300;
	private static final double ANGLE = Math.toRadians(80);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(20));
		g2.setColor(Color.blue);

		// draw a horizontal line
		int x1 = 50;
		int y1 = SIZE / 2;
		int x2 = SIZE - 50;
		int y2 = y1;
		g2.drawLine(x1, y1, x2, y2);

		// rotate the line around its center
		double cx = (x1 + x2) / 2.0; // center x
		double cy = (y1 + y2) / 2.0; // center y
		AffineTransform at = AffineTransform.getRotateInstance(ANGLE, cx, cy);
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		Shape rotatedLine = at.createTransformedShape(line);

		// draw the rotated line
		g2.setColor(Color.red);
		g2.draw(rotatedLine);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SIZE, SIZE);
	}

	private static void createAndShowGui() {
		JFrame frame = new JFrame("RotateLine");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new RotateLine());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}
}
