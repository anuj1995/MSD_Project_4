package edu.uga.cs.quiz_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import edu.uga.cs.quiz_app.R;

public class QuizFragment extends Fragment {


    public static QuizFragment newInstance() {

        final QuizFragment mf = new QuizFragment();

        final Bundle args = new Bundle();
        args.putString("somedata", "somedata");
        mf.setArguments(args);

        return mf;
    }

    public QuizFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate and locate the main ImageView
        final View v = inflater.inflate(R.layout.fragment_quiz_question, container, false);
        //...
        String continent_question = getArguments().getString("continent_question");
        TextView continent_question_view = v.findViewById(R.id.continent_question);
        continent_question_view.setText(continent_question);

        String continent_option1 = getArguments().getString("continent_option1");
        TextView continent_option1_button = v.findViewById(R.id.continent_option1);
        continent_option1_button.setText(continent_option1);

        String continent_option2 = getArguments().getString("continent_option2");
        TextView continent_option2_button = v.findViewById(R.id.continent_option2);
        continent_option2_button.setText(continent_option2);

        String continent_option3 = getArguments().getString("continent_option3");
        TextView continent_option3_button = v.findViewById(R.id.continent_option3);
        continent_option3_button.setText(continent_option3);

        String neighbor_question = getArguments().getString("neighbor_question");
        TextView neighbor_question_view = v.findViewById(R.id.neighbor_question);
        neighbor_question_view.setText(neighbor_question);

        String neighbor_option1 = getArguments().getString("neighbor_option1");
        TextView neighbor_option1_button = v.findViewById(R.id.neighbor_option1);
        neighbor_option1_button.setText(neighbor_option1);

        String neighbor_option2 = getArguments().getString("neighbor_option2");
        TextView neighbor_option2_button = v.findViewById(R.id.neighbor_option2);
        neighbor_option2_button.setText(neighbor_option2);

        String neighbor_option3 = getArguments().getString("neighbor_option3");
        TextView neighbor_option3_button = v.findViewById(R.id.neighbor_option3);
        neighbor_option3_button.setText(neighbor_option3);

        return v;
    }

}
