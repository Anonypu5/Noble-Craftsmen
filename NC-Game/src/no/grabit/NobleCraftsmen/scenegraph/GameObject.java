package no.grabit.NobleCraftsmen.scenegraph;

import no.grabit.NobleCraftsmen.physics.Transform;
import org.lwjgl.util.vector.Matrix4f;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 23/05/2015.
 */
public class GameObject implements Serializable {
	private static final long serialVersionUID = 2L;

	/**
	 * GameObject class.
	 * parent - the GameObject over this one on the hierarchy.
	 * */

	private GameObject parent;
	private Transform transform;
	private final String tag;

	private List<GameObject> children;
	private List<GameComponent> components;

	public GameObject(String tag) {
		this.tag = tag;
		children = new ArrayList<>();
		components = new ArrayList<>();
		transform = new Transform();
	}

	protected void updateObject() {}

	protected void renderObject() {}

	protected void exitObject() {}

	public final void update() {
		updateObject();

		for(GameComponent component : components) {
			component.update();
		}
		for(GameObject child : children) {
			child.update();
		}
	}

	public final void render() {
		renderObject();

		for(GameComponent component : components) {
			component.render();
		}
		for(GameObject child : children) {
			child.render();
		}
	}

	public final void exit() {
		exitObject();

		for(GameComponent component : components) {
			component.exit();
		}
		for(GameObject child : children) {
			child.exit();
		}
	}

	public final void add(GameObject child) {
		if(child == null) return;
		child.parent = this;
		children.add(child);
	}

	public final void remove(GameObject child) {
		if(child == null) return;
		child.parent = null;
		if(children.contains(child))
			children.remove(child);
	}

	public final void add(GameComponent component) {
		if(component == null) return;
		component.setParent(this);
		components.add(component);
	}

	public final void remove(GameComponent component) {
		if(component == null) return;
		component.setParent(null);
		if(components.contains(component))
			components.remove(component);
	}

	public final Transform getTransform() {
		return transform;
	}

	public final Matrix4f getModelView() {
		Matrix4f modelView = getTransform().getModelView();

		if(parent == null)
			return modelView;
		else
			return Matrix4f.mul(parent.getModelView(), modelView, null);
	}

	public final GameObject getParent() {
		return parent;
	}

	public final GameObject getRoot() {
		GameObject cur = this;
		while(true) {
			if(cur.parent == null)
				break;

			cur = cur.parent;
		}
		return cur;
	}

	public final String getTag() {
		String[] tags = tag.split("\\.");
		return tags[tags.length - 1];
	}

	public final String getFullTag() {
		return tag;
	}

}
