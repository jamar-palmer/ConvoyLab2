package edu.temple.convoy;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseService extends FirebaseMessagingService {

    private LocalBroadcastManager broadcaster;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

       // broadcaster = LocalBroadcastManager.getInstance(this);
        //Intent intent = new Intent();
        //intent.putExtra("location", remoteMessage.getData().toString());
        //broadcaster.sendBroadcast(intent);



        Log.d("TAG", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {



//json
            String json = "{\"action\":\"UPDATE\", \"data\":[{\"username\":\"user1\", \"firstname\":\"firstname1\", \"lastname\":\"lastname1\", \"latitude\":72.3456, \"longitude\":125.345356}, {\"username\":\"user2\", \"firstname\":\"firstname2\", \"lastname\":\"lastname2\", \"latitude\":72.4434, \"longitude\":125.27543}, {\"username\":\"user3\", \"firstname\":\"firstname3\", \"lastname\":\"lastname3\", \"latitude\":72.42434, \"longitude\":125.25683}]}";

            try {
                JSONObject jobj =  new JSONObject(json);
                String jsonConvert = jobj.getString("data");
                JSONArray jsonArray = new JSONArray(jsonConvert);
                for(int i = 0; i < jsonArray.length(); i++){
                    String jsonArrayConvert = jsonArray.getString(i);
                    JSONObject jobjDetail =  new JSONObject(jsonArrayConvert);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("TAG3", "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                //  scheduleJob();
            } else {
                // Handle message within 10 seconds
                // handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("TAG2", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d("TAG21", "Message Notification title: " + remoteMessage.getNotification().getTitle());
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.d("TAG", "token: " + s);
        //upload token to server

    }
}
