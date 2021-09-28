package patientintake.notifier;
import java.util.ArrayList;

//only need this test double for unit testing,
// so it keeps track of all the notifications that were built and attempt to be sent.
public class EmailNotifierTestDouble implements EmailNotification{

    ArrayList<Message> receivedMessages = new ArrayList<>();


    @Override
    public void sendNotification(String subject, String body, String address) {
         receivedMessages.add(new Message(subject, body, address));
    }

    class Message {
        public String toAddress;
        public String subject;
        public String body;

        public Message(String subject, String body, String toAddress) {
            this.subject = subject;
            this.toAddress = toAddress;
            this.body = body;
        }
    }
}
