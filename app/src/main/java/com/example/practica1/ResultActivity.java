package com.example.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        Button reviewButton = findViewById(R.id.reviewButton);

        // Obtener la puntuación desde el Intent
        Intent intent = getIntent();
        int finalScore = intent.getIntExtra("FINAL_SCORE", 0);
        String[] questions = getIntent().getStringArrayExtra("QUESTIONS");
        String[] selectedAnswers = getIntent().getStringArrayExtra("SELECTED_ANSWERS");
        String[] correctAnswers = getIntent().getStringArrayExtra("CORRECT_ANSWERS");

        scoreTextView.setText("Puntuación final: " + finalScore);

        // Botón para navegar a la pantalla de revisión
        reviewButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(ResultActivity.this, ReviewActivity.class);
            intent1.putExtra("QUESTIONS", questions);
            intent1.putExtra("SELECTED_ANSWERS", selectedAnswers);
            intent1.putExtra("CORRECT_ANSWERS", correctAnswers);
            startActivity(intent1);
        });
    }
}
