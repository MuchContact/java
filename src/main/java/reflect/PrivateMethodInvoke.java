package reflect;

public class PrivateMethodInvoke {
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
}
