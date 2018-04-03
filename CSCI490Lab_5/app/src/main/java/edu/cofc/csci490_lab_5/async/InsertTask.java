package edu.cofc.csci490_lab_5.async;

import android.os.AsyncTask;
import android.provider.ContactsContract;

import edu.cofc.csci490_lab_5.data.LabDatabase;
import edu.cofc.csci490_lab_5.entities.Person;

/**
 * Created by kenso on 3/15/2018.
 */

public class InsertTask extends AsyncTask<String,Void,Void>
{
    LabDatabase database;

    public InsertTask(LabDatabase db)
    {
        database = db;
    }
    @Override
    protected Void doInBackground(String... strings)
    {
        Person person = new Person();
        person.setName(strings[0]);
        database.personDao().insertPerson(person);
        return null;
    }
}
