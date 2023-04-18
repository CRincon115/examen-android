package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModificarNotaActivity extends AppCompatActivity {
    private EditText inputNote, inputTitle;
    private Note notaSeleccionada;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener la nota seleccionada del Intent
//        Intent intent = getIntent();
        notaSeleccionada = (Note) getIntent().getSerializableExtra("nota");

        // Obtener referencias a los EditText
        inputNote = findViewById(R.id.input_note);
        inputTitle = findViewById(R.id.input_title);

        // Establecer el contenido de los EditText con los datos de la nota seleccionada
        inputTitle.setText(notaSeleccionada.getTitle());
        inputNote.setText(notaSeleccionada.getContent());

        // Obtener una instancia del DatabaseHelper
        databaseHelper = new DatabaseHelper(this);
    }

    public void addNoteOnClick(View view) {
        // Obtener el nuevo título y contenido de la nota
        String newTitle = inputTitle.getText().toString();
        String newContent = inputNote.getText().toString();

        if (!newContent.isEmpty() && !newTitle.isEmpty()) {
            // Actualizar la nota en la base de datos
            notaSeleccionada.setTitle(newTitle);
            notaSeleccionada.setContent(newContent);

            // Regresar a la actividad MainActivity
            Intent intent = new Intent(ModificarNotaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(ModificarNotaActivity.this, "Por favor ingrese un título y contenido", Toast.LENGTH_SHORT).show();
        }
    }
}