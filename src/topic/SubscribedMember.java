package topic;

import com.hazelcast.core.*;

public class SubscribedMember {

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ITopic<String> topic = hz.getTopic("topic");
        topic.addMessageListener(new MessageListenerImpl());
        System.out.println("Subscribed");
    }

    private static class MessageListenerImpl implements MessageListener<String> {
        public void onMessage(Message<String> m) {
            System.out.println("Subscriber received: " + m.getMessageObject());
            
        }
    }
}
