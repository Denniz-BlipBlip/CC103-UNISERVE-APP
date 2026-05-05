package GUI;
import DataManager.DataManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {
    private KeyAdapter checkField;
    private Window window;
    private DataManager dm;
    private JLabel titleText;
    private JButton loginBtn, registerBtn;
    private JPasswordField passwordField;
    private JTextField nameField;
    private final Dimension BTNSIZE=new Dimension(320,50);
    private final Dimension FIELDSIZE=new Dimension(Integer.MAX_VALUE,40);
    private final Color BTN_HEADERS=new Color(0xBA, 0x75, 0x17);
    private final Color BACKGROUND=new Color(0xFA, 0xEE, 0xDA);
    private final Color TEXT=new Color(0x41, 0x24, 0x02);
    private final Font TEXTTITLE=new Font("Georgia", Font.BOLD, 24);
    private final Font TEXTINPUT=new Font("Trebuchet MS", Font.PLAIN, 15);
    private final Font TEXTLABEL=new Font("Trebuchet MS", Font.BOLD, 20);
    private final Font TEXTBTN=new Font("Arial",Font.BOLD,18);
    private final Dimension HEADERLOGOSIZE=new Dimension(150,150);
    private final Dimension HEADERSIZE=new Dimension(320,150);

    public Login(Window window,DataManager dm)
    {        
        this.dm=dm;
        this.window=window;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(0,30,30,30));

        add(header());
        add(Box.createVerticalStrut(20))
;        
        titleText=new JLabel("Login");
        titleText.setFont(TEXTTITLE);
        titleText.setForeground(BTN_HEADERS);
        titleText.setAlignmentX(CENTER_ALIGNMENT);
        add(titleText);

        add(Box.createVerticalStrut(30));

        nameField=new JTextField();
        add(field("Name: ",nameField));

        passwordField=new JPasswordField();
        add(field("Password: ",passwordField));

        add(Box.createVerticalStrut(30));
        
        loginBtn=new JButton("Login");
        loginBtn.addActionListener(e->loginHandler());
        add(fieldBtn(loginBtn));

        registerBtn=new JButton("Register");
        registerBtn.addActionListener(e->toRegister());
        add(fieldBtn(registerBtn));
        checkAllField();
    }

    private JPanel field(String labelText, JComponent comp)
    {
        JPanel panel=new JPanel();
        JLabel label=new JLabel(labelText);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND);
        panel.setAlignmentX(CENTER_ALIGNMENT);

        label.setFont(TEXTLABEL);
        label.setForeground(TEXT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        comp.setMaximumSize(FIELDSIZE);
        comp.setFont(TEXTINPUT);
        comp.setForeground(TEXT);
        comp.setAlignmentX(LEFT_ALIGNMENT);

        add(Box.createVerticalStrut(15));
        panel.add(label);
        add(Box.createVerticalStrut(5));
        panel.add(comp);
        return panel;
    }

    private JPanel fieldBtn(JComponent comp)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND);
        panel.setAlignmentX(CENTER_ALIGNMENT);

        comp.setFont(TEXTBTN);
        comp.setForeground(TEXT);
        comp.setBackground(BTN_HEADERS);
        comp.setMaximumSize(BTNSIZE);
        comp.setAlignmentX((CENTER_ALIGNMENT));

        panel.add(comp);
        panel.add(Box.createVerticalStrut(10));
        return panel;
    }

    private void loginHandler()
    {
        String name=nameField.getText();
        String password=new String(passwordField.getPassword());
        
        if(name.isEmpty()||password.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"All fields must be fill","Error",JOptionPane.ERROR_MESSAGE);
            nameField.setText("");
            passwordField.setText("");
            revertBtnColor();
            return;
        }
        else if(!dm.loginUser(name,password))
        {
            JOptionPane.showMessageDialog(this,"Wrong name or password","Error",JOptionPane.ERROR_MESSAGE);
            nameField.setText("");
            passwordField.setText("");
            revertBtnColor();
            return;
        }
        JOptionPane.showMessageDialog(this, "successfully login!");
        window.showPanel("Landing");
    }

    private void toRegister()
    {
        nameField.setText("");
        passwordField.setText("");
        window.showPanel("Register");
    }

    private void checkAllField()
    {
        checkField=new KeyAdapter() {
            public void keyReleased(KeyEvent e){loginBtn.setBackground(changeColor());}
        };
        passwordField.addKeyListener(checkField);
        nameField.addKeyListener(checkField);
    }

    private Color changeColor()
    {
        String name=nameField.getText();
        String password=new String(passwordField.getPassword());

        boolean allFilled =
            !name.isEmpty()&&
            !password.isEmpty();

        return allFilled ? Color.GREEN:BTN_HEADERS;
    } 

    private void revertBtnColor(){loginBtn.setBackground(BTN_HEADERS);}

    private JPanel header()
    {
        JPanel header=new JPanel();
        header.setLayout(new BoxLayout(header,BoxLayout.Y_AXIS));
        header.setBackground(BACKGROUND);
        header.setAlignmentX(CENTER_ALIGNMENT);
        header.setMaximumSize(HEADERSIZE);

        ImageIcon logo=new ImageIcon(getClass().getResource("/GUI/UniServeLogo.png"));
        Image logoImage=logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel labelLogo=new JLabel(new ImageIcon(logoImage));
        labelLogo.setMaximumSize(HEADERLOGOSIZE);
        labelLogo.setBackground(BACKGROUND);
        labelLogo.setAlignmentX(CENTER_ALIGNMENT);

        header.add(labelLogo);
        return header;
    }
}