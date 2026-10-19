package com.clipboardshield.app;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.content.Intent;
import android.os.Bundle;

public class NotificationService extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title", "");
        String text = extras.getString("android.text", "");

        // Check if message is a financial transaction or OTP
        if (text.contains("debited") || text.contains("credited") || text.contains("OTP") || text.contains("₹")) {
            // Launch floating prompt overlay
            Intent dialogIntent = new Intent(this, FloatingOverlayActivity.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            dialogIntent.putExtra("ALERT_MSG", text);
            startActivity(dialogIntent);
        }
    }
}