package adamq.chinesestudyglossary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by adamq on 2017/5/5.
 */

public class AddWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcharacterform);
        Button submitword = (Button) findViewById(R.id.submit);
        submitword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhChar = ((EditText)findViewById(R.id.zhcharacter)).getText().toString();
                String engDeff = ((EditText)findViewById(R.id.engdefinition)).getText().toString();
                String partofspeach = ((EditText)findViewById(R.id.partofspeech)).getText().toString();
                String pinyin = ((EditText)findViewById(R.id.pinyin)).getText().toString();
                String sourcetext = ((EditText)findViewById(R.id.sourcetext)).getText().toString();
                Word word = new Word(zhChar,engDeff,pinyin,partofspeach,sourcetext);

                if(zhChar.length() == 0 || engDeff.length() == 0 || partofspeach.length() == 0 ||
                        pinyin.length() == 0 || sourcetext.length() == 0) {
                    return;
                }
                Data data = Holder.getInstance().data;
                data.addToGlossary(word);

                Holder.getInstance().getListView().setAdapter(new ListViewAdapter(Holder.getInstance().getContext(),Holder.getInstance().getData().getGlossaryEntries()));
                Holder.getInstance().save();
                finish();
            }
        });


    }

}
