package com.example.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Button prehistory = findViewById(R.id.prehistory);
        prehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, PrehistoryActivity.class);
                startActivity(intent);
            }
        });

        Button ancient_age = findViewById(R.id.buttonAncientAge);

        ancient_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] answers = {-1, -1, -1, -1, -1};
                Intent intent = new Intent(LevelsActivity.this, AncientAgeActivity.class);
                intent.putExtra("answers", answers);
                startActivity(intent);
            }
        });


    }
}
