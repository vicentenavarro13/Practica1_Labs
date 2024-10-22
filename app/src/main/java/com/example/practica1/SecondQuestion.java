package com.example.practica1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int optionSelected = -1;
    private int score;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondQuestion newInstance(String param1, String param2) {
        SecondQuestion fragment = new SecondQuestion();
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
        View view = inflater.inflate(R.layout.fragment_second_question, container, false);

        if (getArguments() != null) {
            score = getArguments().getInt("score"); // Obtener el entero
        }

        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText("¿Cuál de estas construcciones se encuentra en egipto?");
        TextView feedbackText = view.findViewById(R.id.correctionText);


        Button exitButton = view.findViewById(R.id.exit);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LevelsActivity.class);
                startActivity(intent);
            }
        });



        Button next_btn = view.findViewById(R.id.buttonNext);
        next_btn.setEnabled(false);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("score", score);

                Navigation.findNavController(view).navigate(R.id.thirdQuestion, bundle);
            }
        });

        Button check_button = view.findViewById(R.id.buttonCheck);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionSelected == 4){
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

        ImageButton image1 = view.findViewById(R.id.answer1);
        ImageButton image2 = view.findViewById(R.id.answer2);
        ImageButton image3 = view.findViewById(R.id.answer3);
        ImageButton image4 = view.findViewById(R.id.answer4);


        image1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                optionSelected = 1;
            }
        });

        image2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                optionSelected = 2;
            }
        });

        image3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                optionSelected = 3;
            }
        });

        image4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                optionSelected = 4;
            }
        });







        return view;
    }
}