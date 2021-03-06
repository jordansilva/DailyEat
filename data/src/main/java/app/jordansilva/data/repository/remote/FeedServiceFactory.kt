package app.jordansilva.data.repository.remote

import app.jordansilva.data.util.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [ApiFeedService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object FeedServiceFactory {

    fun makeFeedService(isDebug: Boolean): ApiFeedService {
        val logginInterceptor = makeLoggingInterceptor(isDebug)
        val okHttpClient = makeOkHttpClient(logginInterceptor)
        return makeFeedService(okHttpClient, makeGson())
    }

    private fun makeFeedService(okHttpClient: OkHttpClient, gson: Gson): ApiFeedService {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.GITHUB_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(ApiFeedService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

}