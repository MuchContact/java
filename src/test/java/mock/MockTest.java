package mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MockTest {
    @Mock
    MyTarget target;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private class MyTarget {
        public void one() {
            two();
        }

        public void two() {

        }
    }

    @Test
    public void should_not_invoke_two() throws Exception {
        target.one();
        verify(target, times(1)).one();
        verify(target, times(0)).two();
    }
}
