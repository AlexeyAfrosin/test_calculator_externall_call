package com.afrosin.testcalculatorexternalapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String KEY_EXTERNAL_INTENT_DIGIT = "external_intent_digit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_send_text = findViewById(R.id.tv_send_text);

        Button bt_send = findViewById(R.id.bt_send);
        bt_send.setOnClickListener(v -> {
            Uri uri = Uri.parse("calculator://intent");
            Intent runEchoIntent = new Intent(Intent.ACTION_VIEW, uri);
            runEchoIntent.putExtra(KEY_EXTERNAL_INTENT_DIGIT, tv_send_text.getText().toString());
            ActivityInfo activityInfo = runEchoIntent.resolveActivityInfo(getPackageManager(),
                    runEchoIntent.getFlags());
            if (activityInfo != null) {
                startActivity(runEchoIntent);
            } else {
                Toast.makeText(this, getBaseContext().getString(R.string.external_app_not_found), Toast.LENGTH_LONG).show();
            }
        });

    }
}