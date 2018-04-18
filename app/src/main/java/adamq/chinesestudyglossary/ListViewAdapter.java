package adamq.chinesestudyglossary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adamq on 2017/5/5.
 */

public class ListViewAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Word> words;

    public ListViewAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
        this.context = context;
        this.words = words;
        this.layoutInflater = LayoutInflater.from(context.getApplicationContext());

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listentry,null);
        }
        final TextView oTextView = (TextView) convertView.findViewById(R.id.entry);
        convertView.setTag(convertView);

        final Word word = words.get(position);
        oTextView.setText(word.getZhCharacter() + " - " + word.getEngDefinition());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holder.getInstance().setCurrentWord(word);
                Intent intent = new Intent(context, ViewWordActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        public TextView textView;

        public ViewHolder(TextView textView) {
            this.textView = textView;
            textView.setHeight(300);
        }
    }
}
