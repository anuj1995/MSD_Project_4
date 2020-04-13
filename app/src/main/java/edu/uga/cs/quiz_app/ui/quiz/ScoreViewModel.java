package edu.uga.cs.quiz_app.ui.quiz;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

public class ScoreViewModel {


    private final MutableLiveData<HashMap<Integer,Integer>> continentResult = new MutableLiveData<>();
    private final MutableLiveData<HashMap<Integer,Integer>> neighbourResult = new MutableLiveData<>();
    private final MutableLiveData<Integer> position = new MutableLiveData<>();

    private static ScoreViewModel scoreViewModel = null;
    public static synchronized ScoreViewModel getInstance(){
        if(scoreViewModel == null){
            scoreViewModel = new ScoreViewModel();
        }
        return scoreViewModel;
    }
    private ScoreViewModel(){
        if(continentResult.getValue()== null) continentResult.setValue(new HashMap<Integer, Integer>());
        if(neighbourResult.getValue()== null) neighbourResult.setValue(new HashMap<Integer, Integer>());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateContinentScore(int pos, int value){
        if(continentResult.getValue().containsKey(pos) ){
            continentResult.getValue().replace(pos,value);
        }
        else{
            continentResult.getValue().put(pos,value);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateNeighbourScore(int pos, int value){
        if(neighbourResult.getValue().containsKey(pos) ){
            neighbourResult.getValue().replace(pos,value);
        }
        else{
            neighbourResult.getValue().put(pos,value);
        }
    }
    void updatePosition(int pos){
        position.setValue(pos);
    }
    public LiveData<Integer> getPosition (){
        return position;
    }
    public HashMap<Integer, Integer> getContinentScore(){
        return continentResult.getValue();
    }
    public HashMap<Integer,Integer> getNeighbourScore(){
        return neighbourResult.getValue();
    }
}
