package org.bitbucket.moviex

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins
import org.bitbucket.moviex.di.DaggerInjector
import org.bitbucket.moviex.di.Injector
import org.bitbucket.moviex.di.modules.AppModule
import timber.log.Timber

class App: Application() {

    private val GEUST_TOKEN = "f777eba6b164efa579ead6eb8d7d0b72"
    private val API_KEY_V3 = "12bc7d66c68d1cfad629138b2f2b46e2"
    private val API_KEY_V4 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMmJjN2Q2NmM2OGQxY2ZhZDYyOTEzOGIyZjJiNDZlMiIsInN1YiI6IjViN2Y5ZGE0YzNhMzY4N2UwNzAwNzRhNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.HCRWsJ_CqfgEQe3sHHS7vbe1F19RsuDg098E_mbk0UQ"

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