package Helper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    private static ArrayList<String>locations=new ArrayList<String>();
    private static ArrayList<String>variants=new ArrayList<String>();
    private static ArrayList<String>sauces=new ArrayList<String>();
    private static ArrayList<String>deliveryTime =new ArrayList<String>();

    public static ArrayList<String> loadVariants() throws IOException
    {
        File file=new File("variants.txt");
        if(!file.exists()){file.createNewFile();}
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line;
        while((line=reader.readLine())!=null)
        {
            variants.add(line);
        }
        reader.close();
        return variants;
    }

    public static ArrayList<String> loadDeliveryTime() throws IOException
    {
        File file=new File("deliverTimes.txt");
        if(!file.exists()){file.createNewFile();}
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line;
        while((line=reader.readLine())!=null)
        {
            deliveryTime.add(line);
        }
        reader.close();
        return deliveryTime;
    }

    public static ArrayList<String> loadSauces() throws IOException
    {
        File file=new File("sauces.txt");
        if(!file.exists()){file.createNewFile();}
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line;
        while((line=reader.readLine())!=null)
        {
            sauces.add(line);
        }
        reader.close();
        return sauces;
    }

    public static ArrayList<String> loadLocation() throws IOException
    {
        File file=new File("locations.txt");
        if(!file.exists()){file.createNewFile();}
        BufferedReader read=new BufferedReader(new FileReader(file));
        String line;
        while((line=read.readLine())!=null)
        {
            locations.add(line);
        }
        read.close();
        return locations;
    }
}
