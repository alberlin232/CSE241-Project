import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;


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
                if (action == 'I' || action == 'i') {
                    String address = getString(in, "Enter the address:");
                    int building_number = getInt(in, "Enter the building number:");
                    String building_name = getString(in, "Enter the building name:");
                    db.InsertProperty(address, building_number, building_name);
                }
                if (action == 'G' || action == 'g') {
                    String address = getString(in, "Please Enter the Address:");
                    int num = getInt(in, "Please Enter the number of apartments that you want to generate:");
                    int MAX_SQ_FOOT = getInt(in, "Please enter the max SQ_FOOT:");
                    int MIN_SQ_FOOT = getInt(in, "Please enter the min SQ_FOOT:");
                    while (true) {
                        if (MIN_SQ_FOOT > MAX_SQ_FOOT) {
                            System.out.println("The min SQ_FOOT must be less than the max SQ_FOOT");
                            MIN_SQ_FOOT = getInt(in, "Please enter the min SQ_FOOT: ");
                        } else {
                            break;
                        }
                    }
                    int MAX_BEDROOM = getInt(in, "Please enter the max number of bedrooms: ");
                    int MAX_BATHROOM = getInt(in, "Please enter the max number of bathrooms: ");
                    generateRandomApartments(db, address, num, MAX_SQ_FOOT, MIN_SQ_FOOT, MAX_BEDROOM, MAX_BATHROOM);
                    System.out.println("Sick! All the apartments have been generated!");
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
            System.out.println("    [G] Generate Apartments");
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

    private static void generateRandomApartments(Database db, String address, int num, int MAX_SQ_FOOT, int MIN_SQ_FOOT, int MAX_BEDROOM, int MAX_BATHROOM) {
        int app_num, sq_foot, bedroom, bathroom, rent, pet, rand;
        String[] PrAms = {"Pool", "Gym", "Laundry,", "Parking", "Elevator"};
        String[] ApAms = {"Your Mom", "Hot Tub", "Petting Zoo", "Heated Floor", "DeezNuts"};

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < PrAms.length; i++) {
            map.put(PrAms[i], (int) Math.random() * 100);
        }
        for (int i = 0; i < ApAms.length; i++) {
            map.put(ApAms[i], (int) Math.random() * 100);
        }

        ArrayList<Apartment> nums = db.SelectAppNums(address);
        int[] app_nums = new int[nums.size() + num];
        int j = 0;
        while(j < nums.size()) {
            app_nums[j] = nums.get(j).app_num;
            j++;
        }
        
        for (int i = 0; i < num; i++) {
            app_num = findSmallestMissing(app_nums);
            app_nums[j+i] = app_num;
            sq_foot = (int) (Math.random() * (MAX_SQ_FOOT - MIN_SQ_FOOT) + MIN_SQ_FOOT);
            bedroom = (int) (Math.random() * MAX_BEDROOM);
            bathroom = (int) (Math.random() * MAX_BEDROOM);
            rent = (int) (Math.random() * sq_foot * bedroom * bathroom);
            pet = Math.random() < 0.8 ? 0 : 1;
            db.InsertApartment(app_num, address, sq_foot, bedroom, bathroom, rent, pet);

            rand = (int) (Math.random() * ApAms.length);
            db.InsertApAm(app_num, address, ApAms[rand], (int) map.get(ApAms[rand]));

            rand = (int) (Math.random() * PrAms.length);
            db.InsertPrAm(address, PrAms[rand], (int) map.get(PrAms[rand]));
        }
    }

    public static int findSmallestMissing(int[] nums){
        // use a range constructor to initialize the set from array elements
        Set<Integer> distinct = Arrays.stream(nums).boxed().collect(Collectors.toSet());
 
        // return first smallest missing positive number from the set
        int index = 1;
        while (true)
        {
            if (!distinct.contains(index)) {
                return index;
            }
            index++;
        }
    }
}
