package lambda;

import java.util.function.Function;

/**
 * 查看匿名类加入下面参数
 * -Djdk.internal.lambda.dumpProxyClasses=.
 */
public class Sample {
    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> {
            System.out.println("Hello World!");
        };
        runnable.run();
        String hello = "Hello ";
        Function<String, String> function = string -> hello + string;
        function.apply("Doge");
    }

}
