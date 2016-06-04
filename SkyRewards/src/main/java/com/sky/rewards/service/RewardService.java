package com.sky.rewards.service;

import com.sky.rewards.exception.RewardsException;

/*
 * Interface to calculate the reward applicable to an account
 */
public interface RewardService {
	String getReward(String accountNumber , String subscription) throws RewardsException;
}
