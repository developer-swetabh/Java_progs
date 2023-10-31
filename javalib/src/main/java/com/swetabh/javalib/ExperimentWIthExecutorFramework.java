package com.swetabh.javalib;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Thread pool executor framework.
 * ----------------------------------------
 * Creating a new thread for every job may create performance and memory problems.
 * To overcome this we should go for thread pool.
 * Thread pool is a pool of already created threads ready to do our jobs
 * Java 1.5v introduces ThreadPool framework to implement ThreadPools.
 * ThreadPool framework also known as Executor Framework.
 * We can create a ThreadPool as follow:
 * ExecutorService service = Executors.newFixedThreadPool(3);
 * We can submit a runnable job by using {@code submit()} method:
 * {@code
 * service.submit(job)
 * }
 * We can shutdown executor service by using shutdown() method:
 * {@code
 * service.shutdown()
 * }
 * <p>
 * Note:
 * While designing/developing webservers and application servers we can use thread pool concept.
 * <p>
 * {@linkplain  java.util.concurrent.Callable} and {@linkplain java.util.concurrent.Future}
 * ----------------------------------------------------------------------------------------
 * In the case of {@linkplain Runnable} job, thread won't return anything after completing the job.
 * If a thread is required to return some result after execution then we should go for
 * {@link Callable}.
 * {@link Callable} interface contains only one method i.e
 * {@code public object call() throws Exception}
 * <p>
 * If we submit a {@link Callable} object to executor then after completing the job, thread returns
 * and object of the type {@link Future} i.e Future object can be used to retrieve the result from
 * {@link Callable} job
 *
 * Difference between Runnable and Callable
 * 1. If a thread is not required to return anything after completing the job then we should go
 * for runnable. If a thread required to return something after completing the job then we should
 * go for Callable
 * 2. Runnable interface contains only one method i.e run(). Callable interface contains only one
 * method i.e call().
 * 3. Runnable job not required to return anything and hence return type of run method is void.
 * Callable job is required to return something and hence return type of call() method is object.
 * 4. Within the run method if there is any chance of writing checked exception compulsory we
 * should handle using try catch because we can't use throws keyword for run() method. Inside
 * call() method if there is any chance of writing checked exception we are not required to handle
 * by using try catch because call method already throws exception.
 * 5. Runnable interface present in java.lang package, Callable interface present in
 * java.util.concurrent package
 * 6. Runnable introduced in 1.0 version, Callable introduced in 1.5v
 *
 */
public class ExperimentWIthExecutorFramework {

    public static void main(String[] args) throws Exception{
        //--------- Using Runnable interface ------------------
        /*
        PrintJob[] jobs = {
                new PrintJob("Swetabh"),
                new PrintJob("Sashwat"),
                new PrintJob("Ayush"),
                new PrintJob("Vishwas"),
                new PrintJob("Saumya"),
                new PrintJob("Shashi")
        };

        System.out.println("Creating executor service of thread pool count 4");
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (PrintJob job : jobs) {
            service.submit(job);
        }
        System.out.println("Shutting down executor service");
        service.shutdown();
*/
        //------------------------------------------------------------------
        System.out.println("Callable case: Creating executor service of thread pool count 3");
        MyCallable[] calls = {
                new MyCallable(10),
                new MyCallable(20),
                new MyCallable(30),
                new MyCallable(40),
                new MyCallable(50),
                new MyCallable(60)
        };
        ExecutorService sumService = Executors.newFixedThreadPool(3);
        for (MyCallable job : calls) {
            Future future =sumService.submit(job);
            System.out.println(future.get());

        }
        System.out.println("Shutting down callable executor service");
        sumService.shutdown();

    }

    public static class PrintJob implements Runnable {

        private final String name;

        public PrintJob(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Printing -----> " + name
                    + "------> Started by " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class MyCallable implements Callable {
        private int num;

        public MyCallable(int num) {
            this.num = num;
        }

        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() +
                    " is responsible to find sum of first " +
                    num + " numbers");
            int total = 0;
            for(int i =0;i<=num;i++){
                total +=i;
            }
            return total;
        }
    }
}
