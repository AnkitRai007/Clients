import java.util.HashMap;
import java.util.Map;

public enum FileType {

    YAML("Yaml"),
    JSON("Json"),
    XML("Xml");

    private static final Map<String, FileType> lookup = new HashMap<>();

    static {
        for (FileType v : FileType.values()) {
            lookup.put(v.getDisplayName(), v);
        }
    }

    private final String displayName;

    FileType(String displayName) {
        this.displayName = displayName;
    }

    public static FileType get(String displayName) {
        return lookup.get(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }
}