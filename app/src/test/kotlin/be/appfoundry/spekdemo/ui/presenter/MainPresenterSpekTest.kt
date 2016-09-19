package be.appfoundry.spekdemo.ui.presenter

import be.appfoundry.spekdemo.model.MailItem
import be.appfoundry.spekdemo.ui.contract.MainContract.View
import be.vergauwen.simon.mockito1_kotlin.argThat
import be.vergauwen.simon.mockito1_kotlin.mock
import be.vergauwen.simon.mockito1_kotlin.times
import be.vergauwen.simon.mockito1_kotlin.verify
import org.hamcrest.Matchers.isA
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith


@RunWith(JUnitPlatform::class)
class MainPresenterSpekTest : Spek({
    describe("The MainPresenter is handling the MainView") {
        var mainPresenter = MainPresenter()
        var mainView = mock<View>()

        beforeEach {
            mainPresenter = MainPresenter()
            mainView = mock<View>()
            mainPresenter.attachView(mainView)
        }

        on("processing data") {
            listOf("test@appfoundry.be", "ttest@fffff.org",
                    "bbbb@bbbbb.com", "@AppFoundryBE").forEach { email ->

                it("should show $email as email") {
                    mainPresenter.processText(email)
                    verify(mainView, times(1)).showItem(argThat(isA(MailItem::class.java)))
                }
            }
        }
    }
})
//
//fun String.getType(): Type {
//    return when {
//        Patterns.PHONE.matcher(this).matches() -> Type.Phone
//        Patterns.EMAIL_ADDRESS.matcher(this).matches() -> Type.Email
//        else -> Type.Unrecognized
//    }
//}
//
//enum class Type { Phone, Email, Unrecognized }

