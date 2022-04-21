import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CLI {


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
}
