package edu.uga.cs.quiz_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.uga.cs.quiz_app.datamodel.CountryContinent;
import edu.uga.cs.quiz_app.datamodel.CountryNeighbours;
import edu.uga.cs.quiz_app.datamodel.Quiz;
import edu.uga.cs.quiz_app.datamodel.QuizResults;

public class QuizAppData {

    public static final String DEBUG_TAG = "QuizAppData";

    public SQLiteDatabase db;
    private SQLiteOpenHelper quizAppDBHelper;

    public QuizAppData (Context context){
        this.quizAppDBHelper = QuizAppDBHelper.getInstance(context);
    }

    public void open(){
        db = quizAppDBHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "QuizAppData: db open" );
    }

    // Close the database
    public void close() {
        if( quizAppDBHelper != null ) {
            quizAppDBHelper.close();
            Log.d(DEBUG_TAG, "QuizAppData: db closed");
        }
    }

    public void loadCountryContinent(CountryContinent countryContinent){
        ContentValues values = new ContentValues();
        values.put(QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_COUNTRY,countryContinent.getCountry());
        values.put(QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_CONTINENT,countryContinent.getContinent());

        long id = db.insert( QuizAppDBHelper.TABLE_COUNTRY_CONTINENT, null, values );

        Log.d( DEBUG_TAG, "Stored new Country Continent id: " + id );
    }

    public void loadCountryNeighbours(CountryNeighbours countryNeighbours){
        ContentValues values = new ContentValues();
        values.put(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_COUNTRY,countryNeighbours.getCountry());
        values.put(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS,countryNeighbours.getNeighbours());

        long id = db.insert( QuizAppDBHelper.TABLE_COUNTRY_NEIGHBOURS, null, values );

        Log.d( DEBUG_TAG, "Stored new Country Neighbour with id: " + id );
    }
    public List<CountryContinent> retrieveAllCountyContinents(){
        List<CountryContinent> countryContinents = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query( QuizAppDBHelper.TABLE_COUNTRY_CONTINENT,
                    new String[]{
                            QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_ID,
                            QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_COUNTRY,
                            QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_CONTINENT
                    },
                    null, null, null, null, null );

            if (cursor.getCount() > 0 ){
                while (cursor.moveToNext()){
                    CountryContinent countryContinent= new CountryContinent(
                            cursor.getLong(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_COUNTRY)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_CONTINENT_COLUMN_CONTINENT))
                    );
                    countryContinents.add(countryContinent);

                    Log.d( DEBUG_TAG, "Retrieved JobLead: " + countryContinent );
                }
                Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
            }
        }catch (Exception e){
            Log.e( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return  countryContinents;
    }


    public List<CountryNeighbours> retrieveAllCountyNeighbours(){
        List<CountryNeighbours> countryNeighbours = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(QuizAppDBHelper.TABLE_COUNTRY_NEIGHBOURS,
                    new String[]{
                            QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_ID,
                            QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_COUNTRY,
                            QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS
                    },
                    null, null, null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    CountryNeighbours countryNeighbour = new CountryNeighbours(
                            cursor.getLong(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_COUNTRY)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS))
                    );
                    countryNeighbours.add(countryNeighbour);

                    Log.d(DEBUG_TAG, "Retrieved CountryNeighbour: " + countryNeighbour);
                }
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
            }
        }
        catch (Exception e) {
            Log.e(DEBUG_TAG, "Exception caught: " + e);
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // we should close the cursor
        return  countryNeighbours;
    }



    public void createTableQuiz(){
        db.execSQL(QuizAppDBHelper.CREATE_NEW_QUIZ);
        Log.d( DEBUG_TAG, "Table " + QuizAppDBHelper.TABLE_NEW_QUIZ + " created" );
    }

    public void StoreQuiz(Quiz quiz){
        ContentValues values = new ContentValues();
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_USERNAME,quiz.getUserName());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_DATE,quiz.getDate());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q1,quiz.getQ1());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q2,quiz.getQ2());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q3,quiz.getQ3());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q4,quiz.getQ4());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q5,quiz.getQ5());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q6,quiz.getQ6());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_A1,quiz.getA1());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_A2,quiz.getA2());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_A3,quiz.getA3());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_A4,quiz.getA4());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_A5,quiz.getA5());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_A6,quiz.getA6());
        values.put(QuizAppDBHelper.NEW_QUIZ_COLUMN_RESULT,quiz.getResult());

        long id = db.insert( QuizAppDBHelper.TABLE_NEW_QUIZ, null, values );
        Log.d( DEBUG_TAG, "Stored new Country Neighbour with id: " + id );
    }

    public Quiz retrieveCurrentQuiz(){
        Cursor cursor = null;
        Quiz quiz = null;
        try {
            cursor = db.query( QuizAppDBHelper.TABLE_NEW_QUIZ,
                    new String[]{
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_USERNAME,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_DATE,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_Q1,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_Q2,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_Q3,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_Q4,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_Q5,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_Q6,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_A1,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_A2,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_A3,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_A4,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_A5,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_A6,
                            QuizAppDBHelper.NEW_QUIZ_COLUMN_RESULT,

                    },
                    null, null, null, null, null );

            if (cursor.getCount() > 0 ) {
                while (cursor.moveToNext()) {
                    quiz = new Quiz(
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_USERNAME)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_DATE)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q1)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q2)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q3)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q4)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q5)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_Q6))
                    );
                    quiz.setA1(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_A1)));
                    quiz.setA2(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_A2)));
                    quiz.setA3(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_A3)));
                    quiz.setA4(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_A4)));
                    quiz.setA5(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_A5)));
                    quiz.setA6(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_A6)));
                    quiz.setResult(cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_RESULT)));
                }
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return quiz;
    }

    public List<QuizResults> retrieveAllQuizResults(){
        Cursor cursor = null;
        List<QuizResults> results = new ArrayList<>();
        try {
            cursor = db.query( QuizAppDBHelper.TABLE_NEW_RESULT,
                    new String[]{
                            QuizAppDBHelper.NEW_RESULT_COLUMN_RESULT,
                            QuizAppDBHelper.NEW_RESULT_COLUMN_USERNAME,
                            QuizAppDBHelper.NEW_RESULT_COLUMN_DATE

                    },
                    null, null, null, null, null );

            if (cursor.getCount() > 0 ) {
                while (cursor.moveToNext()) {
                    QuizResults result = new QuizResults(
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_RESULT_COLUMN_USERNAME)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_QUIZ_COLUMN_DATE)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.NEW_RESULT_COLUMN_RESULT))
                    );
                    results.add(result);
                }
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return results;
    }
}