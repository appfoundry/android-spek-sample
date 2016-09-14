package be.appfoundry.spekdemo.ui.presenter

import android.util.Patterns
import android.webkit.URLUtil
import be.appfoundry.spekdemo.core.mvp.MVPRxPresenter
import be.appfoundry.spekdemo.model.MailItem
import be.appfoundry.spekdemo.model.PhoneItem
import be.appfoundry.spekdemo.model.TwitterItem
import be.appfoundry.spekdemo.ui.contract.MainContract
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor() : MVPRxPresenter<MainContract.View>(), MainContract.Presenter<MainContract.View> {

    override fun processText(text: String) {
        when {
            URLUtil.isValidUrl(text) || Patterns.WEB_URL.matcher(text).matches() -> Timber.e("Something")
                //view?.showItem(URLItem(text))
            Patterns.EMAIL_ADDRESS.matcher(text).matches() -> view?.showItem(MailItem(text))
            Patterns.PHONE.matcher(text).matches() -> view?.showItem(PhoneItem(text))
            text.startsWith("@") -> view?.showItem(TwitterItem(text))
            else -> view?.showInvalid()
        }
    }


}