package ifp.project.livefootball.Chatbot;

import android.util.Log;
import com.google.gson.Gson;
import okhttp3.*;
import java.io.IOException;

public class ChatbotService {
    private static final String TAG = "ChatbotService";
    private static final String WEBHOOK = "https://joanbono.app.n8n.cloud/webhook/31d7f229-0d7d-4320-858a-40a89f2fbd98";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final Gson gson;

    public ChatbotService() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public String sendMessage(String userId, String message) throws IOException {
        JsonMessage jsonMessage = new JsonMessage(userId, message);
        String json = gson.toJson(jsonMessage);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(WEBHOOK)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body() != null ? response.body().string() : "Sin respuesta";
        } catch (IOException e) {
            Log.e(TAG, "Error al enviar mensaje", e);
            throw e;
        }
    }

    private static class JsonMessage {
        final String userId;
        final String message;

        JsonMessage(String userId, String message) {
            this.userId = userId;
            this.message = message;
        }
    }
}