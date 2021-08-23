package com.aheighton.coolBlackjack;

import com.aheighton.blackjack.BlackJackPlayer;


public class CoolBlackjackPlayer extends BlackJackPlayer
{
	public static final String[] CHEATS = {"Free ace","Ditch last card","See other hands"};
	//TODO: expand use of cheats.
	private String ability;

	public CoolBlackjackPlayer(String name, boolean isCPU, boolean isDealer)
	{
		super(name, isCPU, isDealer);
		setAbility("");
	}

	public String getAbility()
	{
		return ability;
	}

	public void setAbility(String ability)
	{
		this.ability = ability;
	}

	public void newAbility()
	{
		int index = (int) (Math.random()*CHEATS.length);
		setAbility(CHEATS[index]);
	}
}
