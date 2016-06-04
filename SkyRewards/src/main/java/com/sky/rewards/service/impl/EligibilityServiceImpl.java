package com.sky.rewards.service.impl;

import com.sky.rewards.enums.Eligibility;
import com.sky.rewards.exception.RewardsException;
import com.sky.rewards.service.EligibilityService;

//Stubbed implementation , will always return customer_eligible
public class EligibilityServiceImpl implements EligibilityService {

	
	@Override
	public Eligibility getCustomerEligibility(String accountNumber)
			throws RewardsException {
		return Eligibility.CUSTOM_ELIGIBLE;
	}

}
