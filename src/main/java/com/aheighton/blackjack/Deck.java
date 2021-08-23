package com.aheighton.blackjack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck extends Pile
{
	public Deck()
	{
		List<Card> contents = new LinkedList<>();
		setContents(contents);
		addAll();
	}

	public void shuffle()
	{
		List<Card> newDeck = new LinkedList<>();

		while (!getContents().isEmpty())
		{
			Random r = new Random();
			int index =(r.nextInt(getContents().size()));

			newDeck.add(getContents().get(index));
			getContents().remove(getContents().get(index));
		}

		setContents(newDeck);
	}


}
