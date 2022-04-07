package com.rommansabbir.androidtesting

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private val myHttpClient: OkHttpClient by lazy {
        val ins = OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
        ins.addInterceptor(loggingInterceptor)
        ins.addInterceptor(MockInterceptor())
        ins.build()
    }


    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
    }

    private fun getNewInstance(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(myHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiEndpoint
        get() = getNewInstance("https://www.mocky.io/v2/").create(ApiEndpoint::class.java)
}

class LoginResponse {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("address")
    @Expose
    var address = null
}

interface ApiEndpoint {
    @GET("login")
    /*fun login(): Call<LoginResponse>*/
    fun login(): Call<ResponseBody>
}


class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.endsWith("login") -> getListOfReposBeingStarredJson
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}

const val getListOfReposBeingStarredJson = "{\"name\":\"velmurugan\",\"address\":\"Chennai 600096\"}"