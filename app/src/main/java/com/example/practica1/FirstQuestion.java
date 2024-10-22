package com.example.practica1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int optionSelected = -1;
    private int score;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstQuestion newInstance(String param1, String param2) {
        FirstQuestion fragment = new FirstQuestion();
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
        View view = inflater.inflate(R.layout.fragment_first_question, container, false);





        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText("¿Cuál fue el primer emperador de Roma?");


        RadioButton option1 = view.findViewById(R.id.radio_1);
        RadioButton option2 = view.findViewById(R.id.radio_2);
        RadioButton option3 = view.findViewById(R.id.radio_3);
        RadioButton option4 = view.findViewById(R.id.radio_4);
         Button exitButton = view.findViewById(R.id.exit);


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

        option1.setText("Julio Cesar");
        option2.setText("Trajano");
        option3.setText("Augusto");
        option4.setText("Nerón");



        Button next_btn = view.findViewById(R.id.buttonNext);
        next_btn.setEnabled(false);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);

                Navigation.findNavController(view).navigate(R.id.secondQuestion, bundle);
            }
        });

        Button check_button = view.findViewById(R.id.buttonCheck);
        TextView feedbackText = view.findViewById(R.id.correctionText);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionSelected == 3){
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


        option1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionSelected = 1;
            }
        });

        option2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionSelected = 2;
            }
        });

        option3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionSelected = 3;
            }
        });

        option4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionSelected = 4;
            }
        });






        return view;
    }
}