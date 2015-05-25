package no.grabit.NobleCraftsmen.graphics;

import no.grabit.NobleCraftsmen.util.FileUtils;
<<<<<<< HEAD
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBVertexShader;
=======
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import java.nio.FloatBuffer;
>>>>>>> origin/master

import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;

/**
 * Created by Ole on 24/05/2015.
 */
public class Shader {

<<<<<<< HEAD
=======
	private static Shader curShader = null;

>>>>>>> origin/master
	public static final Shader basicShader = new Shader("basic");

	private int program;

	private Shader(String shaderName) {
		int vertShader = 0, fragShader = 0;

		try {
			vertShader = createShader(shaderName, ARBVertexShader.GL_VERTEX_SHADER_ARB);
			fragShader = createShader(shaderName, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		} catch (Exception exc) {
			exc.printStackTrace();
			return;
		} finally {
			if (vertShader == 0 || fragShader == 0)
				return;
		}

		int tempProgram = glCreateProgramObjectARB();

		if (tempProgram == 0)
			return;

		glAttachObjectARB(tempProgram, vertShader);
		glAttachObjectARB(tempProgram, fragShader);

		glLinkProgramARB(tempProgram);
		if (glGetObjectParameteriARB(tempProgram, GL_OBJECT_LINK_STATUS_ARB) == GL_FALSE) {
			System.err.println(getLogInfo(tempProgram));
			return;
		}

		glValidateProgramARB(tempProgram);
		if (glGetObjectParameteriARB(tempProgram, GL_OBJECT_VALIDATE_STATUS_ARB) == GL_FALSE) {
			System.err.println(getLogInfo(tempProgram));
			return;
		}

<<<<<<< HEAD
=======

		GL20.glBindAttribLocation(program, 0, "attr_Position");
		GL20.glBindAttribLocation(program, 1, "attr_TexCoord");

		int uniform1 = glGetUniformLocationARB(program, "modelView");

>>>>>>> origin/master
		this.program = tempProgram;
	}

	public void bind() {
		glUseProgramObjectARB(program);
<<<<<<< HEAD
=======
		curShader = this;
>>>>>>> origin/master
	}

	public static void unbind() {
		glUseProgramObjectARB(0);
<<<<<<< HEAD
=======
		curShader = null;
	}

	public static void setUnfiformMat4f(Matrix4f mat4, String name) {
		if(curShader == null)
			return;
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
		mat4.store(floatBuffer);
		floatBuffer.flip();
		glUniformMatrix4ARB(glGetUniformLocationARB(curShader.program, name), false, floatBuffer);
>>>>>>> origin/master
	}

	private int createShader(String shaderName, int shaderType) throws Exception {
		int shader = 0;
		try {
			shader = glCreateShaderObjectARB(shaderType);

			if (shader == 0)
				return 0;

			glShaderSourceARB(shader, FileUtils.readFileAsString("/shaders/" + shaderName + (shaderType == ARBFragmentShader.GL_FRAGMENT_SHADER_ARB ? ".frag" : ".vert")));
			glCompileShaderARB(shader);

			if (glGetObjectParameteriARB(shader, GL_OBJECT_COMPILE_STATUS_ARB) == GL_FALSE)
				throw new RuntimeException("Error creating shader: " + getLogInfo(shader));

			return shader;
		} catch (Exception exc) {
			glDeleteObjectARB(shader);
			throw exc;
		}
	}

	private static String getLogInfo(int obj) {
		return glGetInfoLogARB(obj, glGetObjectParameteriARB(obj, GL_OBJECT_INFO_LOG_LENGTH_ARB));
	}

}
