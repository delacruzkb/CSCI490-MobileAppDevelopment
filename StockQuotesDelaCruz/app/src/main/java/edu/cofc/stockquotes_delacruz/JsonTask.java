package edu.cofc.stockquotes_delacruz;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by kenso on 3/8/2018.
 */
public class JsonTask extends AsyncTask<String, Void, String>
{
    Context mContext;
    Stock mStock;
    TextView symbolText;
    TextView nameText;
    TextView lastTradePriceText;
    TextView lastTradeTimeText;
    TextView changeText;
    TextView weekRangeText;
    public JsonTask(Context context, String symbol)
    {
      mContext = context;
      mStock = new Stock(symbol);
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s.equalsIgnoreCase("OK")) {
            View parentView = ((Activity) mContext).getWindow().getDecorView();

            symbolText = parentView.findViewById(R.id.symbol_text);
            nameText = parentView.findViewById(R.id.name_text);
            lastTradePriceText = parentView.findViewById(R.id.last_trade_price_text);
            lastTradeTimeText = parentView.findViewById(R.id.last_trade_time_text);
            changeText = parentView.findViewById(R.id.change_text);
            weekRangeText = parentView.findViewById(R.id.week_range_text);

            symbolText.setText(mStock.getSymbol());
            nameText.setText(mStock.getName());
            lastTradePriceText.setText(mStock.getLastTradePrice());
            lastTradeTimeText.setText(mStock.getLastTradeTime());
            changeText.setText(mStock.getChange());
            weekRangeText.setText(mStock.getRange());
        }
        else
        {
            // make a toast
            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String doInBackground(String... strings)
    {
        // update stuff
        String status;
        try
        {
            mStock.load();
            status = "OK";
        }
        catch (Exception e)
        {
            status = "Error in retrieving stock symbol";
        }
        return status;
    }

}