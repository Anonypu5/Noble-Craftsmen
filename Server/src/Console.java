import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Elias on 5/23/2015.
 */
public class Console {

    private static JFrame frame;
    private static JTextPane textPane;
	private static StyledDocument document;
	private static Style style;
    private static JTextField textField;
    private static boolean nextRequested;
    private static String request = "";

    public Console(){
        frame = new JFrame("NC-Server");
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
<<<<<<< HEAD
        textPane.setBackground(Color.black);
=======
		document = textPane.getStyledDocument();
		style = textPane.addStyle("Style all the things", null);
>>>>>>> 7110ec5e406604d74a9da37e0aa6eec07be2ef7b
        frame.add(new JScrollPane(textPane), BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
                if (!e.getActionCommand().equals("")) {
                    println("> " + e.getActionCommand());
=======
                if(!e.getActionCommand().equals("")){
                    println("> "+e.getActionCommand(), Color.BLUE);
>>>>>>> 7110ec5e406604d74a9da37e0aa6eec07be2ef7b
                    textField.setText("");

                    if (nextRequested) {
                        request = e.getActionCommand();
                    } else {
                        doCommand(e.getActionCommand());
                    }
                }
            }
        });
        textField.setBackground(new Color(40,40,40));
        textField.setCaretColor(Color.green);
        textField.setForeground(Color.blue);
        frame.add(textField, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void println(String text){
		println(text, Color.GREEN);
    }

	public static void println(String text, Color color){
		StyleConstants.setForeground(style, color);
		try {
			document.insertString(document.getLength(), text + "\n", style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public static void printErr(String text) {
		println(text, Color.RED);
	}

    public static void doCommand(String command){
        if(command.startsWith("echo ")){
            commands.echo(command);
        }else{
            printErr("Unknown command \""+command+"\"");
        }
    }

    public static String requestNext() {
        if(nextRequested){
            return null;
        }
        nextRequested = true;
        request = "";
        while(request.equals("")){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nextRequested = false;
        return request;
    }
}class commands{

    public static void echo(String command){
        command = command.substring(5);
        Console.println("Echoed \""+command+"\"");
    }
}
