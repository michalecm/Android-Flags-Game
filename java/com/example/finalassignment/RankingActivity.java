package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


public class RankingActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> cities;
    private static final String PREF = "com.example.finalassignment.PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREF, MODE_PRIVATE);
        Map vals = sharedPreferences.getAll();
        ArrayList<String> init = new ArrayList<>();
        for(Object key: vals.keySet()){
            if(key.toString().contains("GAME")) {
                init.add(vals.get(key) + "$" + ((String)key).substring(4));
            }
        }
        class MAL implements Comparable<MAL>{
            String[] data;
            public MAL(String data) {
                this.data = data.split("\\$");
            }
            @Override
            public int compareTo(MAL o) {
                int score1 = Integer.parseInt(this.data[1]);
                int score2 = Integer.parseInt(o.data[1]);
                if(score1 > score2) return 1;
                if(score1 == score2) return 0;
                if(score1 < score2) return -1;
                return 0;
            }

            @Override
            public String toString() {
                return "Name: " + this.data[0] + "\t\t\t\tScore: " + this.data[1] + "\t\t\t\tGames Played: " + this.data[3] + "\nGame ID: " + this.data[4] + "\nDate: " + this.data[2];
            }
        }
        ArrayList<MAL> res = new ArrayList<>();
        for(String x: init)
            res.add(new MAL(x));
        Collections.sort(res, Collections.<MAL>reverseOrder());
        ArrayList<String> y = new ArrayList<>();
        for (MAL mal : res) {
            y.add(mal.toString());
        }

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, y);
        listView.setAdapter(adapter);
    }
}
