package design.patterns.chain.responsibility.ba;

public class BeforeAfterChainFilter {
    private final int id;

    public BeforeAfterChainFilter(int i) {
        this.id = i;
    }

    public void before() {
        System.out.println("before " + id);
    }

    public void after() {
        System.out.println("after " + id);
    }

    public void going(BeforeAfterChain beforeAfterChain) {
        before();
        beforeAfterChain.exe();
        after();
    }
}
