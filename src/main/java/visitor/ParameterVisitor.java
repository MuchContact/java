package visitor;

import html.HTMLStringTag;
import html.HTMLTag;
import html.ParameterTag;

public class ParameterVisitor implements Visitor{

    @Override
    public void visit(HTMLStringTag htmlTag) {
        System.out.println("Sorry, you are not my flower...");
    }

    @Override
    public void visit(ParameterTag htmlTag) {
        System.out.println("I'm waiting for you--ParameterTag");
    }

    @Override
    public void visit(HTMLTag htmlTag) {
        System.out.println("nothing");
    }
}
