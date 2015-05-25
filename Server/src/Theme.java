import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Ole on 25/05/2015.
 */
public class Theme implements Serializable {

	private static HashMap<String, Color> defaultColors = new HashMap<>();
	private static HashMap<String, Color> colors = new HashMap<>();
	private static HashMap<String, Theme> themes = new HashMap<>();
	private static boolean initialized;

	private String name;
	private Color background, error, command, info;

	public static void init() {
		if (initialized)
			return;
		initialized = true;

		defaultColors.put("gray", Color.GRAY);
		defaultColors.put("light_gray", Color.LIGHT_GRAY);
		defaultColors.put("dark_gray", Color.DARK_GRAY);
		defaultColors.put("cyan", Color.CYAN);
		defaultColors.put("red", Color.RED);
		defaultColors.put("green", Color.GREEN);
		defaultColors.put("blue", Color.BLUE);
		defaultColors.put("black", Color.BLACK);
		defaultColors.put("white", Color.WHITE);
		defaultColors.put("pink", Color.PINK);
		defaultColors.put("orange", Color.ORANGE);
		defaultColors.put("magenta", Color.MAGENTA);
	}

	public static void createTheme(String name, Color background, Color command, Color info, Color error) {
		Theme theme = new Theme(name, background, command, info, error);

		if (!themes.containsKey(name)) {
			themes.put(name, theme);
			Console.println("Successfully created a theme with the name \"" + name + "\"");
		} else {
			Console.printErr("A theme with the name \"" + name + "\" already exists");
		}
	}

	public static void createColor(String name, int r, int g, int b) {
		Color color = new Color(r, g, b);
		if (!colors.containsKey(name)) {
			colors.put(name, color);
			Console.println("Color " + name + "(" + r + ", " + g + ", " + b + ") has been created");
		}
	}

	private Theme(String name, Color background, Color command, Color info, Color error) {
		this.name = name;
		this.background = background;
		this.error = error;
		this.command = command;
		this.info = info;
	}

	public static Theme getTheme(String name) {
		if (themes.containsKey(name)) {
			return themes.get(name);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Color getBackground() {
		return background;
	}

	public Color getError() {
		return error;
	}

	public Color getCommand() {
		return command;
	}

	public Color getInfo() {
		return info;
	}

}
