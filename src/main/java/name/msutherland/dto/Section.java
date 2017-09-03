package name.msutherland.dto;

/**
 * Created by michaelsutherland on 03/09/2017.
 */
public class Section {
    private int id;
    private String name;

    public Section(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
