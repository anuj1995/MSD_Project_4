package edu.uga.cs.quiz_app.datamodel;

public class QuizResults {
    private String username;
    private String result;
    private String date;

    public QuizResults (String username,String date, String result){
        this.date = date;
        this.result = result;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
