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
            Object var = toVisit.remove();
            Class cal = var.getClass();
            Map<String, HeapNode> name2field = new HashMap<>();
            System.out.print(var.toString() + "|");

            for (Field field : cal.getFields()) {
                    String name = field.getName();
                    String type = field.getType().toString();
                Object newVar = null;
                try {
                    newVar = field.get(var);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                System.out.println("name:" + name + " type:" + type + " value:" + newVar.toString());

                    if(newVar.getClass().isPrimitive()){
                        name2field.put(name, new PrimVar(type, newVar, Integer.getInteger(newVar.toString())));
                    }else if (newVar == null){
                        name2field.put(name, new PointVar(type));
                    }else {
                        name2field.put(name, new PointVar(type, newVar));

                    }

                    if (visited.add(newVar)) {
                        toVisit.add(newVar);
                    }
            }

            heap.addNode(new PointVar(cal.getTypeName(), var, name2field));
        }

        return heap;
    }
}

