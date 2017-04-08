package lab_4;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SampleQueue {

    public static void main(String[] args) throws Exception {

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        BlockingQueue<String> queue = hazelcastInstance.getQueue("tasks");
        queue.put("asd");
        String task = queue.take();

        System.out.println(task);

        boolean offered = queue.offer("213", 10, TimeUnit.SECONDS);
        task = queue.poll(5, TimeUnit.SECONDS);
        if (task != null) {
        	System.out.println("This our task " + task);
        }

    }
}