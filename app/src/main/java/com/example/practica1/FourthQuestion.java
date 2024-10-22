package com.example.practica1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourthQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int[] optionSelected = {0, 0, 0, 0};
    private int score;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CheckBox option1;
    CheckBox option2;
    CheckBox option3;
    CheckBox option4;

    public FourthQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourthQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthQuestion newInstance(String param1, String param2) {
        FourthQuestion fragment = new FourthQuestion();
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
        View view = inflater.inflate(R.layout.fragment_fourth_question, container, false);

        if (getArguments() != null) {
            score = getArguments().getInt("score"); // Obtener el entero
        }

        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText("¿Cuál de los siguientes fueron dioses en la mitología egipcia?");


        option1 = view.findViewById(R.id.checkBox);
        option2 = view.findViewById(R.id.checkBox2);
        option3 = view.findViewById(R.id.checkBox3);
        option4 = view.findViewById(R.id.checkBox4);

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

        option1.setText("Zeus");
        option2.setText("Osiris");
        option3.setText("Atenea");
        option4.setText("Neptuno");

        TextView feedbackText = view.findViewById(R.id.correctionText);

        Button back_btn = view.findViewById(R.id.buttonCheck);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean goodAnswer = checkAnswer();
                if (goodAnswer){
                    feedbackText.setText("Correcto");
                    feedbackText.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                } else {
                    feedbackText.setText("Incorrecto");
                    feedbackText.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }
        });

        option1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    optionSelected[0] = 0;
                }else{
                    optionSelected[0] = 1;
                }
            }
        });

        option2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    optionSelected[1] = 0;
                }else{
                    optionSelected[1] = 1;
                }
            }
        });

        option3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    optionSelected[2] = 0;
                }else{
                    optionSelected[2] = 1;
                }
            }
        });

        option4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    optionSelected[3] = 0;
                }else{
                    optionSelected[3] = 1;
                }
            }
        });

        Button next_btn =  view.findViewById(R.id.buttonNext);
        next_btn.setEnabled(false);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });



        Button check_button = view.findViewById(R.id.buttonCheck);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean good_answer = checkAnswer();
                if(good_answer){
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

    private boolean checkAnswer(){
        if (optionSelected[1] == 1 || optionSelected[3] == 1){
            return false;
        } else if (optionSelected[0] == 1 && optionSelected[2] == 1) {
            return true;
        } else{
            return false;
        }
    }
}