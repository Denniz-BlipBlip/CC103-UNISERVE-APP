package Users;

public class Account {
    private String name,location,password;
    private int accId,phoneNumber;

    public Account(String name, String location,String pass, int id, int number)
    {
        this.name=name;
        this.password=pass;
        this.accId=id;
        this.phoneNumber=number;
        this.location=location;
    }

    public String getLocation(){return this.location;}
    public String getName(){return this.name;}
    public int getPhoneNumber(){return this.phoneNumber;}
    public int getId(){return this.accId;}
    public String getPass(){return this.password;}
    public void setName(String name){this.name=name;}
    public void setId(int id){this.accId=id;}
    public void setPass(String pass){this.password=pass;}
    public void setNumber(int n){this.phoneNumber=n;}
    public void setLocation(String loc){this.location=loc;}
}