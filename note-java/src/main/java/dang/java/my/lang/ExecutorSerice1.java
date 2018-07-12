package com.dang.java.my.lang;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * 线程池u
 * */
public class ExecutorSerice1 implements ExecutorService {

    @Override
    public void execute(Runnable command) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void shutdown() {
        // TODO 自动生成的方法存根

    }

    @Override
    public List<Runnable> shutdownNow() {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public boolean isShutdown() {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean isTerminated() {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        // TODO 自动生成的方法存根
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(
            Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                           long timeout, TimeUnit unit) throws InterruptedException,
            ExecutionException, TimeoutException {
        // TODO 自动生成的方法存根
        return null;
    }

}
