package com.sky.rewards.service.impl;

import com.sky.rewards.enums.Eligibility;
import com.sky.rewards.enums.Reward;
import com.sky.rewards.enums.Subscription;
import com.sky.rewards.exception.InvalidAccountNumberException;
import com.sky.rewards.exception.InvalidSubscriptionException;
import com.sky.rewards.exception.RewardsException;
import com.sky.rewards.exception.TechnicalFailureException;
import com.sky.rewards.service.EligibilityService;
import com.sky.rewards.service.RewardService;
import com.sky.rewards.service.handler.RewardsHandler;
import com.sky.rewards.service.handler.impl.RewardsHandlerImpl;

public class RewardServiceImpl implements RewardService {

	EligibilityService eligibilityService;

	RewardsHandler rewardsHandler;

	public RewardServiceImpl() {
		eligibilityService = new EligibilityServiceImpl();
		rewardsHandler = new RewardsHandlerImpl();
	}

	@Override
	public String getReward(String accountNumber, String subscription)
			throws RewardsException {

		String returnReward = null;

		Subscription subscriptionLocal = null;
		//check if the subscription input is valid otherwise throw 
		// Invalid subscription exception
		if (Subscription.hasValue(subscription)) {
			subscriptionLocal = Subscription.valueOf(subscription);
		} else {
			throw new InvalidSubscriptionException("Invalid Subscription");
		}

		try {
			/*
			 * Checks the account eligibility
			 */
			if ( eligibilityService.getCustomerEligibility(accountNumber)
					.equals(Eligibility.CUSTOM_INELIGIBLE)) {
				returnReward = Reward.NO_REWARD.getReward();
			}
		} catch (TechnicalFailureException e) {
			returnReward = Reward.NO_REWARD.getReward();
		} catch (InvalidAccountNumberException e) {
			returnReward = Reward.NO_REWARD_INVALID_ACCOUNT.getReward();
		}

		/*
		 * If no issue in the account eligibility call the handler class
		 * to calculate the reward
		 */
		if (returnReward == null) {
			returnReward = rewardsHandler.getReward(subscriptionLocal)
					.getReward();
		}
		return returnReward;
	}
}
