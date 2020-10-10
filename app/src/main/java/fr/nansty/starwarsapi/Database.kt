package fr.nansty.starwarsapi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import fr.nansty.starwarsapi.people.People


private const val DATABASE_NAME = "starwars.db"
private const val DATABASE_VERSION = 1

private const val PEOPLE_TABLE_NAME = "people"
private const val PEOPLE_KEY_ID = "id"
private const val PEOPLE_KEY_NAME = "name"

private const val PEOPLE_TABLE_CREATE = """
    CREATE TABLE $PEOPLE_TABLE_NAME (
        $PEOPLE_KEY_ID INTEGER PRIMARY KEY,
        $PEOPLE_TABLE_NAME TEXT
    )
"""

private const val PEOPLE_QUERY_SELECT_ALL = "SELECT * FROM $PEOPLE_KEY_NAME"

class Database (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION)
{
    val TAG = Database::class.java.simpleName

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(PEOPLE_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun createPeople(people: People) : Boolean{
        val values = ContentValues()
        values.put(PEOPLE_KEY_NAME, people.name)

        Log.d(TAG, "Creating people: $values")

        val id = writableDatabase.insert(PEOPLE_TABLE_NAME, null, values)
        people.id = id

        return id > 0
    }

    fun getAllPeoples(): MutableList<People> {
        val peoples = mutableListOf<People>()
        readableDatabase.rawQuery(PEOPLE_QUERY_SELECT_ALL, null).use { cursor ->
            while (cursor.moveToNext()){
                val people = People(
                    cursor.getLong(cursor.getColumnIndex(PEOPLE_KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(PEOPLE_KEY_NAME))
                    )
                peoples.add(people)
            }
        }
        return peoples
    }

}