package com.example.brama.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// This activity is used for making new Journal entries
public class InputActivity extends AppCompatActivity {

    private String mood;
    private ImageView lastClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    // This handles the choosing of a mood
    public void chooseMood(View v) {
        if (lastClicked != null)
            lastClicked.setBackgroundResource(R.color.colorPrimary);
        switch (v.getId()) {
            case R.id.mood1:
                mood = "love_lovely";
                lastClicked = findViewById(R.id.mood1);
                v.setBackgroundResource(R.color.colorPrimaryDark);
                break;
            case R.id.mood2:
                mood = "smile";
                lastClicked = findViewById(R.id.mood2);
                v.setBackgroundResource(R.color.colorPrimaryDark);
                break;
            case R.id.mood3:
                mood = "icon";
                lastClicked = findViewById(R.id.mood3);
                v.setBackgroundResource(R.color.colorPrimaryDark);
                break;
            case R.id.mood4:
                mood = "sick_ill_trouble";
                lastClicked = findViewById(R.id.mood4);
                v.setBackgroundResource(R.color.colorPrimaryDark);
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
