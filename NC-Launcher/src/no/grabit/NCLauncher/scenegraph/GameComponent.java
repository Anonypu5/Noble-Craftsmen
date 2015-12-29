package no.grabit.NCLauncher.scenegraph;

import no.grabit.NCLauncher.physics.Transform;
import org.lwjgl.util.vector.Matrix4f;

import java.io.Serializable;

/**
 * Created by Ole on 23/05/2015.
 */
public abstract class GameComponent implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String tag;
	private GameObject parent;
	private Transform transform;

	public GameComponent(String tag) {
		this.tag = tag;
		transform = new Transform();
	}

	public abstract void update();

	public abstract void render();

	public abstract void exit();

	public final GameObject getParent() {
		return parent;
	}

	public final void setParent(GameObject parent) {
		this.parent = parent;
	}

	public final GameObject getRoot() {
		if (parent == null)
			return null;

		return parent.getRoot();
	}

	public final Transform getTransform() {
		return transform;
	}

	public final Matrix4f getModelView() {
		Matrix4f modelView = getTransform().getModelView();

		if (parent == null)
			return modelView;
		else
			return Matrix4f.mul(parent.getModelView(), modelView, null);
	}

	public final String getTag() {
		String[] tags = tag.split("\\.");
		return tags[tags.length - 1];
	}

	public final String getFullTag() {
		return tag;
	}

}
