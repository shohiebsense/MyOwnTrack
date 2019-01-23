package com.shohiebsense.myowntracking.service.servicelocator

import android.content.Context
import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.service.network.CatApi
import com.shohiebsense.myowntracking.service.repository.cat.CatRepository
import java.util.concurrent.Executor

interface CatServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance : CatServiceLocator? = null
        fun instance(context : Context) : CatServiceLocator {
            synchronized(LOCK) {
                if(instance == null){
                    instance =
                            DefaultCatServiceLocator(
                                app = context.applicationContext as Application,
                                useInNetwork = true
                            )
                }
                return instance!!
            }
        }
    }

    fun getNetworkExecutor(): Executor
    fun getDiskIOExecutor(): Executor
    fun getCallApi(): CatApi
    fun getRepository(type: CatRepository.Type): CatRepository


}