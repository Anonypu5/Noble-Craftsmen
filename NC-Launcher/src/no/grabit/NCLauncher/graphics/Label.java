package no.grabit.NCLauncher.graphics;

import no.grabit.NCLauncher.scenegraph.GameComponent;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.awt.Color;

/**
 * Created by Ole on 26/05/2015.
 */
public class Label extends GameComponent {

	public static Font medievalFont;
	private static boolean initialized;

	public static void init() {
		if(initialized)
			return;
		initialized = true;

		try {
			medievalFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/JimNightshade-Regular.ttf"));
//			medievalFont = new java.awt.Font("Times New Roman", Font.PLAIN, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String text;
	private float size;
	private Font actualFont;
	private UnicodeFont font;

	public Label(String tag, Font font, String text, float size) {
		super("label." + tag);
		this.actualFont = font;
		this.text = text;
		this.size = size;
		updateFont();
	}

	private void updateFont() {
		actualFont = actualFont.deriveFont(size);
		font = new UnicodeFont(actualFont);
		font.getEffects().add(new ColorEffect(Color.WHITE));
		font.addAsciiGlyphs();
		try { font.loadGlyphs(); }
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void setSize(float size) {
		this.size = size;
		updateFont();
	}

	public void setText(String text) {
		this.text = text;
	}

	public void update() {

	}

	public void render() {
		Texture.unbind();
		Shader curShader = Shader.getCurShader();
		Shader.unbind();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glScalef(getTransform().getScale().getX(), getTransform().getScale().getY(), 1f);
		font.drawString(0, 0, text, org.newdawn.slick.Color.yellow);
		GL11.glLoadIdentity();
		curShader.bind();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public void exit() {
		font.destroy();
	}

}
