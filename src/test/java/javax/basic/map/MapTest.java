package javax.basic.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MapTest {
    @Test
    public void should_able_to_init_map_use_two_bracket() throws Exception {
        Map map = new HashMap<String, Object>() {{
            put("a", "b");
        }};
        assertThat(map.size(), is(1));
    }

    @Test
    public void should_get_default_not_null_value_when_get_wrong_key() throws Exception {
        Map hashMap = new HashMap<String, Object>() {{
            put("a", "b");
        }};
        assertThat(hashMap.getOrDefault("key", ""), is(""));
        assertThat(hashMap.getOrDefault("key", "").equals(""), is(true));
        assertThat(hashMap.getOrDefault("key", "").toString().isEmpty(), is(true));
    }
}
