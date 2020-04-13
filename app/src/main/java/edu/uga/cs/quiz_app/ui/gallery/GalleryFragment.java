package edu.uga.cs.quiz_app.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.database.QuizAppData;
import edu.uga.cs.quiz_app.datamodel.QuizResults;

public class GalleryFragment extends Fragment {


    private GalleryViewModel galleryViewModel;
    private QuizAppData quizAppData;
    private List<QuizResults> resultsList;
    private TableLayout table;
    private QuizResultsReader reader;

    public TableRow getNewEntry(Context context, String user, String date, String score) {


        QuizHistoryTableRow row = new QuizHistoryTableRow(context, user, date, score);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        return row;
    }
/**
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
*/
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        table = root.findViewById(R.id.quiz_history_table);
        TableRow row = getNewEntry(getContext(), "user", "date", "score");
        table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quizAppData = new QuizAppData(getContext());
        reader = new QuizResultsReader();
        reader.execute(getContext());
    }


    private class QuizResultsReader extends AsyncTask<Context,Void,Void> {

        @Override
        protected Void doInBackground(Context... context) {
            android.os.Debug.waitForDebugger();
            openAppData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            final String DEBUG_TAG = "onPostExecute";
            populateTable();
            Log.d( DEBUG_TAG, " QuizResults retrieved");

        }
    }

    public void openAppData() {
        android.os.Debug.waitForDebugger();
        String TAG = "QuizResults reader";
        try {
            quizAppData.open();
            resultsList = quizAppData.retrieveAllQuizResults();
        }
        catch (Exception e){
            Log.e(TAG,e.toString());
        }
    }

    public void populateTable() {
        String user, date, score;

        for (QuizResults results: resultsList) {
            user = results.getUsername();
            date = results.getDate();
            score = results.getResult();

            TableRow row = getNewEntry(getContext(), user, date, score);
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        TableRow row = getNewEntry(getContext(), "user", "date", "score");
        table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        quizAppData.close();
    }

}