package FileHandler;
import java.util.List;

public interface FileHandler {
    void saveData();
    void saveOrderData(List<String>data);
    void loadData();    
}
