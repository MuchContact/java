package override.overload;

public class Children1 extends Parent{
    @Override
    public String Name() {
        return "child-1";
    }

    public String Eat(Egg egg) {
        return "eat egg";
    }

    public String Play(Object obj) {
        return "play obj";
    }

    public static class Egg {
    }
}
