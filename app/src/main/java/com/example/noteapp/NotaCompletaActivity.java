package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotaCompletaActivity extends AppCompatActivity {

    private TextView textViewTitulo;
    private TextView textViewContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_completa);

        // Obtener referencias a los TextViews
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewContenido = findViewById(R.id.textViewContenido);

        // Obtener la nota seleccionada del Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        textViewTitulo.setText(title);
        textViewContenido.setText(content);
    }

}