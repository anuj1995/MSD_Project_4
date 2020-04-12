package edu.uga.cs.quiz_app.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Vector;

import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.datamodel.Quiz;
import edu.uga.cs.quiz_app.ui.FragmentAdapter;
import edu.uga.cs.quiz_app.ui.QuizEndFragment;
import edu.uga.cs.quiz_app.ui.QuizFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Context context = this.getContext();
        List<Fragment> fragments = new Vector<Fragment>();
        homeViewModel =
                ViewModelProviders.of(this,new QuizViewModelFactory(context)).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager mPager = root.findViewById(R.id.pager);

        homeViewModel.getQuiz().observe(this, new Observer<Quiz>() {
            @Override
            public void onChanged(Quiz quiz) {
                //for each fragment you want to add to the pager
                Bundle page1 = new Bundle();
                page1.putString("question", "what continent is"+quiz.getQ1());
                page1.putString("continent_option1", "continent option 1");
                page1.putString("continent_option2", "continent option 2");
                page1.putString("continent_option3", "continent option 3");
                page1.putString("neighbor_option1", "neighbor option 1");
                page1.putString("neighbor_option2", "neighbor option 2");
                page1.putString("neighbor_option3", "neighbor option 3");
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page1));

                Bundle page2 = new Bundle();
                page2.putString("question", "question");
                page2.putString("continent_option1", "continent option 1");
                page2.putString("continent_option2", "continent option 2");
                page2.putString("continent_option3", "continent option 3");
                page2.putString("neighbor_option1", "neighbor option 1");
                page2.putString("neighbor_option2", "neighbor option 2");
                page2.putString("neighbor_option3", "neighbor option 3");
                fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page2));

                Bundle page3 = new Bundle();
                page3.putString("question", "question");
                page3.putString("continent_option1", "continent option 1");
                page3.putString("continent_option2", "continent option 2");
                page3.putString("continent_option3", "continent option 3");
                page3.putString("neighbor_option1", "neighbor option 1");
                page3.putString("neighbor_option2", "neighbor option 2");
                page3.putString("neighbor_option3", "neighbor option 3");
                fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page3));

                Bundle page4 = new Bundle();
                page4.putString("question", "question");
                page4.putString("continent_option1", "continent option 1");
                page4.putString("continent_option2", "continent option 2");
                page4.putString("continent_option3", "continent option 3");
                page4.putString("neighbor_option1", "neighbor option 1");
                page4.putString("neighbor_option2", "neighbor option 2");
                page4.putString("neighbor_option3", "neighbor option 3");
                fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page4));

                Bundle page5 = new Bundle();
                page5.putString("question", "question");
                page5.putString("continent_option1", "continent option 1");
                page5.putString("continent_option2", "continent option 2");
                page5.putString("continent_option3", "continent option 3");
                page5.putString("neighbor_option1", "neighbor option 1");
                page5.putString("neighbor_option2", "neighbor option 2");
                page5.putString("neighbor_option3", "neighbor option 3");
                fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page5));

                Bundle page6 = new Bundle();
                page6.putString("question", "question");
                page6.putString("continent_option1", "continent option 1");
                page6.putString("continent_option2", "continent option 2");
                page6.putString("continent_option3", "continent option 3");
                page6.putString("neighbor_option1", "neighbor option 1");
                page6.putString("neighbor_option2", "neighbor option 2");
                page6.putString("neighbor_option3", "neighbor option 3");
                fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page6));

                Bundle page7 = new Bundle();
                fragments.add(Fragment.instantiate(getContext(), QuizEndFragment.class.getName(),page7));
                PagerAdapter mPagerAdapter  = new FragmentAdapter(getChildFragmentManager(), fragments);
                mPager.setAdapter(mPagerAdapter);
            }
        });



        return root;
    }

}