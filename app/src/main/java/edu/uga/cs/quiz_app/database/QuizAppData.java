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
            cursor = db.query( QuizAppDBHelper.TABLE_COUNTRY_NEIGHBOURS,
                    new String[]{
                            QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_ID,
                            QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_COUNTRY,
                            QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS
                    },
                    null, null, null, null, null );

            if (cursor.getCount() > 0 ){
                while (cursor.moveToNext()){
                    CountryNeighbours countryNeighbour= new CountryNeighbours(
                            cursor.getLong(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_COUNTRY)),
                            cursor.getString(cursor.getColumnIndex(QuizAppDBHelper.COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS))
                    );
                    countryNeighbours.add(countryNeighbour);

                    Log.d( DEBUG_TAG, "Retrieved CountryNeighbour: " + countryNeighbour );
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
        return  countryNeighbours;
    }



    private void createNewQuiz(String userName){
        db.execSQL(QuizAppDBHelper.CREATE_NEW_QUIZ);
        Log.d( DEBUG_TAG, "Table " + QuizAppDBHelper.TABLE_NEW_QUIZ + " created" );
        this.open();


    }


}