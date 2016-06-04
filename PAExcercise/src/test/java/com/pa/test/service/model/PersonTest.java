package com.pa.test.service.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest{

    private static <T> void testEqualsCommon(Class<T> type) {
        EqualsVerifier.forClass(type).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }

    @Test
    public void testMeetingRequest() {
        testEqualsCommon(Person.class);
    }
}
