package reflect;

public class PrivateMethodInvoke {
    private static String staticField;

    private String innerMethod(){
        return "test";
    }
    private String name;

    public PrivateMethodInvoke(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getStaticField() {
        return staticField;
    }

    public static void setStaticField(String staticField) {
        PrivateMethodInvoke.staticField = staticField;
    }
}
