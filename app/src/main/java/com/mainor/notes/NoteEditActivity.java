package com.mainor.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mainor.notes.entities.Note;
import com.mainor.notes.persistence.AppDatabase;
import com.mainor.notes.persistence.NoteDao;
import com.mainor.notes.persistence.NotePersistence;
//import com.mainor.notes.persistence.NotePersistence;

import org.joda.time.DateTime;

public class NoteEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        final Context context = this;
        final Note newNote = new Note();
        newNote.setCreationDate(DateTime.now().getMillis());

        final EditText titleEditText = findViewById(R.id.noteEditActivity_et_title);
        TextView dateTextView = findViewById(R.id.noteEditActivity_et_creationDate);
        final EditText contentEditText = findViewById(R.id.noteEditActivity_et_content);
        ImageButton fab = findViewById(R.id.fab);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();
     final   NoteDao noteDao = database.noteDao();
      final   Note note = new Note();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Bundle extras = new Bundle();
                 String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                extras.putString("EXTRA_TITLE",title);
                extras.putString("EXTRA_CONTENT",content);
                note.setTitle(titleEditText.getText().toString());
                note.setContent(contentEditText.getText().toString());
                note.setCreationDate(DateTime.now().getMillis());
                noteDao.insert(note);
                Runnable target = new Runnable() {
                    @Override
                    public void run() {
                        NotePersistence.getDb(view.getContext()).noteDao().insert(note);
                    }
                };
                Thread t = new Thread(target);
                t.start();
                startActivity(new Intent(context, NoteViewActivity.class).putExtras(extras));

            }
        });
    }


}
