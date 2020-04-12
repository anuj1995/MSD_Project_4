package edu.uga.cs.quiz_app.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import edu.uga.cs.quiz_app.R;

public class QuizSubmitFragment extends Fragment {

    public static QuizSubmitFragment newInstance() {

        final QuizSubmitFragment mf = new QuizSubmitFragment();

        return mf;
    }

    public QuizSubmitFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate and locate the main ImageView
        final View v = inflater.inflate(R.layout.fragment_quiz_submit, container, false);

        Button submit = v.findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizEndActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
