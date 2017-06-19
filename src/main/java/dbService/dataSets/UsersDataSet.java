package dbService.dataSets;

/**
 * Created by nb on 18.06.17.
 */
public class UsersDataSet {

    private long id;
    private String name;
    private String pass;

    public UsersDataSet(long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
