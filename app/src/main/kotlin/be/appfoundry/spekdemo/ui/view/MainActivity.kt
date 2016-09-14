package be.appfoundry.spekdemo.ui.view

import android.os.Bundle
import be.appfoundry.spekdemo.DemoApp
import be.appfoundry.spekdemo.R
import be.appfoundry.spekdemo.core.mvp.MVPDaggerActivity
import be.appfoundry.spekdemo.model.Item
import be.appfoundry.spekdemo.ui.component.DaggerMainComponent
import be.appfoundry.spekdemo.ui.component.MainComponent
import be.appfoundry.spekdemo.ui.contract.MainContract
import be.appfoundry.spekdemo.ui.presenter.MainPresenter
import org.jetbrains.anko.frameLayout

class MainActivity : MVPDaggerActivity<MainContract.View, MainPresenter, MainComponent>(), MainContract.View {

    override fun createComponent(): MainComponent = DaggerMainComponent.builder().applicationComponent((application as DemoApp).component).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {
            fitsSystemWindows = true
            id = R.id.content_frame
        }
    }

    override fun showItem(item: Item) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showInvalid() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}