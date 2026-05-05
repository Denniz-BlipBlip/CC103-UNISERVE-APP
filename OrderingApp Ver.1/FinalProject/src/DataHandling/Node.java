package DataHandling;
import Users.Account;

public class Node {
    private Account data;
    private Node right;
    private Node left;

    public Node(Account data)
    {
        this.data=data;
        this.right=null;
        this.left=null;
    }

    public Node getRight(){return this.right;}
    public Node getLeft(){return this.left;}
    public Account getData(){return data;}
    public Node setRight(Node node){return this.right=node;}
    public Node setLeft(Node node){return this.left=node;}
}
