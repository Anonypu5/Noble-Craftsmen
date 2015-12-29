package no.grabit.NCLauncher.input;

import no.grabit.NCLauncher.graphics.Shader;
import no.grabit.NCLauncher.graphics.Texture;
import no.grabit.NCLauncher.scenegraph.GameObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.nio.FloatBuffer;

/**
 * Created by Ole on 26/05/2015.
 */
public class Label extends GameObject {

	public static final int POSITION_CENTERED = 0;
	public static final int POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT = 1;
	public static Font medievalFont;
	private static boolean initialized;
	protected String text;
	protected Font actualFont;
	protected UnicodeFont font;
	protected int positioning = POSITION_CENTERED;
	private float size;
	public Label(String tag, Font font, String text, float size, int positioningMode) {
		super("label." + tag);
		this.actualFont = font;
		this.text = text;
		this.size = size;
		this.positioning = positioningMode;
		updateFont();

		getTransform().getScale().set(0.001f, -0.001f);
	}

	public static void init() {
		if (initialized)
			return;
		initialized = true;

		try {
			medievalFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/JimNightshade-Regular.ttf"));
//			medievalFont = new java.awt.Font("Times New Roman", Font.PLAIN, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateFont() {
		actualFont = actualFont.deriveFont(size);
		font = new UnicodeFont(actualFont);
		font.getEffects().add(new ColorEffect(Color.WHITE));
		font.addAsciiGlyphs();
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		System.out.println("yeah?");
		switch (positioning) {
			case POSITION_CENTERED:
				getTransform().getPosition().set(getTransform().getPosition().getX() - font.getWidth(text) / 2, getTransform().getPosition().getY() - font.getLineHeight());
				break;
			case POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT:
				System.out.println("yeah.");
				//getTransform().getPosition().set(getTransform().getPosition().getX(), getTransform().getPosition().getY() - font.getLineHeight() + 100f);
				getTransform().getPosition().set(0f, 0f, 0f);
				break;
			default:
				break;
		}
	}

	public void setSize(float size) {
		this.size = size;
		updateFont();
	}

	public void setText(String text) {
		this.text = text;
		updateFont();
	}

	public void updateObject() {

	}

	public void renderObject() {
		Texture.unbind();
		Shader curShader = Shader.getCurShader();
		Shader.unbind();

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		Matrix4f mat4 = getModelView();
//		System.out.println("label: " + getTag() + "\n" + mat4.toString());
		System.out.println("\n" + getTag() + "(" + getTransform().getPosition().getX() + ", " + getTransform().getPosition().getY() + ")");
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
		mat4.store(floatBuffer);
		floatBuffer.flip();
		GL11.glLoadMatrix(floatBuffer);

		font.drawString(0, 0, text, org.newdawn.slick.Color.yellow);

		GL11.glLoadIdentity();

		if (curShader != null) curShader.bind();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public void exitObject() {
		font.destroy();
	}

	public void setPositioning(int mode) {
		this.positioning = mode;
	}

}
