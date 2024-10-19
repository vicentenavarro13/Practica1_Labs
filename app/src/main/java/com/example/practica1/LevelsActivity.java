package com.example.practica1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LevelsActivity extends AppCompatActivity {

    private boolean isListViewVisible = false;  // Estado de visibilidad del ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Button expandButton = findViewById(R.id.expandButton);
        ListView levelsListView = findViewById(R.id.levelsListView);

        // Crear lista de niveles
        List<String> levels = new ArrayList<>();
        levels.add("Prehistoria");
        levels.add("Edad Antigua");
        levels.add("Edad Media");
        levels.add("Edad Moderna");
        levels.add("Edad Contemporánea");

        // Configurar el adaptador
        LevelAdapter adapter = new LevelAdapter(this, levels);
        levelsListView.setAdapter(adapter);

        // Configurar el botón para mostrar/ocultar el ListView
        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isListViewVisible) {
                    // Si el ListView está visible, lo ocultamos
                    levelsListView.setVisibility(View.GONE);
                    isListViewVisible = false;
                } else {
                    // Si el ListView está oculto, lo mostramos
                    levelsListView.setVisibility(View.VISIBLE);
                    isListViewVisible = true;
                }
            }
        });
    }
}


