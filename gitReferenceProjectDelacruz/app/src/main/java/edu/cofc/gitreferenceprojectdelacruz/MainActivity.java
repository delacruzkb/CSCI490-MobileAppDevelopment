package edu.cofc.gitreferenceprojectdelacruz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static final int BACKGROUND_CODE = 1;
    public final String fileName = "gitReference.json";
    private ListView listView;
    String jsonString;
    ArrayList<GitReference> arrayList;
    InputStream is;
    OutputStream os;
    GitReferenceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent,BACKGROUND_CODE);

            }
        });

        listView = findViewById(R.id.git_commands);
        populateData();
        adapter = new GitReferenceAdapter(this, arrayList);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == BACKGROUND_CODE && resultCode == Activity.RESULT_OK)
        {
            Bundle extras = data.getExtras();
            if ((extras != null))
            {
                String command = extras.getString("command");
                String explanation = extras.getString("explanation");
                String example = extras.getString("example");
                String section = extras.getString("section");
                String newCommand = ",\n" +
                        "\t{\n" +
                        "\t  \"command\":\"" + command + "\",\n" +
                        "\t  \"example\":\"" + example +"\",\n" +
                        "\t  \"explanation\":\"" + explanation + "\",\n" +
                        "\t  \"section\":\"" + section + "\"\n" +
                        "\t}\n" +
                        "   ]\n" +
                        "}";
                try
                {
                    JsonUtils.append(getApplicationContext(),fileName, newCommand);
                    populateData();
                    adapter = new GitReferenceAdapter(this, arrayList);
                    listView.setAdapter(adapter);
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                }

            }
        }
    }

    private void populateData()
    {
        try {
            is = getApplicationContext().getAssets().open(fileName);
            jsonString = JsonUtils.parseJsonToString(is);
            arrayList = JsonUtils.populateGitReferences(jsonString);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
