public class Test {
    

    public static void main(String[] args) {
        Database db = Database.getDatabase();
        if (db == null)
            return;
        else System.out.println("Connected to database");

        db.InsertProperty("TEST", 445, "Best Building");
        System.out.println("YES");
    }
    
}
