package design.patterns.chain.responsibility.ba;


public class AnotherChain {
    public static void main(String[] args) {
        BeforeAfterChain beforeAfterChain = new BeforeAfterChain();
        beforeAfterChain.add(new BeforeAfterChainFilter(1));
        beforeAfterChain.add(new BeforeAfterChainFilter(2));
        beforeAfterChain.exe();

    }


}
