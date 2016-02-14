package serializable;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        Period period = new Period(new Date(), new Date());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        out = new ObjectOutputStream(bos);
        out.writeObject(period);
        byte[] yourBytes = bos.toByteArray();
        Period p = (Period) deserialize(yourBytes);
        System.out.println(p);
    }
    // Returns the object with the specified serialized form
    private static Object deserialize(byte[] sf) {
        try {
            InputStream is = new ByteArrayInputStream(sf);
            ObjectInputStream ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
