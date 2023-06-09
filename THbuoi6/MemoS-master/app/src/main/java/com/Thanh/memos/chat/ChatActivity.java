package com.Thanh.memos.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Thanh.memos.R;
import com.Thanh.memos.adapter.ChatAdapter;
import com.Thanh.memos.model.ChatModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    CircleImageView imageView;
    TextView name, status;
    EditText chatET;
    ImageView sendBtn;
    RecyclerView recyclerView;
    FirebaseUser user;
    ChatAdapter adapter;
    List<ChatModel> list;
    String  chatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();
        loadUserData();
        loadMessages();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = chatET.getText().toString().trim();
                if (message.isEmpty()){
                    return;
                }

                CollectionReference reference = FirebaseFirestore.getInstance().collection("Messages");

                Map<String, Object> map = new HashMap<>();

                map.put("lastMessage", message);
                map.put("time", FieldValue.serverTimestamp());

                reference.document(chatID).update(map);

                String messageID = reference
                        .document(chatID)
                        .collection("Messages")
                        .document()
                        .getId();

                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("id", messageID);
                messageMap.put("message", message);
                messageMap.put("senderID", user.getUid());
                messageMap.put("time", FieldValue.serverTimestamp());

                reference.document(chatID).collection("Messages").document(messageID).set(messageMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    chatET.setText("");
                                }else {
                                    Toast.makeText(ChatActivity.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    void init(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        imageView = findViewById(R.id.profileImage);
        name = findViewById(R.id.nameTV);
        status = findViewById(R.id.statusTV);
        chatET = findViewById(R.id.chatET);
        sendBtn = findViewById(R.id.sendBtn);

        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        adapter = new ChatAdapter(this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    void loadUserData(){
        String oppositeUID = getIntent().getStringExtra("uid");

        FirebaseFirestore.getInstance().collection("Users").document(oppositeUID)
                .addSnapshotListener((value, error) -> {
                    if (error != null)
                        return;
                    if (value == null)
                        return;
                    if (!value.exists())
                        return;
                    //check user online or offline
                    boolean isOnline = value.getBoolean("online");
                    status.setText(isOnline ? "Online" : "Offline");

                    int statusColor = isOnline ? Color.GREEN : Color.GRAY;
                    status.setTextColor(statusColor);

                    Glide.with(getApplicationContext()).load(value.getString("profileImage")).into(imageView);
                    name.setText(value.getString("name"));
                });
    }

    void loadMessages(){

        chatID = getIntent().getStringExtra("id");


        CollectionReference reference = FirebaseFirestore.getInstance()
                .collection("Messages")
                .document(chatID)
                .collection("Messages");

        reference
                .orderBy("time", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null)
                    return;
                if (value == null || value.isEmpty())
                    return;
                list.clear();
                for (QueryDocumentSnapshot snapshot : value){
                    ChatModel model = snapshot.toObject(ChatModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus(true);
    }

    @Override
    protected void onPause() {
        updateStatus(false);
        super.onPause();
    }

    void updateStatus(boolean status){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            Map<String, Object> map = new HashMap<>();
            map.put("online", status);

            FirebaseFirestore.getInstance()
                    .collection("Users")
                    .document(userId)
                    .update(map);
        } else {
            // Handle the situation when the FirebaseUser object is null
        }
    }
}