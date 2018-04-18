package adamq.chinesestudyglossary;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by adamq on 2017/5/4.
 */

public class Data {
    //Input Values
    ArrayList<Word> glossaryEntries = new ArrayList<>();
    ArrayList<Word> bookmarkedEntries = new ArrayList<>();
    Context context;

    //Getters and Setters

    public ArrayList<Word> getGlossaryEntries() {
        return glossaryEntries;
    }

    public void setGlossaryEntries(ArrayList<Word> glossaryEntries) {
        this.glossaryEntries = glossaryEntries;
    }

    public ArrayList<Word> getBookmarkedEntries() {
        return bookmarkedEntries;
    }

    public void setBookmarkedEntries(ArrayList<Word> bookmarkedEntries) {
        this.bookmarkedEntries = bookmarkedEntries;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Data (Context context) {
        this.context = context;

        Word word = new Word("Char", "english", "pinyin", "noun", "sourcetext");
        Word newword = new Word ("char", "chinese", "zhuyin", "verb", "sourcefile");
/*        addToGlossary(word);
        addToGlossary(newword);
        addToBookmarked(new Word("test", "test", "test", "test", "test"));
        addToBookmarked(new Word("te43st", "te345st", "t2345est", "te2345st", "te2345st"));
*/
    }

    //Functions
    public void addToGlossary (Word toAdd)  {
        glossaryEntries.add(toAdd);
    }

    public void addToBookmarked (Word toAdd) {
        bookmarkedEntries.add(toAdd);
    }

    public void deleteFromBookmarked (int index) {
        bookmarkedEntries.remove(index);
    }

    public ArrayList<Word> getModifiedList(String filter) {
        if(filter.length() == 0) {
            return glossaryEntries;
        }

        ArrayList<Word> filteredList = new ArrayList<>();

        for(Word word : glossaryEntries) {
            if(word.getZhCharacter().length() < filter.length()) {
                continue;
            }
            boolean add = true;
            for(int i = 0; i < filter.length(); i++) {
                if(word.getZhCharacter().charAt(i) != filter.charAt(i)) {
                    add = false;
                    break;
                }
            }
            if(add) {
                filteredList.add(word);
            }
        }

        return filteredList;
    }

    //Write to Function
    public String writeToFile (){
        //Create a string of all of the content that the word objects hold
        StringBuilder sb = new StringBuilder();
        sb.append("Glossary\n");
        for (int i = 0; i < glossaryEntries.size(); i++){
            Word word = glossaryEntries.get(i);
            sb.append(word.getZhCharacter() + "/" +  word.getEngDefinition() + "/" + word.getPinyin() + "/" + word.getPartOfSpeech() + "/" + word.getSourceText() + "\n"); //Add a word to the glossary section of the file
        }

        sb.append("Bookmarks\n");
        for (int i = 0; i < bookmarkedEntries.size(); i++){
            Word word = bookmarkedEntries.get(i);
            sb.append(word.getZhCharacter() + "/" +  word.getEngDefinition() + "/" + word.getPinyin() + "/" + word.getPartOfSpeech() + "/" + word.getSourceText() + "\n"); //Add a word to the bookmarked section of the file
        }
        //Add a word to the Bookmarks section
        return sb.toString();
    }

    //Read file data to create the word objects
    public void readFile () {
        //Check if the file exists
        File file = new File (context.getFilesDir()+"/Glossary.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       /* scanner.hasNext(); //Returns boolean
        scanner.next(); //Gets next word
        scanner.nextLine(); //Gets next line
        */

       //Scan the document for each Line
        int counter = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Scanner scanLine = new Scanner (line);
            scanLine.useDelimiter("/");

            //Define the variables for the scanner to input for your creating the different objects
            String zhCharacter;
            String engDefinition;
            String partOfSpeech;
            String pinyin;
            String sourceText;


            //Scan the line for the objects
            if (line.equals("Glossary")){
                counter = 1;

            }
            else if (line.equals("Bookmarks")){
                counter = 2;
            }
            else if (counter == 1){
                //Order is word.getZhCharacter() + "/" +  word.getEngDefinition() + "/" + word.getPinyin() + "/" + word.getPartOfSpeech() + "/" + word.getSourceText()
                zhCharacter = scanLine.next();
                engDefinition = scanLine.next();
                pinyin = scanLine.next();
                partOfSpeech = scanLine.next();
                sourceText = scanLine.next();

                Word word = new Word (zhCharacter, engDefinition, pinyin, partOfSpeech, sourceText);
                addToGlossary (word);
            }

            else if (counter == 2){
                //Order is word.getZhCharacter() + "/" +  word.getEngDefinition() + "/" + word.getPinyin() + "/" + word.getPartOfSpeech() + "/" + word.getSourceText()
                zhCharacter = scanLine.next();
                engDefinition = scanLine.next();
                pinyin = scanLine.next();
                partOfSpeech = scanLine.next();
                sourceText = scanLine.next();

                Word word = new Word (zhCharacter, engDefinition, pinyin, partOfSpeech, sourceText);
                addToBookmarked (word);
            }
        }
    }
}
