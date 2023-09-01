package rd.snake;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

	private int repaintInterval = 25;

	Snake snake = new Snake();

	final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
	final Runnable gameTickTask = () -> gameTick();
	final Runnable swingInvokeTickTask = () -> SwingUtilities.invokeLater(gameTickTask);

	Color backgroundColor = Color.BLACK;

	Point2D.Double center;

	final KeyListener keyListener = new KeyListener() {
		final int K_LEFT = 37;
		final int K_TOP = 38;
		final int K_RIGHT = 39;
		double rotationSpeed = Math.toRadians(10);
		double moveSpeed = 10;

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case K_LEFT: {
				snake.getHead().rotate(-rotationSpeed);
				break;
			}
			case K_RIGHT: {
				snake.getHead().rotate(+rotationSpeed);
				break;
			}
			case K_TOP: {
				Point2D.Double pos = snake.getHead().getPosition();
				Point2D.Double newPos = new Point2D.Double(pos.x, pos.y - moveSpeed);
				Point2D.Double newPosRotated = rotatePoint(newPos, pos, snake.getHead().getRotation());
				snake.getHead().setPosition(newPosRotated);
				break;
			}

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased: " + e.getKeyCode());
		}
	};

	public GUIFrame(int width, int height, Color background) {
		this.backgroundColor = background;
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

		center = new Point2D.Double(width / 2, height / 2);

		this.addKeyListener(keyListener);
		snake.getSegments().add(new Snake.Segment(center, 0));
		snake.addSegments(10);
		gameTick();
		setVisible(true);
	}

//	public void paint() {
//
//		Graphics2D g2d = graphicsContext.createGraphics();
//		g2d.setRenderingHints(antialiasing);
//
//		// clear the background
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(0, 0, graphicsContext.getWidth(), graphicsContext.getHeight());
//
//		// set up the circle
//		double circleRadius = 5;
////		for (Point point : fractal.points) {
////			Point2D circleCenter = new Point2D.Double(point.x, point.y);
////			Ellipse2D circle = getCircleByCenter(circleCenter, circleRadius);
////			// fill Background
////			g2d.setColor(Color.GRAY);
////			g2d.fill(circle);
////			// draw Stroke
////			g2d.setColor(Color.DARK_GRAY);
////			g2d.draw(circle);
////
////		}
//
//		g2d.dispose();
//		// force the container for the context to re-paint itself
//		contextRender.repaint();
//
//	}

	double rotation = 0;

	public void gameTick() {
		Graphics2D g2d = (Graphics2D) graphicsContext.getGraphics();
		g2d.addRenderingHints(antialiasing);

		Rectangle2D.Double background = new Rectangle2D.Double(0, 0, graphicsContext.getWidth(),
				graphicsContext.getHeight());
		g2d.setColor(backgroundColor);
		g2d.fill(background);

//		Rectangle2D.Double head = new Rectangle2D.Double(50, 50, 30, 15);

//		g2d.rotate(rotation, head.getCenterX(), head.getCenterY());
//		rotation += Math.PI / 180 * 1;

		for (Snake.Segment segment : snake.getSegments()) {
			Point2D.Double segmentPos = segment.getPosition();
			// Create Segment-Rectangle
			Point2D.Double rectangleCorner = new Point2D.Double(segmentPos.x + 5, segmentPos.y - 10);
			Rectangle2D.Double headRect = new Rectangle2D.Double();
			headRect.setFrameFromCenter(segmentPos, rectangleCorner);

			g2d.rotate(segment.getRotation(), segmentPos.x, segmentPos.y);
			g2d.setColor(Color.GREEN);
			g2d.draw(headRect);
//			g2d.setColor(Color.GREEN);
//			g2d.fill(headRect);
			g2d.rotate(-segment.getRotation(), segmentPos.x, segmentPos.y);
		}

		snake.updateTailPosition();

		contextRender.repaint();
		executor.schedule(swingInvokeTickTask, repaintInterval, TimeUnit.MILLISECONDS);
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

	// A method that takes two points (p and o) and an angle (theta) in radians, and
	// returns a new point that is the result of rotating p around o by theta
	public Point2D.Double rotatePoint(Point2D.Double p, Point2D.Double anchorPoint, double theta) {
		// Calculate the difference between the coordinates of p and o
		double dx = p.x - anchorPoint.x;
		double dy = p.y - anchorPoint.y;

		// Apply the rotation matrix formula to get the new coordinates of p'
		double x = Math.cos(theta) * dx - Math.sin(theta) * dy + anchorPoint.x;
		double y = Math.sin(theta) * dx + Math.cos(theta) * dy + anchorPoint.y;

		// Create and return a new point with the new coordinates
		return new Point2D.Double(x, y);
	}

}
