package edu.cofc.csci490_lab_5.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.csci490_lab_5.PersonActivity;
import edu.cofc.csci490_lab_5.data.LabDatabase;
import edu.cofc.csci490_lab_5.entities.Person;

/**
 * Created by kenso on 3/15/2018.
 */


public class QueryTask extends AsyncTask<Void,Void,ArrayList<Person>>
{
    LabDatabase personDB;
    Context context;
    public QueryTask(LabDatabase personDB, Context context)
    {
        this.personDB = personDB;
        this.context = context;
    }

    @Override
    protected void onPostExecute(ArrayList<Person> persons)
    {
        super.onPostExecute(persons);
        ArrayList<String> personNames = new ArrayList<>();
        for(Person p: persons) {
            personNames.add(p.getName());
        }

        Intent i = new Intent(context,PersonActivity.class);
        i.putExtra("Persons", personNames);
        context.startActivity(i);
    }

    @Override
    protected ArrayList<Person> doInBackground(Void... voids)
    {
        ArrayList<Person> persons = (ArrayList) personDB.personDao().getAllPersons();
        return persons;
    }
}
