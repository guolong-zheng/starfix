package repair.fix;

import java.lang.reflect.Field;

public class PrimFix {
    Object obj;
    String fieldName;
    String type;
    String toValue;

    public PrimFix() {
    }

    public PrimFix(Object obj, String fieldName, String type, String toValue) {
        this.obj = obj;
        this.fieldName = fieldName;
        this.type = type;
        this.toValue = toValue;
    }

    public void perform() {
        try {
            Field f = obj.getClass().getField(fieldName);
            switch (type) {
                case "int":
                    f.setInt(obj, Integer.parseInt(toValue));
                    break;
                case "long":
                    f.setLong(obj, Long.parseLong(toValue));
                    break;
                case "float":
                    f.setFloat(obj, Float.parseFloat(toValue));
                    break;
                case "double":
                    f.setDouble(obj, Double.parseDouble(toValue));
                    break;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
