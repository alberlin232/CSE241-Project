public class Lease {
    public final int lease_id;
    public final int app_num;
    public final String address;
    public final int term_length;
    public final int rent;
    public final int deposit;
    public final int give_back;

    public Lease(int lease_id, int app_num, String address, int term_length, int rent, int deposit, int give_back) {
        this.lease_id = lease_id;
        this.app_num = app_num;
        this.address = address;
        this.term_length = term_length;
        this.rent = rent;
        this.deposit = deposit;
        this.give_back = give_back;
    }

    public String toString() {
        return "[" + lease_id + "] Apartment: - " + app_num + " - " + address + " - " + term_length + " months - $" + rent;
    }
}
