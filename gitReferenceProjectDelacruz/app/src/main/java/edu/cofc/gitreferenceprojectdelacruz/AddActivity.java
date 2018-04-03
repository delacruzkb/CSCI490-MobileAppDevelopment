package edu.cofc.gitreferenceprojectdelacruz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kenso on 2/28/2018.
 */

public class AddActivity extends AppCompatActivity
{
    EditText commandText;
    EditText explanationText;
    EditText exampleText;
    EditText sectionText;
    Button addButton;
    String command;
    String explanation;
    String example;
    String section;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_command_screen);

        commandText = findViewById(R.id.new_command);

        explanationText = findViewById(R.id.new_explanation);

        exampleText = findViewById(R.id.new_example);

        sectionText = findViewById(R.id.new_section);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command = commandText.getText().toString();
                explanation = explanationText.getText().toString();
                example = exampleText.getText().toString();
                finish();
            }
        });
    }

    @Override
    public void finish()
    {
        Intent intent = new Intent();
        intent.putExtra("command", command);
        intent.putExtra("command", explanation);
        intent.putExtra("example", example);
        intent.putExtra("section", section);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
