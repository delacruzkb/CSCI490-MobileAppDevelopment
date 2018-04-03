package edu.cofc.csci490_lab_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        downloadJson("https://jsonplaceholder.typicode.com/users");
    }

    public String downloadJson(String url)
    {
        JsonTask jsonTask = new JsonTask(textView);
        jsonTask.execute(url);
        return "";
    }
}
