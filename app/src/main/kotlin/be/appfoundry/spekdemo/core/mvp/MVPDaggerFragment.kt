package be.appfoundry.spekdemo.core.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import be.appfoundry.spekdemo.DemoApp

/**
 * Created by simonvergauwen on 01/08/16.
 */
abstract class MVPDaggerFragment<V : MVPContract.View, out P : MVPContract.Presenter<V>, out C : MVPContract.Component<V, P>> : Fragment(), MVPContract.View {
    protected val presenter: P by lazy { component.presenter }
    protected val component: C by lazy { createComponent() }

    protected abstract fun createComponent(): C

    //This happens under the hood in java.
    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as DemoApp).refWatcher?.watch(this)
    }
}