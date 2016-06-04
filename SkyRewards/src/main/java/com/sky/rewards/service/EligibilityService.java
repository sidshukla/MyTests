package com.sky.rewards.service;

import com.sky.rewards.enums.Eligibility;
import com.sky.rewards.exception.RewardsException;

/*
 * Interface for checking the eligibility of an account
 */
public interface EligibilityService {
	Eligibility getCustomerEligibility(String accountNumber) throws RewardsException;
}
