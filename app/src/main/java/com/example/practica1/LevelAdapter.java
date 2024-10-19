package com.example.practica1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LevelAdapter extends ArrayAdapter<String> {
    private final Context context;

    public LevelAdapter(@NonNull Context context, List<String> levels) {
        super(context, 0, levels);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String level = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_level, parent, false);
        }

        Button levelButton = convertView.findViewById(R.id.levelButton);
        levelButton.setText(level);

        levelButton.setOnClickListener(v -> {
            switch (level) {
                case "Prehistoria":
                    context.startActivity(new Intent(context, PrehistoryActivity.class));
                    break;
                case "Edad Antigua":
                    int[] answers = {-1, -1, -1, -1, -1};
                    Intent ancientIntent = new Intent(context, AncientAgeActivity.class);
                    ancientIntent.putExtra("answers", answers);
                    context.startActivity(ancientIntent);
                    break;

            }
        });

        return convertView;
    }
}