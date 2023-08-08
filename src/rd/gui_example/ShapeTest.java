package rd.gui_example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/** Source: https://stackoverflow.com/a/22573536
 *  Author: Sam
 */
public class ShapeTest extends JFrame {

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

	public static void main(String[] args) {
		// you should always use the SwingUtilities.invodeLater() method
		// to perform actions on swing elements to make certain everything
		// is happening on the correct swing thread
		Runnable swingStarter = new Runnable() {
			@Override
			public void run() {
				new ShapeTest();
			}
		};

		SwingUtilities.invokeLater(swingStarter);
	}

	public ShapeTest() {
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
		setVisible(true);
	}

	public void paint() {

		Graphics2D g2d = graphicsContext.createGraphics();
		g2d.setRenderingHints(antialiasing);

		// Set up the font to print on the circles
		Font font = g2d.getFont();
		font = font.deriveFont(Font.BOLD, 14f);
		g2d.setFont(font);

		FontMetrics fontMetrics = g2d.getFontMetrics();

		// clear the background
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, graphicsContext.getWidth(), graphicsContext.getHeight());

		// set up the large circle
		Point2D largeCircleCenter = new Point2D.Double((double) width / 2 + padding, (double) height / 2 + padding);
		double largeCircleRadius = (double) width / 2;
		Ellipse2D largeCircle = getCircleByCenter(largeCircleCenter, largeCircleRadius);

		// here we build the small circle
		Point2D smallCircleCenter = new Point2D.Double();
		double smallCircleRadius = 15;
		// we need to make certain it is confined inside the larger circle
		// so we choose the following values carefully

		// we want to go a random direction from the circle, so chose an
		// angle randomly in any direction
		double smallCenterVectorAngle = random.nextDouble() * 360.0d;
		// and we want to be a random distance from the center of the large circle, but
		// we limit the distance based on the radius of the small circle to prevent it
		// from appearing outside the large circle
		double smallCenterVectorLength = random.nextDouble() * (largeCircleRadius - smallCircleRadius);
		Line2D vectorToSmallCenter = getVector(largeCircleCenter, smallCenterVectorAngle, smallCenterVectorLength);
		// the resulting end point of the vector is a random distance from the center of
		// the large circle
		// in a random direction, and guaranteed to not place the small circle outside
		// the large
		smallCircleCenter.setLocation(vectorToSmallCenter.getP2());
		Ellipse2D smallCircle = getCircleByCenter(smallCircleCenter, smallCircleRadius);

		// before we draw any of the circles or lines, set the clip to the large circle
		// to prevent drawing outside our boundaries
		g2d.setClip(largeCircle);

		// chose a random angle for the line through the center of the small circle
		double angle = random.nextDouble() * 360.0d;
		// we create two lines that start at the center and go out at the angle in
		// opposite directions. We use 2*largeCircleRadius to make certain they
		// will be large enough to fill the circle, and the clip we set prevent stray
		// marks outside the big circle
		Line2D centerLine1 = getVector(smallCircleCenter, angle, largeCircleRadius * 2);
		Line2D centerLine2 = getVector(smallCircleCenter, angle, -largeCircleRadius * 2);

		// now we just add 20 and 120 to our angle for the center-line, start at the
		// center
		// and again, use largeCircleRadius*2 to make certain the lines are big enough
		Line2D sightVector1 = getVector(smallCircleCenter, angle + 60, largeCircleRadius * 2);
		Line2D sightVector2 = getVector(smallCircleCenter, angle + 120, largeCircleRadius * 2);

		Path2D visible = new Path2D.Double();
		visible.moveTo(sightVector1.getX2(), sightVector1.getY2());
		visible.lineTo(smallCircleCenter.getX(), smallCircleCenter.getY());
		visible.lineTo(sightVector2.getX2(), sightVector2.getY2());
		visible.closePath();

		Path2D greenSide = new Path2D.Double();
		greenSide.moveTo(centerLine1.getX2(), centerLine1.getY2());
		greenSide.lineTo(smallCircleCenter.getX(), smallCircleCenter.getY());
		greenSide.lineTo(centerLine2.getX2(), centerLine2.getY2());
		greenSide.lineTo(sightVector1.getX2(), sightVector1.getY2());
		greenSide.closePath();

