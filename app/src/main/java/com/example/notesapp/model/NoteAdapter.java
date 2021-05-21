package com.example.notesapp.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notesapp.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private List<com.example.notesapp.model.Note> noteList;
    private Context context;
    private OnRowClickListener listener;

    public NoteAdapter(List<Note> noteList, Context context, OnRowClickListener listener) {
        this.noteList = noteList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnRowClickListener {
        void onItemClick (int position);
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.note, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textview_note.setText(noteList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textview_note;
        public OnRowClickListener listener;

        public ViewHolder(@NonNull View itemView, OnRowClickListener listener) {
            super(itemView);
            textview_note = (TextView)itemView.findViewById(R.id.textViewNote);
            this.listener = listener;

            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

}
