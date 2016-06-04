package com.pa.test.service;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.handler.PersonalDetailsHandler;
import com.pa.test.service.model.Person;

/*
 * Service interface implementation which simply delegates the task to the 
 * handler class
 */
@Service("PersonDetailsService")
public class PersonDetailsServiceImpl implements PersonDetailsService {

	@Autowired
	PersonalDetailsHandler personalDetailsHandler;

	@Override
	public Person getPerson(String personId) throws ServiceException {
		return this.personalDetailsHandler.getPerson(personId);
	}

	@Override
	public void putPerson(JAXBElement<Person> xmlPerson)throws ServiceException {
		this.personalDetailsHandler.putPerson(xmlPerson);
	}

}
