package visitor;

import html.HTMLStringTag;
import html.HTMLTag;
import html.ParameterTag;

public interface Visitor {
    void visit(HTMLStringTag htmlTag);
    void visit(ParameterTag htmlTag);
    void visit(HTMLTag htmlTag);
}
