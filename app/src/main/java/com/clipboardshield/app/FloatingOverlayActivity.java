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

        String alertText = getIntent().getStringExtra("ALERT_MSG");
        TextView msgText = findViewById(R.id.overlay_message);
        msgText.setText(alertText);

        Button btnApprove = findViewById(R.id.btn_approve);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnApprove.setOnClickListener(v -> {
            sendAuthorizationToBackend("APPROVED", alertText);
            Toast.makeText(this, "Transaction Approved", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            sendAuthorizationToBackend("CANCELLED", alertText);
            Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void sendAuthorizationToBackend(String status, String details) {
        OkHttpClient client = new OkHttpClient();

        String jsonPayload = "{"
                + "\"deviceId\":\"Android_Device_01\","
                + "\"status\":\"" + status + "\","
                + "\"details\":\"" + (details != null ? details.replace("\"", "'") : "") + "\""
                + "}";

        RequestBody body = RequestBody.create(
                jsonPayload, MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BACKEND_URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Failed to reach backend server", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Successfully logged transaction authorization with backend!");
                }
            }
        });
    }
}