package com.example.demoshaadi.di.module


import com.example.demoshaadi.api.ApiConstants
import com.example.demoshaadi.api.ApiEndPoint
import com.example.demoshaadi.di.scope.AppScope
import com.google.gson.Gson

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @AppScope
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @AppScope
    @Provides
    internal fun provideNewsApi(retrofit: Retrofit): ApiEndPoint {
        return retrofit.create(ApiEndPoint::class.java)
    }
}
