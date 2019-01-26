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
    page : Int,
    onSuccess : (cats : List<Cat>) -> Unit,
    onError : (error : String) -> Unit
){
    Log.e("shohiebsense ","get cats")
    service.search(page) {
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
    private lateinit var moshi : Moshi
    private lateinit var catJsonAdapter : JsonAdapter<Cat>

    class ListingResponse(val data: ListingData)

    class ListingData(
        val children: List<Cat>,
        val after: String?,
        val before: String?
    )

    init {


    }




    fun search(page : Int, action: CatService.(cats : List<Cat>?) -> Unit){
        Log.e("shohiebsense ","invoked")
        urlBuilder = HttpUrl.parse("${APIConstants.CAT_API_BASE_URL}${APIConstants.CAT_API_SEARCH}")
            ?.newBuilder()
        request = Request.Builder().header(
            APIConstants.CAT_API_KEY_HEADER, APIConstants.CAT_API_KEY
        )
        urlBuilder?.addQueryParameter(
            APIConstants.API_PARAM_LIMIT, APIConstants.API_PARAM_VALUE_DEFAULT_LIMIT.toString()
        )
        urlBuilder?.addQueryParameter(
            APIConstants.API_PARAM_PAGE,  page.toString())
        moshi = Moshi.Builder().build()
        catJsonAdapter = moshi.adapter(Cat::class.java)


        url = urlBuilder?.build()


        var builtRequest =  request?.url(url!!)
            ?.build()

        var client = OkHttpClient()
        client.newCall(builtRequest)
            .enqueue(object  : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("shohiebsensee ",e.toString())
                    call.cancel()
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseSource = response.body()?.string()
                    Log.e("shohiebsense on url ${call.request().url()}",responseSource)
                    val listType = Types.newParameterizedType(List::class.java, Cat::class.java)
                    val adapter: JsonAdapter<List<Cat>> = moshi.adapter(listType)
                    val result = adapter.fromJson(responseSource)
                    action(result)
                }
            })
    }
}