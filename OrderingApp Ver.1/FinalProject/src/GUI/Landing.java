package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Landing extends JPanel{
    private JProgressBar loadBar;
    private JLabel label;
    private Timer time;
    private int value=0;
    private final Dimension LOADSIZE=new Dimension(290,30);
    private final Color BTN_HEADERS=new Color(0xBA, 0x75, 0x17);
    private final Color BACKGROUND=new Color(0xFA, 0xEE, 0xDA);
    private final Font TEXTLABEL=new Font("Trebuchet MS", Font.BOLD, 20);
    private final Dimension LOGOSIZE=new Dimension(300,300);

    public Landing(Window window)
    {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        label=new JLabel("UniServe");
        label.setFont(TEXTLABEL);
        label.setForeground(BTN_HEADERS);
        label.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(150));
        loadBar=new JProgressBar();
        loadBar.setMaximumSize(LOADSIZE);
        loadBar.setForeground(BTN_HEADERS);
        loadBar.setStringPainted(true);
        loadBar.setAlignmentX(CENTER_ALIGNMENT);
        add(fieldLogo());
        add(Box.createVerticalStrut(10));
        add(label);
        add(Box.createVerticalStrut(10));
        add(loadBar);
        load(window);
    }

    private void load(Window window)
    {
        time=new Timer(15,e->{
            value++;
            loadBar.setValue(value);
            if(value>=200)
            {
                time.stop();
                window.showPanel("Login");
            }
        });
        time.start();
    }

    private JPanel fieldLogo()
    {
        ImageIcon logo=new ImageIcon(getClass().getResource("/GUI/UniServeLogo.png"));
        Image logoImage=logo.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
        JLabel label=new JLabel(new ImageIcon(logoImage));
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND);
        label.setMaximumSize(LOGOSIZE);
        label.setBackground(BACKGROUND);
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);
        add(Box.createVerticalStrut(15));

        return panel;
    }
}