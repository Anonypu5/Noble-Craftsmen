package no.grabit.NCLauncher.physics;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Ole on 23/05/2015.
 */
public class Transform {

	private static final Vector3f ROTATION_AXIS = new Vector3f(0.0f, 0.0f, 1.0f);

	private Vector3f position;
	private float rotation;
	private Vector2f scale;

	public Transform() {
		position = new Vector3f(0f, 0f, 0f);
		rotation = 0;
		scale = new Vector2f(1f, 1f);
	}

	public Transform(float x, float y, float z, float rotation_, float scaleX, float scaleY) {
		this.position = new Vector3f(x, y, z);
		this.rotation = rotation_;
		this.scale = new Vector2f(scaleX, scaleY);
	}

	public Transform(Vector3f position_, float rotation_, Vector2f scale_) {
		this.position = new Vector3f(position_);
		this.rotation = rotation_;
		this.scale = new Vector2f(scale_);
	}

	public Matrix4f getModelView() {
		Matrix4f modelView = new Matrix4f();
		Matrix4f.setIdentity(modelView);
		modelView.translate(position);
		modelView.rotate((float) Math.toRadians(rotation), ROTATION_AXIS);
		modelView.scale(new Vector3f(scale.x, scale.y, 1));

		return modelView;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = new Vector3f(position);
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = new Vector2f(scale);
	}

}
