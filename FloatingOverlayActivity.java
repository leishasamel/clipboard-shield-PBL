package com.clipboardshield.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FloatingOverlayActivity extends Activity {
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
            // Send authorization payload to your Spring Boot Java Backend
            Toast.makeText(this, "Transaction Approved", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}