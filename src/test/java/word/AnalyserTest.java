package word;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalyserTest {

    private Analyser analyser;

    @Before
    public void setUp() throws Exception {
        analyser = new Analyser();
    }

    @Test
    public void should_spot_chinese() throws Exception {
        assertThat(analyser.isChineseWord('中'), is(true));
        assertThat(analyser.isChineseWord('z'), is(false));
    }

    @Test
    public void should_split_english_sentence() throws Exception {
        List<String> expected = new ArrayList<String>(){{
            add("What");
            add("is");
            add("your");
            add("name");
        }};
        assertThat(analyser.splitByWord("What is your name"), equalTo(expected));

        List<String> expected2 = new ArrayList<String>(){{
            add("What's");
            add("your");
            add("name");
        }};
        assertThat(analyser.splitByWord("What's your name"), equalTo(expected2));
    }
}
