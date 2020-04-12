package edu.uga.cs.quiz_app.ui.quiz;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import edu.uga.cs.quiz_app.R;

public class QuizEndActivity extends AppCompatActivity {

    public String TAG = "010101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_quiz_end);

        if (savedInstanceState == null) {
            Fragment newFragment = new QuizEndFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(newFragment, TAG).commit();
        }
    }
}
