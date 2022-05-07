import java.util.HashMap;
import java.util.ArrayList;


public class Test {
    

    public static void main(String[] args) {
        Database db = Database.getDatabase();
        if (db == null)
            return;
        else System.out.println("Connected to database");
<<<<<<< HEAD
=======

        db.InsertProperty("TEST", 445, "Best Building");
>>>>>>> 3e1a7dea0d1dfc5cff6b04038754d257f789a055
        System.out.println("YES");
        db.disconnect();
    }
    
}
