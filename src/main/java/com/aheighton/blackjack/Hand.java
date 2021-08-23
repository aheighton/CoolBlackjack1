package com.aheighton.blackjack;

import java.util.*;

public class Hand extends Pile
{
	public Hand()
	{
		List<Card> contents = new LinkedList<>();
		setContents(contents);
	}

	public int getScore(){
		int score = 0;
		for (Card card: getContents())
		{
			score += card.getPoints();
		}

		return score;
	}
}
