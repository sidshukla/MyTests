package com.pa.test.service;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pa.test.AbstractTest;
import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.handler.PersonalDetailsHandler;
import com.pa.test.service.model.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonDetailsServiceTest extends AbstractTest{

	@Mock
	PersonalDetailsHandler personalDetailsHandler;
	
	@InjectMocks
	PersonDetailsService personDetailsService = new PersonDetailsServiceImpl();

	@Test
	public void testGetPerson() throws Exception {
		when(personalDetailsHandler.getPerson(eq("1"))).thenReturn(expected);
		Person actual = personDetailsService.getPerson("1");
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void putPerson() throws ServiceException {
		JAXBElement<Person> input = new JAXBElement<Person>(new QName("Person.xsd"), Person.class, expected);
		input.setValue(expected);
		
		personDetailsService.putPerson(input);
		
		verify(personalDetailsHandler).putPerson(input);
		verifyNoMoreInteractions(personalDetailsHandler);
	}


}
