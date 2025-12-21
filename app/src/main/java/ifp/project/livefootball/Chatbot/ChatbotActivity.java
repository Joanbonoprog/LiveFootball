package ifp.project.livefootball.Chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import ifp.project.livefootball.MainMenu.MainMenuActivity;
import ifp.project.livefootball.R;

public class ChatbotActivity extends AppCompatActivity {
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private EditText messageInput;
    private Button sendButton;
    private ChatbotService chatbotService;
    private List<ChatMessage> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        chatbotService = new ChatbotService();
        messages = new ArrayList<>();

        initializeViews();
        setupRecyclerView();
        setupSendButton();

        Button buttonBack = findViewById(R.id.buttonBackToMenu);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(ChatbotActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initializeViews() {
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
    }

    private void setupRecyclerView() {
        chatAdapter = new ChatAdapter(messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        chatRecyclerView.setLayoutManager(layoutManager);
        chatRecyclerView.setAdapter(chatAdapter);
    }

    private void setupSendButton() {
        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                messageInput.setText("");
            }
        });
    }

    private void sendMessage(String message) {
        if (message.trim().isEmpty()) {
            return;
        }if (chatbotService == null) {
            Toast.makeText(this, "Error: Servicio no inicializado", Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage userMessage = new ChatMessage("usuario", message);
        messages.add(userMessage);
        chatAdapter.notifyItemInserted(messages.size() - 1);
        messageInput.setText("");

        new Thread(() -> {
            try {
                String response = chatbotService.sendMessage("usuario", message);
                runOnUiThread(() -> {
                    ChatMessage botMessage = new ChatMessage("bot", response);
                    messages.add(botMessage);
                    chatAdapter.notifyItemInserted(messages.size() - 1);
                    chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    String errorMsg = "Error de conexión: " + e.getMessage();
                    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}
