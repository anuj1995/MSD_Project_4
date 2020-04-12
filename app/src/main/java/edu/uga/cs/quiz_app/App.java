package edu.uga.cs.quiz_app;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.uga.cs.quiz_app.database.QuizAppData;
import edu.uga.cs.quiz_app.datamodel.CountryContinent;
import edu.uga.cs.quiz_app.datamodel.CountryNeighbours;

public class App extends Application {

    QuizAppData quizAppData = null;
    @Override
    public void onCreate() {
        if(!checkDataBaseExistence(this.getDatabasePath("quizapp.db").getPath())){
            quizAppData = new QuizAppData(this);
            new CountryContinentWriter().execute(this);
            new CountryNeighboursWriter().execute(this);
        }
        super.onCreate();
    }


    private boolean checkDataBaseExistence(String DBPath) {
        File dbFile = new File(DBPath);
        return dbFile.exists();
    }



    private class CountryContinentWriter extends AsyncTask<Context,Void,Void>{

        @Override
        protected Void doInBackground(Context... context) {
            android.os.Debug.waitForDebugger();
            CountryContinent countryContinent = new CountryContinent();
            final String TAG = "ContinentCSVReading";
            try {
                quizAppData.open();
                InputStream in_s = context[0].getResources().openRawResource( R.raw.country_continent );
                CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
                String [] nextLine;
                while( ( nextLine = reader.readNext() ) != null ) {
                    countryContinent.setCountry(nextLine[0]);
                    countryContinent.setContinent(nextLine[1]);
                    quizAppData.loadCountryContinent(countryContinent);
                }

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
            Log.d( DEBUG_TAG, " CountryContinent saved");
            quizAppData.close();
        }
    }

    private class CountryNeighboursWriter extends AsyncTask<Context,Void,Void>{

        @Override
        protected Void doInBackground(Context... contexts) {
            CountryNeighbours countryNeighbours = new CountryNeighbours();
            final String TAG = "NeighboursCSVReading";
            try {
                quizAppData.open();
                InputStream in_s = getResources().openRawResource( R.raw.country_neighbors );
                CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
                String [] nextLine;
                while( ( nextLine = reader.readNext() ) != null ) {
                    countryNeighbours.setCountry(nextLine[0]);
                    StringBuilder neighbours = new StringBuilder();
                    int x = 1;
                    for(x = 1; x < nextLine.length; x++){
                        if (!nextLine[x].isEmpty()){
                            neighbours.append(nextLine[x]);
                            neighbours.append(",");
                        }
                    }
                    if(neighbours.length() > 2){
                        neighbours.deleteCharAt(neighbours.length()-1);
                    }

                    countryNeighbours.setNeighbours(neighbours.toString());
                    quizAppData.loadCountryNeighbours(countryNeighbours);
                }

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
            Log.d( DEBUG_TAG, " CountryNeighbour saved");
            quizAppData.close();
        }
    }

}
