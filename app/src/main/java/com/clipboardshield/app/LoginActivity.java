public package com.clipboardshield.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView title = new TextView(this);
        title.setText("C-Shield Login");
        title.setTextSize(24);
        title.setPadding(0, 0, 0, 40);

        EditText username = new EditText(this);
        username.setHint("Username");

        EditText password = new EditText(this);
        password.setHint("Password");
        password.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Button loginBtn = new Button(this);
        loginBtn.setText("Login");
        loginBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Login successful. C-Shield is active in background.", Toast.LENGTH_SHORT).show();
        });

        layout.addView(title);
        layout.addView(username);
        layout.addView(password);
        layout.addView(loginBtn);

        setContentView(layout);
    }
} {
    
}
