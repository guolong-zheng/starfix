package testgene.testsuites;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Formula;
import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.LiteralExpression;
import testgene.*;
import starlib.solver.Model;

public class TestGenerator {

    private static ClassInfo ci;

    private static MethodInfo mi;

    private static Config conf;

    private static String pred;
    private static String dataNode;
    private static String formula;

    private static HashSet<String> models = new HashSet<String>();

    public static int count = 0;
    public static int size = 1; //number of total corrupted pointers

    private static int index = 1;

    private static boolean first = true;

    public static List<String> allVars = new ArrayList<>();

    public static void setClassAndMethodInfo(ClassInfo ci, MethodInfo mi, Config conf) {
        if (first) {
            TestGenerator.ci = ci;
            TestGenerator.mi = mi;
            TestGenerator.conf = conf;
            first = false;
        }
    }

    public static void setPredicates(String pred, String dataNode, String formula) {
        TestGenerator.pred = pred;
        TestGenerator.dataNode = dataNode;
        TestGenerator.formula = formula;
    }

    public static void reset() {
        first = true;
    }

    public static void addModel(String model) {
        models.add(model);
    }

    public static void addModels(HashSet<String> models) {
        TestGenerator.models = models;
    }

    public static void generateTests() {
        StringBuffer test = new StringBuffer();
        init(test);

        for (String model : models) {
            Model m = new Model(model);
            generateTest(m.getFormula(), test, m.getPure());
        }

        test.append("}\n");
//		System.out.println(test);

        PathFinderUtils.writeToFile(test, conf, ci, mi);
    }

    private static void generateTest(Formula f, StringBuffer test, String pure) {
        String objName = "obj";
        String clsName = ci.getSimpleName();

//        test.append("\t@Test\n");
//        test.append("\tpublic void test_" + mi.getName() + index++ + "() throws Exception {\n");

        test.append("\n\tpublic static void main (){\n");

//        if (!mi.isStatic())
//            test.append("\t\t" + clsName + " " + objName + " = new " + clsName + "();\n");

        LocalVarInfo[] args = mi.getArgumentLocalVars();
        FieldInfo[] insFields = ci.getInstanceFields();
        FieldInfo[] staFields = ci.getDeclaredStaticFields();

        HashMap<String, String> knownTypeVars = PathFinderUtils.initTypeVarMap(ci, mi);

        f.updateType(knownTypeVars);
        f.removeTerm();

        if (pure.length() > 0) {
            String[] pureAssigns = pure.split(";");
            for (String pureAssign : pureAssigns) {
                pureAssign = pureAssign.replaceAll("\\(", "");
                pureAssign = pureAssign.replaceAll("\\)", "");

                String[] nameAndValue = pureAssign.split(",");
                String name = nameAndValue[0];
                String value = nameAndValue[1];

                for (Entry<String, String> entry : knownTypeVars.entrySet()) {
                    Variable var = new Variable(entry.getKey(), entry.getValue());
                    if (var.isPrim() && var.getName().equals(name)) {
                        Expression exp1 = new Variable(name, var.getType());
                        Expression exp2 = new LiteralExpression(value);
                        f.addComparisonTerm(Comparator.EQ, exp1, exp2);
                    }
                }
            }
        }

        HashSet<Variable> initVars = new HashSet<Variable>();

        for (FieldInfo field : insFields) {
            if (field.isFinal() || field.isPrivate() || field.isProtected()) {
                String name = "this_" + field.getName();
                String type = PathFinderUtils.toJavaType(field.getType());

                initVars.add(new Variable(name, type));
            }
        }

        if (staFields != null)
            for (FieldInfo field : staFields) {
                if (field.isFinal() || field.isPrivate() || field.isProtected()) {
                    String name = clsName + "_" + field.getName();
                    String type = PathFinderUtils.toJavaType(field.getType());

                    initVars.add(new Variable(name, type));
                }
            }

        TestGenVisitor jpfGen = new TestGenVisitor(knownTypeVars, initVars, objName,
                clsName, insFields, staFields, test);
        jpfGen.visit(f);

//		if (!mi.isStatic())
//			test.append("\t\tSystem.out.println(Utilities.repOK(" + objName + "));\n");

//        if (mi.isStatic())
//            test.append("\t\t" + clsName + "." + mi.getName() + "(");
//        else
//            test.append("\t\t" + objName + "." + mi.getName() + "(");
//
//        String s = "";
//        if (args != null)
//            for (LocalVarInfo arg : args) {
//                if (!arg.getName().equals("this"))
//                    s += arg.getName() + ",";
//            }
//
//        if (!s.isEmpty())
//            s = s.substring(0, s.length() - 1);
//
//        test.append(s + ");\n");

        for (Variable var : initVars)
            allVars.add(var.toString());

        mutate(test);

        test.append("\t\tChecker.repair(x" + ", \"" + TestGenerator.dataNode +
                "\", \"" + TestGenerator.pred + "\", \"" + TestGenerator.formula +
                "\", \"x\"" + ");\n");

        test.append("\t}\n\n");
    }

    private static void init(StringBuffer test) {
        String pack = conf.getProperty("star.test_package");
        if (pack != null) {
            test.append("package " + pack + ";\n\n");
        }

        String[] imps = conf.getStringArray("star.test_imports");
        if (imps != null) {
            for (String imp : imps) {
                test.append("import " + imp + ";\n");
            }
        }

        test.append("import repair.checker;\n");
        //test.append("import org.junit.Test;\n");
        //test.append("import gov.nasa.jpf.util.test.TestJPF;\n");
        test.append("\n");

        test.append("public class " + ci.getSimpleName() + "{\n\n"); // "1 extends TestJPF {\n\n");
        addClassDeclare(test);
        addInitTest(test);
    }

    private static void addInitTest(StringBuffer test) {
        String initTest = conf.getProperty("star.test_init");
        if (initTest == null) return;

        try {
            BufferedReader br = new BufferedReader(new FileReader(initTest));
            String s = br.readLine();

            while (s != null) {
                test.append("\t" + s + "\n");
                s = br.readLine();
            }

            test.append("\n");

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addClassDeclare(StringBuffer test) {
        DataNode dn = DataNodeMap.getAll()[0];
        Variable[] fields = dn.getFields();
        for (Variable var : fields) {
            test.append("\t" + var.getType() + " " + var.getName() + ";\n");
        }
    }


    public static void mutate(StringBuffer test) {
        Random rand = new Random(System.currentTimeMillis());
        String from = "toMutate";
        int index = test.indexOf(from);
        while (count > 0) {
            System.out.println("mutating");
            int n = rand.nextInt(allVars.size());
            String to = allVars.get(n);
            test.replace(index, index + from.length(), to);
            index += to.length();
            index = test.indexOf(from, index);
            count--;
        }
    }

}
