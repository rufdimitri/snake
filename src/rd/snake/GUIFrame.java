package rd.snake;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * https://stackoverflow.com/questions/22573385/java-draw-circle-and-lines-on-swing
 */
public class GUIFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private BufferedImage graphicsContext;
	private JPanel contentPanel = new JPanel();
	private JLabel contextRender;
	private Stroke dashedStroke = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 2f,
			new float[] { 3f, 3f }, 0f);
	private Stroke solidStroke = new BasicStroke(1.5f);
	private RenderingHints antialiasing;
	private Random random = new Random();

	private int repaintInterval = 1000;

	public GUIFrame(int width, int height) {
		antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphicsContext = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		contextRender = new JLabel(new ImageIcon(graphicsContext));

		contentPanel.add(contextRender);

		contentPanel.setLayout(new CardLayout());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(contentPanel);

		// take advantage of auto-sizing the window based on the size of its contents
		this.pack();
		this.setLocationRelativeTo(null); // place window in center of screen
		this.paint();

		setVisible(true);
	}

	public void paint() {

		Graphics2D g2d = graphicsContext.createGraphics();
		g2d.setRenderingHints(antialiasing);

		// clear the background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, graphicsContext.getWidth(), graphicsContext.getHeight());

		// set up the circle
		double circleRadius = 5;
//		for (Point point : fractal.points) {
//			Point2D circleCenter = new Point2D.Double(point.x, point.y);
//			Ellipse2D circle = getCircleByCenter(circleCenter, circleRadius);
//			// fill Background
//			g2d.setColor(Color.GRAY);
//			g2d.fill(circle);
//			// draw Stroke
//			g2d.setColor(Color.DARK_GRAY);
//			g2d.draw(circle);
//
//		}

		g2d.dispose();
		// force the container for the context to re-paint itself
		contextRender.repaint();

	}

	private Line2D getVector(Point2D start, double degrees, double length) {
		// we just multiply the unit vector in the direction we want by the length
		// we want to get a vector of correct direction and magnitute
		double endX = start.getX() + (length * Math.sin(Math.PI * degrees / 180.0d));
		double endY = start.getY() + (length * Math.cos(Math.PI * degrees / 180.0d));
		Point2D end = new Point2D.Double(endX, endY);
		Line2D vector = new Line2D.Double(start, end);
		return vector;
	}

	private double absoluteX(double relativeX) {
		return relativeX + contentPanel.getWidth() / 2;
	}

	private double absoluteY(double relativeY) {
		return contentPanel.getHeight() / 2 - relativeY;
	}

	private Ellipse2D getCircleByCenter(Point2D center, double radius) {
		// TODO fix coords
		double x = absoluteX(center.getX() - radius);
		double y = absoluteY(center.getY() - radius);
		Ellipse2D.Double myCircle = new Ellipse2D.Double(x, y, 2 * radius, 2 * radius);
		return myCircle;
	}

}
