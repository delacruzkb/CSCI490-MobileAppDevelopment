package edu.cofc.tipcalculator_counter_delacruz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    RadioGroup tipChoices;
    Button clearButton;
    Button calculateButton;
    EditText amount;
    EditText splitNumber;
    EditText customTipNumber;
    RadioButton defaultTip1Button;
    RadioButton defaultTip2Button;
    RadioButton customTipButton;
    TextView tipCost;
    TextView totalCost;
    TextView totalCostPerPerson;

    double checkAmount;
    double splitCheckBy;
    double tipPercent;
    double costOfTip;
    double costOfTotal;
    double costOfTotalPerPerson;

    boolean amountIsValid;
    boolean splitByIsValid;
    boolean tipIsValid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnKeyListener mKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                switch (v.getId()) {
                    case R.id.amountText:
                        if (amount.getText().toString().length() > 0)
                        {
                            amountIsValid = true;
                        }
                        if (amountIsValid && splitByIsValid && tipIsValid) {
                            calculateButton.setEnabled(true);
                        }
                        break;
                    case R.id.splitText:
                        if (splitNumber.getText().toString().length() > 0) {
                            splitByIsValid = true;
                        }
                        if (amountIsValid && splitByIsValid && tipIsValid) {
                            calculateButton.setEnabled(true);
                        }
                        break;
                    case R.id.tipText:
                        if (customTipNumber.getText().toString().length() > 0) {
                            tipIsValid = true;
                        }
                        if (amountIsValid && splitByIsValid && tipIsValid) {
                            calculateButton.setEnabled(true);
                        }
                        break;
                }
                return false;
            }
        };


        //Input Fields
        amount = findViewById(R.id.amountText);
        amount.setOnKeyListener(mKeyListener);
        splitNumber = findViewById(R.id.splitText);
        splitNumber.setOnKeyListener(mKeyListener);
        customTipNumber = findViewById(R.id.tipText);
        customTipNumber.setOnKeyListener(mKeyListener);
        customTipNumber.setEnabled(false);


        //Results
        tipCost = findViewById(R.id.tipTotalAmount);
        totalCost = findViewById(R.id.totalCostAmount);
        totalCostPerPerson = findViewById(R.id.totalForEachAmount);

        //Buttons
        defaultTip1Button = findViewById(R.id.defaultTip1Button);
        defaultTip2Button = findViewById(R.id.defaultTip2Button);
        customTipButton = findViewById(R.id.customTipButton);
        tipChoices = findViewById(R.id.tipChoices);
        tipChoices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if( checkedId !=-1)
                {
                    RadioButton rb = findViewById(checkedId);
                    if( rb!=null && !(rb.getText().toString().equals("Custom Tip")))
                    {
                        customTipNumber.setText(rb.getText(),TextView.BufferType.NORMAL);
                        customTipNumber.setEnabled(false);
                    }
                    else
                    {
                        customTipNumber.setEnabled(true);
                        customTipNumber.setText("", TextView.BufferType.NORMAL);
                    }
                    tipIsValid = true;
                    if (amountIsValid && splitByIsValid && tipIsValid) {
                        calculateButton.setEnabled(true);
                    }
                }
            }
        });

        //Reset all fields
        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               tipChoices.clearCheck();
               amount.setText("", TextView.BufferType.NORMAL);
               splitNumber.setText("", TextView.BufferType.NORMAL);
               customTipNumber.setText("", TextView.BufferType.NORMAL);
               customTipNumber.setEnabled(false);
               totalCost.setText("", TextView.BufferType.NORMAL);
               totalCostPerPerson.setText("", TextView.BufferType.NORMAL);
               tipCost.setText("", TextView.BufferType.NORMAL);
               //tipChoices.clearCheck();
               calculateButton.setEnabled(false);
               amountIsValid = false;
               splitByIsValid = false;
               tipIsValid = false;
            }
        });

        //Calculate values
        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setEnabled(false);
        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Reset Validity for error handling
                amountIsValid = false;
                splitByIsValid = false;
                tipIsValid = false;

                //Gather values
                try
                {
                    checkAmount = Double.parseDouble(amount.getText().toString());
                    if(checkAmount >=1)
                    {
                        amountIsValid = true;
                    }
                    else
                    {
                        showErrorAlert(getResources().getString(R.string.amountTooLow),amount.getId());
                    }
                }
                catch (Exception e)
                {
                    showErrorAlert(getResources().getString(R.string.invalidAmount), amount.getId());
                }

                try
                {
                    splitCheckBy = Double.parseDouble(splitNumber.getText().toString());
                    if(splitCheckBy >=1)
                    {
                        splitByIsValid = true;
                    }
                    else
                    {
                        showErrorAlert(getResources().getString(R.string.splitNumberTooLow),splitNumber.getId());
                    }
                }
                catch (Exception e)
                {
                    showErrorAlert(getResources().getString(R.string.invalidSplitNumber), splitNumber.getId());
                }
                try
                {
                    tipPercent = Double.parseDouble(customTipNumber.getText().toString());
                    if(tipPercent >=1)
                    {
                        tipIsValid = true;
                    }
                    else
                    {
                        showErrorAlert(getResources().getString(R.string.tipTooLow),customTipNumber.getId());
                    }
                }
                catch (Exception e)
                {
                    showErrorAlert(getResources().getString(R.string.invalidTip), customTipNumber.getId());
                }

                //calculate values and store them into the respective view
                if (amountIsValid && splitByIsValid && tipIsValid)
                {
                    DecimalFormat twoDecimals = new DecimalFormat("###.##");
                    costOfTip = checkAmount*(tipPercent/100);
                    costOfTotal= checkAmount + costOfTip;
                    costOfTotalPerPerson = costOfTotal/splitCheckBy;
                    tipCost.setText(twoDecimals.format(costOfTip));
                    totalCost.setText(twoDecimals.format(costOfTotal));
                    totalCostPerPerson.setText(twoDecimals.format(costOfTotalPerPerson));

                }

            }
        });

        //After everything is built, restore all saved values
        if(savedInstanceState!=null)
        {
            onRestoreInstanceState(savedInstanceState);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("amount",amount.getText().toString());
        savedInstanceState.putString("splitNumber",splitNumber.getText().toString());
        savedInstanceState.putString("customTipNumber", customTipNumber.getText().toString());

        savedInstanceState.putBoolean("calculateIsEnabled",calculateButton.isEnabled());

        savedInstanceState.putString("tipCost",tipCost.getText().toString());
        savedInstanceState.putString("totalCost",totalCost.getText().toString());
        savedInstanceState.putString("totalCostPerPerson",totalCostPerPerson.getText().toString());

        savedInstanceState.putBoolean("amountIsValid",amountIsValid);
        savedInstanceState.putBoolean("splitByIsValid",splitByIsValid);
        savedInstanceState.putBoolean("tipIsValid",tipIsValid);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        amount.setText(savedInstanceState.getString("amount"), TextView.BufferType.NORMAL);
        splitNumber.setText(savedInstanceState.getString("splitNumber"), TextView.BufferType.NORMAL);
        customTipNumber.setText(savedInstanceState.getString("customTipNumber"), TextView.BufferType.NORMAL);


        calculateButton.setEnabled(savedInstanceState.getBoolean("calculateIsEnabled"));

        tipCost.setText(savedInstanceState.getString("tipCost"), TextView.BufferType.NORMAL);
        totalCost.setText(savedInstanceState.getString("totalCost"), TextView.BufferType.NORMAL);
        totalCostPerPerson.setText(savedInstanceState.getString("totalCostPerPerson"), TextView.BufferType.NORMAL);

        amountIsValid = savedInstanceState.getBoolean("amountIsValid");
        splitByIsValid = savedInstanceState.getBoolean("splitByIsValid");
        tipIsValid = savedInstanceState.getBoolean("tipIsValid");
    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }
}
