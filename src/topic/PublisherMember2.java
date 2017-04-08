package topic;

import com.hazelcast.config.Config;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

import java.util.Random;

public class PublisherMember2 {

    public static void main(String[] args) throws InterruptedException {
        TopicConfig topicConfig = new TopicConfig("topic").setGlobalOrderingEnabled(false);
        Config config = new Config().addTopicConfig(topicConfig);

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
        ITopic<String> topic = hz.getTopic("topic");

        int counter = 0;

        int nodeId = new Random().nextInt(Integer.MAX_VALUE);
        while (true) {
            topic.publish("nodeId = " + nodeId + ", time = " + System.currentTimeMillis());
            counter++;

            //if (counter % 10_000 == 0)
                Thread.sleep(500);
        }
    }
}