package GUI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import DataManager.DataManager;

public class Window extends JFrame {
    private DataManager dm;
    private JPanel mainPanel;
    private CardLayout divs;

    public Window(DataManager dm)
    {
        this.dm=dm;
        setSize(390,844);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        divs=new CardLayout();
        mainPanel=new JPanel(divs);
        mainPanel.add(new Login(this,this.dm),"Login");
        mainPanel.add(new Register(this,this.dm),"Register");
        mainPanel.add(new Landing(this),"Landing");
        mainPanel.add(new Order(this,this.dm),"Order");
        divs.show(mainPanel, "Landing");
        add(mainPanel);
        setVisible(true);
    }

    public void showPanel(String name)
    {
        divs.show(mainPanel,name);
    }
}