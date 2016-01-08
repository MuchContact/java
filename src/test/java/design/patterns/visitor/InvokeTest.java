package design.patterns.visitor;

import design.patterns.visitor.html.HTMLStringTag;
import design.patterns.visitor.html.HTMLTag;
import design.patterns.visitor.html.ParameterTag;
import org.junit.Test;

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
