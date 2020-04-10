package edu.uga.cs.quiz_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizAppDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "QuizAppDBHelper";

    private static final String DB_NAME = "quizapp.db";
    private static int DB_version = 1;
    private static QuizAppDBHelper quizAppDBHelper;

    public static final String TABLE_COUNTRY_CONTINENT = "countrycontinent";
    public static final String COUNTRY_CONTINENT_COLUMN_ID = "_id";
    public static final String COUNTRY_CONTINENT_COLUMN_COUNTRY = "country";
    public static final String COUNTRY_CONTINENT_COLUMN_CONTINENT = "continents";


    public static final String TABLE_COUNTRY_NEIGHBOURS = "countrynieghbours";
    public static final String COUNTRY_NEIGHBOURS_COLUMN_ID = "_id";
    public static final String COUNTRY_NEIGHBOURS_COLUMN_COUNTRY = "country";
    public static final String COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS= "neighbours";


    private static final String CREATE_COUNTRY_CONTINENT =
            "create table " + TABLE_COUNTRY_CONTINENT + " ("
                    + COUNTRY_CONTINENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COUNTRY_CONTINENT_COLUMN_COUNTRY + " TEXT, "
                    + COUNTRY_CONTINENT_COLUMN_CONTINENT + " TEXT "
                    + ")";

    private static final String CREATE_COUNTRY_NEIGHBOURS =
            "create table " + TABLE_COUNTRY_NEIGHBOURS + " ("
                    + COUNTRY_NEIGHBOURS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COUNTRY_NEIGHBOURS_COLUMN_COUNTRY + " TEXT, "
                    + COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS + " TEXT "
                    + ")";

    private QuizAppDBHelper(Context context){
        super(context,DB_NAME,null,DB_version);
    }

    public static synchronized QuizAppDBHelper getInstance(Context context){
        if(quizAppDBHelper == null){
            quizAppDBHelper = new QuizAppDBHelper(context.getApplicationContext());
        }
        return quizAppDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_COUNTRY_CONTINENT );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_CONTINENT + " created" );
        db.execSQL( CREATE_COUNTRY_NEIGHBOURS );
        Log.d( DEBUG_TAG,"Table " + TABLE_COUNTRY_NEIGHBOURS + " created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "drop table if exists " + TABLE_COUNTRY_CONTINENT );
        db.execSQL( "drop table if exists " + TABLE_COUNTRY_NEIGHBOURS );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_CONTINENT + " upgraded" );
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRY_NEIGHBOURS + " upgraded" );
    }
}
