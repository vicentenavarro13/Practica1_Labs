package com.example.practica1;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        Button reviewButton = findViewById(R.id.exitButton);

        // Obtener la puntuación desde el Intent
        Intent intent = getIntent();

        int score = intent.getIntExtra("score", 0);

        scoreTextView.setText("Puntuación final: " + score);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, LevelsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
