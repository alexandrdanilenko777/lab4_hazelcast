package lab_4;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class ConsumerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IQueue<Integer> queue = hz.getQueue("queue");

        while (true) {
            int item = queue.take();
            System.out.println("Consumed: " + item);

            Thread.sleep(500);
            //Thread.sleep(5000);
        }
    }
}