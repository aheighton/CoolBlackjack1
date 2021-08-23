package com.aheighton.coolblackjack;

import com.aheighton.blackjack.BlackJackPlayer;
import com.aheighton.blackjack.Deck;


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

	@Override
	public String getAbility()
	{
		return ability;
	}

	@Override
	public void setAbility(String ability)
	{
		this.ability = ability;
	}

	@Override
	public void newAbility()
	{
		int index = Deck.getR().nextInt(CHEATS.length);
		setAbility(CHEATS[index]);
	}


}
