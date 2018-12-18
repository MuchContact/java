package design.patterns.chain.responsibility.ba;

import java.util.ArrayList;
import java.util.List;

public class BeforeAfterChain {
    private List<BeforeAfterChainFilter> filters = new ArrayList<BeforeAfterChainFilter>();
    private int cur = 0;

    public void add(BeforeAfterChainFilter beforeAfterChainFilter) {
        filters.add(beforeAfterChainFilter);
    }

    public void exe() {
        if (cur == filters.size()) {
            System.out.println("...");
        } else {
            BeforeAfterChainFilter beforeAfterChainFilter = filters.get(cur);
            cur++;
            beforeAfterChainFilter.going(this);
//            beforeAfterChainFilter.before();
//            exe();
//            beforeAfterChainFilter.after();
        }

    }
}
