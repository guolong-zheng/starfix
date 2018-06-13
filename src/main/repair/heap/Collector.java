package repair.heap;

import java.lang.reflect.Field;
import java.util.*;

public class Collector {
    //TODO: Link name(for example N0) to root name
    public static Heap retrieveHeap(Object root, String name) {
        Heap heap = new Heap();

        Queue<Object> toVisit = new LinkedList<>();
        Set<Object> visited = new HashSet<>();
        toVisit.add(root);
        visited.add(root);

        while (!toVisit.isEmpty()) {
            List<String> fields = new LinkedList<>();
            Object var = toVisit.remove();
            Class cal = var.getClass();

            for (Field field : cal.getFields()) {
                //TODO: field information are used when fixing data structure in memory
                //String name = field.getName();
                String type = field.getType().toString();
                Object newVar = null;
                try {
                    newVar = field.get(var);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                fields.add(newVar.toString());

                if (!isPrim(type) && visited.add(newVar)) {
                    toVisit.add(newVar);
                }
            }
            heap.addNode(new HeapNode(cal.getTypeName(), var.toString(), var, fields));
        }

        return heap;
    }

    public static boolean isPrim(String type) {
        if (type.equals("boolean") || type.equals("byte") || type.equals("char") ||
                type.equals("double") || type.equals("float") || type.equals("int") ||
                type.equals("long") || type.equals("short"))
            return true;
        else
            return false;
    }
}

