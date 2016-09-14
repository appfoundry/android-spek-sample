package be.appfoundry.spekdemo

import android.app.Application
import be.appfoundry.spekdemo.core.di.ApplicationComponent
import be.appfoundry.spekdemo.core.di.ApplicationModule
import be.appfoundry.spekdemo.core.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import timber.log.Timber

class DemoApp : Application() {

    lateinit var component: ApplicationComponent
    var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        component = createComponent()
        refWatcher = LeakCanary.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun createComponent(): ApplicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
}