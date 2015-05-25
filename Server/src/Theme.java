import java.awt.*;
import java.util.HashMap;

/**
 * Created by Ole on 25/05/2015.
 */
public class Theme {

	public static HashMap<String, Color> colors = new HashMap<>();
	public static HashMap<String, Theme> themes = new HashMap<>();

	private String name;
	private Color background, error, command, info;

	public static void init() {
		colors.put("gray", Color.GRAY);
		colors.put("light_gray", Color.LIGHT_GRAY);
		colors.put("dark_gray", Color.DARK_GRAY);
		colors.put("cyan", Color.CYAN);
		colors.put("red", Color.RED);
		colors.put("green", Color.GREEN);
		colors.put("blue", Color.BLUE);
		colors.put("black", Color.BLACK);
		colors.put("white", Color.WHITE);
		colors.put("pink", Color.PINK);
		colors.put("ORANGE", Color.ORANGE);
		colors.put("pink", Color.PINK);
	}

	public static void createTheme(String name, Color background, Color error, Color command, Color info) {
		Theme theme = new Theme(name, background, error, command, info);

		if(!themes.containsKey(name)){
			themes.put(name, theme);
			Console.println("Successfully created a theme with the name \"" + name + "\"");
		} else {
			Console.printErr("A theme with the name \"" + name + "\" already exists");
		}
	}

	private Theme(String name, Color background, Color error, Color command, Color info) {
		this.name = name;
		this.background = background;
		this.error = error;
		this.command = command;
		this.info = info;
	}

	public static Theme getTheme(String name) {
		if(themes.containsKey(name)) {
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
