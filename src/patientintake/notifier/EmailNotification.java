package patientintake.notifier;

public interface EmailNotification {
    void sendNotification(String subject, String body, String address);
}
