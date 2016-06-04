package com.pa.test.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.pa.test.service.exception.ServiceException;
import com.pa.test.service.model.Person;

/**
 * 
 * Main service class interface for the RESTful web service
 * @author siddharth
 *
 */
@Path("/")
public interface PersonDetailsService{
	
	/**
	 * Method to get the Person object 
	 * @param personId
	 * @return
	 * @throws ServiceException
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/person/{id}")
	public Person getPerson(@PathParam("id") String personId) throws ServiceException;

	/**
	 * Method to put the person object
	 * @param xmlPerson
	 * @throws ServiceException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/person")
	public void putPerson(JAXBElement<Person> xmlPerson) throws ServiceException;

}
