package html;
import visitor.Visitor;

public class HTMLTag {
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
