import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CLI {
    public static void main(String[] args) {
        Database db = Database.getDatabase();
        if (db == null)
            return;
        else System.out.println("Connected to database");
            
        // Start the basic command-line interpreter:
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        //START MENU
        while (true) {
            char action = interface_menu(in);
            if (action == '?') {
                // Print the help menu
            } else if (action == 'q') {
                break;
            } else if (action == 'p' || action == 'P'){
                //PROPERTY MANAGER
                System.out.println("You have selected the Property Manager Interface");
            } else if (action == 't' || action == 'T') {
                //TENANT
                System.out.println("You have selected the Tenant Interface");
            } else if (action == 'n' || action == 'N') {
                //NUMA MANAGER
                System.out.println("You have selected the NUMA Manager Interface");
                action = numa_interface(in);
                if (action == 'G' || action == 'g') {
                    
                }
            } else {
                System.out.println("Invalid Command");
            }
        }

        db.disconnect();
    }

    static String getString(BufferedReader in, String message) {
        String s;
        try {
            System.out.print(message + " :> ");
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return s;
    }
    

    static int getInt(BufferedReader in, String message) {
        int i = -1;
        try {
            System.out.print(message + " :> ");
            i = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.out.println("Not An Integer");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Not An Integer");
            return -1;
        }
        return i;
    }

    static char interface_menu(BufferedReader in) {
        
        String actions = "pPtTnNq?";

        while (true) {
            System.out.println("Select which interface you want to use:");
            System.out.println("    [P] Property Manager");
            System.out.println("    [T] Tenant");
            System.out.println("    [N] NUMA Manager");
            System.out.println("    [q] Quit");
            System.out.println("    [?] Print Help Menu");

            String action;
            try {
                action = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (action.length() != 1)
                continue;
            if (actions.contains(action)) {
                return action.charAt(0);
            }
            System.out.println("Invalid Command");
        }
    }

    static char numa_interface(BufferedReader in) {
        
        String actions = "IiGgq?";

        while (true) {
            System.out.println("Select which interface you want to use:");
            System.out.println("    [I] Insert Property");
            System.out.println("    [G] Generate Properties");
            System.out.println("    [q] Quit");
            System.out.println("    [?] Print Help Menu");

            String action;
            try {
                action = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (action.length() != 1)
                continue;
            if (actions.contains(action)) {
                return action.charAt(0);
            }
            System.out.println("Invalid Command");
        }
    }

    private void generateRandomApartments(String address, int num, int MAX_SQ_FOOT, int MIN_SQ_FOOT, int MAX_BEDROOM, int MAX_BATHROOM) {
        int = app_num, sq_foot, bedroom, bathroom, rent, pet, rand;
        String[] PrAms = {"Pool", "Gym", "Laundry,", "Parking", "Elevator"};
        String[] ApAms = {"Your Mom", "Hot Tub", "Petting Zoo", "Heated Floor", "DeezNuts"};

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < PrAms.length; i++) {
            map.put(PrAms[i], (int) Math.random() * 100);
        }
        for (int i = 0; i < ApAms.length; i++) {
            map.put(ApAms[i], (int) Math.random() * 100);
        }
        for (int i = 0; i < num; i++) {
            app_num = i;
            sq_foot = (int) (Math.random() * (MAX_SQ_FOOT - MIN_SQ_FOOT) + MIN_SQ_FOOT);
            bedroom = (int) (Math.random() * MAX_BEDROOM);
            bathroom = (int) (Math.random() * MAX_BEDROOM);
            rent = (int) (Math.random() * sq_foot * bedroom * bathroom;
            pet = Math.random() < 0.8 ? 0 : 1;
            db.InsertApartment(address, app_num, sq_foot, bedroom, bathroom, rent, pet)

            rand = (int) (Math.random() * ApAms.length);
            db.insertApAm(app_num, address, ApAms[rand], map.get(ApAms[rand]));

            rand = (int) (Math.random() * PrAms.length);
            db.insertPrAm(app_num, address, PrAms[rand], map.get(PrAms[rand]));
        }
    }
}
