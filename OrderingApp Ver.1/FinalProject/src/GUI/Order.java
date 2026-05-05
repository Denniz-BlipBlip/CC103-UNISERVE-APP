package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import DataManager.DataManager;
import Helper.FileLoader;

public class Order extends JPanel {
    private JLabel titleLabel,greetingLabel,qntyLabel,locationLabel,subtotalLabel,totalLabel;
    private JSpinner qntySpin;
    private JTextArea instructions;
    private JComboBox<String> variantBox,dippingSauceBox,deliverTimeBox;
    private Window window;
    private DataManager dm;
    private final Dimension FIELDSIZE=new Dimension(Integer.MAX_VALUE,40);
    private final Dimension BOXSIZE=new Dimension(320,40);
    private final Color BTN_HEADERS=new Color(0xBA, 0x75, 0x17);
    private final Color BACKGROUND=new Color(0xFA, 0xEE, 0xDA);
    private final Color TEXT=new Color(0x41, 0x24, 0x02);
    private final Font TEXTTITLE=new Font("Georgia", Font.BOLD, 24);
    private final Font TEXTINPUT=new Font("Trebuchet MS", Font.PLAIN, 15);
    private final Font TEXTLABEL=new Font("Trebuchet MS", Font.BOLD, 20);
    private final Font TEXTBTN=new Font("Arial",Font.BOLD,18);

    public Order(Window window,DataManager dm)
    {        
        this.dm=dm;
        this.window=window;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        titleLabel=new JLabel("Good day "+"!");
        titleLabel.setFont(TEXTTITLE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setForeground(BTN_HEADERS);
        add(titleLabel);

        add(Box.createVerticalStrut(40));

        variantBox=new JComboBox<String>();
        loadVariants(variantBox);
        add(fieldBox("Variants",variantBox));

        dippingSauceBox=new JComboBox<String>();
        loadSauces(dippingSauceBox);
        add(fieldBox("Dipping Sauces",dippingSauceBox));

        deliverTimeBox=new JComboBox<String>();
        loadDeliveryTime(deliverTimeBox);
        add(fieldBox("Delivery Time",deliverTimeBox));

        
    }

    private JPanel fieldSpinnaer(String name, JSpinner comp)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBackground(BACKGROUND);


        return panel;
    }

    private JPanel fieldBox(String labelText,JComboBox comp)
    {
        JPanel panel=new JPanel();
        JLabel label=new JLabel(labelText);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND);
        panel.setAlignmentX(CENTER_ALIGNMENT);

        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(TEXTLABEL);
        label.setForeground(TEXT);

        comp.setMaximumRowCount(5);
        comp.setMaximumSize(BOXSIZE);
        comp.setAlignmentX(LEFT_ALIGNMENT);
        comp.setFont(TEXTINPUT);
        comp.setForeground(TEXT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(comp);
        panel.add(Box.createVerticalStrut(15));
        return panel;
    }

    private void loadSauces(JComboBox<String> box)
    {
        box.addItem("Select Sauce");
        try
        {
            for(String line:FileLoader.loadSauces())
            {
                box.addItem(line);
            }
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Data for sauces did not load","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        box.setSelectedIndex(0);
    }

    private void loadVariants(JComboBox<String> box)
    {
        box.addItem("Select Variant");
        try
        {
            for(String line:FileLoader.loadVariants())
            {
                box.addItem(line);
            }
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Data for Variants did not load","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        box.setSelectedIndex(0);
    }

    private void loadDeliveryTime(JComboBox<String> box)
    {
        box.addItem("Select Delivery Time");
        try
        {
            for(String line:FileLoader.loadDeliveryTime())
            {
                box.addItem(line);
            }
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Data for Delivery Time did not load","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        box.setSelectedIndex(0);
    }

    private void toLogin()
    {
        window.showPanel("login");
    }

    private void orderHandler()
    {

    }
}