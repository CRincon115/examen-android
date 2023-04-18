package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputNote;
    private ListView noteList;
    private ArrayAdapter<Note> noteAdapter;
    private List<Note> notes = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputNote = findViewById(R.id.input_note);
        noteList = findViewById(R.id.note_list);
        noteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        noteList.setAdapter(noteAdapter);
        databaseHelper = new DatabaseHelper(this);
        loadNotes();
    }

    public void addNoteOnClick(View view) {
        String content = inputNote.getText().toString();
        if (!content.isEmpty()) {
            Note note = new Note("", content);
            databaseHelper.addNote(note);
            notes.add(note);
            noteAdapter.notifyDataSetChanged();
            inputNote.setText("");
        }
    }

    private void loadNotes() {
        notes.clear();
        notes.addAll(databaseHelper.getAllNotes());
        noteAdapter.notifyDataSetChanged();
    }
}


