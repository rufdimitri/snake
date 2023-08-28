package rd.snake;

import javax.swing.SwingUtilities;

public class Main {
	static GUIFrame frame;

	public static void main(String[] args) throws InterruptedException {
		Runnable guiTask = new Runnable() {
			@Override
			public void run() {
				frame = new GUIFrame(300, 300);
			}
		};

		SwingUtilities.invokeLater(guiTask);
	}

}
