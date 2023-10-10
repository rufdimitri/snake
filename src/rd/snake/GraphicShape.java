package rd.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class GraphicShape {
	public Shape shape;
	public Color fillColor = Color.BLACK; // null = do not fill
	public Color lineColor = Color.BLACK; // null = do not draw line

	public GraphicShape(Shape shape, Color fillColor, Color lineColor) {
		super();
		this.shape = shape;
		this.fillColor = fillColor;
		this.lineColor = lineColor;
	}

	public void drawOn(Graphics2D g2d) {
		if (fillColor != null) {
			// fill Segment
			g2d.setColor(fillColor);
			g2d.fill(shape);
		}
		if (lineColor != null) {
			// Draw Segment shape
			g2d.setColor(lineColor);
			g2d.draw(shape);
		}
	}

}
