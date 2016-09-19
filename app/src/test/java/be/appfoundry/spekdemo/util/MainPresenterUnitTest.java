package be.appfoundry.spekdemo.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import be.appfoundry.spekdemo.model.MailItem;
import be.appfoundry.spekdemo.ui.contract.MainContract.View;
import be.appfoundry.spekdemo.ui.presenter.MainPresenter;

import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(Parameterized.class)
public class MainPresenterUnitTest {

    @Parameters
    public static String[] data() {
        return new String[]{"test@appfoundry.be", "ttest@fffff.org",
                "bbbb@bbbbb.com", "@AppFoundryBE"};
    }

    private String input;
    private View mainView;
    private MainPresenter mainPresenter;

    public MainPresenterUnitTest(String input) {
        this.input = input;
    }

    @Before
    public void setUp() {
        mainView = mock(View.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(mainView);
    }

    @Test
    public void testIfItemIsShowAsEmail() {
        mainPresenter.processText(input);
        verify(mainView, times(1)).showItem(argThat(isA(MailItem.class)));
    }
}
