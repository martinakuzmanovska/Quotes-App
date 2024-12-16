package com.lab3.quoteapp.domain.retrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class QuoteDbApiProvider {

    companion object {
        @Volatile
        private var INSTANCE: QuoteDbApi? = null

        @JvmStatic
        fun getQuoteDbApi(): QuoteDbApi {

            return INSTANCE ?: synchronized(this) {
                val instance = createQuoteDbApi()
                INSTANCE = instance
                instance
            }
        }

        private fun createQuoteDbApi(): QuoteDbApi {
            class QueryParamInterceptor : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request() //precekaj go request-ot
                    val htt = request.url.newBuilder()
                    //    .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                    request = request.newBuilder().url(htt).build()
                    return chain.proceed(request)
                }
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(QueryParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val gsonConverterFactory = GsonConverterFactory.create(gson)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .client(okhttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
            return retrofit.create(QuoteDbApi::class.java)
        }
    }


}
