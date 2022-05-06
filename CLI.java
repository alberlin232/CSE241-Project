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
            } else if (action == 'p' || action == 'P') {
                while (true){

//========================================================================================================================

                    //PROPERTY MANAGER
                    action = prop_interface(in);
                    if (action == 'T' || action == 't'){
                        //Add Tenant
                        String first_name = getString(in, "Enter first name:");
                        String last_name = getString(in, "Enter last name:");
                        int age = getInt(in, "Enter age:");
                        int social = getInt(in, "Enter social security number:");
                        db.InsertTenant(first_name, last_name, age, social);
                    } else if (action == 'P' || action == 'p'){
                        //Add Perspective
                        String first_name = getString(in, "Enter first name:");
                        String last_name = getString(in, "Enter last name:");
                        int age = getInt(in, "Enter age:");
                        db.InsertPerspective(first_name, last_name, age);
                    } else if (action == 'V' || action == 'v'){
                        //Add Visit
                        int app_num = getInt(in, "Enter app_num:");
                        String address = getString(in, "Enter address:");
                        int num = getInt(in, "Enter how many perspectives visited:");
                        for(int i = 0; i < num; i++){
                            int persp_id = getInt(in, "Enter perspective ID:");
                            db.InsertVisited(app_num, address, persp_id);
                        }
                    } else if (action == 'L' || action == 'l'){
                        //Add Lease
                        action = lease_interface(in);
                        if (action == 'C' || action == 'c'){
                            //Create Lease
                            int app_num = getInt(in, "Enter apartment number:");
                            String address = getString(in, "Enter address:");
                            int term_length = getInt(in, "Enter term length:");
                            int rent = getInt(in, "Enter rent:");
                            int deposit = getInt(in, "Enter deposit:");
                            db.InsertLease(app_num, address, term_length, rent, deposit, 1);
                        } else if (action == 'I' || action == 'i'){
                            ///Add Tenant to Lease
                            int lease_id = getInt(in, "Enter lease ID:");
                            int num = getInt(in, "Enter how many tenants you want to add:");
                            for(int i = 0; i < num; i++){
                                int tenant_id = getInt(in, "Enter tenant ID:");
                                db.InsertRents(lease_id, tenant_id);
                            }
                        } else if (action == 'Q' || action == 'q') {
                            break;
                        }
                    } else if (action == 'C' || action == 'c'){
                        //Create Next Months Payments
                        String address = getString(in, "Enter address:");
                        String date = getString(in, "Enter date in YYYYMMDD format:");
                        IncurPayment(db, address, date);
                    } else if (action == 'K' || action == 'k'){
                        //Search Person
                        String last_name = getString(in, "Enter last name:");
                        SelectTenantByLastName(db, last_name);
                    } else if (action == 'A' || action == 'a'){
                        //Search Apartment
                        String address = getString(in, "Enter address:");
                        SelectAppNums(db, address);
                    } else if (action == 'G' || action == 'g') {
                        //Search Leases
                        String address = getString(in, "Enter address:");
                        SelectLeases(db, address);

                    } else if (action == 'Q' || action == 'q'){
                        break;
                    } 
                }
                System.out.println("You have selected the Property Manager Interface");
            } else if (action == 't' || action == 'T') {

//==========================================================================================================================


                //TENANT
                System.out.println("You have selected the Tenant Interface");
                int tenant_id = getInt(in, "Please select enter your unique ID given when signing your lease");
                Tenant user = db.SelectTenant(tenant_id);
                int due = db.CalculatePayment(tenant_id);
                System.out.println("Hey there " + user.first_name + " " + user.last_name + "!");
                System.out.println("Your payment due is $" + -1 * due + ".");


                while(true){
                    action = tenant_interface(in);
                    if (action == 'Q' || action == 'q'){
                        break;
                    } else if (action == 'P' || action == 'p'){
                        SelectLeaseID(db, tenant_id);
                        int lease_id = getInt(in, "Enter which lease you want to pay:");
                        String date = getString(in, "Enter current date in YYYYMMDD format:");
                        String method = getString(in, "Enter payment method (cash, check, credit):");
                        while(true){
                            if (method.equals("cash") || method.equals("check") || method.equals("credit")){
                                break;
                            } else {
                                System.out.println("Invalid payment method");
                                method = getString(in, "Enter payment method (cash, check, credit):");
                            }
                        }
                        int amount = getInt(in, "Enter amount:");
                        System.out.println(lease_id + " " + tenant_id + " " + date + " " + method + " " + amount);
                        db.InsertRentPayment(lease_id, tenant_id, date, method, amount);
                    }
                }
            } else if (action == 'n' || action == 'N') {

//==========================================================================================================================

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
//==========================================================================================================================
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

    static char prop_interface(BufferedReader in) {

        String actions = "TtPpVvLlKkCcAaGgq?";

        while (true) {
            System.out.println("Select which service you want to use:");
            System.out.println("    [T] Add Tenant");
            System.out.println("    [P] Add Perspective");
            System.out.println("    [V] Add Visit");
            System.out.println("    [L] Add/Edit Lease");
            System.out.println("    [C] Initiate Payments for Lease");
            System.out.println("Search Tools:");
            System.out.println("    [K] Search People");
            System.out.println("    [A] Search Apartments");
            System.out.println("    [G] Search Leases");
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

    static char lease_interface(BufferedReader in) {
        String actions = "CcIiLlq?";

        while (true) {
            System.out.println("Select what you want to do with the lease:");
            System.out.println("    [C] Create new Lease");
            System.out.println("    [I] Add Tenant");
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

    static char tenant_interface(BufferedReader in) {
        String actions = "PpQq?";

        while (true) {
            System.out.println("Select what you want to do with the lease:");
            System.out.println("    [P] Make a Payment");
            System.out.println("    [I] DO some fun stuff");
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

    private static void generateRandomApartments(Database db, String address, int num, int MAX_SQ_FOOT, int MIN_SQ_FOOT,
            int MAX_BEDROOM, int MAX_BATHROOM) {
        int app_num, sq_foot, bedroom, bathroom, rent, pet, rand;
        String[] PrAms = { "Pool", "Gym", "Laundry,", "Parking", "Elevator" };
        String[] ApAms = { "Your Mom", "Hot Tub", "Petting Zoo", "Heated Floor", "DeezNuts" };

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
        while (j < nums.size()) {
            app_nums[j] = nums.get(j).app_num;
            j++;
        }

        for (int i = 0; i < num; i++) {
            app_num = findSmallestMissing(app_nums);
            app_nums[j + i] = app_num;
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

    public static int findSmallestMissing(int[] nums) {
        // use a range constructor to initialize the set from array elements
        Set<Integer> distinct = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        // return first smallest missing positive number from the set
        int index = 1;
        while (true) {
            if (!distinct.contains(index)) {
                return index;
            }
            index++;
        }
    }

    private static void SelectTenantByLastName(Database db, String last_name) {
        ArrayList<Tenant> tenants = db.SelectTenantByLastName(last_name);
        for (Tenant tenant : tenants) {
            System.out.println(tenant.toString());
        }
    }

    private static void SelectAppNums(Database db, String address) {
        ArrayList<Apartment> apartments = db.SelectAppNums(address);
        for (Apartment apartment : apartments) {
            System.out.println(apartment.toString());
        }
    }

    private static void SelectLeases(Database db, String address) {
        ArrayList<Lease> leases = db.SelectLeasesByAddress(address);
        for (Lease lease : leases) {
            ArrayList<Integer> tenants = db.SelectRents(lease.lease_id);
            System.out.println(lease.toString());
            for (Integer tenant : tenants) {
                System.out.println("    " + db.SelectTenant(tenant).toString());
            }
        }
    }

    private static void IncurPayment(Database db, String address, String date) {
        ArrayList<Lease> leases = db.SelectLeasesByAddress(address);
        for (Lease lease : leases) {
            ArrayList<Integer> tenants = db.SelectRents(lease.lease_id);
            System.out.println(lease.toString());
            for (Integer tenant : tenants) {
                // Add to the payment table
                System.out.println(lease.lease_id + " " + tenant + " " + date + " " + lease.rent);
                db.InsertRentPayment(lease.lease_id, tenant, date, "due", -(lease.rent));
            }
        }
    }

    private static void SelectLeaseID(Database db, int tenant_id) {
        ArrayList<Integer> lease_ids = db.SelectLeaseID(tenant_id);
        for (Integer lease_id : lease_ids) {
            System.out.println("List of Leases:");
            System.out.println("    " + db.SelectLease(lease_id).toString());
        }
    }
}
