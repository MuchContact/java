import html.HTMLStringTag;
import html.HTMLTag;
import html.ParameterTag;
import org.junit.Test;
import visitor.ParameterVisitor;
import visitor.Visitor;

import java.util.List;

import static java.util.Arrays.asList;

public class InvokeTest {
    @Test
    public void testName() throws Exception {
        List<HTMLTag> htmlNodes = asList(new HTMLStringTag(), new ParameterTag());
        Visitor parameterVisitor = new ParameterVisitor();
        htmlNodes.stream().forEach(node->{
           node.accept(parameterVisitor);
        });
    }
}
