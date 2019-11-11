package com.mainor.notes;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mainor.notes.NotesFragment.OnListFragmentInteractionListener;
import com.mainor.notes.entities.Note;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Note} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>  {
    private MyNoteRecyclerViewAdapter mAdapter;




    private final List<Note> mValues;
    List <Note> myDataLists;


    public MyNoteRecyclerViewAdapter(List<Note> items) {
        mValues = items;
      //  mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        final Bundle extras = new Bundle();

        final String title = mValues.get(position).getTitle();
        String content = mValues.get(position).getContent();
        extras.putString("EXTRA_TITLE",title);
        extras.putString("EXTRA_CONTENT",content);

        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getContent());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), holder.mContentView.getText(), Toast.LENGTH_LONG).show();
                v.getContext().startActivity(new Intent(v.getContext(),NoteViewActivity.class).putExtras(extras));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues != null? mValues.size() : 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        public final TextView mTitle;
        public final TextView mContentView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.item_number);
            mTitle = view.findViewById(R.id.note_tv_title);
            mContentView = view.findViewById(R.id.note_tv_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
