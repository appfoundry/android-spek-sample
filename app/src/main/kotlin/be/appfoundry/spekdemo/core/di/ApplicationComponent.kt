package be.appfoundry.spekdemo.core.di

import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
}