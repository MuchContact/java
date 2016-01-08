package design.patterns.visitor.html;

import design.patterns.visitor.Visitor;

public class HTMLTag {
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
