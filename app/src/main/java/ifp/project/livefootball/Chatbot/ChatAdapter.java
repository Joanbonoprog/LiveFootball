package ifp.project.livefootball.Chatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ifp.project.livefootball.Chatbot.ChatMessage;
import ifp.project.livefootball.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private final List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        if (message == null) return;

        holder.messageText.setText(message.getMessage());
        boolean isBot = "bot".equals(message.getSender());

        holder.messageText.setBackgroundResource(
                isBot ? R.drawable.message_background_bot : R.drawable.message_background_user
        );

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.messageText.getLayoutParams();
        if (params == null) {
            params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
        }

        params.startToStart = isBot ? ConstraintLayout.LayoutParams.PARENT_ID : -1;
        params.endToEnd = isBot ? -1 : ConstraintLayout.LayoutParams.PARENT_ID;

        params.setMargins(
                isBot ? 16 : 64,
                4,
                isBot ? 64 : 16,
                4
        );

        holder.messageText.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return messages != null ? messages.size() : 0;
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        final TextView messageText;

        ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
        }
    }
}