package com.swetabh.javalib;

public class ExperimentWithThreadGroup {
    /**
     * ThreadGroup : Based on functionality we can group threads into a single unit which is nothing
     * but threadgroup i.e thread group contains a group of threads.
     * In addition to threads, threadgroup can also contains subthread groups.
     * The main advantage of maintaining threads in the form of threadgroup is we can perform common
     * operations very easily.
     * Every threadgroup in java is a child group of system group, either directly or indirectly.
     * Hence system group access root for all threadgroups in java.
     * System group contains several system level threads like, finalizer , reference handler,
     * signal dispatcher, attach listener etc .
     * ThreadGroup is a java class present in java.lang package and it is the direct child class of
     * object.
     * Constructors :
     * 1. ThreadGroup g = new ThreadGroup("String gName");
     *  Creates a new Thread group with specified group name. The parent of this new group is the
     *  ThreadGroup of currently executing thread.
     * 2. ThreadGroup g = new ThreadGroup(ThreadGroup pg , String name)
     *  Creates a new thread group with the specified group name. The parent of this new threadgroup
     *  is specified parent group.*/
    public static void main(String args[]){

        ThreadGroup g = new ThreadGroup("FirstGroup");
        System.out.println(g.getParent().getName());
        ThreadGroup g2 = new ThreadGroup(g,"Second Group");
        System.out.println(g2.getParent().getName());
    }
}
