package no.grabit.NobleCraftsmen.scenegraph;

/**
 * Created by Ole on 23/05/2015.
 */
public abstract class GameComponent {

	private GameObject parent;

	public abstract void update();

	public abstract void render();

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

}
