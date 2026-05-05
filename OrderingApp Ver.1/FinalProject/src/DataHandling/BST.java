package DataHandling;
import Users.Account;
import java.io.BufferedWriter;
import java.io.IOException;

public class BST {
    private Node root=null;

    private boolean findByName(Node root, String name,String password)
    {
        if(root==null){return false;}
        if(root.getData().getName().equals(name)&&root.getData().getPass().equals(password))
        {
            return true;
        }
        return findByName(root.getRight(), name, password)||findByName(root.getLeft(), name, password);
    }

    private Node insert(Node root, String name, String location, int id, String pass, int number)
    {
        if(root==null){return new Node(new Account(name,location,pass,id,number));}

        if(id>root.getData().getId())
            {
                root.setRight(insert(root.getRight(), name, location, id, pass, number));
            }
        else
        {
            root.setLeft(insert(root.getLeft(), name, location, id, pass, number));
        }
        return root;
    }

    private Node findId(Node root, int id)
    {
        if(root==null){return null;}
        if(id>root.getData().getId()){return findId(root.getRight(), id);}
        else if(id<root.getData().getId()){return findId(root.getLeft(), id);}
        return root;
    }

    private boolean checkExists(Node root, int id)
    {
        if(root==null){return false;}
        if(id>root.getData().getId()){return checkExists(root.getRight(), id);}
        else if(id<root.getData().getId()){return checkExists(root.getLeft(), id);}
        return true;
    }

    public void insertData(Account data)
    {
        root=insert(root,data.getName(),data.getLocation(),data.getId(),data.getPass(),data.getPhoneNumber());
    }

    private void save(Node root,BufferedWriter writer) throws IOException
    {
        if(root==null){return;}
        save(root.getLeft(), writer);
        writer.write(root.getData().getName()+","+root.getData().getId()+","+root.getData().getPass()+","+root.getData().getLocation()+","+root.getData().getPhoneNumber());
        writer.newLine();
        save(root.getRight(), writer);
    }

    public void saveData(BufferedWriter writer) throws IOException
    {
        save(root, writer);
    }

    public boolean check(int id)
    {
        if(!checkExists(root, id)){return false;}
        else
            {
                return true;
            }
    }

    public Account findData(int id)
    {
        if(!check(id)){return null;}
        root=findId(root, id);
        return root.getData();
    }

    public boolean verifyAcc(String name,String pass)
    {
        if(!findByName(root, name, pass)){return false;}
        else{return true;}
    }
}