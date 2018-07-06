package repair.checker;

public enum Status {
    BACKWARD,       // backward
    STAY,           // don't need to backward
    STOP,           // unhandled error happen, stop current searching
    PASS,           // pass checking
    BackAndStay
}
