package classes;

public class Borrower {

    private int id;
    private String name;

    private String email;
    private String cin;

    public Borrower(int id, String name, String email,String cin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCin() {
        return cin;
    }


}
