package com.example.brama.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

// This adapter fills the journal list on the main page
public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        String date = cursor.getString(cursor.getColumnIndex("date"));

        TextView titleText = view.findViewById(R.id.titleText);
        ImageView moodEmoji = view.findViewById(R.id.moodEmoji);
        TextView dateText = view.findViewById(R.id.dateText);

        titleText.setText(title);
        dateText.setText(date);

        int id = context.getResources().getIdentifier(mood,"drawable", context.getPackageName());
        moodEmoji.setImageResource(id);
    }
}
