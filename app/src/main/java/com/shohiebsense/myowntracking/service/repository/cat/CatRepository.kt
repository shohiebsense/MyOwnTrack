package com.shohiebsense.myowntracking.service.repository.cat

import com.shohiebsense.myowntracking.service.Listing
import com.shohiebsense.myowntracking.data.model.Cat

interface CatRepository {
    fun getCats() : Listing<Cat>

    enum class Type {
        IN_NETWORK,
        IN_DB
    }
}