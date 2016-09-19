package be.appfoundry.spekdemo.util;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.appfoundry.spekdemo.RxJavaResetRule;
import custommatchers.RxMatchersKt;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

public class RxUnitTest {
    @Rule
    public RxJavaResetRule rxJavaResetRule = new RxJavaResetRule();

    TestSubscriber<Long> testSubscriber;

    @Before
    public void setUp() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void testRx() {
        RxMethodKt.doSomeRxing().subscribe(testSubscriber);

        assertThat(testSubscriber,
                allOf(RxMatchersKt.<Long>onNextEvents(Matchers.hasSize(1)),
                        RxMatchersKt.<Long>hasNoErrors(),
                        RxMatchersKt.<Long>onNextEvents(Matchers.hasItem(1L)),
                        RxMatchersKt.<Long>isCompleted()));
    }
}