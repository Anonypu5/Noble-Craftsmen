package no.grabit.NobleCraftsmen.scenegraph;

import no.grabit.NobleCraftsmen.physics.Transform;
import org.lwjgl.util.vector.Matrix4f;

import java.io.Serializable;

/**
 * Created by Ole on 23/05/2015.
 */
public abstract class GameComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	private GameObject parent;
	private Transform transform;
	private final String tag;

	public GameComponent(String tag) {
		this.tag = tag;
		transform = new Transform();
	}

	public abstract void update();

	public abstract void render();

	public abstract void exit();

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public GameObject getParent() {
		return parent;
	}

	public GameObject getRoot() {
		if(parent == null)
			return null;

		return parent.getRoot();
	}

	public Transform getTransform() {
		return transform;
	}

	public final Matrix4f getModelView() {
		Matrix4f modelView = getTransform().getModelView();

		if(parent == null)
			return modelView;
		else
			return Matrix4f.mul(parent.getModelView(), modelView, null);
	}

	public String getTag() {
		String[] tags = tag.split("\\.");
		return tags[tags.length - 1];
	}

	public String getFullTag() {
		return tag;
	}

}
