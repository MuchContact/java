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

        public final void two() {
            three();
        }

        public void three() {

        }
    }

    @Test
    public void should_not_invoke_two() throws Exception {
        target.one();
        verify(target, times(1)).one();
        verify(target, times(0)).two();
    }

    @Test
    public void should_invoke_method_three_because_method_two_is_final() throws Exception {
        target.two();
        verify(target, times(1)).two();
        verify(target, times(1)).three();
    }
}
