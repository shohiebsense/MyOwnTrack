package com.shohiebsense.myowntracking.data

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.*
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import com.shohiebsense.myowntracking.data.dao.CategoryDao
import com.shohiebsense.myowntracking.data.dao.NoteDao
import com.shohiebsense.myowntracking.data.model.Category
import com.shohiebsense.myowntracking.data.model.Note

@Database(entities = [Note::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao
    abstract fun categoryDao() : CategoryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase? {
            if(instance == null){
                synchronized(AppDatabase::class){
                    instance =  Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "myowntracking_db"
                    ).fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }

            }
            return instance
        }

        private val roomCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }


        fun destroyInstance(){
            instance = null
        }


    }

    override fun clearAllTables() {
    }

    class PopulateDbAsyncTask(db : AppDatabase?) : AsyncTask<Unit, Unit, Unit>(){
        private val categoryDao = db?.categoryDao()

        override fun doInBackground(vararg params: Unit?) {
            categoryDao?.insert(Category("Guitar",1))
            categoryDao?.insert(Category("Programming",2))
            categoryDao?.insert(Category("Sport",3))
        }
    }

}