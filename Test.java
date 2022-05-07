import java.util.HashMap;
import java.util.ArrayList;


public class Test {
    

    public static void main(String[] args) {
        Database db = Database.getDatabase();
        if (db == null)
            return;
        else System.out.println("Connected to database");
        System.out.println("YES");
        db.disconnect();
    }
    
}
