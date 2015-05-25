package no.grabit.NobleCraftsmen.graphics;

import no.grabit.NobleCraftsmen.scenegraph.GameComponent;
<<<<<<< HEAD
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

=======
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Sprite extends GameComponent {

	Texture texture;
	int vao, vbo, vboi;
	int indicesCount;

	public Sprite(String tag, String spriteName) {
		super("sprite." + tag);
		texture = new Texture(spriteName);

		float[] vertices = {
				-0.5f, -0.5f, 0.0f, 0.0f, 1.0f,
				0.5f, -0.5f, 0.0f, 1.0f, 1.0f,
				0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
				-0.5f, 0.5f, 0.0f, 0.0f, 0.0f,
		};

		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length * 4);
		verticesBuffer.put(vertices);
		verticesBuffer.flip();

		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

		glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 5 * 4, 3 * 4);

		glBindBuffer(GL_ARRAY_BUFFER, 0);

		GL30.glBindVertexArray(0);

		byte[] indices = {
				0, 1, 2,
				2, 3, 0
		};
		indicesCount = indices.length;
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
		indicesBuffer.put(indices);
		indicesBuffer.flip();

		vboi = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboi);

		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void update() {
		// don't need this
>>>>>>> origin/master
	}

	public void render() {
		texture.bind();
<<<<<<< HEAD
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
=======

//		Vector2f translation = getParent().getTransform().getPosition();
//		glTranslatef(translation.x, translation.y, 0);
//
//		float rotation = getParent().getTransform().getRotation();
//		glRotatef(rotation, 0f, 0f, 1f);
//
//		Vector2f scale = getParent().getTransform().getPosition();
//		glScalef(scale.x, scale.y, 1);

//		glBegin(GL_QUADS);
//		glVertex2f(0.0f, 0.0f);
//		glVertex2f(1.0f, 0.0f);
//		glVertex2f(1.0f, 1.0f);
//		glVertex2f(0.0f, 1.0f);
//		glEnd();

		Shader.setUnfiformMat4f(getModelView(), "modelView");

		GL30.glBindVertexArray(vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboi);

		glDrawElements(GL_TRIANGLES, indicesCount, GL_UNSIGNED_BYTE, 0);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);

		Texture.unbind();
	}

	public void exit() {

	}

}
>>>>>>> origin/master
