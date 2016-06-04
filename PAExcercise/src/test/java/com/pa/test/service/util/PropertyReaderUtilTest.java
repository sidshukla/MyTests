package com.pa.test.service.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.pa.test.AbstractTest;
import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

@RunWith(MockitoJUnitRunner.class)
public class PropertyReaderUtilTest extends AbstractTest{
	
	PropertyReaderUtil propertyReaderUtil = new PropertyReaderUtil();
	
	@Test
	public void readFileWithValidFileName() throws ServiceException{
		Person actual  = propertyReaderUtil.readFile("person-1.txt");
		Assert.assertEquals(expected, actual);
	}
	
	@Test (expected = ServiceException.class)
	public void readFileWithValidInvalidFileName() throws ServiceException{
		propertyReaderUtil.readFile("person-10.txt");
	}

}
