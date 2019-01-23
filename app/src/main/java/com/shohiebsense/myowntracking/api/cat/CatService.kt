package com.shohiebsense.myowntracking.api.cat

import android.util.Log
import com.shohiebsense.myowntracking.data.APIConstants
import com.shohiebsense.myowntracking.model.Cat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import java.io.IOException


//add any parameters llke page here
fun getCats(
    service: CatService,
    onSuccess : (cats : List<Cat>) -> Unit,
    onError : (error : String) -> Unit
){
    service.invoke {
        cats ->
        if(cats == null){
            onError("")
        }
        else{
            onSuccess(cats)
        }
    }
}

class CatService {
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

    init {
        urlBuilder = HttpUrl.parse("${APIConstants.CAT_API_BASE_URL}${APIConstants.CAT_API_SEARCH}")
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



    operator fun invoke(action: CatService.(cats : List<Cat>?) -> Unit){
        url = urlBuilder?.build()
        var builtRequest =  request?.url(url!!)
            ?.build()

        var client = OkHttpClient()
        client.newCall(builtRequest)
            .enqueue(object  : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    call.cancel()
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseSource = response.body()?.source()
                    Log.e("shohiebsense ",response.body().toString())
                    val listType = Types.newParameterizedType(List::class.java, Cat::class.java)
                    val adapter: JsonAdapter<List<Cat>> = moshi.adapter(listType)
                    val result = adapter.fromJson(response.body()?.string()!!)
                    action(result)
                }
            })
    }
}