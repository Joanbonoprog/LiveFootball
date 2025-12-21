package ifp.project.livefootball.Chatbot;

public class ChatMessage {
    private final String sender;
    private final String message;

    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}


