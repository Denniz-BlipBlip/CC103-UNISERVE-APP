package DataManager;
import DataHandling.BST;
import FileHandler.FileHandler;
import Users.Account;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataManager implements FileHandler {
    private BST bst;
    private List<String>order=new ArrayList<String>();

    public DataManager()
    {
        bst=new BST();
        loadData();
    }

    public void registerUser(String name,int phoneNumber,String password,String location)
    {
        bst.insertData(new Account(name,location,password,generateUserId(),phoneNumber));
        saveData();
    }

    public boolean loginUser(String name,String password)
    {
        if(!bst.verifyAcc(name, password)){return false;}
        else
        {
            return true;
        }
    }

    public void takeOrder(String product, int qnty, double amount, String location)
    {
        saveLog(product, qnty, amount, location);
    }

    private void saveLog(String product, int qnty, double amount, String location)
    {
        order.add("Product: "+product+"\t| Quantity: "+qnty+"\t| Total amount: "+amount+"\t| Location of the customer: "+location);
        saveOrderData(order);
    }

    @Override
    public void saveOrderData(List<String>data)
    {
        try
        {
            BufferedWriter writer=new BufferedWriter(new FileWriter("OrderLog.txt",true));
            for(String log:data)
            {
                writer.write(log);
            }
            writer.close();
        }catch(IOException e){JOptionPane.showMessageDialog(null,"Data did not save"); return;}
    }

    @Override
    public void saveData()
    {
        try
        {
            BufferedWriter writer=new BufferedWriter(new FileWriter("AccData.txt",true));
            bst.saveData(writer);
            writer.close();
        }catch(IOException e){JOptionPane.showMessageDialog(null, "Data did not load", "Error", JOptionPane.ERROR_MESSAGE); }
    }

    @Override
    public void loadData()
    {
        try
        {       
            File file=new File("AccData.txt");
            if(!file.exists()){file.createNewFile();}
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine())!=null)
            {
                String[] parts=line.split(",");
                String name=parts[0];
                int id=Integer.parseInt(parts[1]);
                String password=parts[2];
                String location=parts[3];
                int phoneNumber=Integer.parseInt(parts[4]);
                bst.insertData(new Account(name,location,password,id,phoneNumber));
            }
            reader.close();
        }catch(IOException e){JOptionPane.showMessageDialog(null,"Data did not laod", "Error",JOptionPane.ERROR_MESSAGE); return;}            
    }

    private Random rand=new Random();

    private int generateUserId()
    {
        int id;
        do{
            id=rand.nextInt(900000)+100000;
        }while(bst.check(id));
        return id;
    }
}