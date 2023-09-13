package rd.snake;

import java.awt.Color;
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

}
