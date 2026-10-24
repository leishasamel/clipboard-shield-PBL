package com.clipboardshield.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Simple UI prompting user to grant Notification Access
        Button btnPermission = new Button(this);
        btnPermission.setText("Grant Notification Access");
        btnPermission.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
            Toast.makeText(this, "Enable Clipboard Shield access", Toast.LENGTH_LONG).show();
        });
        
        setContentView(btnPermission);
    }
}