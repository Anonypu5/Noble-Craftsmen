package no.grabit.NCLauncher.graphics;

import no.grabit.NCLauncher.scenegraph.GameObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector4f;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

/**
 * Created by Ole on 05/06/2015.
 */
public class ColoredQuadrilateral extends GameObject {

	int vao, vbo, vboi;
	int indicesCount;

	public ColoredQuadrilateral(String tag, Vector4f color) {
		super(tag);

		float[] vertices = {
				-0.5f, -0.5f, 0.0f, color.getX(), color.getY(), color.getZ(), color.getW(),
				0.5f, -0.5f, 0.0f, color.getX(), color.getY(), color.getZ(), color.getW(),
				0.5f, 0.5f, 0.0f, color.getX(), color.getY(), color.getZ(), color.getW(),
				-0.5f, 0.5f, 0.0f, color.getX(), color.getY(), color.getZ(), color.getW(),
		};

		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length * 4);
		verticesBuffer.put(vertices);
		verticesBuffer.flip();

		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

		glVertexAttribPointer(0, 3, GL_FLOAT, false, 7 * 4, 0);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 7 * 4, 3 * 4);

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

	@Override
	protected void updateObject() {
	}

	@Override
	protected void renderObject() {
		Shader.coloredQuadrilateralShader.bind();
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
		Shader.unbind();
	}

	@Override
	protected void exitObject() {

	}
}
