package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger atomicInteger= new AtomicInteger(0);
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            next=current>=2147483647?0:current+1;
            if(this.atomicInteger.compareAndSet(current,next)) {
                System.out.println("******************next值"+next);
                return next;
            }
        } while (true);




    }
     @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index=getAndIncrement()%serviceInstances.size();
        index=1;
        return serviceInstances.get(index);
    }
}
