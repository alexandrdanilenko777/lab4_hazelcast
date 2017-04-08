package topic;

import com.hazelcast.config.Config;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.core.*;

import java.util.Random;

public class PublisherMember {

    public static void main(String[] args) throws InterruptedException {
        TopicConfig topicConfig = new TopicConfig("topic").setGlobalOrderingEnabled(false);
        Config config = new Config().addTopicConfig(topicConfig);

        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
        ITopic<String> topic = hz.getTopic("topic");
        topic.addMessageListener(new MessageListenerImpl());

        int counter = 0;
        int nodeId = new Random().nextInt(Integer.MAX_VALUE);
        while (true) {
            topic.publish("nodeId = " + nodeId + ", time = " + System.currentTimeMillis());

            counter++;
            if (counter % 10_000 == 0)
                Thread.sleep(100);
        }
    }

    private static class MessageListenerImpl implements MessageListener<String> {
        public void onMessage(Message<String> m) {
            System.out.println("Published received: " + m.getMessageObject());
            /*try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}

