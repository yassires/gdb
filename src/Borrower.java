public class Borrower {

    private int id;
    private String name;
    private String uid;

    private String cin;

    public Borrower(int id, String name, String uid, String cin) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getCin() {
        return cin;
    }

    public void borrowBook() {
        System.out.println("get user info + book wanted");
    }


}
