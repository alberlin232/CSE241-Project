public class Tenant {

    private int tenant_id;
    private String first_name;
    private String last_name;
    private int age;
    private int social;

    public Tenant(int tenant_id, String first_name, String last_name, int age, int social) {
        this.tenant_id = tenant_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.social = social;
    }

    public String toString() {
        return "[" + tenant_id + "] " + last_name + ", " + first_name + " (Age: " + age + ", Social:  " + social + ")";
    }
}
