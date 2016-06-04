package com.bmc.analyser.springer.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class AnalysedScoreTest {
	
	    @Test
	    public void testEqualsHashCode() {
	    	EqualsVerifier.forClass(GridLocation.class).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
	    }
}
