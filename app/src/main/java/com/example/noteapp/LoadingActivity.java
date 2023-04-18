package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.QuickViewConstants;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.noteapp.databinding.ActivityLoadingBinding;


public class LoadingActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 3000; // Tiempo en milisegundos para mostrar la pantalla de presentación
    private Handler mHandler; // Manejador para postergar la tarea
    ActivityLoadingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this).load(R.drawable.notes).into(binding.NotesImage);

        mHandler = new Handler(); // Crear un nuevo manejador

        // Postergar la tarea de transición a la actividad principal después de un tiempo determinado
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Iniciar la actividad principal y cerrar la actividad de presentación
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Limpiar el manejador para evitar fugas de memoria
        mHandler.removeCallbacksAndMessages(null);
    }
}