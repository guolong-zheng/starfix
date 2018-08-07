package testgene;

public class MethodInfo {
    String name;
    boolean isStatic;
    LocalVarInfo[] argumentLocalVars;

    public MethodInfo(String name) {
        this.name = name;
        isStatic = false;
        argumentLocalVars = null;
    }

    public String getName() {
        return this.name;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public LocalVarInfo[] getArgumentLocalVars() {
        return argumentLocalVars;
    }
}
