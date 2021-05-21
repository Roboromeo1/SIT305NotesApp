package com.example.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

public class CreateNoteActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button button_done;
    EditText editText_name, editText_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        db = new DatabaseHelper(this);

        button_done = (Button)findViewById(R.id.buttonUpdate);
        editText_name = findViewById(R.id.updateName);
        editText_content = findViewById(R.id.updateContent);
    }

    public void onClickDone(View v) {
        String name = editText_name.getText().toString();
        String content = editText_content.getText().toString();

        long result = db.insertNote(new Note(name, content));
        if (result != 0){
            Toast.makeText(CreateNoteActivity.this, "Complete!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(CreateNoteActivity.this, "Oops. Something went wrong :(", Toast.LENGTH_SHORT).show();
        }
    }

}
