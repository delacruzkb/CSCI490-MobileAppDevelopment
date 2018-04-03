package edu.cofc.csci490_lab_5;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.cofc.csci490_lab_5.async.InsertTask;
import edu.cofc.csci490_lab_5.async.QueryTask;
import edu.cofc.csci490_lab_5.data.LabDatabase;

public class MainActivity extends AppCompatActivity
{
    Button submitButton;
    Button listButton;
    EditText nameField;
    LabDatabase labDB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.name_field);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),nameField.getText().toString(), Toast.LENGTH_SHORT).show();
                InsertTask task = new InsertTask(labDB);
                task.execute(nameField.getText().toString());
            }
        });

        listButton = findViewById(R.id.list_button);
        listButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Listing names", Toast.LENGTH_SHORT).show();
                QueryTask task = new QueryTask(labDB,getApplicationContext());
                task.execute();
            }
        });

        labDB = Room.databaseBuilder(this, LabDatabase.class, "Persons").build();;

    }

}
