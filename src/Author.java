public class Author {
    private int id;
    private String name;


    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void CreateAuthor(){

        System.out.println("Autour created successfully");
    }

}
