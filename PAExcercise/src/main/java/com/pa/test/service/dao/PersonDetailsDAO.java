package com.pa.test.service.dao;

import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

/*
 * DAO interface to get the data from the underline system
 */
public interface PersonDetailsDAO {

	Person getPerson(String personId) throws ServiceException;

	void putPerson(Person person)  throws ServiceException;

}
