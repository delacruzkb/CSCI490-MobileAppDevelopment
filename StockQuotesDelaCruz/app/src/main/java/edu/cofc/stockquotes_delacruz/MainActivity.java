package edu.cofc.stockquotes_delacruz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText promptText;
    private TextView symbolText;
    private TextView nameText;
    private TextView lastTradePriceText;
    private TextView lastTradeTimeText;
    private TextView changeText;
    private TextView weekRangeText;
    JsonTask stockTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        promptText = findViewById(R.id.prompt_text);
        symbolText= findViewById(R.id.symbol_text);
        nameText= findViewById(R.id.name_text);
        lastTradePriceText= findViewById(R.id.last_trade_price_text);
        lastTradeTimeText= findViewById(R.id.last_trade_time_text);
        changeText= findViewById(R.id.change_text);
        weekRangeText= findViewById(R.id.week_range_text);
        if(savedInstanceState !=null)
        {
            onRestoreInstanceState(savedInstanceState);
        }
        final Context context = this;
        promptText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if((keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    stockTask= new JsonTask(context, promptText.getText().toString());
                    stockTask.execute();
                }
                return false;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("promptText",promptText.getText().toString());
        savedInstanceState.putString("symbolText",symbolText.getText().toString());
        savedInstanceState.putString("nameText",nameText.getText().toString());
        savedInstanceState.putString("lastTradePriceText",lastTradePriceText.getText().toString());
        savedInstanceState.putString("lastTradeTimeText",lastTradeTimeText.getText().toString());
        savedInstanceState.putString("changeText",changeText.getText().toString());
        savedInstanceState.putString("weekRangeText",weekRangeText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        promptText.setText(savedInstanceState.getString("promptText"));
        symbolText.setText(savedInstanceState.getString("symbolText"));
        nameText.setText(savedInstanceState.getString("nameText"));
        lastTradePriceText.setText(savedInstanceState.getString("lastTradePriceText"));
        lastTradeTimeText.setText(savedInstanceState.getString("lastTradeTimeText"));
        changeText.setText(savedInstanceState.getString("changeText"));
        weekRangeText.setText(savedInstanceState.getString("weekRangeText"));
    }
}
