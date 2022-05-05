public class Apartment {
    public final int app_num;
    public final String address;
    public final int sq_foot;
    public final int bedroom;
    public final int bathroom;
    public final int rent;
    public final int pet;


    public Apartment(int app_num, String address, int sq_foot, int bedroom, int bathroom, int rent, int pet) {
        this.app_num = app_num;
        this.address = address;
        this.sq_foot = sq_foot;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.rent = rent;
        this.pet = pet;
    }

    public String toString() {
        return "Apartment " + app_num + ": " + address + "($" + rent + ")";
    }
}
