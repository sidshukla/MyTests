package com.sky.rewards.enums;

/*
 * Enum containing all the valid subscription
 */


public enum Subscription {
	SPORTS ("SPORTS"),
	KIDS ("KIDS"),
	MUSIC ("MUSIC"),
	NEWS ("NEWS"),
	MOVIES ("MOVIES");
	
	private String subscription;
	
	Subscription(String subscription){
		this.subscription = subscription;
	}
	
	public String getSubscription(){
		return subscription;
	}
	
	public static boolean hasValue(String subscription){
		for(Subscription value : Subscription.values()){
			if(value.getSubscription().equals(subscription)){
				return true;
			}
		}
		return false;
	}
}
