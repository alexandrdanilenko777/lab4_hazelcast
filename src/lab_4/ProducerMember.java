package lab_4;

import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class ProducerMember {
    public static void main(String[] args) throws Exception {
        Config cfg = new Config()
                .addQueueConfig(new QueueConfig("queue").setMaxSize(20));

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);

        IQueue<Integer> queue = hz.getQueue("queue");

        //for (int k = 1; k < 10_000; k++) {
       for (int k = 1; k < 100; k++) {
            queue.put(k);
            System.out.println("Producing: " + k);
            Thread.sleep(250);
            //Thread.sleep(1000);
        }

        queue.put(-1);
        System.out.println("Producer Finished!");
    }
}
