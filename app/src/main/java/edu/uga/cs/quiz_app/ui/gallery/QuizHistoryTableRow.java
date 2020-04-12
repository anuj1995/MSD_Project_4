package edu.uga.cs.quiz_app.ui.gallery;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class QuizHistoryTableRow extends TableRow {

    public QuizHistoryTableRow(Context context, String user, String date, String score){
        super(context);

        TextView userView = new TextView(context);
        userView.setText(user);
        userView.setTextColor(Color.BLACK);
        userView.setTextSize(22);
        userView.setLayoutParams(new LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        TextView dateView = new TextView(context);
        dateView.setText(date);
        dateView.setTextColor(Color.BLACK);
        dateView.setTextSize(22);
        dateView.setLayoutParams(new LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        TextView scoreView = new TextView(context);
        scoreView.setText(score);
        scoreView.setTextColor(Color.BLACK);
        scoreView.setTextSize(22);
        scoreView.setLayoutParams(new LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        addView(userView);
        addView(dateView);
        addView(scoreView);
    }

}
