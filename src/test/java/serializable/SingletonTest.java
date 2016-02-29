package serializable;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class SingletonTest {
    @Test
    public void should_validate_singleton_without_readResolve() throws Exception {
        final MySingletonWithoutResolve instance = MySingletonWithoutResolve.getInstance();
        ByteArrayOutputStream bout = writeToByteArrayOutputStream(instance);

        final ObjectInputStream oin = readObjectInputStream(bout);
        final MySingletonWithoutResolve mySingletonWithoutResolve = (MySingletonWithoutResolve) oin.readObject();
        oin.close();
        assertThat(mySingletonWithoutResolve, not(instance));
        instance.setName("Change Name");
        assertThat(mySingletonWithoutResolve.getName(), is("single"));
    }

    @Test
    public void should_keep_singleton_with_readResolve() throws Exception {
        final MySingletonWithResolve instance = MySingletonWithResolve.getInstance();
        ByteArrayOutputStream bout = writeToByteArrayOutputStream(instance);

        final ObjectInputStream oin = readObjectInputStream(bout);
        final MySingletonWithResolve mySingletonWithResolve = (MySingletonWithResolve) oin.readObject();
        oin.close();
        assertThat(mySingletonWithResolve, is(instance));
        final String newName = "NewName";
        instance.setName(newName);
        assertThat(mySingletonWithResolve.getName(), is(newName));
    }

    private ObjectInputStream readObjectInputStream(ByteArrayOutputStream bout) throws IOException {
        final ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        return new ObjectInputStream(bin);
    }

    private ByteArrayOutputStream writeToByteArrayOutputStream(Object instance) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final ObjectOutputStream outputStream = new ObjectOutputStream(bout);
        outputStream.writeObject(instance);
        outputStream.close();
        return bout;
    }
}
