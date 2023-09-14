package rd.snake;

import java.awt.Color;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Runnable guiTask = () -> new GUIFrame(500, 500, Color.WHITE);
		SwingUtilities.invokeLater(guiTask);
	}

}
