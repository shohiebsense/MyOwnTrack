package com.shohiebsense.myowntracking.view.viewmodel

import com.shohiebsense.myowntracking.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import com.shohiebsense.myowntracking.service.repository.cat.CatRepository
import com.shohiebsense.myowntracking.service.servicelocator.CatServiceLocator

class CatViewModel(appilcation : Application) : AndroidViewModel(appilcation) {

    private val catId = MutableLiveData <String>()
    private var catRepositor = CatServiceLocator.instance(getApplication()).getRepository(CatRepository.Type.IN_NETWORK)
    private val repoResult = map(catId) {
        catRepositor.getCats()
    }
    val cats = switchMap(repoResult, { it.pagedList })!!
    val networkState = switchMap(repoResult, { it.networkState })!!
    val refreshState = switchMap(repoResult, { it.refreshState })!!

}