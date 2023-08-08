package rd.fractal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * https://stackoverflow.com/questions/22573385/java-draw-circle-and-lines-on-swing
 */
public class GUIFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int width = 500;
	private int height = 500;
	private int padding = 50;
	private BufferedImage graphicsContext;
	private JPanel contentPanel = new JPanel();
	private JLabel contextRender;
	private Stroke dashedStroke = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 2f,
			new float[] { 3f, 3f }, 0f);
	private Stroke solidStroke = new BasicStroke(3.0f);
	private RenderingHints antialiasing;
	private Random random = new Random();

	public GUIFrame(int width, int height) {
		antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphicsContext = new BufferedImage(width + (2 * padding), width + (2 * padding), BufferedImage.TYPE_INT_RGB);
		contextRender = new JLabel(new ImageIcon(graphicsContext));

		contentPanel.add(contextRender);
		contentPanel.setSize(width + padding * 2, height + padding * 2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(contentPanel);
		// take advantage of auto-sizing the window based on the size of its contents
		this.pack();
		this.setLocationRelativeTo(null);
		this.paint();

		repaintTimer();

		setVisible(true);
	}

	void repaintTimer() {
		Runnable repaintTask = new Runnable() {
			@Override
			public void run() {
				GUIFrame.this.paint();
			}
		};
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(repaintTask);
			}
		}, 100, 100);
	}

	public void paint() {

		Graphics2D g2d = graphicsContext.createGraphics();
		g2d.setRenderingHints(antialiasing);

		// clear the background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, graphicsContext.getWidth(), graphicsContext.getHeight());

		// set up the circle
		Point2D circleCenter = new Point2D.Double((double) width / 2 + padding, (double) height / 2 + padding);
		double circleRadius = (double) random.nextDouble(100);
		Ellipse2D circle = getCircleByCenter(circleCenter, circleRadius);

		// draw the two center lines lines
//		g2d.setStroke(dashedStroke);
		g2d.setColor(new Color(0, 200, 0));
		g2d.draw(circle);

		g2d.dispose();
		// force the container for the context to re-paint itself
		contextRender.repaint();

	}

	private static Line2D getVector(Point2D start, double degrees, double length) {
		// we just multiply the unit vector in the direction we want by the length
		// we want to get a vector of correct direction and magnitute
		double endX = start.getX() + (length * Math.sin(Math.PI * degrees / 180.0d));
		double endY = start.getY() + (length * Math.cos(Math.PI * degrees / 180.0d));
		Point2D end = new Point2D.Double(endX, endY);
		Line2D vector = new Line2D.Double(start, end);
		return vector;
	}

	private static Ellipse2D getCircleByCenter(Point2D center, double radius) {
		Ellipse2D.Double myCircle = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, 2 * radius,
				2 * radius);
		return myCircle;
	}

}
