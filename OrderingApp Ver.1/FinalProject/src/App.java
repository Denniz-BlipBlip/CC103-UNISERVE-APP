import javax.swing.SwingUtilities;
import GUI.Window;
import DataManager.DataManager;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                DataManager dm=new DataManager();
                new Window(dm);
            }
        });
    }
}
