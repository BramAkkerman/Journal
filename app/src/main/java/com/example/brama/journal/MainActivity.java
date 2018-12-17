package com.example.brama.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

// The main screen of the Journal app showing all Journal entries
public class MainActivity extends AppCompatActivity {

    EntryDatabase db;
    EntryAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView journalList = findViewById(R.id.entryList);

        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(getApplicationContext(),db.selectAll());
        journalList.setAdapter(adapter);

        journalList.setOnItemClickListener(new onEntryClick());

        journalList.setOnItemLongClickListener(new onEntryLongClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    public void addEntry(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // When a user long clicks on an entry, a dialog box pops up to ask if the user really wants
    // to delete the entry
    public void deleteEntry(View v) {
        int dbid = cursor.getInt(cursor.getColumnIndex("_id"));
        db.delete(dbid);
        updateData();
        findViewById(R.id.dialogBox).setVisibility(View.INVISIBLE);
    }

    // If the user decides not to delete the entry
    public void dontDelete(View v) {
        findViewById(R.id.dialogBox).setVisibility(View.INVISIBLE);
    }

    // Whenever an entry is deleted, update the screen
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    // When clicked on an entry
    private class onEntryClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));

            intent.putExtra("title", title);
            intent.putExtra("mood", mood);
            intent.putExtra("content", content);
            intent.putExtra("date", date);

            startActivity(intent);
        }
    }

    // When long clicked on an entry
    private class onEntryLongClick implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            findViewById(R.id.dialogBox).setVisibility(View.VISIBLE);
            return true;
        }
    }
}
