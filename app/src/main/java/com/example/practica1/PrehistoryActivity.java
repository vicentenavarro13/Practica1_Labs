package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrehistoryActivity extends AppCompatActivity {
    private TextView questionText, feedbackText, scoreText;
    private Button option1, option2, option3, option4, exit;
    private Button confirmButton;
    private ImageView questionImageView;

    private String[] questions = {
            "¿Qué herramienta usaban los primeros seres humanos para cazar?",
            "¿Qué era la Edad de Piedra?",
            "¿Cuándo comenzaron los humanos a practicar la agricultura?",
            "¿Que tipo de arte rupestre se encuentra en Altamira?",
            "¿Cuál es el nombre de la siguiente herramienta usada en la prehistoria?"
    };

    private Object[][] options = {
            {"Lanzas", "Arcos", "Espadas", "Cuchillos"},
            {"Un período de glaciación", "Un periodo de cambio climático", "Un período en el que los humanos usaban piedras para fabricar herramientas", "Un periodo de grandes guerras"},
            {"Hace 500 años", "Hace 10,000 años", "Hace 2,000 años", "Hace 1,000 años"},
            {"Animales pintados en las paredes", "Esculturas talladas en piedra", "Representaciones de figuras humanas", "Escritura jeroglífica"},
            {"Lanza", "Cuchillo de hueso ", "Bifaz", "Raspador de piedra"}
    };

    private Object[] correctAnswers = {
            "Lanzas",
            "Un período en el que los humanos usaban piedras para fabricar herramientas",
            "Hace 10,000 años",
            "Animales pintados en las paredes",
            "Bifaz"
    };

    private int currentQuestion = 0;
    private int score = 0;
    private int selectedOption = -1;

    private Object[] selectedAnswers = new Object[questions.length];

    private int[] questionImages = {
            0, // No hay imagen para la pregunta 1
            0, // No hay imagen para la pregunta 2
            0, // No hay imagen para la pregunta 3
            0, // Hay imagen para la pregunta 4
            R.drawable.piedra  // Hay imagen para la pregunta 5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preshistory);

        questionText = findViewById(R.id.questionText);
        feedbackText = findViewById(R.id.feedbackText);
        scoreText = findViewById(R.id.scoreText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        confirmButton = findViewById(R.id.confirmButton);
        questionImageView = findViewById(R.id.questionImageView);
        exit = findViewById(R.id.exit);

        loadQuestion();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = 0;
                highlightSelectedOption(0);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = 1;
                highlightSelectedOption(1);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = 2;
                highlightSelectedOption(2);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = 3;
                highlightSelectedOption(3);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption == -1) {
                    Toast.makeText(PrehistoryActivity.this, "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show();
                } else {
                    checkAnswer();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(PrehistoryActivity.this, LevelsActivity.class);
                    startActivity(intent);
                }
        });

    }

    private void loadQuestion() {
        // Mostrar la pregunta
        questionText.setText(questions[currentQuestion]);

        // Mostrar la imagen de la pregunta, si hay alguna
        if (questionImages[currentQuestion] != 0) {
            questionImageView.setImageResource(questionImages[currentQuestion]);
            questionImageView.setVisibility(View.VISIBLE);  // Hacer visible la imagen

        } else {
            questionImageView.setVisibility(View.GONE);  // Ocultar la imagen si no hay
        }

        // Configurar las opciones (pueden ser texto o imágenes)
        setOption(option1, options[currentQuestion][0]);
        setOption(option2, options[currentQuestion][1]);
        setOption(option3, options[currentQuestion][2]);
        setOption(option4, options[currentQuestion][3]);

        // Resetear selección de opción
        selectedOption = -1;
        highlightSelectedOption(-1); // Sin selección

        // Limpiar feedback
        feedbackText.setText("");
    }

    private void setOption(Button button, Object option) {

        if (option instanceof Integer) {
            button.setBackgroundResource((int) option);
            button.setText("");  // No texto si es una imagen
        } else {

            button.setBackground(null);
            button.setText(option.toString());
        }
    }

    private void endGame() {
        // Crear el Intent para ir a ReviewActivity
        Intent intent = new Intent(PrehistoryActivity.this, ReviewActivity.class);

        // Convertir las respuestas correctas a un array de String para enviarlas
        String[] correctAnswersArray = new String[correctAnswers.length];
        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i] instanceof String) {
                correctAnswersArray[i] = (String) correctAnswers[i];
            }
        }

        // Convertir las respuestas seleccionadas a String[] para enviarlas en el Intent
        String[] selectedAnswersArray = new String[questions.length];
        for (int i = 0; i < questions.length; i++) {
            if (selectedAnswers[i] instanceof String) {
                selectedAnswersArray[i] = (String) selectedAnswers[i];
            }
        }

        // Pasar la puntuación, preguntas y respuestas seleccionadas/correctas a ReviewActivity
        intent.putExtra("FINAL_SCORE", score);
        intent.putExtra("QUESTIONS", questions);
        intent.putExtra("SELECTED_ANSWERS", selectedAnswersArray);  // Pasar las respuestas seleccionadas
        intent.putExtra("CORRECT_ANSWERS", correctAnswersArray);    // Pasar las respuestas correctas

        // Iniciar la nueva actividad
        startActivity(intent);

        // Finalizar la actividad actual
        finish();
    }

    private void highlightSelectedOption(int selected) {
        // Cambiar la opacidad o el color de los botones según la selección
        option1.setAlpha(selected == 0 ? 0.5f : 1.0f);
        option2.setAlpha(selected == 1 ? 0.5f : 1.0f);
        option3.setAlpha(selected == 2 ? 0.5f : 1.0f);
        option4.setAlpha(selected == 3 ? 0.5f : 1.0f);
    }

    private void checkAnswer() {
        Object selected = options[currentQuestion][selectedOption];
        Object correct = correctAnswers[currentQuestion];

        // Almacenar la respuesta seleccionada por el usuario
        selectedAnswers[currentQuestion] = selected;

        // Verificar si la respuesta seleccionada es correcta
        if (selected.equals(correct)) {
            feedbackText.setText("Correcto!");
            feedbackText.setTextColor(getResources().getColor(android.R.color.holo_green_light));
            score += 3;
        } else {
            feedbackText.setText("Incorrecto");
            feedbackText.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            score -= 2;
        }

        // Mostrar puntuación
        scoreText.setText("Puntuación: " + score);

        // Cargar la siguiente pregunta después de un breve retraso
        confirmButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    loadQuestion();
                } else {
                    feedbackText.setText("Juego terminado. Puntuación final: " + score);
                    confirmButton.setEnabled(false);  // Desactivar el botón al final
                    endGame();  // Llamar a endGame() cuando todas las preguntas hayan sido respondidas
                }
            }
        }, 1000);  // 1 segundo de retraso
    }


}
