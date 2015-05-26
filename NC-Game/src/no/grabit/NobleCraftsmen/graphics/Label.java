package no.grabit.NobleCraftsmen.graphics;

import no.grabit.NobleCraftsmen.scenegraph.GameComponent;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

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
//			medievalFont = Font.createFont(Font.TRUETYPE_FONT, Label.class.getResourceAsStream("/fonts/JimNightshade-Regular.tff"));
			medievalFont = new java.awt.Font("Times New Roman", Font.PLAIN, 24);
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
		font.getEffects().add(new ColorEffect(Color.white));
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
		Shader.setUnfiformMat4f(getModelView(), "modelView");
		font.drawString(0, 0, text, org.newdawn.slick.Color.yellow);
	}

	public void exit() {
		font.destroy();
	}

}
