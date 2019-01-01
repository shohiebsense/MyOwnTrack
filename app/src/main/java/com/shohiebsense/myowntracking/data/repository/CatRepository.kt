package com.shohiebsense.myowntracking.data.repository

import com.shohiebsense.myowntracking.Application
import com.shohiebsense.myowntracking.data.APIConstants
import okhttp3.*
import java.io.IOException

class CatRepository {
    private var urlBuilder : HttpUrl.Builder? = null
    private var url : HttpUrl? = null
    private var request : Request.Builder? = null

    companion object {
        @Volatile private var instance : CatRepository? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CatRepository()
                    .also { instance = it }
            }
    }


    init {
        urlBuilder = HttpUrl.parse("{APIConstants.CAT_API_BASE_URL}${APIConstants.CAT_API_SEARCH}")
            ?.newBuilder()
        request = Request.Builder().header(
            APIConstants.CAT_API_KEY_HEADER, APIConstants.CAT_API_KEY
        )
        default()
    }

    fun default(){
        urlBuilder?.addQueryParameter(
            APIConstants.API_PARAM_PAGE, APIConstants.API_PARAM_VALUE_DEFAULT_PAGE.toString())
        urlBuilder?.addQueryParameter(
            APIConstants.API_PARAM_LIMIT, APIConstants.API_PARAM_VALUE_DEFAULT_LIMIT.toString()
        )
    }

    operator fun invoke(action: CatRepository.() -> Unit){
        action()
        url = urlBuilder?.build()
        var builtRequest =  request?.url(url!!)
            ?.build()

        var client = OkHttpClient()
        client.newCall(builtRequest)
            .enqueue(object  : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    call.cancel()
                }

                override fun onResponse(call: Call, response: Response) {
                    val response = response.body()?.string()

                }

            })
    }
}