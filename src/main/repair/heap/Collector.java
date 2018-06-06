package repair.heap;

import java.lang.reflect.Field;
import java.util.*;

public class Collector {
    public static Heap retrieveHeap(Object root) {
        Heap heap = new Heap();

        Queue<Object> toVisit = new LinkedList<>();
        Set<Object> visited = new HashSet<>();
        toVisit.add(root);
        visited.add(root);

        while (!toVisit.isEmpty()) {
            HeapNode heapNode = new HeapNode();
            Object var = toVisit.remove();
            Class cal = var.getClass();
            Map<String, HeapNode> name2field = new HashMap<>();
            System.out.println(var.toString() + "|");

            for (Field field : cal.getFields()) {
                String name = field.getName();
                String type = field.getType().toString();
                Object newVar = null;
                try {
                    newVar = field.get(var);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                System.out.println("name:" + name + "\t type:" + type + "\t value:" + newVar.toString());

                /*
                if (newVar.getClass().isPrimitive()) {
                    name2field.put(name, new PrimVar(type, newVar, Integer.getInteger(newVar.toString())));
                } else if (newVar == null) {
                    name2field.put(name, new PointVar(type));
                } else {
                    name2field.put(name, new PointVar(type, newVar));

                }
                */

                if (!isPrim(newVar) && visited.add(newVar)) {
                    toVisit.add(newVar);
                }
            }

            // heap.addNode(new PointVar(cal.getTypeName(), var, name2field));
        }

        return heap;
    }

    public static boolean isPrim(Object var) {
        return Integer.class.isInstance(var);
    }
}

