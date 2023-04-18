package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.QuickViewConstants;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;


import com.bumptech.glide.Glide;
import com.example.noteapp.databinding.ActivityLoadingBinding;


public class LoadingActivity extends AppCompatActivity {

    ActivityLoadingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this).load(R.drawable.notes).into(binding.NotesImage);

        binding.btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}