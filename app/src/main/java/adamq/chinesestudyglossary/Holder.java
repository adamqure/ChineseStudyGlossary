package adamq.chinesestudyglossary;

import android.content.Context;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by adamq on 2017/5/5.
 */

public class Holder {
    private static final Holder ourInstance = new Holder();

    public static Holder getInstance() {
        return ourInstance;
    }
    public Data data;
    public ListView listView;
    public Context context;
    public Word currentWord;
    public ListView bookmarkView;
    private Holder() {
    }
    public void createData(Context context, ListView listView) {
        data = new Data(context);
        this.listView = listView;
    }

    public static Holder getOurInstance() {
        return ourInstance;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Word getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(Word currentWord) {
        this.currentWord = currentWord;
    }

    public ListView getBookmarkView() {
        return bookmarkView;
    }

    public void setBookmarkView(ListView bookmarkView) {
        this.bookmarkView = bookmarkView;
    }

    public void save() {
        String filename = context.getFilesDir() + File.separator+"myfile.txt";
        String string = data.writeToFile();
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
