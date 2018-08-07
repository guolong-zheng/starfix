package testgene;

public class FieldInfo {
    private String name;
    private String type;
    private boolean isFinal = false;
    private boolean isProtected = false;
    private boolean isPrivate = false;

    public FieldInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
