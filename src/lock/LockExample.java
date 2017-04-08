package lock;

import com.hazelcast.config.Config;
import com.hazelcast.config.LockConfig;
import com.hazelcast.config.QuorumConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.locks.Lock;

public class LockExample {

    public static void main(String[] args) throws InterruptedException {
        QuorumConfig quorumConfig = new QuorumConfig("lock-quorum", true, 2);
        LockConfig lockConfig = new LockConfig("myLock")
                .setQuorumName("lock-quorum");

        Config cfg = new Config()
                .addQuorumConfig(quorumConfig)
                .addLockConfig(lockConfig);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(cfg);

        Thread.sleep(20_000);

        Lock lock = hazelcastInstance.getLock("myLock");
        IMap<String, Integer> map = hazelcastInstance.getMap("myMap");

        String key = "key";

        map.putIfAbsent(key, 0);

        for (int i = 0; i < 1000; i++) {
            if (i % 20 == 0) {
                System.out.println("Processed: " + i);
            }
            lock.lock();
            try {
                Integer oldValue = map.get(key);
                Thread.sleep(10);
                map.put(key, oldValue + 1);
            } finally {
                lock.unlock();
            }
        }

        System.out.println(map.get(key));
    }
}
