package no.grabit.NobleCraftsmen.main;

import no.grabit.NobleCraftsmen.graphics.Shader;
import no.grabit.NobleCraftsmen.graphics.Sprite;
import no.grabit.NobleCraftsmen.scenegraph.GameComponent;
import no.grabit.NobleCraftsmen.scenegraph.GameObject;
import no.grabit.NobleCraftsmen.util.Time;
<<<<<<< HEAD
=======
import org.lwjgl.BufferUtils;
>>>>>>> origin/master
import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.input.Cursor;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Ole on 23/05/2015.
 */
public class Game implements Runnable {

	public static final String TITLE = "Noble Craftsmen";

	private GameObject root;
	private GameComponent sprite;

	public void run() {
		init();
		initGame();

<<<<<<< HEAD
		initGame();

		long lastTime = System.nanoTime();

		//root.add(sprite);

=======
		long lastTime = System.nanoTime();

>>>>>>> origin/master
		while(!Display.isCloseRequested()) {
			long now = System.nanoTime();
			Time.setDeltaTime(lastTime, now);
			lastTime = now;

			update();
			render();

			handleDisplayUpdate();
		}

		root.exit();

		Display.destroy();
	}

	private void update() {
		root.update();
<<<<<<< HEAD
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		Shader.basicShader.bind();

		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(-0.5f, -0.5f);
		glTexCoord2f(1, 0);
		glVertex2f(0.5f, -0.5f);
		glTexCoord2f(1, 1);
		glVertex2f(0.5f, 0.5f);
		glTexCoord2f(0, 1);
		glVertex2f(-0.5f, 0.5f);
		glEnd();

		root.render();
	}

=======
		root.getTransform().getPosition().set(getMouseX(), getMouseY());
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();

		Shader.basicShader.bind();
		root.render();
		Shader.unbind();
	}

>>>>>>> origin/master
	public static void main(String[] args) {
		Game game = new Game();
		new Thread(game, "main loop").start();
	}

	private void handleDisplayUpdate() {
		Display.update();

		if(Display.wasResized()) {
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(-((float)Display.getWidth() / (float) Display.getHeight()), ((float)Display.getWidth() / (float) Display.getHeight()), -1, 1, 1, -1);
			glMatrixMode(GL_MODELVIEW);
		}
		Display.sync(Display.getDesktopDisplayMode().getFrequency());
	}

	private static void init() {
		initDisplay();
		initGL();
	}

	private void initGame() {
<<<<<<< HEAD
		root = new GameObject();
		sprite = new Sprite("textures/Commando.png");
=======
		root = new GameObject("Root");
		sprite = new Sprite("test sprite", "/textures/Commando.png");
		root.add(sprite);

		root.getTransform().setRotation(45f);
>>>>>>> origin/master
	}

	private static void initGL() {
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-((float)Display.getWidth() / (float) Display.getHeight()), ((float)Display.getWidth() / (float) Display.getHeight()), -1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.15f, 0.3f, 0.8f, 1.0f);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_DEPTH_TEST);
	}

	private static void initDisplay() {
		try {

			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
			Display.setResizable(true);
			Display.setTitle(Game.TITLE);
<<<<<<< HEAD
=======
			Display.setVSyncEnabled(true);

			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));

>>>>>>> origin/master
		} catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(-69);
		}
	}

	public static float getMouseX() {
		return ((float) Mouse.getX() - Display.getWidth() / 2) / (float) Display.getHeight() * 2;
	}

	public static float getMouseY() {
		return ((float) Mouse.getY() - Display.getHeight() / 2) / (float) Display.getHeight() * 2;
	}

}
