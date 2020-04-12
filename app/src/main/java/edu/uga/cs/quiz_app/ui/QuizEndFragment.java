package edu.uga.cs.quiz_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import edu.uga.cs.quiz_app.R;

public class QuizEndFragment extends Fragment {

    public static QuizEndFragment newInstance() {

        final QuizEndFragment mf = new QuizEndFragment();

        final Bundle args = new Bundle();
        args.putString("somedata", "somedata");
        mf.setArguments(args);

        return mf;
    }

    public QuizEndFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate and locate the main ImageView
        final View v = inflater.inflate(R.layout.fragment_quiz_end, container, false);


        return v;
    }
}

