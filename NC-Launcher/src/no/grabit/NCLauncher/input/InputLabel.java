package no.grabit.NCLauncher.input;

import no.grabit.NCLauncher.main.Launcher;
import no.grabit.NCLauncher.util.Time;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.awt.event.KeyEvent;

import static org.lwjgl.input.Keyboard.*;

import java.awt.*;

/**
 * Created by Ole on 30/05/2015.
 */
public class InputLabel extends Label implements Focusable {

	private static final String characters = " 0123456789abcdefghijklmnopqrstuvwxyz";

	private static final int[] characterCodes = {
			KEY_SPACE,
			KEY_0,
			KEY_1,
			KEY_2,
			KEY_3,
			KEY_4,
			KEY_5,
			KEY_6,
			KEY_7,
			KEY_8,
			KEY_9,
			KEY_A,
			KEY_B,
			KEY_C,
			KEY_D,
			KEY_E,
			KEY_F,
			KEY_G,
			KEY_H,
			KEY_I,
			KEY_J,
			KEY_K,
			KEY_L,
			KEY_M,
			KEY_N,
			KEY_O,
			KEY_P,
			KEY_Q,
			KEY_R,
			KEY_S,
			KEY_T,
			KEY_U,
			KEY_V,
			KEY_W,
			KEY_X,
			KEY_Y,
			KEY_Z,
	};
	private static boolean[] hasBeenPressed = new boolean[characterCodes.length];

	private FocusGroup group;
	private Vector2f bounds;
	private boolean hasFocus, showCaret;
	private String actualText, inputString;
	private boolean showInputText;
	private float backspaceTimer;

	public InputLabel(String tag, Font font, String text, float size, int positioningMode, Vector2f bounds) {
		super(tag, font, text, size, positioningMode);
		this.bounds = bounds;
		actualText = text;
		inputString = "";
		showInputText = true;
	}

	public InputLabel(String tag, Font font, String text, float size, int positioningMode, Vector2f bounds, boolean showInputText) {
		super(tag, font, text, size, positioningMode);
		this.bounds = bounds;
		actualText = text;
		inputString = "";
		this.showInputText = showInputText;
	}

	public void updateObject() {
		super.updateObject();

		if (Mouse.isButtonDown(0)) {
			Point point = new Point(Launcher.getMouseX(), Launcher.getMouseY());
			Rectangle rectangle = null;
			if (positioning == POSITION_CENTERED) {
				rectangle = new Rectangle(getTransform().getPosition().getX(), getTransform().getPosition().getY() + (float) font.getHeight(text) / Display.getHeight() / 2, bounds.getX(), bounds.getY());
			} else if (positioning == POSITION_CENTERED_MIDDLE_LEFT_TO_RIGHT) {
				rectangle = new Rectangle(getTransform().getPosition().getX(), getTransform().getPosition().getY() + (float) font.getHeight(text) / Display.getHeight() / 2, bounds.getX(), bounds.getY());
			}

			if (rectangle != null) {
				if (rectangle.contains(point)) {
					if (group != null) {
						group.updateFocus(this, Time.getTime());
					} else {
						hasFocus = true;
					}
				} else {
					if (group != null) {
						group.updateFocus(null, Time.getTime());
					} else {
						hasFocus = false;
					}
				}
			}
		}

		if (hasFocus) {
			if (Time.getTime() % Time.getSecond() < Time.getSecond() * 0.5) {
				showCaret = true;
			} else {
				showCaret = false;
			}

			for (int index = 0; index < characterCodes.length; index++) {
				if (Keyboard.isKeyDown(characterCodes[index])) {
					if (!hasBeenPressed[index]) {
						if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
							inputString += characters.toUpperCase().charAt(index);
						else
							inputString += characters.charAt(index);
						hasBeenPressed[index] = true;
					}
				} else {
					hasBeenPressed[index] = false;
				}
			}
			if (backspaceTimer <= 0 && Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
				backspaceTimer = 0.19f;
				if (inputString.length() > 0) inputString = inputString.substring(0, inputString.length() - 1);
			}
			if (backspaceTimer > 0) {
				backspaceTimer -= Time.deltaTime();
			}
		}
	}

	boolean f = true;

	@Override
	public void renderObject() {
		if(f) {
f = !f;
			getTransform().getPosition().set(getTransform().getPosition().getX(), getTransform().getPosition().getY());
		}

		if (showInputText)
			text = actualText + inputString;
		else {
			text = actualText;
			for (int i = 0; i < inputString.length(); i++) {
				text += "*";
			}
		}

		if (hasFocus && showCaret)
			text += "|";

		super.renderObject();
	}

	public void onGetFocus() {
		this.hasFocus = true;
	}

	public void onLoseFocus() {
		this.hasFocus = false;
	}

	public void setShowInputText(boolean showInputText) {
		this.showInputText = showInputText;
	}

	public boolean hasFocus() {
		return hasFocus;
	}

	public void setFocusGroup(FocusGroup group) {
		this.group = group;
	}

	public String getInputString() {
		return inputString;
	}

}
