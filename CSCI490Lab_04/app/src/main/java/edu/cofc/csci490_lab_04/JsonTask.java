package edu.cofc.csci490_lab_04;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by kenso on 3/8/2018.
 */
public class JsonTask extends AsyncTask<String, Void, String>{
    private TextView text;
    public JsonTask(TextView textView)
    {
        text = textView;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("JSON", s);
        text.setText(s);
    }

    @Override
    protected String doInBackground(String... strings)
    {
        return urlConnection(strings[0]);
    }

    public String urlConnection(String urlString)
    {
        String line = "";
        try{
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            int i = 0;
            while(i < 25)
            {
                line += in.readLine();

                i++;
            }
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.i("JSON", line);
        return line;
    }
}