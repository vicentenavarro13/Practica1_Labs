package com.example.practica1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
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
    private int optionSelected = -1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        View view = inflater.inflate(R.layout.fragment_first_question, container, false);

        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText("¿Cuál de los siguientes fue un dios importante en la mitología egipcia?");


        RadioButton option1 = view.findViewById(R.id.radio_1);
        RadioButton option2 = view.findViewById(R.id.radio_2);
        RadioButton option3 = view.findViewById(R.id.radio_3);
        RadioButton option4 = view.findViewById(R.id.radio_4);
        Button exitButton = view.findViewById(R.id.exit);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LevelsActivity.class);
                startActivity(intent);
            }
        });

        option1.setText("Zeus");
        option2.setText("Osiris");
        option3.setText("Ares");
        option4.setText("Neptuno");

        AnswersViewModel viewModel = new ViewModelProvider(requireActivity()).get(AnswersViewModel.class);
        viewModel.getArray().observe(getViewLifecycleOwner(), new Observer<int[]>() {
            @Override
            public void onChanged(int[] answers) {
                if (answers != null) {
                    // Acceder a una posición específica, por ejemplo, la posición 2
                    int option = answers[3];
                    switch (option){
                        case 1:
                            option1.setChecked(true);
                            break;
                        case 2:
                            option2.setChecked(true);
                            break;
                        case 3:
                            option3.setChecked(true);
                            break;
                        case 4:
                            option4.setChecked(true);
                            break;
                    }
                }
            }
        });

        Button back_btn = view.findViewById(R.id.buttonBack);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.actualizarElemento(3,optionSelected);
                Navigation.findNavController(view).navigate(R.id.thirdQuestion);
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