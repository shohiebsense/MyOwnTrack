package com.shohiebsense.myowntracking.service.repository.cat

import com.shohiebsense.myowntracking.data.model.Cat
import com.shohiebsense.myowntracking.service.Listing
import com.shohiebsense.myowntracking.service.network.CatApi
import java.util.concurrent.Executor

class CatApiRepository(private val catApi: CatApi,
                       private val networkExecutor: Executor) : CatRepository {



    override fun getCats(): Listing<Cat> {
        return Listing<Cat>()
    }


}