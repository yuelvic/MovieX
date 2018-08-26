package org.bitbucket.moviex

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins
import org.bitbucket.moviex.di.DaggerInjector
import org.bitbucket.moviex.di.Injector
import org.bitbucket.moviex.di.modules.AppModule
import timber.log.Timber

class App: Application() {

    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initPluginsErrorHandler()
    }

    private fun initDagger() {
        this.injector = DaggerInjector.builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            run {
                Timber.plant(Timber.DebugTree())
            }
        }
    }

    private fun initPluginsErrorHandler() = RxJavaPlugins.setErrorHandler { Timber.e(it) }

}