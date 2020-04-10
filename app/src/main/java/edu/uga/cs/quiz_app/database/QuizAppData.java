package edu.uga.cs.quiz_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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


}
