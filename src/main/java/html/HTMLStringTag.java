package html;

import visitor.Visitor;

public class HTMLStringTag extends HTMLTag{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
