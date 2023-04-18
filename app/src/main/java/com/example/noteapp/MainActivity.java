package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputNote, inputTitle;
    private ListView noteList;
    private ArrayAdapter<Note> noteAdapter;
    private List<Note> notes ;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputNote = findViewById(R.id.input_note);
        inputTitle = findViewById(R.id.input_title);
        noteList = findViewById(R.id.note_list);
        notes = new ArrayList<>();
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

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la nota seleccionada
                Note notaSeleccionada = notes.get(position);
                String title = notaSeleccionada.getTitle();
                String content = notaSeleccionada.getContent();


                // Crear un Intent para iniciar la actividad NotaCompletaActivity
                Intent intent = new Intent(MainActivity.this, NotaCompletaActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
    }


    public void addNoteOnClick(View view) {
        String title = inputTitle.getText().toString();
        String content = inputNote.getText().toString();

        if (!content.isEmpty() && !title.isEmpty()) {
            Note note = new Note(title, content);
            databaseHelper.addNote(note);
            // Agregar la nota a la lista de notas
            notes.add(note);

            // Notificar al adaptador para que se actualice la vista del ListView
            noteAdapter.notifyDataSetChanged();

            // Limpiar los campos de texto
            inputNote.setText("");
            inputTitle.setText("");

            Toast.makeText(MainActivity.this, "Nota guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Por favor ingrese un título y contenido", Toast.LENGTH_SHORT).show();

        }
    }

    private void loadNotes() {
        notes.clear();
        notes.addAll(databaseHelper.getAllNotes());
        noteAdapter.notifyDataSetChanged();
    }



}