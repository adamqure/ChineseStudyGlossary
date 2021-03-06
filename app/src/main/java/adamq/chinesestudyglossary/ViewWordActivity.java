package adamq.chinesestudyglossary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by adamq on 2017/5/5.
 */

public class ViewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewword);

        final Word word = Holder.getInstance().getCurrentWord();

        TextView zhCharacterView = (TextView) findViewById(R.id.wordzhcharacter);
        String zhtext = word.getZhCharacter();
        zhCharacterView.setText(zhtext);

        TextView pinyinView = (TextView) findViewById(R.id.wordpinyin);
        String pinyintext = word.getPinyin();
        pinyinView.setText(pinyintext);

        TextView englishDefinitionView = (TextView) findViewById(R.id.wordenglishdefinition);
        String englishdefinitiontext = word.getEngDefinition();
        englishDefinitionView.setText(englishdefinitiontext);

        TextView partofspeechView = (TextView) findViewById(R.id.wordpartofspeech);
        String partofspeechtext = word.getPartOfSpeech();
        partofspeechView.setText(partofspeechtext);

        TextView sourceTextView = (TextView) findViewById(R.id.wordsourcetext);
        String sourcetext = word.getSourceText();
        sourceTextView.setText(sourcetext);

        Button addBookmark = (Button) findViewById(R.id.addtobookmarks);
        addBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holder.getInstance().data.addToBookmarked(word);
                Holder.getInstance().getBookmarkView().setAdapter(new BookmarksAdapter(Holder.getInstance().getContext(),Holder.getInstance().getData().getBookmarkedEntries()));
                finish();
            }
        });
        Button deleteWordButton = (Button) findViewById(R.id.deleteword);
        deleteWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holder.getInstance().getData().getGlossaryEntries().remove(Holder.getInstance().getCurrentWord());
                Holder.getInstance().getData().getBookmarkedEntries().remove(Holder.getInstance().getCurrentWord());

                Holder.getInstance().getListView().setAdapter(new ListViewAdapter(Holder.getInstance().getContext(),Holder.getInstance().getData().getGlossaryEntries()));
                Holder.getInstance().getBookmarkView().setAdapter(new BookmarksAdapter(Holder.getInstance().getContext(),Holder.getInstance().getData().getBookmarkedEntries()));

                finish();
            }
        });




    }

}
