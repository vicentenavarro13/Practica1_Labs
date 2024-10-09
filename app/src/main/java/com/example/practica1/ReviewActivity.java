package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {

    private TextView questionText, feedbackText, scoreText;
    private ImageView selectedImageView;
    private Button prevButton, nextButton, backButton;
    private int currentQuestion = 0;
    private int score;

    private String[] questions;
    private Object[] selectedAnswers;
    private Object[] correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        questionText = findViewById(R.id.questionText);
        feedbackText = findViewById(R.id.feedbackText);
        scoreText = findViewById(R.id.scoreText);
        selectedImageView = findViewById(R.id.selectedImageView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        score = intent.getIntExtra("FINAL_SCORE", 0);
        questions = intent.getStringArrayExtra("QUESTIONS");
        selectedAnswers = (Object[]) intent.getSerializableExtra("SELECTED_ANSWERS");
        correctAnswers = (Object[]) intent.getSerializableExtra("CORRECT_ANSWERS");

        // Mostrar la primera pregunta
        loadQuestion();

        // Botón "Anterior"
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion > 0) {
                    currentQuestion--;
                    loadQuestion();
                }
            }
        });

        // Botón "Siguiente"
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion < questions.length - 1) {
                    currentQuestion++;
                    loadQuestion();
                }
            }
        });

        // Botón "Volver a la pantalla de niveles"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent levelsIntent = new Intent(ReviewActivity.this, LevelsActivity.class);
                startActivity(levelsIntent);
                finish();  // Finalizamos la actividad actual
            }
        });
    }

    private void loadQuestion() {
        // Mostrar la pregunta actual
        questionText.setText(questions[currentQuestion]);

        // Mostrar la respuesta seleccionada y la respuesta correcta
        Object selected = selectedAnswers[currentQuestion];
        Object correct = correctAnswers[currentQuestion];

        // Mostrar si la respuesta seleccionada es un texto o una imagen
        if (selected instanceof String) {
            feedbackText.setText("Seleccionaste: " + selected + "\nCorrecto: " + correct);
            selectedImageView.setVisibility(View.GONE);  // Ocultar ImageView si no es una imagen
        } else if (selected instanceof Integer) {
            feedbackText.setText("Seleccionaste una imagen. Correcto: " + correct);
            selectedImageView.setVisibility(View.VISIBLE);  // Mostrar ImageView si es una imagen
            selectedImageView.setImageResource((int) selected);  // Establecer la imagen seleccionada
        }

        // Mostrar la respuesta correcta (si es texto o imagen)
        if (correct instanceof String) {
            feedbackText.append("\nRespuesta Correcta: " + correct);
        } else if (correct instanceof Integer) {
            feedbackText.append("\nRespuesta Correcta: Imagen mostrada");
        }

        // Actualizar la puntuación
        scoreText.setText("Puntuación: " + score);

        // Deshabilitar los botones de navegación si estamos en la primera o última pregunta
        prevButton.setEnabled(currentQuestion > 0);
        nextButton.setEnabled(currentQuestion < questions.length - 1);
    }
}
