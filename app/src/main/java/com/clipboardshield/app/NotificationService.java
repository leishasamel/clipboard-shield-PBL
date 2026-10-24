package com.clipboardshield.app;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null || sbn.getNotification() == null) {
            return;
        }

        Bundle extras = sbn.getNotification().extras;
        if (extras == null) {
            return;
        }

        String title = extras.getString("android.title", "");
        CharSequence textSeq = extras.getCharSequence("android.text");
        String text = (textSeq != null) ? textSeq.toString() : "";

        String combined = (title + " " + text).toLowerCase();

        // Triggers for OTP/verification keywords regardless of app (Truecaller, SMS, WhatsApp)
        if (combined.contains("otp") || combined.contains("code") || combined.contains("verify") || combined.contains("verification")) {
            Intent intent = new Intent(this, FloatingOverlayActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("notification_text", text);
            startActivity(intent);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // No action required on removal
    }
}