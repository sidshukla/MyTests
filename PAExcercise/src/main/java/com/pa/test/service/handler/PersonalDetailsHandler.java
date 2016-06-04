package com.pa.test.service.handler;

import javax.xml.bind.JAXBElement;

import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

/**
 * Handler class interface
 * @author Siddharth
 *
 */
public interface PersonalDetailsHandler {

	/**
	 * @param personId
	 * @return
	 * @throws ServiceException
	 */
	Person getPerson(String personId)  throws ServiceException;

	/**
	 * @param xmlPerson
	 * @throws ServiceException
	 */
	void putPerson(JAXBElement<Person> xmlPerson)  throws ServiceException;

}
