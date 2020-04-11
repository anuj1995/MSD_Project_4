package edu.uga.cs.quiz_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Vector;

import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.ui.FragmentAdapter;
import edu.uga.cs.quiz_app.ui.QuizFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /**
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */

        ViewPager mPager = root.findViewById(R.id.pager);

        List<Fragment> fragments = new Vector<Fragment>();

        //for each fragment you want to add to the pager
        Bundle page1 = new Bundle();
        page1.putString("continent_question1", "question1");
        fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page1));

        Bundle page2 = new Bundle();
        page2.putString("continent_question2", "question2");
        fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page2));

        Bundle page3 = new Bundle();
        page3.putString("continent_question3", "question3");
        fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page3));

        Bundle page4 = new Bundle();
        page4.putString("continent_question4", "question4");
        fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page4));

        Bundle page5 = new Bundle();
        page5.putString("continent_question5", "question5");
        fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page5));

        Bundle page6 = new Bundle();
        page6.putString("continent_question6", "question6");
        fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page6));

        Bundle page7 = new Bundle();
        fragments.add(Fragment.instantiate(getContext(),QuizFragment.class.getName(),page7));

        PagerAdapter mPagerAdapter  = new FragmentAdapter(getFragmentManager(), fragments);

        mPager.setAdapter(mPagerAdapter);
        return root;
    }

}