package idGenerator;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

public class IdGeneratorExample {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IdGenerator idGen = hazelcastInstance.getIdGenerator("newId");
        while (true) {
            Long id = idGen.newId();
            System.err.println("Id: " + id);
            Thread.sleep(1000);
        }
    }
}
