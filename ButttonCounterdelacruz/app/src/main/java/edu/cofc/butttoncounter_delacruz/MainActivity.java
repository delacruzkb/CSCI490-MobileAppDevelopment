package edu.cofc.butttoncounter_delacruz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    TextView countText;
    Button countButton;
    Button clearButton;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            count= savedInstanceState.getInt("count");

        }

        countButton = findViewById(R.id.countbutton);
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                countText.setText(String.format(Locale.getDefault(), "%d", count));
            }
        });

        clearButton = findViewById(R.id.clearbutton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                countText.setText(String.format(Locale.getDefault(), "%d", count));
            }
        });

        countText = findViewById(R.id.countText);
        countText.setText(String.format(Locale.getDefault(), "%d", count));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("count", count);
        savedInstanceState.putBoolean("resuming",true);

    }
}
