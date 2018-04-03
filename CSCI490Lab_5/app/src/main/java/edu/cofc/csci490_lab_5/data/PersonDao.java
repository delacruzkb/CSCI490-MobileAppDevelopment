package edu.cofc.csci490_lab_5.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.cofc.csci490_lab_5.entities.Person;

/**
 * Created by kenso on 3/15/2018.
 */
@Dao
public interface PersonDao
{
    @Insert
    void insertPerson(Person person);

    @Query("SELECT * FROM Person")
    List<Person> getAllPersons();
}
