package oom;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    static class OOMObject{

    }

    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
