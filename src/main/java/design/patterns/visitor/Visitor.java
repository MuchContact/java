package design.patterns.visitor;

import design.patterns.visitor.html.HTMLStringTag;
import design.patterns.visitor.html.HTMLTag;
import design.patterns.visitor.html.ParameterTag;

public interface Visitor {
    void visit(HTMLStringTag htmlTag);
    void visit(ParameterTag htmlTag);
    void visit(HTMLTag htmlTag);
}
