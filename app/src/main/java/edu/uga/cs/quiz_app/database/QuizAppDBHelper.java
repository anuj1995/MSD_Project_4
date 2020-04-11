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

    // Table country-continent
    public static final String TABLE_COUNTRY_CONTINENT = "countrycontinent";
    public static final String COUNTRY_CONTINENT_COLUMN_ID = "_id";
    public static final String COUNTRY_CONTINENT_COLUMN_COUNTRY = "country";
    public static final String COUNTRY_CONTINENT_COLUMN_CONTINENT = "continents";

    // Table country-neighbours
    public static final String TABLE_COUNTRY_NEIGHBOURS = "countrynieghbours";
    public static final String COUNTRY_NEIGHBOURS_COLUMN_ID = "_id";
    public static final String COUNTRY_NEIGHBOURS_COLUMN_COUNTRY = "country";
    public static final String COUNTRY_NEIGHBOURS_COLUMN_NEIGHBOURS= "neighbours";

    // Table new quiz

    public static final String TABLE_NEW_QUIZ = "newquiz";
    public static final String NEW_QUIZ_COLUMN_USERNAME = "username";
    public static final String NEW_QUIZ_COLUMN_DATE = "date";
    public static final String NEW_QUIZ_COLUMN_Q1 = "Q1";
    public static final String NEW_QUIZ_COLUMN_Q2 = "Q2";
    public static final String NEW_QUIZ_COLUMN_Q3 = "Q3";
    public static final String NEW_QUIZ_COLUMN_Q4 = "Q4";
    public static final String NEW_QUIZ_COLUMN_Q5 = "Q5";
    public static final String NEW_QUIZ_COLUMN_Q6 = "Q6";
    public static final String NEW_QUIZ_COLUMN_A1 = "A1";
    public static final String NEW_QUIZ_COLUMN_A2 = "A2";
    public static final String NEW_QUIZ_COLUMN_A3 = "A3";
    public static final String NEW_QUIZ_COLUMN_A4 = "A4";
    public static final String NEW_QUIZ_COLUMN_A5 = "A5";
    public static final String NEW_QUIZ_COLUMN_A6 = "A6";


    // TABLE NEW QUIZ
    public static final String TABLE_NEW_RESULT = "newresult";
    public static final String NEW_RESULT_COLUMN_USERNAME = "username";
    public static final String NEW_RESULT_COLUMN_RESULT = "result";
    public static final String NEW_RESULT_COLUMN_DATE = "date";



    public static final String CREATE_NEW_RESULT =
            "create table " + TABLE_NEW_RESULT + " ("
                    + NEW_RESULT_COLUMN_USERNAME + " TEXT, "
                    + NEW_RESULT_COLUMN_DATE + " TEXT "
                    + NEW_RESULT_COLUMN_RESULT + " TEXT, "
                    + ") ";


    public static final String CREATE_NEW_QUIZ =
            "create table " + TABLE_NEW_QUIZ + " ("
                    + NEW_QUIZ_COLUMN_USERNAME + " TEXT, "
                    + NEW_QUIZ_COLUMN_DATE + " TEXT, "
                    + NEW_QUIZ_COLUMN_Q1 + " TEXT, "
                    + NEW_QUIZ_COLUMN_A1 + " TEXT, "
                    + NEW_QUIZ_COLUMN_Q2 + " TEXT, "
                    + NEW_QUIZ_COLUMN_A2 + " TEXT, "
                    + NEW_QUIZ_COLUMN_Q3 + " TEXT, "
                    + NEW_QUIZ_COLUMN_A3 + " TEXT, "
                    + NEW_QUIZ_COLUMN_Q4 + " TEXT, "
                    + NEW_QUIZ_COLUMN_A4 + " TEXT, "
                    + NEW_QUIZ_COLUMN_Q5 + " TEXT, "
                    + NEW_QUIZ_COLUMN_A5 + " TEXT, "
                    + NEW_QUIZ_COLUMN_Q6 + " TEXT, "
                    + NEW_QUIZ_COLUMN_A6 + " TEXT "
                    + ")";


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