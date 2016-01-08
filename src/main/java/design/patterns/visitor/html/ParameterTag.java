package design.patterns.visitor.html;

import design.patterns.visitor.Visitor;

public class ParameterTag extends HTMLTag {
    @Override
    public void accept(Visitor visitor) {
       visitor.visit(this);
    }
}
