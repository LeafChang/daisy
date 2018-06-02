package com.gs.leaf.thread;

import java.util.concurrent.*;

/**
 * 测试异步编程
 */
public class ThreadTest {

    public static void main(String [] args){

        System.out.println("Get request from TC, thread id="+Thread.currentThread().getId());

        //构造线程池
        ExecutorService es = Executors.newCachedThreadPool();


        //task
        Callable<Integer> rmTask = new RMTask();
        Callable<Integer> cpudTask = new CPUDTask();

        Future<Integer> rmResult = es.submit(rmTask);
        Future<Integer> cpudResult = es.submit(cpudTask);
        es.shutdown();

        System.out.println("ES shutdown, thread id="+Thread.currentThread().getId());

        try {
            Integer result = rmResult.get();
            System.out.println("process rmResult, thread id="+Thread.currentThread().getId());
            System.out.println("process rmResult="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}

//task
class RMTask implements Callable<Integer>{
    public Integer call() throws Exception {
        System.out.println("creating response =================RMTask========== Rich media, thread id="+Thread.currentThread().getId());
        Thread.sleep(3000);
        System.out.println("end response =================RMTask==========  Rich media, thread id="+Thread.currentThread().getId());
        return new Integer(3);
    }
}

class CPUDTask implements Callable<Integer>{
    public Integer call() throws Exception {
        System.out.println("creating =================CPUDTask========== CUPD msg, thread id="+Thread.currentThread().getId());
        Thread.sleep(8000);
        System.out.println("end response ==================CPUDTask=========== media, thread id="+Thread.currentThread().getId());
        return new Integer(10);
    }
}