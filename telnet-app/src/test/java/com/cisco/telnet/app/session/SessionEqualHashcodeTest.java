package com.cisco.telnet.app.session;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.cisco.telnet.app.BaseTelnetAppTest;

@RunWith(MockitoJUnitRunner.class)
public class SessionEqualHashcodeTest extends BaseTelnetAppTest {

    private static <T> void testEqualsCommon(Class<T> type) {
        EqualsVerifier.forClass(type).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }

    @Test
    public void testMeetingRequest() {
        testEqualsCommon(Session.class);
    }
}
