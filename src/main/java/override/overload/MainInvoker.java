package override.overload;

public class MainInvoker {
    public String handle(Parent person) {
        return "person";
    }

    public String handle(Children1 person) {
        return "child-1";
    }

    public String handle(Children2 person) {
        return "child-2";
    }
}
