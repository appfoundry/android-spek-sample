package be.appfoundry.spekdemo.ui.presenter

import be.appfoundry.spekdemo.model.Item
import be.appfoundry.spekdemo.ui.contract.MainContract
import be.vergauwen.simon.mockito1_kotlin.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate


@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnitPlatform::class)
@PrepareForTest()
class MainPresenterUnitTest : Spek({
    describe("When handling the MainView") {
        val mainView = mock<MainContract.View>()
        val mainPresenter = MainPresenter().apply { attachView(mainView) }
        afterEach { reset(mainView) }

        it("should be setup correctly") {
            assertThat(mainPresenter, notNullValue())
            assertThat(mainPresenter.view, notNullValue())
        }

        context("When processing items") {
            given("A correct email adres") {
                listOf("test@appfoundry.be", "ttest@fffff.org", "bbbb@bbbbb.com").forEach {
                    it("$it should be shown as email") {
                        mainPresenter.processText(it)
                        verify(mainView, times(1)).showItem(any<Item>())
                    }
                }
            }
        }
    }
})