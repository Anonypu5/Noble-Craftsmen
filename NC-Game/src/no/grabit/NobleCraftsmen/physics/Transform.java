package no.grabit.NobleCraftsmen.physics;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Ole on 23/05/2015.
 */
public class Transform {

	private Vector2f position;
	private float rotation;
	private Vector2f scale;

	public void Transform() {
		position = new Vector2f(0, 0);
		rotation = 0;
		scale = new Vector2f(1, 1);
	}

	public void Transform(float x, float y, float rotation_, float scaleX, float scaleY) {
		this.position = new Vector2f(x, y);
		this.rotation = rotation_;
		this.scale = new Vector2f(scaleX, scaleY);
	}

	public void Transform(Vector2f position_, float rotation_, Vector2f scale_) {
		this.position = new Vector2f(position_);
		this.rotation = rotation_;
		this.scale = new Vector2f(scale_);
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = new Vector2f(position);
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
