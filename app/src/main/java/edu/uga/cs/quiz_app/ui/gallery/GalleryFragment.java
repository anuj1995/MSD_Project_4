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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.database.QuizAppData;
import edu.uga.cs.quiz_app.datamodel.QuizResults;

public class GalleryFragment extends Fragment {


    private GalleryViewModel galleryViewModel;
    private QuizAppData quizAppData;
    private List<QuizResults> resultsList;
    private QuizResultsReader reader;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    public TableRow getNewEntry(Context context, String user, String date, String score) {
        QuizHistoryTableRow row = new QuizHistoryTableRow(context, user, date, score);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        return row;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View root = getLayoutInflater().inflate(R.layout.fragment_gallery, null, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.quiz_history_table);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        quizAppData = new QuizAppData(getContext());
        new QuizResultsReader().execute();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.quiz_history_table);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);



        return root;
    }

    private class QuizResultsReader extends AsyncTask<Void,Void, List<QuizResults>> {

        @Override
        protected List<QuizResults> doInBackground(Void... params) {
            android.os.Debug.waitForDebugger();
            String TAG = "QuizResults reader";
            try {
                quizAppData.open();
                resultsList = quizAppData.retrieveAllQuizResults();
            }
            catch (Exception e){
                Log.e(TAG,e.toString());
            }

            return resultsList;
        }

        @Override
        protected void onPostExecute(List<QuizResults> resultsList) {
            super.onPostExecute(resultsList);
            final String DEBUG_TAG = "onPostExecute";

            QuizResults test = new QuizResults("user", "date", "score");
            resultsList.add(test);

            recyclerAdapter = new QuizResultsRecyclerAdapter(resultsList);
            recyclerView.setAdapter(recyclerAdapter);


            quizAppData.close();
            Log.d( DEBUG_TAG, " QuizResults retrieved");

        }
    }

}