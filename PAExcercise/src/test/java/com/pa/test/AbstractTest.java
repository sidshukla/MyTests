package com.pa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;

import com.pa.test.service.model.Person;

public class AbstractTest {

	protected Person expected;
	
	@Before
	public void setup() throws ParseException{
		expected = new Person();
		expected.setId("1");
		expected.setFamilyName("Rooney");
		expected.setGivenName("Wayne");
		expected.setHeight(1.76f);
		expected.setPlaceOfBirth("Liverpool");
		expected.setTwitterId("@WayneRooney");
		expected.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-24"));
		expected.setMiddleNames("Mark");
	}
}
