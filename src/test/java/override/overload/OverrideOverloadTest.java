package override.overload;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OverrideOverloadTest {
    @Test
    public void testOverload() throws Exception {
        List<Parent> people = asList(new Parent(), new Children1(), new Children2());
        MainInvoker main = new MainInvoker();

        for (Parent person : people) {
            assertThat(main.handle(person), is("person"));
        }
    }

    @Test
    public void testOverride() throws Exception {
        List<Parent> people = asList(new Parent(), new Children1(), new Children2());

        String expected [] = {"person", "child-1", "child-2"};
        int index = 0;
        for (Parent person : people) {
            assertThat(person.Name(), is(expected[index++]));
        }
    }

    @Test
    public void testConfusingOverload() throws Exception {
        Children1 children = new Children1();
        Children1.Egg egg = new Children1.Egg();
        assertThat(children.Eat(egg), is("eat egg"));
        assertThat(children.Eat(new Object()), is("eat obj"));

        Parent.Badminton badminton = new Parent.Badminton();
        assertThat(children.Play(badminton), is("play badminton"));
        assertThat(children.Play(new HashMap<>()), is("play obj"));

        Object anotherBadminton = new Parent.Badminton();
        assertThat(children.Play(anotherBadminton), is("play obj"));
        assertThat(children.Play(new HashMap<>()), is("play obj"));

    }
}
