package serializable;

import java.io.*;

public final class MySingletonWithoutResolve implements Serializable {
    public static final MySingletonWithoutResolve INSTANCE = new MySingletonWithoutResolve();
    private String name;

    private MySingletonWithoutResolve(){
        name = "single";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static MySingletonWithoutResolve getInstance(){
        return INSTANCE;
    }

}
final class MySingletonWithResolve implements Serializable {
    public static final MySingletonWithResolve INSTANCE = new MySingletonWithResolve();
    private String name;

    private MySingletonWithResolve(){
        name = "singleWithReadResolve";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static MySingletonWithResolve getInstance(){
        return INSTANCE;
    }

    private Object readResolve() throws ObjectStreamException{
        return INSTANCE;
    }
}
