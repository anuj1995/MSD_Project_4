package edu.uga.cs.quiz_app.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import edu.uga.cs.quiz_app.MainActivity;
import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.datamodel.Quiz;

public class QuizMainFragment extends Fragment {

    private QuizViewModel quizViewModel;
    public FragmentAdapter mPagerAdapter;
    private ScoreViewModel scoreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Context context= this.getContext();
        List<Fragment> fragments = new Vector<Fragment>();
        quizViewModel =
                ViewModelProviders.of(this,new QuizViewModelFactory(context)).get(QuizViewModel.class);
        scoreViewModel = ScoreViewModel.getInstance();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager mPager = root.findViewById(R.id.pager);
        //FragmentAdapter mPagerAdapter;
        quizViewModel.getQuiz().observe(this, new Observer<Quiz>() {
            @Override
            public void onChanged(Quiz quiz) {
                //for each fragment you want to add to the pager

                String[] q1 = quiz.getQ1().split(",");
                String[] q2 = quiz.getQ2().split(",");
                String[] q3 = quiz.getQ3().split(",");
                String[] q4 = quiz.getQ4().split(",");
                String[] q5 = quiz.getQ5().split(",");
                String[] q6 = quiz.getQ6().split(",");

                String[] a1 = quiz.getA1().split(",");
                String[] a2 = quiz.getA2().split(",");
                String[] a3 = quiz.getA3().split(",");
                String[] a4 = quiz.getA4().split(",");
                String[] a5 = quiz.getA5().split(",");
                String[] a6 = quiz.getA6().split(",");

                String[] continentOptions1 = getRandomOptions(new String[]{
                        q1[1],q1[2],a1[0]
                });
                String[] neighbourOptions1;
                if(a1[1].equals("no neighbours")){
                   neighbourOptions1 = getRandomOptions(new String[]{q1[3],q1[4],"no neighbours"});
                }else {
                    neighbourOptions1 = getRandomOptions(new String[]{q1[3],a1[1],"no neighbours"});
                }
                String[] continentOptions2 = getRandomOptions(new String[]{
                        q2[1],q2[2],a2[0]
                });
                String[] neighbourOptions2;
                if(a2[1].equals("no neighbours")){
                    neighbourOptions2 = getRandomOptions(new String[]{q2[3],q2[4],"no neighbours"});
                }else {
                    neighbourOptions2 = getRandomOptions(new String[]{q2[3],a2[1],"no neighbours"});
                }
                String[] continentOptions3 = getRandomOptions(new String[]{
                        q3[1],q3[2],a3[0]
                });
                String[] neighbourOptions3;
                if(a3[1].equals("no neighbours")){
                    neighbourOptions3 = getRandomOptions(new String[]{q3[3],q3[4],"no neighbours"});
                }else {
                    neighbourOptions3 = getRandomOptions(new String[]{q3[3],a3[1],"no neighbours"});
                }
                String[] continentOptions4 = getRandomOptions(new String[]{
                        q4[1],q4[2],a4[0]
                });
                String[] neighbourOptions4;
                if(a4[1].equals("no neighbours")){
                    neighbourOptions4 = getRandomOptions(new String[]{q4[3],q4[4],"no neighbours"});
                }else {
                    neighbourOptions4 = getRandomOptions(new String[]{q4[3],a4[1],"no neighbours"});
                }
                String[] continentOptions5 = getRandomOptions(new String[]{
                        q5[1],q5[2],a5[0]
                });
                String[] neighbourOptions5;
                if(a5[1].equals("no neighbours")){
                    neighbourOptions5 = getRandomOptions(new String[]{q5[3],q5[4],"no neighbours"});
                }else {
                    neighbourOptions5 = getRandomOptions(new String[]{q5[3],a5[1],"no neighbours"});
                }
                String[] continentOptions6 = getRandomOptions(new String[]{
                        q6[1],q6[2],a6[0]
                });
                String[] neighbourOptions6;
                if(a6[1].equals("no neighbours")){
                    neighbourOptions6 = getRandomOptions(new String[]{q6[3],q6[4],"no neighbours"});
                }else {
                    neighbourOptions6 = getRandomOptions(new String[]{q6[3],a6[1],"no neighbours"});
                }


                Bundle page1 = new Bundle();
                page1.putString("question", "What continent does "+ q1[0]+" belong to and which of " +
                        "the following is its neighbour ?");
                page1.putString("continent_option1", continentOptions1[0]);
                page1.putString("continent_option2", continentOptions1[1]);
                page1.putString("continent_option3", continentOptions1[2]);
                page1.putString("neighbor_option1", neighbourOptions1[0]);
                page1.putString("neighbor_option2", neighbourOptions1[1]);
                page1.putString("neighbor_option3", neighbourOptions1[2]);
                page1.putString("continentAnswer",a1[0]);
                page1.putString("neighbourAnswer",a1[1]);
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page1));

                Bundle page2 = new Bundle();
                page2.putString("question", "What continent does "+ q2[0]+" belong to and which of " +
                        "the following is its neighbour ?");
                page2.putString("continent_option1", continentOptions2[0]);
                page2.putString("continent_option2", continentOptions2[1]);
                page2.putString("continent_option3", continentOptions2[2]);
                page2.putString("neighbor_option1", neighbourOptions2[0]);
                page2.putString("neighbor_option2", neighbourOptions2[1]);
                page2.putString("neighbor_option3", neighbourOptions2[2]);
                page2.putString("continentAnswer",a2[0]);
                page2.putString("neighbourAnswer",a2[1]);
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page2));

                Bundle page3 = new Bundle();
                page3.putString("question", "What continent does "+ q3[0]+" belong to and which of " +
                        "the following is its neighbour ?");
                page3.putString("continent_option1", continentOptions3[0]);
                page3.putString("continent_option2", continentOptions3[1]);
                page3.putString("continent_option3", continentOptions3[2]);
                page3.putString("neighbor_option1", neighbourOptions3[0]);
                page3.putString("neighbor_option2", neighbourOptions3[1]);
                page3.putString("neighbor_option3", neighbourOptions3[2]);
                page3.putString("continentAnswer",a3[0]);
                page3.putString("neighbourAnswer",a3[1]);
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page3));

                Bundle page4 = new Bundle();
                page4.putString("question", "What continent does "+ q4[0]+" belong to and which of " +
                        "the following is its neighbour ?");
                page4.putString("continent_option1", continentOptions4[0]);
                page4.putString("continent_option2", continentOptions4[1]);
                page4.putString("continent_option3", continentOptions5[2]);
                page4.putString("neighbor_option1", neighbourOptions4[0]);
                page4.putString("neighbor_option2", neighbourOptions4[1]);
                page4.putString("neighbor_option3", neighbourOptions4[2]);
                page4.putString("continentAnswer",a4[0]);
                page4.putString("neighbourAnswer",a4[1]);
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page4));

                Bundle page5 = new Bundle();
                page5.putString("question", "What continent does "+ q5[0]+" belong to and which of " +
                        "the following is its neighbour ?");
                page5.putString("continent_option1", continentOptions5[0]);
                page5.putString("continent_option2", continentOptions5[1]);
                page5.putString("continent_option3", continentOptions5[2]);
                page5.putString("neighbor_option1", neighbourOptions5[0]);
                page5.putString("neighbor_option2", neighbourOptions5[1]);
                page5.putString("neighbor_option3", neighbourOptions5[2]);
                page5.putString("continentAnswer",a5[0]);
                page5.putString("neighbourAnswer",a5[1]);
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page5));

                Bundle page6 = new Bundle();
                page6.putString("question", "What continent does "+ q6[0]+" belong to and which of " +
                        "the following is its neighbour ?");
                page6.putString("continent_option1", continentOptions6[0]);
                page6.putString("continent_option2", continentOptions6[1]);
                page6.putString("continent_option3", continentOptions6[2]);
                page6.putString("neighbor_option1", neighbourOptions6[0]);
                page6.putString("neighbor_option2", neighbourOptions6[1]);
                page6.putString("neighbor_option3", neighbourOptions6[2]);
                page6.putString("continentAnswer",a6[0]);
                page6.putString("neighbourAnswer",a6[1]);
                fragments.add(Fragment.instantiate(getContext(), QuizFragment.class.getName(),page6));

                Bundle page7 = new Bundle();
                fragments.add(Fragment.instantiate(getContext(), QuizSubmitFragment.class.getName(),page7));
                mPagerAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
                mPager.setAdapter(mPagerAdapter);

            }

        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 6) {
                    Fragment frag = mPagerAdapter.getItem(0);
                    Log.d("On Page Selected", "last page");

                    Intent intent = new Intent(getActivity(), QuizEndActivity.class);
                    startActivity(intent);
                }
                scoreViewModel.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return root;
    }
    private String[] getRandomOptions(String[] options){
        String[] result = new String[3];
        Random random = new Random();
        Set<Integer> randomNumbers = new LinkedHashSet<Integer>();
        while (randomNumbers.size() < 3) {
                randomNumbers.add(random.nextInt(3));
        }
        for(int i = 0; i < 3;i++){
                result[(int)randomNumbers.toArray()[i]] =options[i];
        }
        return result;
    }

}