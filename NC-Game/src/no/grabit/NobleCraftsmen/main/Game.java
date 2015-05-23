package no.grabit.NobleCraftsmen.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Ole on 23/05/2015.
 */
public class Game implements Runnable {

	float x = 400, y = 300;

	public Game() {}

	public void run() {
		init();

		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			glColor3f(1f, 0f, 0f);

			glBegin(GL_TRIANGLES);
			glVertex2f(-0.5f, -0.5f);
			glVertex2f(0.5f, -0.5f);
			glVertex2f(0, 0.5f);
			glEnd();

			Display.update();
			handleDisplayChanges();
		}

		Display.destroy();
	}

	public static void main(String[] args) {

		Game game = new Game();
		//new Thread(game, "mainloop").start();
		game.run();
	}

	private void handleDisplayChanges() {
		if(Display.wasResized()) {
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(-((float)Display.getWidth() / (float) Display.getHeight()), ((float)Display.getWidth() / (float) Display.getHeight()), -1, 1, 1, -1);
			glMatrixMode(GL_MODELVIEW);
		}
	}

	private static void init() {
		initDisplay();
		initGL();
	}

	private static void initGL() {
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-((float)Display.getWidth() / (float) Display.getHeight()), ((float)Display.getWidth() / (float) Display.getHeight()), -1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.15f, 0.3f, 0.8f, 1.0f);

		glDisable(GL_DEPTH_TEST);
	}

	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
			Display.setResizable(true);

		} catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(-69);
		}
	}

}
