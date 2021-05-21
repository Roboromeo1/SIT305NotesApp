package com.example.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button button_create;
    Button button_show;
    ImageView image_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_create = (Button) findViewById(R.id.buttonCreate);
        button_show = (Button) findViewById(R.id.buttonShow);
        image_home = (ImageView) findViewById(R.id.imageHome);
    }

    public void onClickCreate(View v) {
        Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
        startActivity(intent);  // Start CreateNoteActivity
    }

    public void onClickShow(View v) {
        Intent intent = new Intent(MainActivity.this, ShowNotesActivity.class);
        startActivity(intent);  // Start CreateNoteActivity
    }
}
