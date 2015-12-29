package no.grabit.NCLauncher.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Ole on 24/05/2015.
 */
public class FileUtils {

	public static String readFileAsString(String fileName) {
		String result = "";

		try {
			URL url = FileUtils.class.getResource(fileName);

			File file = new File(url.toURI());

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				result += scanner.nextLine() + "\n";
			}

			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static BufferedImage loadImageFile(String fileName) {
		try {
			return ImageIO.read(FileUtils.class.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
