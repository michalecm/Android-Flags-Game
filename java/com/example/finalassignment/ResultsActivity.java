package com.example.finalassignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ResultsActivity extends AppCompatActivity {
    private static final String PREF = "com.example.finalassignment.PREF";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView nameTV2 = findViewById(R.id.nameTV222);
        TextView pointsTV = findViewById(R.id.pints);
        pointsTV.setText(getIntent().getIntExtra("score", 0) + "/3");
        nameTV2.setText(getIntent().getStringExtra("name"));
        Random rand = new Random();
        long id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREF, MODE_PRIVATE);
        sharedPreferences.edit().putInt(getIntent().getStringExtra("name"), sharedPreferences.getInt(getIntent().getStringExtra("name"), 0) + 1).apply();
        String data = getIntent().getStringExtra("name") + "$" + getIntent().getIntExtra("score", 0) + "$" + formatter.format(date) + "$" + sharedPreferences.getInt(getIntent().getStringExtra("name"), 0);
        sharedPreferences.edit().putString("GAME"+Long.toString(id), data).apply();
    }

    public void onClickReplay(View view) {
        finish();
        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        return;
    }
}
