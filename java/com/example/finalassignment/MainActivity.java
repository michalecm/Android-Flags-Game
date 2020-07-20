package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameET;
    TextView nameTV;
    Button rankingBT;
    Button playBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = findViewById(R.id.nameET);
        nameTV = findViewById(R.id.nameTV2);
        rankingBT = findViewById(R.id.rankingBT);
        playBT = findViewById(R.id.playBT);

        nameET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE) {
                    nameET.clearFocus();
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(nameET.getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    public void onClickPlay(View view) {
        nameET = findViewById(R.id.nameET);
        if(!nameET.getText().toString().isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("name", nameET.getText().toString());
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.name_error, Toast.LENGTH_LONG).show();
        }
    }

    public void onClickRanking(View view) {
        Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(intent);
    }
}
