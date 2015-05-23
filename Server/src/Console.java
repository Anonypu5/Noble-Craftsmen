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
        textPane.setBackground(Color.BLACK);
		document = textPane.getStyledDocument();
		style = textPane.addStyle("Style all the things", null);
        frame.add(new JScrollPane(textPane), BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(!e.getActionCommand().equals("")){
                    println("> "+e.getActionCommand(), new Color(255,0,255));
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
        textField.setCaretColor(Color.GREEN);
        textField.setForeground(new Color(255,0,255));
        frame.add(textField, BorderLayout.SOUTH);

        frame.setVisible(true);

		frame.requestFocus();
		textField.requestFocusInWindow();
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
