package be.appfoundry.spekdemo.ui.contract

import be.appfoundry.spekdemo.core.mvp.MVPContract
import be.appfoundry.spekdemo.model.Item


interface MainContract {
    interface View : MVPContract.View {
        fun showItem(item: Item)
        fun showInvalid()
    }

    interface Presenter<V : View> : MVPContract.Presenter<V> {
        fun processText(text: String)
    }

    interface Component<V : View, out P : Presenter<V>> : MVPContract.Component<V, P>
}