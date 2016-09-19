package be.appfoundry.spekdemo.util;


import android.support.v4.util.ArrayMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class JsonUtilUnitTest {

    ArrayMap<String,String> arrayMap;

    @Before
    public void setUp() throws Exception {
        arrayMap = new ArrayMap<>();
    }

    @Test
    public void testWriteSingleMapToJSON() {
        arrayMap.put("A","B");
        String json = JsonUtil.writeToJson(arrayMap);
        assertThat(json, containsString("\"A\":\"B\""));
    }

    @After
    public void tearDown() throws Exception { }
}
