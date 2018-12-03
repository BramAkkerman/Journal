package com.example.brama.journal;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void chooseMood(View v) {
        switch (v.getId()) {
            case R.id.mood1:
                mood = "love_lovely";
                break;
            case R.id.mood2:
                mood = "smile";
                break;
            case R.id.mood3:
                mood = "icon";
                break;
            case R.id.mood4:
                mood = "sick_ill_trouble";
                break;
        }
    }

    public void submitEntry(View v) {
        TextView title = findViewById(R.id.inputTitle);
        String titleText = title.getText().toString();
        TextView content = findViewById(R.id.inputContent);
        String contentText = content.getText().toString();

        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        JournalEntry newEntry = new JournalEntry(titleText, contentText, mood);
        Log.d("blabla","hier");
        db.insert(newEntry);
        InputActivity.this.finish();
    }
}
