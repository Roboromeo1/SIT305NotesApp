package com.example.notesapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

public class UpdateNoteActivity extends AppCompatActivity {

    DatabaseHelper db;
    String note_name;
    String note_content;
    Note note;
    String updated_name;
    String updated_content;

    EditText update_name;
    EditText update_content;
    Button button_update;
    Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        db = new DatabaseHelper(this);
        update_name = findViewById(R.id.updateName);
        update_content = findViewById(R.id.updateContent);
        button_update = (Button)findViewById(R.id.buttonUpdate);
        button_delete = (Button)findViewById(R.id.buttonDelete);

        note_name = getIntent().getStringExtra("current_note_name");
        note_content = getIntent().getStringExtra("current_note_content");
        Toast.makeText(UpdateNoteActivity.this, "note_name" + note_name, Toast.LENGTH_SHORT).show();
        note = new Note(note_name, note_content);

        update_name.setText(note.getName());
        update_content.setText(note.getContent());
    }

    public void onClickUpdate(View v){

        EditText updated_name_edit = findViewById(R.id.updateName);
        EditText updated_content_edit = findViewById(R.id.updateContent);
        updated_name = updated_name_edit.getText().toString();
        updated_content = updated_content_edit.getText().toString();
        Note updated_note = new Note(updated_name, updated_content);

        boolean done = db.updateNote(updated_note);

        if (done == true) {
            Toast.makeText(UpdateNoteActivity.this, "Complete!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(UpdateNoteActivity.this, "Oops. Something went wrong :(", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickDelete(View v) {
        db.deleteNote(note);
        //Toast.makeText(UpdateNoteActivity.this, "Deleted!" + note.getId(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
