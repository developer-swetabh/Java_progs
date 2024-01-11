package com.swetabh.javalib;

/**
 * {@link ThreadLocal} class provides ThreadLocal variable.
 * {@link ThreadLocal} class maintains values per thread basis.
 * Each {@link ThreadLocal} object maintains a separate value like userId, transactionId, etc for each that
 * access that object.
 * Thread can access its local value, manipulate its value and even can remove its value.
 * In every part of the code which is executed by the thread we can access its local variable.
 * Example:
 * Consider a servlet which invokes some business methods.
 * We have a requirement to generate a unique transactionId for each and every request and we have
 * to pass this transactionId to the business methods for this requirement we can use ThreadLocal
 * to maintain a separate transactionId for every request i.e for every thread.
 *
 * Note:
 * 1. {@link ThreadLocal} class introduced in 1.2v and enhanced in 1.5v
 * 2. {@link ThreadLocal} can be assiciated with ThreadScope.
 * 3. Total code which is executed by the thread has access to the corresponding {@link ThreadLocal}
 * variable.
 * 4. A thread can access its own local variables and can't access other threads local variables.
 * 5. Once thread enters into dead state all its local variable are by default eligible for GC.
 *
 * Constructor:
 * ----------------
 * ThreadLocal tl = new ThreadLocal(); //creates a ThreadLocal variable.
 *
 * Methods:
 * ----------------
 * 1. Object get(): returns the value of ThreadLocal variable associated with current thread
 * 2. Object initialValue(): returns initial value of ThreadLocal variable associated with
 * current thread.
 * The default implementation of this method returns null.
 * To customize our own initial value we have to override this method.
 * 3. void set(Object newValue): To set a new value.
 * 4. void remove(): To remove the value of ThreadLocal variable associated with current thread.
 * It is newly added method in 1.5v.
 * After removal, if we are trying to access, it will be reinitialized once again by invoking its
 * initial value method.
 *
 * {@link InheritableThreadLocal} vs {@link ThreadLocal}
 * -------------------------------------------------------
 * Parent threads of thread local variable by default not available to the child thread.
 * If we want to make parent thread ThreadLocal variable value available to the child thread, then
 * we should go for {@link InheritableThreadLocal} class.
 *
 * By default child thread value is exactly same as parents thread value, but we can provide
 * customized value for child thread by overriding childValue() method.
 *
 * Constructor:
 * -------------
 * InheritableThreadLocal tl = new InheritableThreadLocal();
 *
 * Methods:
 * -----------
 * InheritableThreadLocal is a child class ThreadLocal and hence all methods present in thread local
 * by default available to InheritableThreadLocal.
 * Inaddition to these methods it contains only one method.
 * {@code public Object childValue(Object parentValue)}
 * */
public class ExperimentWIthThreadLocal {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return "Suman";
            }
        };
        System.out.println(threadLocal.get()); // null (without initialValue override)
        threadLocal.set("swetabh");
        System.out.println(threadLocal.get()); // swetabh
        threadLocal.remove();
        System.out.println(threadLocal.get()); // null (without initialValue override)

        /**
         * In above program a separate customer Id will be maintained by thread local object
         * */
        CustomerThread c1 = new CustomerThread("Customer 1");
        CustomerThread c2 = new CustomerThread("Customer 2");
        CustomerThread c3 = new CustomerThread("Customer 3");
        CustomerThread c4 = new CustomerThread("Customer 4");

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        // InheritableThreadLocal
        /*
        * If we are maintaining InheritableThreadLocal and we are not overriding childValue()
        * method, then output =
        * parent thread value ---- pp
        * child thread value ---- pp
        * */
        ParentThread pt = new ParentThread();
        pt.start();
    }

    public static class CustomerThread extends Thread {
        private static int custId = 0;
        private static ThreadLocal tl =  new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return ++custId;
            }
        };

        public CustomerThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +
                    " executing with customer id " +  tl.get());
        }
    }

    public static class ParentThread extends Thread {

        public static InheritableThreadLocal tl = new InheritableThreadLocal(){
            @Override
            protected Object childValue(Object parentValue) {
                return "cc";
            }
        };
        @Override
        public void run() {
          tl.set("pp");
          System.out.println("Parent Thread value --- " + tl.get());
          ChildThread ct = new ChildThread();
          ct.start();
        }
    }
    private static class ChildThread extends Thread{

        @Override
        public void run() {
            System.out.println("Child Thread value --- "+ParentThread.tl.get());
        }
    }
}
