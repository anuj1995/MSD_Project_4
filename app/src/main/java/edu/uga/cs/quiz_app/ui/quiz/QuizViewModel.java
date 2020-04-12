package edu.uga.cs.quiz_app.ui.quiz;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import edu.uga.cs.quiz_app.App;
import edu.uga.cs.quiz_app.database.QuizAppData;
import edu.uga.cs.quiz_app.datamodel.CountryContinent;
import edu.uga.cs.quiz_app.datamodel.CountryNeighbours;
import edu.uga.cs.quiz_app.datamodel.Quiz;

public class QuizViewModel extends ViewModel {

    private MutableLiveData<Quiz> mQuiz;
    QuizAppData quizAppData = null;
    Random random = new Random();
    private List<CountryContinent> countryContinentList;
    private List<CountryNeighbours> countryNeighboursList;
    QuizViewModel(Context context) {
        quizAppData = new QuizAppData(context);
        mQuiz = new MutableLiveData<>();
        new databaseReader().execute(context);
    }

    public LiveData<Quiz> getQuiz() {
        return mQuiz;
    }

    private Quiz generateNewQuiz(Context context,String userName){

        Date currentTime = Calendar.getInstance().getTime();
        Set<Integer> randomNumbers = new LinkedHashSet<Integer>();
        List<StringBuilder> questions = new ArrayList<>();
        List<StringBuilder> answers = new ArrayList<>();
        while (randomNumbers.size() < 6) {
            randomNumbers.add(random.nextInt(countryContinentList.size()));
        }
        List<String> continentList = getContinentList(countryContinentList);
        List<String> countryList = getCountryList(countryContinentList);
        List<String> neighbourList = getNeighboursList(countryNeighboursList);
        //List<String> distinctContinents = getDistinctContinents(continentList);
        for(int randomNumber: randomNumbers ){
            List<String> distinctContinents = getDistinctContinents(continentList);
            List<String> distinctCountries = getCountryList(countryContinentList);
            StringBuilder question = new StringBuilder();
            StringBuilder answer = new StringBuilder();
            question.append(countryList.get(randomNumber));
            question.append(",");
            answer.append(continentList.get(randomNumber));
            answer.append(",");
            String[] neighbours = neighbourList.get(randomNumber).split(",");
            if (!(neighbours[0].isEmpty() || neighbours.length == 1)) {
                answer.append(neighbours[random.nextInt(neighbours.length - 1)]);
            }
            else if(neighbours.length == 1 && !neighbours[0].isEmpty()) {
                answer.append(neighbours[0]);
            }
            else {
                answer.append("No neighbour");
            }
            distinctContinents.remove(continentList.get(randomNumber));
            question.append(distinctContinents.get(random.nextInt(distinctContinents.size()-1)));
            question.append(",");
            distinctContinents.remove(continentList.get(randomNumber));
            question.append(distinctContinents.get(random.nextInt(distinctContinents.size()-1)));
            question.append(",");
            distinctCountries.remove(countryList.get(randomNumber));
            question.append(distinctCountries.get(random.nextInt(distinctCountries.size()-1)));
            question.append(",");
            distinctCountries.remove(countryList.get(randomNumber));
            question.append(distinctCountries.get(random.nextInt(distinctCountries.size()-1)));
            question.append(",");
            questions.add(question);
            answers.add(answer);


        }
        Quiz quiz = new Quiz(userName,
                currentTime.toString(),
                questions.get(0).toString(),
                questions.get(1).toString(),
                questions.get(2).toString(),
                questions.get(3).toString(),
                questions.get(4).toString(),
                questions.get(5).toString());
        quiz.setA1(answers.get(0).toString());
        quiz.setA2(answers.get(1).toString());
        quiz.setA3(answers.get(2).toString());
        quiz.setA4(answers.get(3).toString());
        quiz.setA5(answers.get(4).toString());
        quiz.setA6(answers.get(5).toString());
        return quiz;
    }

    private List<String> getContinentList(List<CountryContinent> countryContinents){
        List<String> continents = new ArrayList<>();
        for(CountryContinent countryContinent: countryContinents ){
            continents.add(countryContinent.getContinent());
        }
        return  continents;
    }

    private List<String>getCountryList(List<CountryContinent> countryContinents){
        List<String> countries = new ArrayList<>();
        for(CountryContinent countryContinent: countryContinents ){
            countries.add(countryContinent.getCountry());
        }
        return  countries;
    }

    private List<String> getNeighboursList(List<CountryNeighbours> countryNeighboursList){
        List<String> neighbours = new ArrayList<>();
        for(CountryNeighbours countryNeighbours: countryNeighboursList ){
            neighbours.add(countryNeighbours.getNeighbours());
        }
        return  neighbours;
    }

    private  List<String> getDistinctContinents(List<String> continents){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return continents.stream().distinct().collect(Collectors.toList());
        }
        else return continents;
    }

    private class databaseReader extends AsyncTask<Context,Void, Pair<List<CountryContinent>,List<CountryNeighbours>>>{
        Context context;
        final String TAG = "CountryContinentDatabaseReading";
        @Override
        protected Pair<List<CountryContinent>,List<CountryNeighbours>> doInBackground(Context... contexts) {
            quizAppData.open();
            context = contexts[0];
            return  new Pair<>(quizAppData.retrieveAllCountyContinents(),quizAppData.retrieveAllCountyNeighbours());
        }

        @Override
        protected void onPostExecute(Pair<List<CountryContinent>,List<CountryNeighbours>> databaseItems) {
            super.onPostExecute(databaseItems);
            final String DEBUG_TAG = "onPostExecute";
            Log.d( DEBUG_TAG, " CountryContinent read");
            countryContinentList = databaseItems.first;
            countryNeighboursList = databaseItems.second;
            mQuiz.setValue(generateNewQuiz(context,"anuj"));
            Log.d( DEBUG_TAG, "QuizCreated");
            quizAppData.close();
        }
    }

}