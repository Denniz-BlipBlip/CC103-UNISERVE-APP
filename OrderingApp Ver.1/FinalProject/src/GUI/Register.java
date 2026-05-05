package GUI;
import DataManager.DataManager;
import Helper.FileLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JPanel{
    private DataManager dm;
    private Window window;
    private JButton submit,backLogin;
    private JPasswordField passwordField;
    private JTextField numberField,nameField;
    private JComboBox<String> location;
    private JLabel labelTitle;
    private final Dimension BTNSIZE=new Dimension(320,50);
    private final Dimension FIELDSIZE=new Dimension(Integer.MAX_VALUE,40);
    private KeyAdapter checkField;
    private final Color BTN_HEADERS=new Color(0xBA, 0x75, 0x17);
    private final Color BACKGROUND=new Color(0xFA, 0xEE, 0xDA);
    private final Color TEXT=new Color(0x41, 0x24, 0x02);
    private final Font TEXTTITLE=new Font("Georgia", Font.BOLD, 24);
    private final Font TEXTINPUT=new Font("Trebuchet MS", Font.PLAIN, 15);
    private final Font TEXTLABEL=new Font("Trebuchet MS", Font.BOLD, 20);
    private final Font TEXTBTN=new Font("Arial",Font.BOLD,18);
    private final Dimension HEADERLOGOSIZE=new Dimension(150,150);
    private final Dimension HEADERSIZE=new Dimension(320,150);

    public Register(Window window,DataManager dm)
    {
        this.dm=dm;
        this.window=window;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        add(header());
        add(Box.createVerticalStrut(20));

        labelTitle=new JLabel("Register");
        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        labelTitle.setFont(TEXTTITLE);
        labelTitle.setForeground(BTN_HEADERS);
        add(labelTitle);
        add(Box.createVerticalStrut(40));

        nameField=new JTextField();
        add(field("Name", nameField));

        location=new JComboBox<String>();
        loadLocation(location);
        location.setMaximumRowCount(5);
        add(field("Location: ",location));

        numberField=new JTextField();
        add(field("Phone Number: ",numberField));

        passwordField=new JPasswordField();
        add(field("Password: ",passwordField));

        add(Box.createVerticalStrut(30));

        submit=new JButton("Submit");
        submit.addActionListener(e->registerhandler());
        add(fieldBtn(submit));

        backLogin=new JButton("Login");
        backLogin.addActionListener(e->toLogin());
        add((fieldBtn(backLogin)));
        checkAllField();
    }

    private JPanel field(String labelText, JComponent comp)
    {
        JPanel panel=new JPanel();
        JLabel label=new JLabel(labelText);

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND);
        panel.setAlignmentX(CENTER_ALIGNMENT);

        label.setFont(TEXTLABEL);
        label.setForeground(TEXT);
        label.setAlignmentX(LEFT_ALIGNMENT);
        comp.setFont(TEXTINPUT);
        comp.setForeground(TEXT);
        comp.setMaximumSize(FIELDSIZE);
        comp.setAlignmentX((LEFT_ALIGNMENT));

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(comp);
        panel.add(Box.createVerticalStrut(15));
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

    private void registerhandler(){
        String phoneNumber=numberField.getText();
        String name=nameField.getText();
        String password=new String(passwordField.getPassword());
        String selectedLocation=(String)location.getSelectedItem();
        int size=phoneNumber.length();

        if(phoneNumber.isEmpty()||name.isEmpty()||password.isEmpty()||selectedLocation.equals("Select Location"))
        {
            JOptionPane.showMessageDialog(this,"Please fill all the fields","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(!phoneNumber.matches("\\d+")||size>11)
        {
            JOptionPane.showMessageDialog(this,"Phone number must be digits only","Error",JOptionPane.ERROR_MESSAGE);
            numberField.setText("");
            revertBtnColor();
            return;
        }
        int phoneN=Integer.parseInt(phoneNumber);
        dm.registerUser(name, phoneN, password, selectedLocation);
        JOptionPane.showMessageDialog(this,"Successfully created an account");
        window.showPanel("Login");
    }

    private void toLogin()
    {
        nameField.setText("");
        numberField.setText("");
        passwordField.setText("");
        location.setSelectedIndex(0);
        window.showPanel("Login");
    }

    private void loadLocation(JComboBox<String>box)
    {
        box.addItem("Select Location");
        try
        {
            for(String location:FileLoader.loadLocation())
            {
                box.addItem(location);
            }
        }catch(IOException e){JOptionPane.showMessageDialog(null, "Data did not load","Error",JOptionPane.ERROR_MESSAGE); return;}
        box.setSelectedIndex(0);
    }

    private void checkAllField()
    {
        checkField=new KeyAdapter() {
            public void keyReleased(KeyEvent e)
            {
                submit.setBackground(changeColor());
            }
        };
        passwordField.addKeyListener(checkField);
        numberField.addKeyListener(checkField);
        nameField.addKeyListener(checkField);
        location.addActionListener(e->submit.setBackground(changeColor()));
    }

    private Color changeColor()
    {
        String phoneNumber=numberField.getText();
        String name=nameField.getText();
        String password=new String(passwordField.getPassword());
        String selectedLocation=(String)location.getSelectedItem();

        boolean allFilled =
            !phoneNumber.isEmpty()&&
            !name.isEmpty()&&
            !password.isEmpty()&&
            !selectedLocation.equals("Select Location");

        return allFilled ? Color.GREEN:BTN_HEADERS;
    }

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

    private void revertBtnColor(){submit.setBackground(BTN_HEADERS);}
}