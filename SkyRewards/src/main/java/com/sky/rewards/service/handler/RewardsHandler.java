package com.sky.rewards.service.handler;

import com.sky.rewards.enums.Reward;
/*
 * Handler interface for rewards logic
 */
import com.sky.rewards.enums.Subscription;

public interface RewardsHandler {
	public Reward getReward(Subscription subscription);
}
