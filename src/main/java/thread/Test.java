package thread;

public class Test {
    public Test() {
        Test t1 = this;
        Test t2 = this;
        Object o = new Object();
        synchronized (o){
            try{
                System.out.println(Thread.currentThread().getName());
                wait();
                System.out.println("Hello");
            }catch (InterruptedException e){
                System.out.println("Interrupted");
            }catch (Exception e){
                System.out.println(String.format("other Exception: %s", e.toString()));
            }finally {
                System.out.println("Out of Synchronise");
            }

        }
        System.out.println("End");
    }

    public static void main(String[] args) {
        new Test();
    }
}
