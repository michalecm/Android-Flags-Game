package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    Random random = new Random();
    int[] indexOrder = new int[]{random.nextInt(3), random.nextInt(3), random.nextInt(3)};
    static int randomIndex = 0;
    static String name;
    static int rounds = 0;
    static int score = 0;
    int imgInd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //name = getIntent().getStringExtra("name");

        ImageView countryImg = (ImageView) findViewById(R.id.imageView);

        String[] countries = getResources().getStringArray(R.array.countries);
        ArrayList<String> answerChoices = new ArrayList<>();

        class Pairs {
            int id;
            String name;

            public Pairs(int id, String name) {
                this.id = id;
                this.name = name;
            }
        }
        final ArrayList<Pairs> images = new ArrayList<Pairs>();
        images.add(new Pairs(R.drawable.ec, "Ecuador"));
        images.add(new Pairs(R.drawable.sd, "Sudan"));
        images.add(new Pairs(R.drawable.vn, "Venezuela"));

        Collections.shuffle(images);

        final RadioGroup countryChoices = findViewById(R.id.countryChoices);

        answerChoices.clear();
        answerChoices.add(images.get(indexOrder[randomIndex]).name);
        answerChoices.add(countries[random.nextInt(countries.length)]);
        answerChoices.add(countries[random.nextInt(countries.length)]);
        Collections.shuffle(answerChoices);
        countryImg.setImageResource(images.get(indexOrder[randomIndex]).id);

        for (int i = 0; i < countryChoices.getChildCount(); i++) {
            ((RadioButton) countryChoices.getChildAt(i)).setText(answerChoices.get(i));
        }

        countryChoices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                rounds += 1;
                if(checkedRadioButton.getText().toString().equals(images.get(indexOrder[randomIndex]).name)){
                    score += 1;
                }
                else {
                    Toast m = Toast.makeText(getApplicationContext(), "Wrong! Correct answer: " + images.get(indexOrder[randomIndex]).name + "", Toast.LENGTH_SHORT);
                    m.show();
                }
                if(score >= 3 || rounds >= 3) {
                    finish();
                    Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("score", score);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    score = 0;
                    rounds = 0;
                    return;
                }
                else {
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //intent.putExtra("score", score);
                    //intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });
    }
}
