package com.pa.test.service.handler;

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
import com.pa.test.service.dao.PersonDetailsDAO;
import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonalDetailsHandlerTest extends AbstractTest {
	
	@InjectMocks
	PersonalDetailsHandler personalDetailsHandler = new PersonalDetailsHandlerImpl();

	@Mock
	private PersonDetailsDAO personDetailsDAO;
	
	@Test
	public void getPerson() throws ServiceException {
		
		when(personDetailsDAO.getPerson(eq("1"))).thenReturn(expected);
		Person actual = personalDetailsHandler.getPerson("1");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void putPerson() throws ServiceException {
		JAXBElement<Person> input = new JAXBElement<Person>(new QName("Person.xsd"), Person.class, expected);
		input.setValue(expected);
		
		personalDetailsHandler.putPerson(input);
		
		verify(personDetailsDAO).putPerson(input.getValue());
		verifyNoMoreInteractions(personDetailsDAO);
	}
}
