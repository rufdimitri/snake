package rd.fractal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		GUIFrame frame = new GUIFrame(500, 500);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				frame.showGui();
			}
		});
		while (!frame.isVisible()) {
			Thread.sleep(1);
		}
		Fractal fractal = new Fractal();
		for (int i = 0; i < 5; i++) {
			frame.drawPoint(new Point(i, i * i));
		}
	}

}
