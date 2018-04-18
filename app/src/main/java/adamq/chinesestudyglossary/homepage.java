package adamq.chinesestudyglossary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class homepage extends AppCompatActivity {
    Activity activity;
    Data data;
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        activity = this;
        ListView listview = (ListView) findViewById(R.id.itemlist);
        Holder.getInstance().createData(this,listview);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        final String theString = settings.getString("theone","");
        load(theString);


    // Retrieve arrays from Data Class
        //data = new Data(this);
        Holder.getInstance().setContext(this);


       // Holder.getInstance().save();
        //load();


        data = Holder.getInstance().data;
        ArrayList<Word> glossaryArray = data.getGlossaryEntries();
        ArrayList<Word> bookmarksArray = data.getBookmarkedEntries();
        //Create Adapter
        listview.setAdapter(new ListViewAdapter(this,glossaryArray));

        Button addWordButton = (Button) findViewById(R.id.addbutton);
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWord();
            }
        });

        //Holder.getInstance().createData(this, listview);


        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        Button bookmarksButton = (Button) findViewById(R.id.bookmarks);
        bookmarksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        ListView bookmarklistview = (ListView) findViewById(R.id.bookmarkslistview);
        bookmarklistview.setAdapter(new BookmarksAdapter(this, Holder.getInstance().getData().getBookmarkedEntries()));

        Holder.getInstance().setBookmarkView(bookmarklistview);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        final EditText search = (EditText) findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String currentText = search.getText().toString();
                Holder.getInstance().getListView().setAdapter(new ListViewAdapter(Holder.getInstance().getContext(), Holder.getInstance().getData().getModifiedList(currentText)));

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        String stuff = Holder.getInstance().getData().writeToFile();
        editor.putString("theone",stuff);

        editor.commit();
    }



    public void addWord() {
        Intent intent = new Intent(this, AddWordActivity.class);
        this.startActivity(intent);
    }

    public void load(String thestring) {
        StringBuilder sb = new StringBuilder();
        String theText = sb.toString();
        String zhCharacter;
        String engDefinition;
        String partOfSpeech;
        String pinyin;
        String sourceText;

        Scanner scanner = new Scanner(thestring);
        scanner.nextLine();
        while (true) {
            String line = scanner.nextLine();
            if(line.charAt(0) == 'B' && line.charAt(1) == 'o' && line.charAt(2) == 'o' && line.charAt(3) == 'k' && line.charAt(4) == 'm') {
                break;
            }
            Scanner scanLine = new Scanner (line);
            scanLine.useDelimiter("/");
            while(scanLine.hasNext()) {
                zhCharacter = scanLine.next();
                engDefinition = scanLine.next();
                pinyin = scanLine.next();
                partOfSpeech = scanLine.next();
                sourceText = scanLine.next();

                Word word = new Word (zhCharacter, engDefinition, pinyin, partOfSpeech, sourceText);
                Holder.getInstance().getData().addToGlossary (word);
            }
        }

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            Scanner scanLine = new Scanner(line);
            scanLine.useDelimiter("/");
            while (scanLine.hasNext()) {
                zhCharacter = scanLine.next();
                engDefinition = scanLine.next();
                pinyin = scanLine.next();
                partOfSpeech = scanLine.next();
                sourceText = scanLine.next();

                Word word = new Word(zhCharacter, engDefinition, pinyin, partOfSpeech, sourceText);
                Holder.getInstance().getData().addToBookmarked(word);

            }
        }


    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
