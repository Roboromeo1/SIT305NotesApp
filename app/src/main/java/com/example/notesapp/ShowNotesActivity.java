package com.example.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;
import com.example.notesapp.model.NoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowNotesActivity extends AppCompatActivity implements NoteAdapter.OnRowClickListener{
    private RecyclerView notes_recycler;
    private NoteAdapter notesAdapter;
    private List<Note> notes_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        DatabaseHelper db = new DatabaseHelper(this);
        notes_list = db.fetchNotes();

        notes_recycler = findViewById(R.id.notesRecycler);
        notesAdapter = new NoteAdapter(notes_list, ShowNotesActivity.this.getApplicationContext(), this);
        notes_recycler.setAdapter(notesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notes_recycler.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ShowNotesActivity.this, UpdateNoteActivity.class);
        intent.putExtra("current_note_name", notes_list.get(position).getName());
        intent.putExtra("current_note_content", notes_list.get(position).getContent());
        startActivity(intent);
        finish();
    }
}
