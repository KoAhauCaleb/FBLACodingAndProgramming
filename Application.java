import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		GUIWindow theGUI = new GUIWindow();
		theGUI.setTitle("Family Fun Center");
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theGUI.pack();
		theGUI.setVisible(true);
	}
}