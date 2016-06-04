package com.pa.test.service.dao;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.pa.test.AbstractTest;
import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;
import com.pa.test.service.util.PropertyReaderUtil;

@RunWith(MockitoJUnitRunner.class)
public class PersonDetailsDAOTest extends AbstractTest {

	@Mock
	PropertyReaderUtil propertyReaderUtil;

	@InjectMocks
	PersonDetailsFileDAO personDetailsDAO = new PersonDetailsFileDAO();

	@Test
	public void getPersonWithValidInput() throws ServiceException {

		when(propertyReaderUtil.readFile("person-1.txt")).thenReturn(expected);

		Person actual = personDetailsDAO.getPerson("1");
		Assert.assertEquals(expected, actual);

		Person actualBuffered = personDetailsDAO.getPerson("1");
		Assert.assertEquals(expected, actualBuffered);

	}

	@Test
	public void getPersonWithInValidInput() throws ServiceException {

		when(propertyReaderUtil.readFile("person-2.txt")).thenReturn(null);

		Person actual = personDetailsDAO.getPerson("1");
		Assert.assertEquals(null, actual);

	}

	@Test
	public void putPersonWithValidInput() throws Exception {
		Person input = new Person();
		input.setId("2");
		input.setFamilyName("Joe");
		input.setGivenName("Hart");
		input.setHeight(1.86f);
		input.setPlaceOfBirth("London");
		input.setTwitterId("@JoeH");
		input.setDateOfBirth(new SimpleDateFormat("yyyy-mm-dd")
				.parse("1982-12-13"));
		input.setMiddleNames("Tr");

		Assert.assertFalse(personDetailsDAO.getPersonIdMap().containsKey("2"));

		personDetailsDAO.putPerson(input);

		Assert.assertEquals(input, personDetailsDAO.getPersonIdMap().get("2"));

	}
	
	@Test (expected = ServiceException.class)
	public void putPersonWithDuplicateInput() throws Exception {
		Person input = new Person();
		input.setId("2");
		input.setFamilyName("Joe");
		input.setGivenName("Hart");
		input.setHeight(1.86f);
		input.setPlaceOfBirth("London");
		input.setTwitterId("@JoeH");
		input.setDateOfBirth(new SimpleDateFormat("yyyy-mm-dd")
				.parse("1982-12-13"));
		input.setMiddleNames("Tr");

		personDetailsDAO.putPerson(input);
		personDetailsDAO.putPerson(input);
	}

}