		int personCount = 5;
		Area visibleArea = new Area(visible);
		visibleArea.intersect(new Area(largeCircle));

		Area greenSideArea = new Area(greenSide);
		greenSideArea.intersect(new Area(largeCircle));

		// we create a list of the people in the circle to
		// prevent overlap
		ArrayList<Shape> people = new ArrayList<Shape>();
		people.add(smallCircle);

		int i = 0;
		personLoop: while (i < personCount) {
			double personCenterVectorAngle = random.nextDouble() * 360.0d;
			double personCenterVectorLength = random.nextDouble() * (largeCircleRadius - smallCircleRadius);
			Line2D vectorToPersonCenter = getVector(largeCircleCenter, personCenterVectorAngle,
					personCenterVectorLength);
			Point2D personCircleCenter = vectorToPersonCenter.getP2();
			Ellipse2D personCircle = getCircleByCenter(personCircleCenter, smallCircleRadius);

			// this little loop lets us skip a person if they have overlap
			// with another person, since people don't generally overlap
			Area personArea = new Area(personCircle);
			for (Shape person : people) {
				Area overlapArea = new Area(person);
				overlapArea.intersect(personArea);
				// this means that we have found a conflicting
				// person, so should skip them
				if (!overlapArea.isEmpty()) {
					continue personLoop;
				}
			}
			people.add(personCircle);

			personArea.intersect(visibleArea);

			Area greenSideAreaTest = new Area(personCircle);
			greenSideAreaTest.intersect(greenSideArea);
			if (personArea.isEmpty()) {
				if (greenSideAreaTest.isEmpty()) {
					g2d.setColor(Color.orange);
					System.out.println("Person " + i + " is behind the blue line");
				} else {
					System.out.println("Person " + i + " is in front of the blue line");
					g2d.setColor(Color.cyan);
				}
			} else {
				System.out.println("Person " + i + " is between the green lines");
				g2d.setColor(Color.magenta);
			}

			// alternatively to circles intersecting the area of interest, we can check
			// whether the center
			// is in the area of interest which may make more intuitive sense visually
//          if (visibleArea.contains(personCircleCenter)){
//              System.out.println("Person " + i + " is between the green lines");
//              g2d.setColor(Color.magenta);
//          }
//          else {
//              if (greenSideArea.contains(personCircleCenter)) {
//                  System.out.println("Person " + i + " is in front of the blue line");
//                  g2d.setColor(Color.cyan);
//              }
//              else{
//                  g2d.setColor(Color.orange);
//                  System.out.println("Person " + i + " is behind the blue line");
//              }
//          }

			g2d.fill(personCircle);
			g2d.setColor(Color.black);
			String itemString = "" + i;
			Rectangle2D itemStringBounds = fontMetrics.getStringBounds(itemString, g2d);
			double textX = personCircleCenter.getX() - (itemStringBounds.getWidth() / 2);
			double textY = personCircleCenter.getY() + (itemStringBounds.getHeight() / 2);
			g2d.drawString("" + i, (float) textX, (float) textY);
			i++;
		}

		// fill the small circle with blue
		g2d.setColor(Color.BLUE);
		g2d.fill(smallCircle);

		// draw the two center lines lines
		g2d.setStroke(dashedStroke);
		g2d.draw(centerLine1);
		g2d.draw(centerLine2);

		// create and draw the black offset vector
		Line2D normalVector = getVector(smallCircleCenter, angle + 90, largeCircleRadius * 2);
		g2d.setColor(Color.black);
		g2d.draw(normalVector);

		// draw the offset vectors
		g2d.setColor(new Color(0, 200, 0));
		g2d.draw(sightVector1);
		g2d.draw(sightVector2);

		// we save the big circle for last, to cover up any stray marks under the stroke
		// of its perimeter. We also set the clip back to null to prevent the large
		// circle
		// itselft from accidentally getting clipped
		g2d.setClip(null);
		g2d.setStroke(solidStroke);
		g2d.setColor(Color.BLACK);
		g2d.draw(largeCircle);

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
