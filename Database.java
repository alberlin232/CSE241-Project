import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    /**
     * The connection to the database.  When there is no connection, it should
     * be null.  Otherwise, there is a valid open connection
     */
    private Connection mConnection;

    private PreparedStatement InsertProperty;
    private PreparedStatement InsertApartment;
    private PreparedStatement InsertLease;
    private PreparedStatement InsertTenant;
    private PreparedStatement InsertRents;
    private PreparedStatement InsertVisited;
    private PreparedStatement InsertPerspective;
    private PreparedStatement InsertRentPayment;
    private PreparedStatement InsertAmmPayment;
    private PreparedStatement InsertApAm;
    private PreparedStatement InsertPrAm;

    // SELECTS
    private PreparedStatement SelectProperty;
    private PreparedStatement SelectApartment;
    private PreparedStatement SelectLease;
    private PreparedStatement SelectTenant;
    private PreparedStatement SelectRents;
    private PreparedStatement SelectVisited;
    private PreparedStatement SelectPerspective;
    private PreparedStatement SelectRentPayment;
    private PreparedStatement SelectAmmPayment;
    private PreparedStatement SelectApAm;
    private PreparedStatement SelectPrAm;


    private PreparedStatement SelectAppNums;

    // UPDATE
    private PreparedStatement UpdateProperty;
    private PreparedStatement UpdateApartment;
    private PreparedStatement UpdateLease;
    private PreparedStatement UpdateTenant;
    private PreparedStatement UpdateRents;
    private PreparedStatement UpdateVisited;
    private PreparedStatement UpdatePerspective;
    private PreparedStatement UpdateRentPayment;
    private PreparedStatement UpdateAmmPayment;
    private PreparedStatement UpdateApAm;
    private PreparedStatement UpdatePrAm;

    // DELETES
    private PreparedStatement DeleteProperty;
    private PreparedStatement DeleteApartment;
    private PreparedStatement DeleteLease;
    private PreparedStatement DeleteTenant;
    private PreparedStatement DeleteRents;
    private PreparedStatement DeleteVisited;
    private PreparedStatement DeletePerspective;
    private PreparedStatement DeleteRentPayment;
    private PreparedStatement DeleteAmmPayment;
    private PreparedStatement DeleteApAm;
    private PreparedStatement DeletePrAm;



    /**
     * The Database constructor is private: we only create Database objects 
     * through the getDatabase() method.
     */
    private Database() {
    }

    /**
     * Get a fully-configured connection to the database
     * 
     * @param port  The port on the database server to which connection requests
     *              should be sent
     * @param sid   The SID of the database 
     * @return A Database object, or null if we cannot connect properly
     */
    static Database getDatabase() {
        // Create an un-configured Database object
        Database db = new Database();

        // Give the Database object a connection, fail if we cannot get one
        try {
            // System.out.print("Enter Oracle user id: ");
            // String user = in.nextLine();
            // System.out.print("Enter Oracle user password: ");
            // String pass = in.nextLine();
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", "alb323", "P822664504");
            if (conn == null) {
                System.err.println("Error: DriverManager.getConnection() returned a null object");
                return null;
            }
            db.mConnection = conn;
        } catch (SQLException e) {
            System.err.println("Error: DriverManager.getConnection() threw a SQLException");
            e.printStackTrace();
            return null;
        }

        // Attempt to create all of our prepared statements.  If any of these 
        // fail, the whole getDatabase() call should fail
        try {

            //PROPERTY
            db.InsertProperty = db.mConnection.prepareStatement("INSERT INTO Properties VALUES (?, ?, ?)");
            db.SelectProperty = db.mConnection.prepareStatement("SELECT * FROM Properties");
            
            //APARTMENT
            db.InsertApartment = db.mConnection.prepareStatement("INSERT INTO Apartments VALUES (?, ?, ?, ?, ?, ?, ?)");
            db.SelectApartment = db.mConnection.prepareStatement("SELECT * FROM Apartments");
            db.SelectAppNums = db.mConnection.prepareStatement("SELECT * FROM Apartments WHERE address = ?");

            //LEASE
            db.InsertLease = db.mConnection.prepareStatement("INSERT INTO Lease VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
            db.SelectLease = db.mConnection.prepareStatement("SELECT * FROM Lease");
            
            //TENANT
            db.InsertTenant = db.mConnection.prepareStatement("INSERT INTO Tenant VALUES (DEFAULT, ?, ?, ?)");
            db.SelectTenant = db.mConnection.prepareStatement("SELECT * FROM Tenant");  

            //RENTS
            db.InsertRents = db.mConnection.prepareStatement("INSERT INTO Rents VALUES (?, ?)");
            

            //VISITED
            db.InsertVisited = db.mConnection.prepareStatement("INSERT INTO Visited VALUES (?, ?, ?)");

            //PERSPECTIVE
            db.InsertPerspective = db.mConnection.prepareStatement("INSERT INTO Perspective VALUES (DEFAULT, ?, ?)");

            //RENT PAYMENT
            db.InsertRentPayment = db.mConnection.prepareStatement("INSERT INTO Rent_Payment VALUES (?, ?, ?, ?, ?)");
 
            //PROPERTY AMENITIES
            db.InsertPrAm = db.mConnection.prepareStatement("INSERT INTO Prop_Amenities VALUES (DEFAULT, ?, ?, ?)");
            
            //APARTMENT AMENITIES
            db.InsertApAm = db.mConnection.prepareStatement("INSERT INTO App_Amenities VALUES (?, ?, ?)");

            //AMENITIES PAYMENT
            db.InsertAmmPayment = db.mConnection.prepareStatement("INSERT INTO Amm_Payment VALUES (?, ?, ?, ?, ?, ?)");



        } catch (SQLException e) {
            System.err.println("Error creating prepared statement");
            e.printStackTrace();
            db.disconnect();
            return null;
        }
        return db;
    }

    /**
     * Close the current connection to the database, if one exists.
     * 
     * NB: The connection will always be null after this call, even if an 
     *     error occurred during the closing operation.
     * 
     * @return True if the connection was cleanly closed, false otherwise
     */
    boolean disconnect() {
        if (mConnection == null) {
            System.err.println("Unable to close connection: Connection was null");
            return false;
        }
        try {
            mConnection.close();
        } catch (SQLException e) {
            System.err.println("Error: Connection.close() threw a SQLException");
            e.printStackTrace();
            mConnection = null;
            return false;
        }
        mConnection = null;
        return true;
    }

    /**
     * Insert a property into the database
     * 
     * @param address  The address of the property
     * @param building_number The street number of the property
     * @param building_name  The name of the property
     * @return returns the row count of the insert statement 
     */
    int InsertProperty(String address, int building_number, String building_name) {
        int count = 0;
        try {
            InsertProperty.setString(1, address);
            InsertProperty.setInt(2, building_number);
            InsertProperty.setString(3, building_name);
            count += InsertProperty.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    int InsertApartment(int app_num, String address, int sq_foot, int bedroom, int bathroom, int rent, int pet) {
        int count = 0;
        try {
            InsertApartment.setInt(1, app_num);
            InsertApartment.setString(2, address);
            InsertApartment.setInt(3, sq_foot);
            InsertApartment.setInt(4, bedroom);
            InsertApartment.setInt(5, bathroom);
            InsertApartment.setInt(6, rent);
            InsertApartment.setInt(7, pet);
            count += InsertApartment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int InsertLease(int app_num, String address, int term_length, int rent, int deposit, int give_back) {
        int count = 0;
        try {
            InsertLease.setInt(1, app_num);
            InsertLease.setString(2, address);
            InsertLease.setInt(3, term_length);
            InsertLease.setInt(4, rent);
            InsertLease.setInt(5, deposit);
            InsertLease.setInt(6, give_back);
            count += InsertLease.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int insertTenant(String name, String age, String social) {
        int count = 0;
        try {
            InsertTenant.setString(1, name);
            InsertTenant.setString(2, age);
            InsertTenant.setString(3, social);
            count += InsertTenant.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int insertRents(int lease_id, int tenant_id) {
        int count = 0;
        try {
            InsertRents.setInt(1, lease_id);
            InsertRents.setInt(2, tenant_id);
            count += InsertRents.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int insertVisited(int app_num, String address, int persp_id) {
        int count = 0;
        try {
            InsertVisited.setInt(1, app_num);
            InsertVisited.setString(2, address);
            InsertVisited.setInt(3, persp_id);
            count += InsertVisited.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int insertPerspective(String name, int age) {
        int count = 0;
        try {
            InsertPerspective.setString(1, name);
            InsertPerspective.setInt(2, age);
            count += InsertPerspective.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int insertRentPayment(int lease_id, int tenant_id, String date, String method, int amount) {
        int count = 0;
        try {
            InsertRentPayment.setInt(1, lease_id);
            InsertRentPayment.setInt(2, tenant_id);
            InsertRentPayment.setString(3, date);
            InsertRentPayment.setString(4, method);
            InsertRentPayment.setInt(5, amount);
            count += InsertRentPayment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int insertAmmPayment(int am_id, int app_num, int tenant_id, String date, String method, int amount) {
        int count = 0;
        try {
            InsertAmmPayment.setInt(1, am_id);
            InsertAmmPayment.setInt(2, app_num);
            InsertAmmPayment.setInt(3, tenant_id);
            InsertAmmPayment.setString(4, date);
            InsertAmmPayment.setString(5, method);
            InsertAmmPayment.setInt(6, amount);
            count += InsertAmmPayment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int InsertApAm(int app_num, String address, String name, int price) {
        int count = 0;
        try {
            InsertApAm.setInt(1, app_num);
            InsertApAm.setString(2, address);
            InsertApAm.setString(3, name);
            count += InsertApAm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    int InsertPrAm(String address, String name, int price) {
        int count = 0;
        try {
            InsertPrAm.setString(1, address);
            InsertPrAm.setString(2, name);
            InsertPrAm.setInt(3, price);
            count += InsertPrAm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    ArrayList<Apartment> SelectAppNums(String address) {
        ArrayList<Apartment> res = new ArrayList<Apartment>();
        try {
            SelectAppNums.setString(1, address);
            ResultSet rs = SelectAppNums.executeQuery();
            while (rs.next()) {
                res.add(new Apartment(rs.getInt("app_num"), rs.getString("address"), rs.getInt("sq_foot"), rs.getInt("bedroom"), rs.getInt("bathroom"), rs.getInt("rent"), rs.getInt("pet")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}