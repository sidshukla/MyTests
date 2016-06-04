package com.pa.test.service.util;

import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

/**
 * Util class to read the data file and return a Person object
 * @author siddharth
 *
 */
@Component
public class PropertyReaderUtil {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws ServiceException
	 */
	public Person readFile(String fileName) throws ServiceException {

		String filePath = null;
		Properties prop = new Properties();
		Person person = null;

		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("data/" + fileName);

		if (url != null) {
			filePath = url.getPath();
		} else {
			throw new ServiceException("No data file exists for " + fileName);
		}

		try {
			prop.load(new FileInputStream(filePath));

			person = new Person();
			person.setId(prop.getProperty("id"));
			person.setPlaceOfBirth(prop.getProperty("placeOfBirth"));
			person.setHeight(!prop.getProperty("height").isEmpty() ? Float.parseFloat(prop.getProperty("height")) : null);
			person.setFamilyName(prop.getProperty("familyName"));
			person.setGivenName(prop.getProperty("givenName"));
			person.setMiddleNames(prop.getProperty("middleNames"));
			person.setTwitterId(prop.getProperty("twitterId"));
			person.setDateOfBirth(!prop.getProperty("dateOfBirth").isEmpty() ? simpleDateFormat
					.parse(prop.getProperty("dateOfBirth")) : null);
			person.setDateOfDeath(!prop.getProperty("dateOfDeath").isEmpty() ? simpleDateFormat
					.parse(prop.getProperty("dateOfDeath")) : null);

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return person;
	}

}
