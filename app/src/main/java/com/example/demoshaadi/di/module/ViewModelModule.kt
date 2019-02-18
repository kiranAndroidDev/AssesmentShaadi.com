package com.example.demoshaadi.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


import com.example.demoshaadi.di.scope.ViewModelKey
import com.example.demoshaadi.main.MyViewModelFactory
import com.example.demoshaadi.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel


    @Binds
    internal abstract fun bindMyViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}
