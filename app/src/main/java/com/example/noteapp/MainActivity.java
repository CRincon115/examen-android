package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
=======
>>>>>>> 5791ce39cf18770c05ccb1d2367a4f10106f12b5
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.AdapterView;
=======
>>>>>>> 5791ce39cf18770c05ccb1d2367a4f10106f12b5
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> 5791ce39cf18770c05ccb1d2367a4f10106f12b5

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
<<<<<<< HEAD
        notes = new ArrayList<>();
=======
>>>>>>> 5791ce39cf18770c05ccb1d2367a4f10106f12b5
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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la nota seleccionada
                String notaSeleccionada = String.valueOf(notes.get(position));

                // Crear un Intent para iniciar la actividad NotaCompletaActivity
                Intent intent = new Intent(MainActivity.this, NotaCompletaActivity.class);
                intent.putExtra("nota", notaSeleccionada); // Pasa la nota seleccionada como extra en el Intent
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

    private void mostrarNotaCompleta(String nota) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nota completa")
                .setMessage(nota)
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}