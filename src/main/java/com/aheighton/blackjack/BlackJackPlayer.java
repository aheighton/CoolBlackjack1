package com.aheighton.blackjack;

public class BlackJackPlayer extends Player
{
	public BlackJackPlayer(String name, boolean isCPU, boolean isDealer)
	{
		setName(name);
		setCPU(isCPU);
		setDealer(isDealer);
		setHand(new Hand());
	}

	public void hit(Card card)
	{
		getHand().addCard(card);
	}

	@Override
	public void newAbility()
	{

	}

	@Override
	public String getAbility()
	{
		return null;
	}

	@Override
	public void setAbility(String s)
	{

	}
}
