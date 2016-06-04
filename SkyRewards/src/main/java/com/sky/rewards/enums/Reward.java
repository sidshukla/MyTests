package com.sky.rewards.enums;

/*
 * Enum containing all the reward values
 */

public enum Reward {
	
	CHAMPIONS_LEAGUE_FINAL_TICKET ("CHAMPIONS_LEAGUE_FINAL_TICKET"),
	NA ("NOT_APPLICABLE"),
	KARAOKE_PRO_MICROPHONE ("KARAOKE_PRO_MICROPHONE"),
	PIRATES_OF_THE_CARIBBEAN_COLLECTION ("PIRATES_OF_THE_CARIBBEAN_COLLECTION"),
	NO_REWARD ("NO_REWARD"),
	NO_REWARD_INVALID_ACCOUNT ("NO_REWARD_INVALID_ACCOUNT");
	
	String reward;
	
	Reward(String reward){
		this.reward = reward;
	}
	
	public String getReward(){
		return reward;
	}
}
