package repair.heap;

import testgene.ClassInfo;
import testgene.Config;
import testgene.FieldInfo;
import testgene.MethodInfo;
import testgene.testsuites.TestGenerator;

import java.lang.reflect.Field;
import java.util.*;

public class Collector {

    public static Map<String, HeapNode> name2node;    //map variable name to concrete heap node
    public static Map<Object, HeapNode> var2node;  //map original variable to concrete heap node

    //TODO: Link name(for example N0) to root name
    public static Heap retrieveHeap(Object root) {
        getClassInfo(root);

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
                if (newVar == null)
                    fields.add("null");
                else {
                    fields.add(newVar.toString().substring(newVar.toString().length() - 2));

                    if (!isPrim(type) && visited.add(newVar)) {
                        toVisit.add(newVar);
                    }
                }
            }
            heap.addNode(new HeapNode(cal.getTypeName(), var.toString().substring(var.toString().length() - 2), var, fields));
        }

        return heap;
    }

    public static void getClassInfo(Object root) {
        Class cls = root.getClass();
        Field[] fields = cls.getFields();
        FieldInfo[] fieldInfos = new FieldInfo[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldInfos[i] = new FieldInfo(fields[i].getName(), fields[i].getType().toString());
        }

        ClassInfo clsInfo = new ClassInfo(cls.getName(), fieldInfos);
        MethodInfo methodInfo = new MethodInfo(cls.getName());

        TestGenerator.setClassAndMethodInfo(clsInfo, methodInfo, new Config());
        return;
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

