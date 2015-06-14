package no.grabit.NCLauncher.main;

import no.grabit.NCLauncher.input.Button;
import no.grabit.NCLauncher.input.InputLabel;
import no.grabit.NCLauncher.input.Label;
import no.grabit.NCLauncher.graphics.Shader;
import no.grabit.NCLauncher.scenegraph.GameObject;
import no.grabit.NCLauncher.util.Time;
import org.lwjgl.input.*;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Ole on 23/05/2015.
 */
public class Launcher implements Runnable {

	public static final String TITLE = "Noble Craftsmen";

	private GameObject root;

	public void run() {
		init();
		initGame();

		long lastTime = System.nanoTime();

		while(!Display.isCloseRequested()) {
			long now = System.nanoTime();
			Time.setDeltaTime(now, lastTime);
			Time.addTime(Time.deltaTime());
			lastTime = now;

			update();
			render();

			handleDisplayUpdate();
		}

		Display.destroy();
	}

	private void update() {
		root.update();
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		Shader.basicShader.bind();
		root.render();
		Shader.unbind();
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		new Thread(launcher, "main loop").start();
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
		Label.init();
		root = new GameObject("root");
//		label = new Label("password", Label.medievalFont, "password: ", 200, Label.POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT);
//		label.getTransform().getScale().set(0.001f, 0.001f);
//		label.getTransform().getPosition().set(-0.75f, 0.25f);
//		root.add(label);
//		Label label2 = new Label("password", Label.medievalFont, "password: ", 200, Label.POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT);
//		label2.getTransform().getScale().set(0.001f, 0.001f);
//		label2.getTransform().getPosition().set(-0.75f, 0.0f);
//		root.add(label2);
//		Label label3 = new Label("login", Label.medievalFont, "press enter to log in", 200, Label.POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT);
//		label3.getTransform().getScale().set(0.001f, 0.001f);
//		label3.getTransform().getPosition().set(-0.75f, -0.25f);
//		root.add(label3);

		InputLabel username = new InputLabel("username", Label.medievalFont, "username: ", 200, Label.POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT, new Vector2f(1f, 0.2f));
		username.getTransform().getPosition().set(-0.75f, 0.2f);
		root.add(username);

		InputLabel password = new InputLabel("password", Label.medievalFont, "password: ", 200, Label.POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT, new Vector2f(1f, 0.2f), false);
		password.getTransform().getPosition().set(-0.75f, 0f);
		root.add(password);

		Button button = new Button("loginButton", new Vector2f(1f, 0.2f), new Vector4f(1f, 0f, 0f, 1f), new Label("loginButtonLabel", Label.medievalFont, "LOG IN", 200, Label.POSITION_CENTERED));
		button.getTransform().getPosition().set(0f, -0.3f);
		root.add(button);
	}

	private static void initGL() {
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-((float)Display.getWidth() / (float) Display.getHeight()), ((float)Display.getWidth() / (float) Display.getHeight()), -1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.15f, 0.3f, 0.8f, 1.0f);
//		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
	}

	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
			Display.setResizable(true);
			Display.setTitle(Launcher.TITLE);
			Display.setVSyncEnabled(true);
//			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
		} catch(Exception e) {
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
