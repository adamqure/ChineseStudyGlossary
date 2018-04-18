package adamq.chinesestudyglossary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by adamq on 2017/5/12.
 */

public class BookmarksAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Word> words;
    private LayoutInflater inflator;

   public BookmarksAdapter(Context context, ArrayList<Word> words) {
       super(context, 0, words);
       this.context = context;
       this.words = words;

       this.inflator = LayoutInflater.from(context.getApplicationContext());

   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewAdapter.ViewHolder holder;
        if(convertView == null) {
            convertView = inflator.inflate(R.layout.listentry,null);
        }
        final TextView oTextView = (TextView) convertView.findViewById(R.id.entry);
        convertView.setTag(convertView);

        final Word word = words.get(position);
        oTextView.setText(word.getZhCharacter() + " - " + word.getEngDefinition() + " - " + word.getPartOfSpeech());
        oTextView.setTextColor(Color.BLACK);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holder.getInstance().setCurrentWord(word);
                Intent intent = new Intent(context, ViewWordBookmarkActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        public TextView textView;

        public ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
