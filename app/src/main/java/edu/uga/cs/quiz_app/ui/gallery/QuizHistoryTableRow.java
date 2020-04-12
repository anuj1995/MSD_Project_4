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
        userView.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        userView.setText(user);
        userView.setTextColor(Color.BLACK);
        userView.setTextSize(22);

        TextView dateView = new TextView(context);
        dateView.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        dateView.setText(date);
        dateView.setTextColor(Color.BLACK);
        dateView.setTextSize(22);

        TextView scoreView = new TextView(context);
        scoreView.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        scoreView.setText(score);
        scoreView.setTextColor(Color.BLACK);
        scoreView.setTextSize(22);

        addView(userView);
        addView(dateView);
        addView(scoreView);
    }

}
