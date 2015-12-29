package no.grabit.NobleCraftsmen.world;

import no.grabit.NobleCraftsmen.graphics.Sprite;
import no.grabit.NobleCraftsmen.graphics.Texture;
import no.grabit.NobleCraftsmen.scenegraph.GameObject;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Ole on 31/07/2015.
 */
public class World extends GameObject {

	private boolean initialized = false;

	public World(String tag) {
		super("world." + tag);
		init();
	}

	private void init() {
		if(initialized) return;
		initialized = true;

		Texture backgroundTexture = new Texture("/textures/world/grass01.png");
		Sprite background01 = new Sprite("background01", backgroundTexture);
		Sprite background02 = new Sprite("background01", backgroundTexture);
		background02.getTransform().setPosition(new Vector3f(0f, 1f, 0f));

		add(background01);
		add(background02);
	}

}
