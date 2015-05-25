package no.grabit.NobleCraftsmen.scenegraph;

import no.grabit.NobleCraftsmen.physics.Transform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 23/05/2015.
 */
public class GameObject implements Serializable {

	/**
	 * GameObject class.
	 * parent - the GameObject over this one on the hierarchy.
	 * */

	private GameObject parent;
	private Transform transform;

	private List<GameObject> children;
	private List<GameComponent> components;

	public GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
	}

	public void updateObject() {}

	public void renderObject() {}

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

	public void add(GameObject child) {
		if(child == null) return;
		child.parent = this;
		children.add(child);
	}

	public void remove(GameObject child) {
		if(child == null) return;
		child.parent = null;
		if(children.contains(child))
			children.remove(child);
	}

	public void add(GameComponent component) {
		if(component == null) return;
		component.setParent(this);
		components.add(component);
	}

	public void remove(GameComponent component) {
		if(component == null) return;
		component.setParent(null);
		if(components.contains(component))
			components.remove(component);
	}

	public Transform getTransform() {
		return transform;
	}

	public GameObject getParent() {
		return parent;
	}

	public GameObject getRoot() {
		GameObject cur = this;
		while(true) {
			if(cur.parent == null)
				break;

			cur = cur.parent;
		}
		return cur;
	}

}
