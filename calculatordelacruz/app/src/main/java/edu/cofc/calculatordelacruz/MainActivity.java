package edu.cofc.calculatordelacruz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    TextView inputText;
    Button cButton;
    Button ceButton;
    Button deleteButton;
    Button divideButton;
    Button multiplyButton;
    Button minusButton;
    Button addButton;
    Button decimalButton;
    Button signButton;
    Button zeroButton;
    Button oneButton;
    Button twoButton;
    Button threeButton;
    Button fourButton;
    Button fiveButton;
    Button sixButton;
    Button sevenButton;
    Button eightButton;
    Button nineButton;
    Button equalButton;
    double ansCounter;
    boolean isDecimalPresent;
    boolean isSignPresent;
    boolean isAnsDisplayed;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.input_text);
        cButton = findViewById(R.id.c_button);
        ceButton = findViewById(R.id.ce_button);
        deleteButton = findViewById(R.id.delete_button);
        divideButton = findViewById(R.id.divide_button);
        multiplyButton = findViewById(R.id.multiply_button);
        minusButton = findViewById(R.id.minus_button);
        addButton = findViewById(R.id.plus_button);
        decimalButton = findViewById(R.id.decimal_button);
        signButton = findViewById(R.id.sign_button);
        zeroButton = findViewById(R.id.zero_button);
        oneButton = findViewById(R.id.one_button);
        twoButton = findViewById(R.id.two_button);
        threeButton = findViewById(R.id.three_button);
        fourButton = findViewById(R.id.four_button);
        fiveButton = findViewById(R.id.five_button);
        sixButton = findViewById(R.id.six_button);
        sevenButton = findViewById(R.id.seven_button);
        eightButton = findViewById(R.id.eight_button);
        nineButton = findViewById(R.id.nine_button);
        equalButton = findViewById(R.id.equal_button);

        if(savedInstanceState!=null)
        {
            onRestoreInstanceState(savedInstanceState);
        }
        else
        {
            inputText.setText("");
            ansCounter=0;
            isDecimalPresent=false;
            isAnsDisplayed=false;
        }

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.setText("");
                isDecimalPresent=false;
                isSignPresent=false;
                isAnsDisplayed=false;
            }
        });

        ceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.setText("");
                ansCounter =0;
                isDecimalPresent=false;
                isSignPresent=false;
                isAnsDisplayed=false;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp = new StringBuffer(inputText.getText());
                if(temp.length()>0)
                {
                    temp.deleteCharAt(temp.length()-1);
                }
                inputText.setText(temp.toString());
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp = new StringBuffer(inputText.getText());
                if(temp.length()>0 && !isOperation(temp))
                {
                    temp.append('/');
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                inputText.setText(temp.toString());

            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp = new StringBuffer(inputText.getText());
                if(temp.length()>0 && !isOperation(temp))
                {
                    temp.append('x');
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                inputText.setText(temp.toString());

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp = new StringBuffer(inputText.getText());
                if(temp.length()>0 && !isOperation(temp))
                {
                    temp.append('-');
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                inputText.setText(temp.toString());

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp = new StringBuffer(inputText.getText());
                if(temp.length()>0 && !isOperation(temp))
                {
                    temp.append('+');
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                inputText.setText(temp.toString());

            }
        });
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                if(!isDecimalPresent)
                {
                    temp.append('.');
                }
                inputText.setText(temp.toString());
                isDecimalPresent=true;
            }
        });
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                if(!isSignPresent && !isNumber(temp))
                {
                    temp.append('-');
                }
                inputText.setText(temp.toString());
                isSignPresent =true;
            }
        });
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('0');
                inputText.setText(temp.toString());
            }
        });
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('1');
                inputText.setText(temp.toString());
            }
        });
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('2');
                inputText.setText(temp.toString());
            }
        });
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('3');
                inputText.setText(temp.toString());
            }
        });
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('4');
                inputText.setText(temp.toString());
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('5');
                inputText.setText(temp.toString());
            }
        });
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('6');
                inputText.setText(temp.toString());
            }
        });
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('7');
                inputText.setText(temp.toString());
            }
        });
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('8');
                inputText.setText(temp.toString());
            }
        });
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp;
                if(isAnsDisplayed)
                {
                    temp = new StringBuffer("");
                    isDecimalPresent=false;
                    isSignPresent=false;
                    isAnsDisplayed=false;
                }
                else
                {
                    temp = new StringBuffer(inputText.getText());
                }
                temp.append('9');
                inputText.setText(temp.toString());
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                StringBuffer temp = new StringBuffer(inputText.getText());
                ansCounter = parseInput(temp);
                inputText.setText(Double.toString(ansCounter));
                isAnsDisplayed=true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("ansCounter", ansCounter);
        savedInstanceState.putString("inputText",inputText.getText().toString());
        savedInstanceState.putBoolean("isDecimalPresent",isDecimalPresent);
        savedInstanceState.putBoolean("isAnsDisplayed",isAnsDisplayed);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        ansCounter = savedInstanceState.getDouble("ansCounter");
        inputText.setText(savedInstanceState.getString("inputText"));
        isDecimalPresent = savedInstanceState.getBoolean("isDecimalPresent");
        isAnsDisplayed = savedInstanceState.getBoolean("isAnsDisplayed");
    }

    private boolean isOperation(StringBuffer temp)
    {
        boolean rtnval = false;
        char endChar;
        if(temp.length()>0 ) {
            endChar = temp.charAt(temp.length() - 1);
            if ( (endChar == '/') )
            {
                rtnval =true;
            }
            if ( (endChar == 'x') )
            {
                rtnval =true;
            }
            if ( (endChar == '-') )
            {
                rtnval =true;
            }
            if ( (endChar == '+') )
            {
                rtnval =true;
            }
            if ( (endChar == '.') )
            {
                rtnval =true;
            }
        }
        return rtnval;

    }

    private boolean isNumber(StringBuffer temp)
    {
        boolean rtnval = false;
        char endChar;
        if(temp.length()>0 ) {
            endChar = temp.charAt(temp.length() - 1);
            if ( (endChar == '1') )
            {
                rtnval =true;
            }
            if ( (endChar == '2') )
            {
                rtnval =true;
            }
            if ( (endChar == '3') )
            {
                rtnval =true;
            }
            if ( (endChar == '4') )
            {
                rtnval =true;
            }
            if ( (endChar == '5') )
            {
                rtnval =true;
            }
            if ( (endChar == '6') )
            {
                rtnval =true;
            }
            if ( (endChar == '7') )
            {
                rtnval =true;
            }
            if ( (endChar == '8') )
            {
                rtnval =true;
            }
            if ( (endChar == '9') )
            {
                rtnval =true;
            }
            if ( (endChar == '0') )
            {
                rtnval =true;
            }
        }
        return rtnval;
    }

    private double parseInput(StringBuffer temp)
    {
        double rtnval=0;
        try
        {
            boolean multiply=false;
            boolean divide =false;
            boolean add=false;
            boolean minus=false;
            ArrayList<Character> charList = new ArrayList<>();
            for(int i = 0; i<temp.length();i++)
            {
                char tempChar = temp.charAt(i);
                charList.add(new Character(tempChar));
                if ( (tempChar == '/') )
                {
                    divide=true;
                }
                if ( (tempChar == 'x') )
                {
                    multiply =true;
                }
                if ( (tempChar == '-') )
                {
                    minus =true;
                }
                if ( (tempChar == '+') )
                {
                    add =true;
                }
            }

            if(multiply)
            {
                int leftbound=0;
                int rightbound=0;
                int index = charList.indexOf(new Character('x'));
                double leftNumber=0;
                double rightNumber=0;
                double answer=0;
                //find leftNumber
                for(int i = index-1; i>=0;i--)
                {
                    if( (charList.get(i).equals('+')) || (charList.get(i).equals('/')) || (charList.get(i).equals('x')) )
                    {
                        leftbound = i-1;
                        i=-1;
                    }
                    if( (charList.get(i).equals('-')) )
                    {
                        if( (charList.get(i-1).equals('+')) || (charList.get(i-1).equals('x'))
                                || (charList.get(i-1).equals('-')) || (charList.get(i-1).equals('/')))
                        {
                            leftbound = i-2;
                            i=-1;
                        }
                        else
                        {
                            leftbound = i-1;
                            i=-1;
                        }
                    }
                }
                leftNumber = Double.parseDouble(temp.substring(leftbound,index));

                // find rightNumber
                for(int i = index+1; i<charList.size();i++)
                {
                    if( (charList.get(i).equals('+')) || (charList.get(i).equals('/')) || (charList.get(i).equals('x'))
                            || (charList.get(i).equals('-')) )
                    {
                        rightbound = i;
                        i=charList.size()+1;
                    }
                    if((i == charList.size()-1))
                    {
                        rightbound=i;
                    }


                }
                if(index+1 != rightbound)
                {
                    rightNumber = Double.parseDouble(temp.substring(index + 1, rightbound));
                }
                else
                {
                    rightNumber = Double.parseDouble(temp.substring(index + 1));
                }

                //calculate answer
                answer = leftNumber*rightNumber;

                //replace string
                if(index+1 != rightbound)
                {
                    temp = new StringBuffer(temp.substring(0, leftbound) + answer + temp.substring(rightbound));
                }
                else
                {
                    temp = new StringBuffer(temp.substring(0, leftbound) + answer);
                }
                //recurse
                rtnval = parseInput(temp);
            }
            else if(divide)
            {

            }
            else if( add)
            {

            }
            else if(minus)
            {

            }
            else
            {
                rtnval = Double.parseDouble(temp.toString());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return rtnval;
    }
}
