package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        noteAdapter = new ArrayAdapter<Note>(this, R.layout.list_item_note, R.id.note_title, notes) {
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView titleTextView = view.findViewById(R.id.note_title);
                TextView contentTextView = view.findViewById(R.id.note_content);
                titleTextView.setText(notes.get(position).getTitle());
                contentTextView.setText(notes.get(position).getContent());
                return view;
            }
        };
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


