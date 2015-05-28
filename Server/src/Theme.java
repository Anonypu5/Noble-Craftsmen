import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Ole on 25/05/2015.
 */
public class Theme implements Serializable {

	private static Theme currentTheme;
	private static HashMap<String, ThemeColor> defaultColors = new HashMap<>();
	private static HashMap<String, ThemeColor> colors = new HashMap<>();
	private static HashMap<String, Theme> defaultThemes = new HashMap<>();
	private static HashMap<String, Theme> themes = new HashMap<>();
	private static boolean initialized;

	private String name;
	private ThemeColor background, error, command, info;

	public static void init() {
		if (initialized)
			return;
		initialized = true;

		defaultColors.put("gray", ThemeColor.GRAY);
		defaultColors.put("light_gray", ThemeColor.LIGHT_GRAY);
		defaultColors.put("dark_gray", ThemeColor.DARK_GRAY);
		defaultColors.put("cyan", ThemeColor.CYAN);
		defaultColors.put("red", ThemeColor.RED);
		defaultColors.put("yellow", ThemeColor.YELLOW);
		defaultColors.put("green", ThemeColor.GREEN);
		defaultColors.put("blue", ThemeColor.BLUE);
		defaultColors.put("black", ThemeColor.BLACK);
		defaultColors.put("white", ThemeColor.WHITE);
		defaultColors.put("pink", ThemeColor.PINK);
		defaultColors.put("orange", ThemeColor.ORANGE);
		defaultColors.put("magenta", ThemeColor.MAGENTA);

		Theme defaultTheme = new Theme("default", ThemeColor.BLACK, ThemeColor.PINK, ThemeColor.GREEN, ThemeColor.RED);
		defaultThemes.put("default", defaultTheme);
		setTheme("default");

		SaveFile saveFile = Save.openSaveFile("themes.ncs");
		themes = (HashMap<String, Theme>) saveFile.obj[0][0];
		colors = (HashMap<String, ThemeColor>) saveFile.obj[0][1];
	}

	public static void createTheme(String name, String background, String command, String info, String error) {
		if (!themes.containsKey(name)) {
			themes.put(name, new Theme(name, getColor(background), getColor(command), getColor(info), getColor(error)));
			Console.println("Successfully created a theme with the name \"" + name + "\"");
		} else {
			Console.printErr("A theme with the name \"" + name + "\" already exists");
		}
	}

	public static void setTheme(String name) {
		Theme theme = getTheme(name);
		if (theme != null) {
			currentTheme = theme;
			Console.println("Theme set to: " + currentTheme);
		} else {
			Console.printErr("The theme \"" + name + "\" doesn't exist");
		}
	}

	public static Theme getTheme(String name) {
		if (defaultThemes.containsKey(name)) {
			return defaultThemes.get(name);
		} else if (themes.containsKey(name)) {
			return themes.get(name);
		}
		return null;
	}

	public static void createColor(String name, int r, int g, int b) {
		ThemeColor color = new ThemeColor(name, r, g, b);
		if (!colors.containsKey(name)) {
			colors.put(name, color);
			Console.println("Color " + name + "(" + r + ", " + g + ", " + b + ") has been created");
		} else {
			Console.printErr("A color with the name \"" + name + "\" already exists");
		}
	}

	public static void deleteColor(String name) {
		if (colors.containsKey(name)) {
			colors.remove(name);
			Console.println("Color \"" + name + "\" has been deleted");
		}
	}

	public static ThemeColor getColor(String name) {
		if (defaultColors.containsKey(name)) {
			return defaultColors.get(name);
		} else if (colors.containsKey(name)) {
			return colors.get(name);
		}
		System.out.println("plis");
		return null;
	}

	public static boolean doesColorExist(String name) {
		return defaultColors.containsKey(name) || colors.containsKey(name);
	}

	public static boolean doesThemeExist(String name) {
		return defaultThemes.containsKey(name) || themes.containsKey(name);
	}

	public static void printColors() {
		Console.println("current existing colors:");
		int index = 0;
		for (ThemeColor color : defaultColors.values()) {
			index++;
			Console.println("    -" + index + "  -  " + color.getName() + "(" + color.getColor().getRed() + ", " + color.getColor().getGreen() + ", " + color.getColor().getBlue() + ")");
		}
		for (ThemeColor color : colors.values()) {
			index++;
			Console.println("    -" + index + "  -  " + color.getName() + "(" + color.getColor().getRed() + ", " + color.getColor().getGreen() + ", " + color.getColor().getBlue() + ")");
		}
	}

	public static void printThemes() {
		Console.println("current existing themes:");
		int index = 0;
		for (Theme theme : defaultThemes.values()) {
			index++;
			Console.println("    -" + index + "  -  " + theme.name + "(" + theme.background + ", " + theme.command + ", " + theme.info + ", " + theme.error + ")");
		}
		for (Theme theme : themes.values()) {
			index++;
			Console.println("    -" + index + "  -  " + theme.name + "(" + theme.background + ", " + theme.command + ", " + theme.info + ", " + theme.error + ")");
		}
	}

	public static void saveThemesAndColors() {
		SaveFile saveFile = new SaveFile();
		saveFile.obj = new Object[1][2];
		saveFile.obj[0][0] = themes;
		saveFile.obj[0][1] = colors;
		Save.saveFile(saveFile, "themes.ncs");

		Console.println("Saved themes and colors");
	}

	private Theme(String name, ThemeColor background, ThemeColor command, ThemeColor info, ThemeColor error) {
		this.name = name;
		this.background = background;
		this.error = error;
		this.command = command;
		this.info = info;
	}

	public static String getCurrentThemeName() {
		return currentTheme.name;
	}

	public static Color getErrorColor() {
		return currentTheme.error.getColor();
	}

	public static Color getInfoColor() {
		return currentTheme.info.getColor();
	}

	public static Color getCommandColor() {
		return currentTheme.command.getColor();
	}

	public static Color getBackgroundColor() {
		return currentTheme.background.getColor();
	}

}

class ThemeColor {
	public static final ThemeColor GRAY = new ThemeColor("gray", Color.GRAY);
	public static final ThemeColor LIGHT_GRAY = new ThemeColor("light_gray", Color.LIGHT_GRAY);
	public static final ThemeColor DARK_GRAY = new ThemeColor("dark_gray", Color.DARK_GRAY);
	public static final ThemeColor CYAN = new ThemeColor("cyan", Color.CYAN);
	public static final ThemeColor RED = new ThemeColor("red", Color.RED);
	public static final ThemeColor YELLOW = new ThemeColor("yellow", Color.YELLOW);
	public static final ThemeColor GREEN = new ThemeColor("green", Color.GREEN);
	public static final ThemeColor BLUE = new ThemeColor("blue", Color.BLUE);
	public static final ThemeColor BLACK = new ThemeColor("black", Color.BLACK);
	public static final ThemeColor WHITE = new ThemeColor("white", Color.WHITE);
	public static final ThemeColor PINK = new ThemeColor("pink", Color.PINK);
	public static final ThemeColor ORANGE = new ThemeColor("orange", Color.ORANGE);
	public static final ThemeColor MAGENTA = new ThemeColor("magenta", Color.MAGENTA);

	private Color color;
	private String name;

	public ThemeColor(String name, int r, int g, int b) {
		this.name = name;
		this.color = new Color(r, g, b);
	}

	public ThemeColor(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

}
