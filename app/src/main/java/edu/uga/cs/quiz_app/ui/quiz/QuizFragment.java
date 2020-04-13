
package edu.uga.cs.quiz_app.ui.quiz;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import edu.uga.cs.quiz_app.R;

public class QuizFragment extends Fragment {

    RadioGroup continents;
    RadioGroup neighbours;
    RadioButton continentsOptions;
    RadioButton neighbourOptions;
    ScoreViewModel scoreViewModel;
    int position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate and locate the main ImageView
        scoreViewModel = ScoreViewModel.getInstance();
        final View v = inflater.inflate(R.layout.fragment_quiz_question, container, false);
        //...
        continents = v.findViewById(R.id.continent_options);
        neighbours = v.findViewById(R.id.neighbor_options);

        String continentAns = getArguments().getString("continentAnswer");
        String neighbourAns = getArguments().getString("neighbourAnswer");
        String question = getArguments().getString("question");
        TextView question_view = v.findViewById(R.id.question);
        question_view.setText(question);

        String continent_option1 = getArguments().getString("continent_option1");
        TextView continent_option1_button = v.findViewById(R.id.continent_option1);
        continent_option1_button.setText(continent_option1);

        String continent_option2 = getArguments().getString("continent_option2");
        TextView continent_option2_button = v.findViewById(R.id.continent_option2);
        continent_option2_button.setText(continent_option2);

        String continent_option3 = getArguments().getString("continent_option3");
        TextView continent_option3_button = v.findViewById(R.id.continent_option3);
        continent_option3_button.setText(continent_option3);

        String neighbor_option1 = getArguments().getString("neighbor_option1");
        TextView neighbor_option1_button = v.findViewById(R.id.neighbor_option1);
        neighbor_option1_button.setText(neighbor_option1);

        String neighbor_option2 = getArguments().getString("neighbor_option2");
        TextView neighbor_option2_button = v.findViewById(R.id.neighbor_option2);
        neighbor_option2_button.setText(neighbor_option2);

        String neighbor_option3 = getArguments().getString("neighbor_option3");
        TextView neighbor_option3_button = v.findViewById(R.id.neighbor_option3);
        neighbor_option3_button.setText(neighbor_option3);

        scoreViewModel.getPosition().observe(this,integer ->  position = integer );

        continents.setOnCheckedChangeListener((group, checkedId) -> {
            continentsOptions = v.findViewById(checkedId);
            if(matchAnswers(continentsOptions.getText().toString(),continentAns)){
                scoreViewModel.updateContinentScore(position,1);
            }else {scoreViewModel.updateContinentScore(position,0);}
        });

        neighbours.setOnCheckedChangeListener((group, checkedId) -> {
            neighbourOptions = v.findViewById(checkedId);
            if(matchAnswers(neighbourOptions.getText().toString(),continentAns)){
                scoreViewModel.updateNeighbourScore(position,1);
            }else {scoreViewModel.updateNeighbourScore(position,0);}
        });


        return v;
    }

    private boolean matchAnswers(String selected, String ans){
        return selected.equals(ans);
    }

}
