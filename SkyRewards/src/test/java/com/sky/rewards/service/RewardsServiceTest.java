package com.sky.rewards.service;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sky.rewards.enums.Eligibility;
import com.sky.rewards.enums.Reward;
import com.sky.rewards.exception.InvalidAccountNumberException;
import com.sky.rewards.exception.InvalidSubscriptionException;
import com.sky.rewards.exception.RewardsException;
import com.sky.rewards.exception.TechnicalFailureException;
import com.sky.rewards.service.impl.RewardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RewardsServiceTest {
	
	private final String ELIGIBLE_ACCOUNT_NUMBER = "12345";
	private final String INELIGIBLE_ACCOUNT_NUMBER = "667788";
	private final String INVALID_ACCOUNT_NUMBER = "ABCD";
	private final String VALID_SUBSCRIPTION = "SPORTS";
	private final String INVALID_SUBSCRIPTION = "GAMES";
	
	
	@InjectMocks
	RewardService rewardService = new RewardServiceImpl();
	
	@Mock
	EligibilityService eligibilityService;
	
	//@Mock
	//RewardsHandler rewardsHandler;
	
	@Before
	public void setup() throws RewardsException{
		when(eligibilityService.getCustomerEligibility(eq(ELIGIBLE_ACCOUNT_NUMBER)))
				.thenReturn(Eligibility.CUSTOM_ELIGIBLE);
		when(eligibilityService.getCustomerEligibility(eq(INELIGIBLE_ACCOUNT_NUMBER)))
		.thenReturn(Eligibility.CUSTOM_INELIGIBLE);
		when(eligibilityService.getCustomerEligibility(eq(INVALID_ACCOUNT_NUMBER)))
		.thenThrow(new InvalidAccountNumberException("Invalid account"));
	}
	
	@Test
	public void testGetRewardForPositiveFlow() throws RewardsException {
		String actualReward = rewardService.getReward(ELIGIBLE_ACCOUNT_NUMBER, VALID_SUBSCRIPTION);
		Assert.assertEquals(Reward.CHAMPIONS_LEAGUE_FINAL_TICKET.getReward(), actualReward);
	}

	@Test (expected = InvalidSubscriptionException.class)
	public void testGetRewardForInvalidSubscription() throws RewardsException {
		rewardService.getReward(ELIGIBLE_ACCOUNT_NUMBER, INVALID_SUBSCRIPTION);
	}
	
	@Test
	public void testGetRewardForIneligibleAccount() throws RewardsException {
		String actualReward = rewardService.getReward(INELIGIBLE_ACCOUNT_NUMBER, VALID_SUBSCRIPTION);
		Assert.assertEquals(Reward.NO_REWARD.getReward(), actualReward);
	}
	
	@Test
	public void testGetRewardForInvalidAccount() throws RewardsException {
		String actualReward = rewardService.getReward(INVALID_ACCOUNT_NUMBER, VALID_SUBSCRIPTION);
		Assert.assertEquals(Reward.NO_REWARD_INVALID_ACCOUNT.getReward(), actualReward);
	}
	
	@Test
	public void testGetRewardForTechnicalFailure() throws RewardsException {
		when(eligibilityService.getCustomerEligibility(eq(ELIGIBLE_ACCOUNT_NUMBER)))
		.thenThrow(new TechnicalFailureException("Technical Failure"));
		String actualReward = rewardService.getReward(ELIGIBLE_ACCOUNT_NUMBER, VALID_SUBSCRIPTION);
		Assert.assertEquals(Reward.NO_REWARD.getReward(), actualReward);
	}
	
	
}
