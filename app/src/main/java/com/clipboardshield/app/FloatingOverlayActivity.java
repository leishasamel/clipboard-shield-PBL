package com.clipboardshield.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class FloatingOverlayActivity extends Activity {
    private static final String TAG = "FloatingOverlay";
    // Target IP address of your local PC running ShieldServer
    private static final String BACKEND_URL = "http://192.168.0.106:8080/api/v1/shield/verify";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);

        TextView msgText = findViewById(R.id.overlay_message);
        Button btnApprove = findViewById(R.id.btn_approve);
        Button btnCancel = findViewById(R.id.btn_cancel);

        // Retrieve message sent from NotificationService
        String notificationText = getIntent().getStringExtra("notification_text");
        if (notificationText != null) {
            msgText.setText(notificationText);
        }

        btnApprove.setOnClickListener(v -> {
            sendDecisionToBackend("APPROVED", notificationText);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            sendDecisionToBackend("CANCELLED", notificationText);
            finish();
        });
    }

    private void sendDecisionToBackend(String decision, String message) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String jsonPayload = String.format("{\"decision\":\"%s\", \"message\":\"%s\"}", decision, message != null ? message : "");
        RequestBody body = RequestBody.create(jsonPayload, JSON);

        Request request = new Request.Builder()
                .url(BACKEND_URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Failed to send decision to backend", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Backend responded successfully");
                } else {
                    Log.e(TAG, "Backend returned error: " + response.code());
                }
                response.close();
            }
        });
    }
}