package classloader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClassLoaderTest {
    @Test
    public void test_class_loader() {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String substring = name.substring(name.lastIndexOf("." )+1);
                InputStream is = getClass().getResourceAsStream(substring + ".class");
                try {
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };

        try {
            String className = "classloader.ClassLoaderTest";
            Class<?> aClass = classLoader.loadClass(className);
            Object target = aClass.newInstance();
            assertFalse(target instanceof ClassLoaderTest);

            ClassLoaderTest targetTwo = (ClassLoaderTest) Class.forName(className).newInstance();
            assertTrue(targetTwo instanceof ClassLoaderTest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
