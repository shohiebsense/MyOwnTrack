package com.shohiebsense.myowntracking.service.network

import android.util.Log
import com.shohiebsense.myowntracking.data.APIConstants
import com.shohiebsense.myowntracking.data.model.Cat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.*
import java.io.IOException

class CatApi {

    private var urlBuilder : HttpUrl.Builder? = null
    private var url : HttpUrl? = null
    private var request : Request.Builder? = null
    private var moshi : Moshi
    private var catJsonAdapter : JsonAdapter<Cat>

    class ListingResponse(val data: ListingData)

    class ListingData(
        val children: List<Cat>,
        val after: String?,
        val before: String?
    )

    companion object {
        @Volatile private var instance : CatApi? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: CatApi()
                    .also { instance = it }
            }
    }


    init {
        urlBuilder = HttpUrl.parse("{APIConstants.CAT_API_BASE_URL}${APIConstants.CAT_API_SEARCH}")
            ?.newBuilder()
        request = Request.Builder().header(
            APIConstants.CAT_API_KEY_HEADER, APIConstants.CAT_API_KEY
        )
        moshi = Moshi.Builder().build()
        catJsonAdapter = moshi.adapter(Cat::class.java)
        default()
    }

    fun default(){
        urlBuilder?.addQueryParameter(
            APIConstants.API_PARAM_PAGE, APIConstants.API_PARAM_VALUE_DEFAULT_PAGE.toString())
        urlBuilder?.addQueryParameter(
            APIConstants.API_PARAM_LIMIT, APIConstants.API_PARAM_VALUE_DEFAULT_LIMIT.toString()
        )
    }



    operator fun invoke(action: CatApi.() -> Unit){
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
                    val responseSource = response.body()?.source()
                    Log.e("shohiebsense ",response.body().toString())
                    val cats = catJsonAdapter.fromJson(responseSource)
                    Log.e("shohiebsensee ",cats.toString())
                    action()
                }
            })
    }
}