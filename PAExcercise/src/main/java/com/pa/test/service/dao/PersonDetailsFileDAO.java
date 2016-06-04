package com.pa.test.service.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;
import com.pa.test.service.util.PropertyReaderUtil;

/*
 * DAO implementation to read the data from a file and store in a map
 * For put the map is updated
 */
@Component("PersonDetailsDAO")
public class PersonDetailsFileDAO implements PersonDetailsDAO {

	HashMap<String, Person> personIdMap;

	private static String PERSONFILEPREFIX = "person-";

	@Autowired
	PropertyReaderUtil propertyReaderUtil;

	public PersonDetailsFileDAO() {
		personIdMap = new HashMap<String, Person>();
	}

	@Override
	public Person getPerson(String personId) throws ServiceException {
		Person person = null;
		if (personIdMap.containsKey(personId)) {
			return personIdMap.get(personId);
		} else {
			person = propertyReaderUtil.readFile(PERSONFILEPREFIX.concat(
					personId).concat(".txt"));
			if (person != null) {
				personIdMap.put(personId, person);
			}
		}
		return person;
	}

	@Override
	public void putPerson(Person person) throws ServiceException {
		if (personIdMap.containsKey(person.getId())) {
			throw new ServiceException("Person for Id " + person.getId()
					+ " already exsists.");
		} else {
			personIdMap.put(person.getId(), person);
		}

	}

	/**
	 * @return the personIdMap
	 */
	public HashMap<String, Person> getPersonIdMap() {
		return personIdMap;
	}

}
