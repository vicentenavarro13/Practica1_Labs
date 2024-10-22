package com.example.practica1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int optionSelected = -1;
    private int score;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdQuestion newInstance(String param1, String param2) {
        ThirdQuestion fragment = new ThirdQuestion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_question, container, false);

        if (getArguments() != null) {
            score = getArguments().getInt("score"); // Obtener el entero
        }

        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText("¿Cómo terminó la Edad Antigua según la mayoría de los historiadores?");

        Spinner spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.spinner_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Button exitButton = view.findViewById(R.id.exit);
        TextView feedbackText = view.findViewById(R.id.correctionText);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Acción para Opción 1
                        optionSelected = 1;
                        break;
                    case 1:
                        // Acción para Opción 1
                        optionSelected = 2;
                        break;
                    case 2:
                        // Acción para Opción 2
                        optionSelected = 3;
                        break;
                    case 3:
                        // Acción para Opción 3
                        optionSelected = 4;
                        break;
                    case 4:
                        // Acción para Opción 4
                        optionSelected = 5;
                        break;
            }

        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        exitButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
                builder.setTitle("Confirmación")
                        .setMessage("¿Estás seguro de que quieres salir?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), LevelsActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        Button next_btn = view.findViewById(R.id.buttonNext);
        next_btn.setEnabled(false);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);

                Navigation.findNavController(view).navigate(R.id.fourthQuestion, bundle);
            }
        });

        Button check_button = view.findViewById(R.id.buttonCheck);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionSelected == 2){
                    score += 3;
                    feedbackText.setText("Correcto. Puntuación = " + score);
                    feedbackText.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                } else{
                    score -= 2;
                    feedbackText.setText("Incorrecto. Puntuación = " + score);
                    feedbackText.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                next_btn.setEnabled(true);
                check_button.setEnabled(false);
            }
        });






        return view;
    }
}