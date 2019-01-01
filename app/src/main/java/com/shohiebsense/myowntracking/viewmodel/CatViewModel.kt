package com.shohiebsense.myowntracking.viewmodel

import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import com.shohiebsense.myowntracking.data.repository.CatRepository
import androidx.lifecycle.Transformations.switchMap

class CatViewModel(appilcation : Application) : AndroidViewModel(appilcation) {

    private val catId = MutableLiveData <String>()
    private var catRepositor = CatRepository.getInstance()
    /*private val repoResult = map(catId, {
        catRepositor{

        }
    })*/


    fun getCats(){
        catRepositor {
            default()
        }
    }
}