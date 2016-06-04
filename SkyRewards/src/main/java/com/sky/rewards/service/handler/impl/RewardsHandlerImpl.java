package com.sky.rewards.service.handler.impl;

import java.util.HashMap;
import java.util.Map;

import com.sky.rewards.enums.Reward;
import com.sky.rewards.enums.Subscription;
import com.sky.rewards.service.handler.RewardsHandler;

/*
 * Implementation can call the dao class if information is in database in future
 */
public class RewardsHandlerImpl implements RewardsHandler {
	
	Map<Subscription, Reward> subscriptionRewardMapping = null;
	
	public RewardsHandlerImpl(){
		subscriptionRewardMapping = new HashMap<Subscription, Reward>();
		subscriptionRewardMapping.put(Subscription.SPORTS,
				Reward.CHAMPIONS_LEAGUE_FINAL_TICKET);
		subscriptionRewardMapping.put(Subscription.KIDS, Reward.NA);
		subscriptionRewardMapping.put(Subscription.KIDS, Reward.NA);
		subscriptionRewardMapping.put(Subscription.KIDS, Reward.NA);
		subscriptionRewardMapping.put(Subscription.KIDS, Reward.NA);
	}
	
	public Reward getReward(Subscription subscription){
		return subscriptionRewardMapping.get(subscription);
	}

}
