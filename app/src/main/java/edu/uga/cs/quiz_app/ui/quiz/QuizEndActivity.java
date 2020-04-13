package edu.uga.cs.quiz_app.ui.quiz;


import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.database.QuizAppData;
import edu.uga.cs.quiz_app.datamodel.CountryContinent;
import edu.uga.cs.quiz_app.datamodel.QuizResults;

public class QuizEndActivity extends AppCompatActivity {
    QuizAppData quizAppData = null;
    public String TAG = "010101";
    HashMap<Integer,Integer> continentMap;
    HashMap<Integer,Integer> neighbourMap;
    ScoreViewModel scoreViewModel = null;
    int score = 0;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_quiz_end);
        scoreViewModel = ScoreViewModel.getInstance();
        quizAppData = new QuizAppData(this);
        TextView scoreView = findViewById(R.id.score);
        continentMap = scoreViewModel.getContinentScore();
        neighbourMap = scoreViewModel.getNeighbourScore();
        for(int i =0 ; i<6 ; i++ ){
          if(continentMap.get(i).equals( neighbourMap.get(i))){
              score++;
          }
        }
        scoreView.setText(score + "/6" );
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        new resultWriter().execute(new QuizResults("",currentDate,String.valueOf(score)));


    }

    private class resultWriter extends AsyncTask<QuizResults,Void,Void> {

        @Override
        protected Void doInBackground(QuizResults... results) {
            android.os.Debug.waitForDebugger();
            CountryContinent countryContinent = new CountryContinent();
            final String TAG = "resultWriting";
            try {
                quizAppData.open();
                quizAppData.storeResults(results[0]);

            }
            catch (Exception e){
                Log.e(TAG,e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            final String DEBUG_TAG = "onPostExecute";
            Log.d( DEBUG_TAG, " Result saved");
            quizAppData.close();
        }
    }

}
