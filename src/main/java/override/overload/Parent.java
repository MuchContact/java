package override.overload;

public class Parent {
    public String Name() {
        return "person";
    }

    public String Eat(Object obj) {
        return "eat obj";
    }

    public String Play(Badminton badm) {
        return "play badminton";
    }

    public static class Badminton {
    }
}
