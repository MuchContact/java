package classloader;

class Parent {
    protected static int C = 1;

    static {
        A = 2;
        C = 3;
    }

    protected static int A = 1;
}

public class SubClassLoader extends Parent {
    private static int B = A + C;

    public static void main(String[] args) {
        System.out.println(SubClassLoader.B);
    }
}
