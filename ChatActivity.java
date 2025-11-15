package com.ozcelik05.tikapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private EditText messageInput;
    private ImageButton sendButton;
    private List<ChatMessage> messageList;
    private ChatAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        
        initializeChat();
    }
    
    private void initializeChat() {
        recyclerView = findViewById(R.id.recyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        
        messageList = new ArrayList<>();
        adapter = new ChatAdapter(messageList);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        
        sendButton.setOnClickListener(v -> sendMessage());
    }
    
    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (!message.isEmpty()) {
            ChatMessage chatMessage = new ChatMessage(message, "sen", new Date(), "text");
            messageList.add(chatMessage);
            adapter.notifyDataSetChanged();
            messageInput.setText("");
            recyclerView.smoothScrollToPosition(messageList.size() - 1);
        }
    }
}

class ChatMessage {
    String text;
    String sender;
    Date timestamp;
    String type;
    
    public ChatMessage(String text, String sender, Date timestamp, String type) {
        this.text = text;
        this.sender = sender;
        this.timestamp = timestamp;
        this.type = type;
    }
}
