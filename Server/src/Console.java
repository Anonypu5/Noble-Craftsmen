import jexxus.common.Delivery;
import jexxus.server.Server;
import jexxus.server.ServerConnection;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 5/23/2015.
 */
public class Console {

	private static List<Command> commands = new ArrayList<>();
	private static JFrame frame;
	private static JTextPane textPane;
	private static StyledDocument document;
	private static Style style;
	public static JTextField textField;
	private static boolean nextRequested;
	private static String request = "";
	public static List<String> typed = new ArrayList<String>();
	public static int typedInt;

	public Console() {
		Theme.init();

		commands.add(new Command("echo") {
			public void run(String args) {
				if (ServerClass.running) {
					Console.println("Echoed \"" + args.trim() + "\"");
				} else {
					Console.printErr("Server not running");
				}
			}
		});

		commands.add(new Command("stop") {
			public void run(String args) {
				ServerClass.stop();
			}
		});

		commands.add(new Command("list") {
			public void run(String args) {
				for(int i = 0; i < ServerListener.list.size(); i++){
					println(i + "    " + ServerListener.list.get(i).conn.getIP() + "    " + (ServerListener.list.get(i).loggedIn ? ServerListener.list.get(i).name : "not logged in"));
				}
			}
		});

		commands.add(new Command("restart") {
			public void run(String args) {
				ServerClass.stop();
				new Thread(new ServerClass(),"ServerClass").start();
			}
		});

		commands.add(new Command("savesettings") {
			public void run(String args) {
				Save.saveSettings();
			}
		});

		commands.add(new Command("setname") {
			public void run(String args) {
				Save.name = args.trim().split(" ")[0];
				Console.println("set name to \"" + Save.name + "\"");
			}
		});

		commands.add(new Command("getname") {
			public void run(String args) {
				Console.println("Name = " + Save.name);
			}
		});

		commands.add(new Command("start") {
			public void run(String args) {
				new Thread(new ServerClass(),"ServerClass").start();
			}
		});

		commands.add(new Command("clear") {
			public void run(String args) {
				textPane.setText("");
			}
		});

		commands.add(new Command("createtheme") {
			public void run(String args) {
				String[] arguments = args.trim().split(" ");
				if(arguments.length < 5) {
					Console.printErr("Too few arguments - follow the following template\n" +
							" - createtheme name backgroundCol commandCol infoCol errorCol");
				} else if(arguments.length > 5) {
					Console.printErr("Too many arguments - follow the following template\n" +
							" - createtheme name backgroundCol commandCol infoCol errorCol");
				}

				boolean foundError = false;

				String name = arguments[0];

				String background = arguments[1];
				if(!Theme.doesColorExist(background)){
					Console.printErr("The color \"" + background + "\" does not exist");
					foundError = true;
				}
				String command = arguments[2];
				if(!Theme.doesColorExist(command)){
					Console.printErr("The color \"" + command + "\" does not exist");
					foundError = true;
				}
				String info = arguments[3];
				if(!Theme.doesColorExist(info)){
					Console.printErr("The color \"" + info + "\" does not exist");
					foundError = true;
				}
				String error = arguments[4];
				if(!Theme.doesColorExist(error)){
					Console.printErr("The color \"" + error + "\" does not exist");
					foundError = true;
				}

				if(foundError)
					return;

				Theme.createTheme(name, background, command, info, error);
			}
		});

		commands.add(new Command("settheme") {
			public void run(String args) {
				Theme.setTheme(args);
			}
		});

		commands.add(new Command("gettheme") {
			public void run(String args) {
				Console.println("Current theme: " + Theme.getCurrentThemeName());
			}
		});

		frame = new JFrame("NC-ServerClass");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				println("Saving...");
				println("See you soon, captain :)");
				println("PS: We fucked your mum");
				System.exit(0);
			}
		});

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Theme.getBackgroundColor());
		document = textPane.getStyledDocument();
		style = textPane.addStyle("Style all the things", null);
		frame.add(new JScrollPane(textPane), BorderLayout.CENTER);

		textField = new JTextField();
		textField.addActionListener(e -> {
			if (!e.getActionCommand().equals("")) {
				println("> " + e.getActionCommand(), Theme.getCommandColor());
				textField.setText("");

				if (nextRequested) {
					request = e.getActionCommand();
				} else {
					doCommand(e.getActionCommand());
					typed.add(e.getActionCommand());
				}
				typedInt = 0;
			}
		});
		textField.setBackground(new Color(40, 40, 40));
		textField.setCaretColor(Theme.getInfoColor());
		textField.setForeground(new Color(255, 0, 255));
		frame.add(textField, BorderLayout.SOUTH);

		frame.setVisible(true);

		frame.requestFocus();
		textField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {

			}

			public void keyPressed(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (Console.typedInt < Console.typed.size()) {
						Console.typedInt++;
					}
					Console.textField.setText(Console.typed.get(Console.typed.size() - Console.typedInt));
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					if (Console.typedInt > 0) {
						Console.typedInt--;
					}
					if(Console.typedInt > 0){
						Console.textField.setText(Console.typed.get(Console.typed.size() - Console.typedInt));
					} else {
						Console.textField.setText("");
					}
				}
			}
		});
		textField.requestFocusInWindow();
	}

	public static void println(String text) {
		println(text, Theme.getInfoColor());
	}

	public static void println(String text, Color color) {
		StyleConstants.setForeground(style, color);
		try {
			document.insertString(document.getLength(), text + "\n", style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public static void printErr(String text) {
		println(text, Theme.getErrorColor());
	}

    public static void exit(){
        println("Saving...");
        println("See you soon, captain :)");
        println("PS: We fucked your mum");
        System.exit(0);
    }

	public static void doCommand(String commandString) {
		String start = commandString.trim().split(" ")[0].toLowerCase();
		boolean foundCommand = false;
		for (Command command : commands) {
			if (command.getName().equals(start)) {
				command.run(commandString.substring(start.length()));
				foundCommand = true;
				break;
			}
		}
		if(!foundCommand)
			printErr("No such command: \"" + start + "\"");
	}

	public static String requestNext() {
		if (nextRequested) {
			return null;
		}
		nextRequested = true;
		request = "";
		while (request.equals("")) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nextRequested = false;
		return request;
	}

	public static void printStackTrace(Exception e){
		Console.printErr(e.toString());
		for (StackTraceElement x: e.getStackTrace()){
			Console.printErr("    "+x.toString());
		}
		e.printStackTrace();
	}
}

abstract class Command {

	private String name;

	public Command(String name) {
		this.name = name.toLowerCase();
	}

	public abstract void run(String args);

	public String getName() {
		return name;
	}

}
