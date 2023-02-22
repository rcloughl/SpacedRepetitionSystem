import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

import static java.lang.Math.round;
import static java.time.LocalDate.now;

//this class holds all the cards for the review

public class Deck {
    private String name;
    private ArrayList<Card> deck;


    static File directory=new File("C:\\Users\\rjclo\\IdeaProjects\\SpacedRepetitionSystem\\src\\decks");
    static File[] deckList = directory.listFiles();


    public void setName(String q){
        this.name=q;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Card> getCards(){
        return this.deck;
    }


    public static void printDecks(){
        File directory=new File("C:\\Users\\rjclo\\IdeaProjects\\SpacedRepetitionSystem\\src\\decks");
        File[] deckList = directory.listFiles();
        int i=1;
        for (File file : deckList){
            System.out.println(i + ". "+ file.getName());
            i++;
        }
    }


    public Deck(String name) throws FileNotFoundException {

        this.deck=new ArrayList<>();
        this.name=name;
        String path = "C:\\Users\\rjclo\\IdeaProjects\\SpacedRepetitionSystem\\src\\decks\\" + name;
        Scanner sc = new Scanner(new File(path));
        int count=1;
        Card newCard= new Card();
        while(sc.hasNextLine()) {
            if (count==1){
                newCard = new Card();
                newCard.setQuestion(sc.nextLine());
                count++;
            }
            else if (count==2){
                newCard.setAnswer(sc.nextLine());
                count++;
            }
            else if (count==3){
                newCard.setDaysBetween(Integer.parseInt(sc.nextLine()));
                count++;
            }
            else if (count==4){
                newCard.setDueDate(LocalDate.parse(sc.nextLine()));
                count++;
            }
            else if(count==5){
                newCard.setNew(Boolean.parseBoolean(sc.nextLine()));
                deck.add(newCard);
                count++;
            }
            else if(count==6){
                sc.nextLine();
                count=1;
            }
        }
    }

    public void addCard(Card card){
        this.deck.add(card);
    }

    public void save(){
        String fileName = "C:\\Users\\rjclo\\IdeaProjects\\SpacedRepetitionSystem\\src\\decks\\"+this.name;
        File fold= new File(fileName);
        fold.delete();
        File newFold = new File(fileName);
        String source="";
        for (Card card : this.deck){
            source+=card+"\n";
        }
        try{
            FileWriter f2 = new FileWriter(newFold,false);
            f2.write(source);
            f2.close();
        }
        catch( IOException e){
            System.out.println("Try again.");
        }
    }

    public void study(){
        int count=0;
        Scanner input = new Scanner(System.in);
        Collections.shuffle(this.deck, new Random());
        int num=0;
        LocalDate days;
            for (Card card : this.deck) {
                if(count<10 || !card.getNew() ) {
                    if (card.getNew()){
                        card.setNew(false);
                        count++;
                    }
                    if (card.getDueDate().isBefore(now()) || card.getDueDate().isEqual(now())){
                    System.out.println("What is " + card.getQuestion() + "\n \nPress 'f' to flip:");
                    while (!input.nextLine().equals("f")){}
                    Main.clear();
                    System.out.println(card.getAnswer());
                    System.out.println("Was the question (e)asy, (c)orrect, (d)ifficult, (w)rong?");
                    String response= input.nextLine();
                    Main.clear();
                    if (response.equals("e")){
                        num= (int) round((card.getDaysBetween()+1)*1.5);
                        card.setDaysBetween(num);
                        card.setDueDate(now().plusDays(num));
                    }
                    else
                        if (response.equals("c")) {
                            num = (int) round((card.getDaysBetween() + 1) * 1.25);
                            card.setDaysBetween(num);
                            card.setDueDate(now().plusDays(num));
                        }
                        else
                        if (response.equals("d")) {
                            num = (int) round((card.getDaysBetween() + 1) * 1.1);
                            card.setDaysBetween(num);
                            card.setDueDate(now().plusDays(num));
                        }
                        else
                        if (response.equals("w")) {
                            num = (int) round((card.getDaysBetween() + 1) * 0);
                            card.setDaysBetween(num);
                            card.setDueDate(now().plusDays(num));
                        }
                }
            }
            }
    }

}


