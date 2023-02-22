import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//this class implements all the classes and methods

public class Main {
    public static void main(String [] args) throws IOException {

        String running = "0";
        int temp;
        Scanner input = new Scanner(System.in);

        System.out.println("welcome to the Spaced Repetition System!");

        while (!running.equals("4")) {
            System.out.println("Please choose and option:");
            System.out.println("1. Study a deck");
            System.out.println("2. Add cards to a deck");
            System.out.println("3. Create a new deck");
            System.out.println("4. Exit");
            running = input.nextLine();
            clear();
            if (running.equals("1")) {
                System.out.println("Choose an available deck:");
                Deck.printDecks();
                temp = Integer.parseInt(input.nextLine()) - 1;
                clear();
                String name = Deck.deckList[temp].getName();
                Deck wDeck = new Deck(name);
                wDeck.study();
                wDeck.save();
            }
            if (running.equals("2")) {
                String prompt = "";
                System.out.println("Which deck would you like to add to:");
                Deck.printDecks();
                String deck = input.nextLine();
                clear();
                while (!prompt.equals("e")) {
                    System.out.println("Press e to exit question mode: \n");
                    System.out.println("What is the question?");
                    temp = Integer.parseInt(deck) - 1;
                    Card cardNew = new Card();
                    prompt=input.nextLine();
                    if (!prompt.equals("e")) {
                        cardNew.setQuestion(prompt);
                        System.out.println("What is the answer?");
                        cardNew.setAnswer(input.nextLine());
                        String name = Deck.deckList[temp].getName();
                        Deck workingDeck = new Deck(name);
                        workingDeck.addCard(cardNew);
                        workingDeck.save();
                        clear();
                    }
                }
            }
            if (running.equals("3")) {
                System.out.println("Enter the name of your deck:");
                makeDir(input.nextLine());
                clear();
            }
        }
    }


    public static Deck getDeck(int temp) throws FileNotFoundException{
        String name = Deck.deckList[temp].getName();
        Deck newDeck = new Deck(name);
        return newDeck;
    }


    public static void makeDir(String deckName) throws IOException {

        String dir = "C:\\Users\\rjclo\\IdeaProjects\\SpacedRepetitionSystem\\src\\decks";
        File dotDeck = new File(dir);
        if (!dotDeck.exists()) {
            dotDeck.mkdirs();
        }


        String pth = "C:\\Users\\rjclo\\IdeaProjects\\SpacedRepetitionSystem\\src\\decks\\"+deckName+".deck";
        File file = new File(pth);
        if (file.exists()) {
            System.out.println("This deck already exists!");
        }
        else
        {
            file.createNewFile();
        }
    }

    public static void clear(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
