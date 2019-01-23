package com.shohiebsense.myowntracking.service.servicelocator

import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.AppDatabase
import com.shohiebsense.myowntracking.data.model.Cat
import com.shohiebsense.myowntracking.service.network.CatApi
import com.shohiebsense.myowntracking.service.repository.cat.CatApiRepository
import com.shohiebsense.myowntracking.service.repository.cat.CatDbRepository
import com.shohiebsense.myowntracking.service.repository.cat.CatRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class DefaultCatServiceLocator(val app : Application, val useInNetwork : Boolean) :
    CatServiceLocator {


    private val DISK_IO = Executors.newSingleThreadExecutor()

    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    private val db by lazy {
        AppDatabase.getInstance(app)
    }

    private val api by lazy {
        CatApi.getInstance()
    }

    override fun getNetworkExecutor(): Executor = NETWORK_IO

    override fun getDiskIOExecutor(): Executor = DISK_IO

    override fun getCallApi(): CatApi = api

    override fun getRepository(type: CatRepository.Type): CatRepository {
        return when (type) {
            CatRepository.Type.IN_DB -> CatDbRepository(
                catDb = db!!,
                networkExecutor = getNetworkExecutor())
            CatRepository.Type.IN_NETWORK -> CatApiRepository(
                catApi = api,
                networkExecutor = getNetworkExecutor())
        }
    }


}