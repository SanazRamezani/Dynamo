package com.dynamo.task.data.api

import com.dynamo.task.BuildConfig
import com.dynamo.task.ui.MainActivity
import com.dynamo.task.data.Webservice
import com.dynamo.task.ui.CourseDetailsActivity
import com.dynamo.task.util.App
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(injects = [App::class, MainActivity::class, CourseDetailsActivity::class], library = true)
class ApiModule {

    @Provides
    @Singleton
    fun provideDataRepository(webservice: Webservice) : DataRepository = DataRepository(webservice)

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.URL_WEBAPI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(BuildConfig.CONNECTION_TIMEOUT_SECOND * 2, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.READ_TIMEOUT_SECOND * 2, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.READ_TIMEOUT_SECOND * 2, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideApiWebservice(restAdapter: Retrofit): Webservice =
        restAdapter.create(Webservice::class.java)
}
