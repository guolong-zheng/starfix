package testgene;

public class ClassInfo {
    String simpleName;
    FieldInfo[] instanceFields;
    FieldInfo[] declaredStaticFields;

    public ClassInfo(String name, FieldInfo[] fieldInfos) {
        simpleName = name;
        instanceFields = fieldInfos;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public FieldInfo[] getInstanceFields() {
        return instanceFields;
    }

    public FieldInfo[] getDeclaredStaticFields() {
        return declaredStaticFields;
    }
}
