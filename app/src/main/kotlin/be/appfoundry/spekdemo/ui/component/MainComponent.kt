package be.appfoundry.spekdemo.ui.component

import be.appfoundry.spekdemo.core.di.ActivityScope
import be.appfoundry.spekdemo.core.di.ApplicationComponent
import be.appfoundry.spekdemo.ui.contract.MainContract
import be.appfoundry.spekdemo.ui.presenter.MainPresenter
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface MainComponent : MainContract.Component<MainContract.View, MainPresenter> {
    override val presenter : MainPresenter
}