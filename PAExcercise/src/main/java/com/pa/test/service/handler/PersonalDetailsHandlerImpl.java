package com.pa.test.service.handler;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pa.test.service.dao.PersonDetailsDAO;
import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

/**
 * Handler class implementation which calls the DAO class
 *  for getting/putting data
 * @author siddharth
 *
 */
@Component("PersonalDetailsHandler")
public class PersonalDetailsHandlerImpl implements PersonalDetailsHandler{

	@Autowired
	private PersonDetailsDAO personDetailsDAO;
	
	@Override
	public Person getPerson(String personId) throws ServiceException {
		return personDetailsDAO.getPerson(personId);
	}

	@Override
	public void putPerson(JAXBElement<Person> xmlPerson) throws ServiceException {
		personDetailsDAO.putPerson(xmlPerson.getValue());
	}

}
