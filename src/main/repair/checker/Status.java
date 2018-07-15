package repair.checker;

public enum Status {
    BACKWARD,       // backward
    MISMATCH,
    DUPLICATE,
    NULL_ROOT,
    STAY,           // don't need to backward
    STOP,           // unhandled error happen, stop current searching
    PASS,           // pass checking
}
