package no.grabit.NobleCraftsmen.graphics;

import no.grabit.NobleCraftsmen.scenegraph.GameComponent;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Ole on 24/05/2015.
 */
public class Sprite extends GameComponent {

	private Texture texture;

	public Sprite(String spriteName) {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(spriteName));
		} catch (IOException e) {
			e.printStackTrace();
			texture = null;
			System.out.println("Couldn't load texture \"" + spriteName + "\"");
		}
	}

	public void update() {

	}

	public void render() {
		texture.bind();
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
	}
}
