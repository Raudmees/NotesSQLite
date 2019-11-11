package com.mainor.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NoteViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        final TextView titleText = findViewById(R.id.noteViewActivity_tv_title);
        final TextView contentText = findViewById(R.id.noteViewActivity_tv_content);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String title = extras.getString("EXTRA_TITLE");
        String content = extras.getString("EXTRA_CONTENT");
        titleText.setText(title);
        contentText.setText(content);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),MainActivity.class));
            }
        });

    }
}
