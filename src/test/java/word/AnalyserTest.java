package word;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
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
        List<String> expected = asList("What", "is", "your", "name");
        assertThat(analyser.splitByWord("What is your name"), equalTo(expected));

        List<String> expected2 = asList("What's", "your", "name");
        assertThat(analyser.splitByWord("What's your name"), equalTo(expected2));
    }

    @Test
    public void should_split_mix_sentence() throws Exception {
        List<String> expected = asList("加", "入", "us", "!");
        assertThat(analyser.splitByWord("加入us!"), equalTo(expected));

        List<String> expected2 = asList("加", "入", "u-s", "!");
        assertThat(analyser.splitByWord("加入u-s!"), equalTo(expected2));

        List<String> expected3 = asList("加", "入", "u_s", "!");
        assertThat(analyser.splitByWord("加入u_s!"), equalTo(expected3));
    }
}
