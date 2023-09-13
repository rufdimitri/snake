package rd.snake;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import rd.snake.Snake.Segment;

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

	boolean isKeyLeftPressed = false;
	boolean isKeyUpPressed = false;
	boolean isKeyRightPressed = false;

	final KeyListener keyListener = new KeyListener() {
		final int K_LEFT = 37;
		final int K_TOP = 38;
		final int K_RIGHT = 39;

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case K_LEFT: {
					isKeyLeftPressed = true;
					break;
				}
				case K_RIGHT: {
					isKeyRightPressed = true;
					break;
				}
				case K_TOP: {
					isKeyUpPressed = true;
					break;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case K_LEFT: {
					isKeyLeftPressed = false;
					break;
				}
				case K_RIGHT: {
					isKeyRightPressed = false;
					break;
				}
				case K_TOP: {
					isKeyUpPressed = false;
					break;
				}

			}
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
		snake.getSegments().add(new Snake.Segment(snake, center, 0)); // manually add head in center of window
		snake.segmentSize = 20;
		snake.addSegments(10); // add other segments automatically
		gameTick();
		setVisible(true);
	}

	double rotation = 0;

	public void gameTick() {
		if (isKeyLeftPressed) {
			snake.rotateLeft();
		}

		if (isKeyRightPressed) {
			snake.rotateRight();
		}

		if (isKeyUpPressed) {
			snake.move();
		}

		snake.updateTailPosition();

		// Graphics:
		Graphics2D g2d = (Graphics2D) graphicsContext.getGraphics();
		g2d.addRenderingHints(antialiasing);

		Rectangle2D.Double background = new Rectangle2D.Double(0, 0, graphicsContext.getWidth(),
				graphicsContext.getHeight());
		g2d.setColor(backgroundColor);
		g2d.fill(background);

		snake.getSegments().descendingIterator().forEachRemaining(segment -> {
			Point2D.Double segmentPos = segment.getPosition();

			// Rotate canvas to show correct Segment/Head rotation
			g2d.rotate(segment.getRotation(), segmentPos.x, segmentPos.y);

			List<GraphicShape> shapes = null;
			if (segment.getSegmentId() == 0) { // first Segment = Kopf
				shapes = getHeadShapes(segment);
			} else {
				shapes = getSegmentShapes(segment);
			}
			for (GraphicShape shape : shapes) {
				if (shape.fill) {
					// fill Segment
					g2d.setColor(shape.fillColor);
					g2d.fill(shape.shape);
				}
				if (shape.line) {
					// Draw Segment shape
					g2d.setColor(shape.lineColor);
					g2d.draw(shape.shape);
				}
			}

			// Revert canvas rotation
			g2d.rotate(-segment.getRotation(), segmentPos.x, segmentPos.y);
		});

		contextRender.repaint();
		executor.schedule(swingInvokeTickTask, repaintInterval, TimeUnit.MILLISECONDS);
	}

	private Shape getRectangeShape(Segment segment) {
		Point2D.Double segmentPos = segment.getPosition();
		double segmentSize = segment.getSnake().segmentSize;
		Point2D.Double rectangleCorner = new Point2D.Double(segmentPos.x + segmentSize / 2,
				segmentPos.y - segmentSize / 2);
		Rectangle2D.Double segmentShape = new Rectangle2D.Double();
		segmentShape.setFrameFromCenter(segmentPos, rectangleCorner);
		return segmentShape;
	}

	private Shape getEllipseShape(Segment segment) {
		Point2D.Double segmentPos = segment.getPosition();
		double segmentSize = segment.getSnake().segmentSize;
		Point2D.Double rectangleCorner = new Point2D.Double(segmentPos.x + segmentSize / 2,
				segmentPos.y - segmentSize / 2);
		Ellipse2D.Double segmentShape = new Ellipse2D.Double();
		segmentShape.setFrameFromCenter(segmentPos, rectangleCorner);
		return segmentShape;
	}

	private List<GraphicShape> getHeadShapes(Segment segment) {
		List<GraphicShape> shapes = new ArrayList<>();

		// create head
		Shape headShape = getEllipseShape(segment);
		// TODO GraphicShape for head

		Point2D.Double segmentPos = segment.getPosition();
		double segmentSize = segment.getSnake().segmentSize;

		double eyeSize = segmentSize / 10;
		double eyePosDeltaX = segmentSize / 3;
		double eyePosDeltaY = segmentSize / 10;
		Ellipse2D.Double leftEye = new Ellipse2D.Double(segmentPos.x - eyePosDeltaX - eyeSize / 2,
				segmentPos.y - eyePosDeltaY - eyeSize / 2, eyeSize, eyeSize);
		shapes.add(leftEye);

		Ellipse2D.Double rightEye = new Ellipse2D.Double(segmentPos.x + eyePosDeltaX - eyeSize / 2,
				segmentPos.y - eyePosDeltaY - eyeSize / 2, eyeSize, eyeSize);
		shapes.add(rightEye);

		double mouthSizeX = segmentSize / 3;
		double mouthSizeY = segmentSize / 3;
		double mouthPosDeltaX = mouthSizeX / 2;
		double mouthPosDeltaY = segmentSize / 3;
		Arc2D.Double mouth = new Arc2D.Double(segmentPos.x - mouthPosDeltaX, segmentPos.y - mouthPosDeltaY, mouthSizeX,
				mouthSizeY, 0, 180, Arc2D.Double.OPEN);
		shapes.add(mouth);
		return shapes;
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
