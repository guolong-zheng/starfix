package starlib.formula;


public class NullVariable extends Variable {
    private static final NullVariable Instance = new NullVariable();

    private NullVariable() {
        super("null");
    }

    public static NullVariable getInstance() {
        return Instance;
    }
}
