import java.time.LocalDate;

import static java.time.LocalDate.now;

//this class creates all the card objects and the rules to use them

public class Card {
    private String question;
    private String answer;
    private int daysBetween;
    private LocalDate dueDate;
    private boolean newCard;

    public Card(){
        question="";
        answer="";
        daysBetween=0;
        dueDate=now();
        newCard=true;
    }

    public String toString(){
        return question+"\n"+answer+"\n"+daysBetween+"\n"+dueDate+"\n"+newCard+"\n";
    }




    public void setQuestion(String q){
        this.question=q;
    }

    public String getQuestion(){
        return this.question;
    }

    public void setAnswer(String q){
        this.answer=q;
    }

    public String getAnswer(){
        return this.answer;
    }

    public void setDaysBetween(int q){
        this.daysBetween=q;
    }

    public int getDaysBetween(){
        return this.daysBetween;
    }

    public void setDueDate(LocalDate q){
        this.dueDate=q;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }

    public void setNew(boolean q){
        this.newCard=q;
    }

    public boolean getNew(){
        return this.newCard;
    }
}

