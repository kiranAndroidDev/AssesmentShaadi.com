package com.example.demoshaadi.di.module


import com.example.demoshaadi.api.RxSingleSchedulers
import com.example.demoshaadi.di.scope.AppScope
import dagger.Module
import dagger.Provides


@Module
class RxModule {
    @AppScope
    @Provides
    fun providesScheduler(): RxSingleSchedulers {
        return RxSingleSchedulers.DEFAULT
    }
}
