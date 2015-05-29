import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 5/23/2015.
 */
public class Save {

	public static String osd, os, folder;

	public static int port;
	public static String name = "Server";

	public Save() {
		List<String> list = new ArrayList<String>();
		Object a = list;

		os = System.getProperty("os.name");
		Console.println("Running on platform using " + os);

		if (os.startsWith("Windows")) {
			osd = "\\";
			folder = System.getProperty("user.home") + "\\AppData\\Roaming\\.NC-ServerClass";
		} else if (os.startsWith("Mac")) {
			osd = "/";
			folder = System.getProperty("user.home") + "/NC-ServerClass";
		} else {
			Console.println("NC-ServerClass Does not currently support this platform");
		}

		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		dir = new File(dir.getPath() + osd + "Settings.ncs");
		if (!dir.exists()) {
			Console.println("Creating and saving settings...");
			port = 1999;
			saveSettings();
		} else {
			loadSettings();
		}
	}

	private void loadSettings() {
		SaveFile file = openSaveFile("settings.ncs");

		if (file == null)
			return;

		port = (int) file.obj[0][1];
		name = (String) file.obj[1][1];
	}

	public static SaveFile openSaveFile(String name) {
		try {
			FileInputStream i = new FileInputStream(folder + osd + name);
			long length = new File(folder + osd + name).length();
			byte[] bytes = new byte[(int) length];
			i.read(bytes);
			ByteArrayInputStream b = new ByteArrayInputStream(bytes);
			ObjectInputStream o = new ObjectInputStream(b);
			SaveFile file = (SaveFile) o.readObject();
			return file;
		} catch (Exception e) {
			Console.printErr(e.toString());
			for (StackTraceElement x : e.getStackTrace()) {
				Console.printErr("    " + x.toString());
			}
			e.printStackTrace();
			Console.println("The process above failed, due to the error above \n Do you want to try again y/n?");
			String awnser = Console.requestNext();
			while (!awnser.equals("y") && !awnser.equals("n")) {
				Console.println("Please write y or n");
				awnser = Console.requestNext();
			}
			if (awnser.equals("y")) {
				return openSaveFile(name);
			}
		}
		return null;
	}

	public static void saveSettings() {
		Console.println("Saved settings:" +
				"\nPort: " + Save.port +
				"\nName: " + Save.name);
		SaveFile file = new SaveFile();
		file.obj = new Object[2][2];
		file.obj[0][0] = "Port";
		file.obj[0][1] = port;
		file.obj[1][0] = "Name";
		file.obj[1][1] = name;

		saveFile(file, "settings.ncs");
	}

	public static void saveFile(SaveFile saveFile, String name) {
		byte[] bytes;
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream o = new ObjectOutputStream(b);
			o.writeObject(saveFile);
			bytes = b.toByteArray();
			FileOutputStream fos = new FileOutputStream(folder + osd + name);
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			Console.printErr(e.toString());
			for (StackTraceElement x : e.getStackTrace()) {
				Console.printErr("    " + x.toString());
			}
			e.printStackTrace();
			Console.println("The process above failed, due to the error above \n Do you want to try again y/n?");
			String answer = Console.requestNext();
			while (!answer.equals("y") && !answer.equals("n")) {
				Console.println("Please write y or n");
				answer = Console.requestNext();
			}
			if (answer.equals("y")) {
				saveSettings();
			} else {
				Console.exit();
			}
		}
	}

}

class SaveFile implements Serializable {
	private static final long serialVersionUID = 1L;
	Object obj[][];
}
