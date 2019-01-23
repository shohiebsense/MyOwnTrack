package com.shohiebsense.myowntracking.db

import android.util.Log
import androidx.paging.DataSource
import com.shohiebsense.myowntracking.data.dao.CatDao
import com.shohiebsense.myowntracking.model.Cat
import java.util.concurrent.Executor

class CatLocalCache(
                    private val catDao: CatDao,
                    private val ioExecutor: Executor) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(repos: List<Cat>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${repos.size} repos")
            catDao.insert(repos)
            insertFinished()
        }
    }

    fun getCats() : DataSource.Factory<Int, Cat>{
        //val query = "%${name.replace(' ', '%')}%"
        return catDao.getCats()
    }

    fun getSize(){
        ioExecutor.execute {
            Log.e("shohiebsensee ","urrent sizee "+catDao.getSize())
        }
    }

}