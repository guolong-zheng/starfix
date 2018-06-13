package repair.checker;

import repair.heap.HeapNode;

import java.lang.reflect.Field;

public class Fix {
    HeapNode bugNode;
    String fieldName;
    HeapNode fixNode;

    public void perform() {
        Object fromObj = bugNode.getValue();
        Class cal = fromObj.getClass();
        Object toObj = fixNode.getValue();
        try {
            Field from = cal.getDeclaredField(fieldName);
            from.set(fromObj, toObj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
