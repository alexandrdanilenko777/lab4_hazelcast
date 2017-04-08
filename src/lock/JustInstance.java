package lock;

import java.util.concurrent.locks.Lock;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class JustInstance {

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        
        
        
        Lock lock = hz.getLock( "myLock" );
        lock.lock();
        try {
          System.out.println("lock");
        } finally {
          lock.unlock();
          System.out.println("unlock");
        }
        
        
    }
}
