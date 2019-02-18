package com.example.demoshaadi.di.component


import com.example.demoshaadi.DemoApp
import com.example.demoshaadi.di.module.ActivityBindingModule
import com.example.demoshaadi.di.module.ApiModule
import com.example.demoshaadi.di.module.ApplicationModule
import com.example.demoshaadi.di.module.RxModule
import com.example.demoshaadi.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ApiModule::class,
        RxModule::class
    )
)
interface ApplicationComponent : AndroidInjector<DemoApp> {

    override fun inject(application: DemoApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DemoApp): Builder

        fun build(): ApplicationComponent
    }
}
