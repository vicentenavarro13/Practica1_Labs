package com.example.practica1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnswersViewModel extends ViewModel {
    private final MutableLiveData<int[]> answers = new MutableLiveData<>();

    // Método para establecer el array
    public void setArray(int[] array) {
        answers.setValue(array);
    }

    // Método para obtener el array
    public LiveData<int[]> getArray() {
        return answers;
    }

    public void actualizarElemento(int index, int nuevoValor) {
        int[] arrayActual = answers.getValue(); // Obtener el array actual
        if (arrayActual != null && index >= 0 && index < arrayActual.length) {
            arrayActual[index] = nuevoValor; // Actualizar el valor
            answers.setValue(arrayActual); // Notificar que el valor ha cambiado
        }
    }
}
