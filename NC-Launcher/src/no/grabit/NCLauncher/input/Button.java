package no.grabit.NCLauncher.input;

import no.grabit.NCLauncher.graphics.ColoredQuadrilateral;
import no.grabit.NCLauncher.main.Launcher;
import no.grabit.NCLauncher.scenegraph.GameObject;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Ole on 03/06/2015.
 */
public class Button extends GameObject {

	protected boolean hoveringOver;
	private boolean pressed;
	private boolean justPressed;
	protected Vector2f bounds;
	protected Label label;

	public Button(String tag, Vector2f bounds, Vector4f color, Label label) {
		super("button." + tag);
		this.bounds = bounds;
		ColoredQuadrilateral background = new ColoredQuadrilateral("ButtonBackground." + tag, color);
		background.getTransform().setScale(bounds);
		this.add(background);
		this.label = label;
		this.add(label);
	}

	@Override
	protected void updateObject() {
		Point mouse = new Point(Launcher.getMouseX(), Launcher.getMouseY());
		Rectangle button = new Rectangle(getTransform().getPosition().getX() - bounds.getX() / 2, getTransform().getPosition().getY() - bounds.getY() / 2, bounds.getX(), bounds.getY());

		if(button.contains(mouse)) {
			hoveringOver = true;
			if(Mouse.isButtonDown(0)) {
				if(!pressed) {
					justPressed = true;
				} else {
					justPressed = false;
				}
				pressed = true;
			} else {
				pressed = justPressed = false;
			}
		} else {
			hoveringOver = pressed = justPressed = false;
		}

		if(justPressed) {
			System.out.println("logged in");
		}
	}

	@Override
	protected void renderObject() {

	}

	public boolean isPressed() {
		return pressed;
	}

	public boolean isHoveringOver() {
		return hoveringOver;
	}
}
