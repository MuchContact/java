package html;

import visitor.Visitor;

public class ParameterTag extends HTMLTag{
    @Override
    public void accept(Visitor visitor) {
       visitor.visit(this);
    }
}
