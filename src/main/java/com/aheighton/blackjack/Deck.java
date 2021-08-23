package com.aheighton.blackjack;

import java.util.*;
import java.lang.Math;

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
			int index = (int) (Math.random()*getContents().size());

			newDeck.add(getContents().get(index));
			getContents().remove(getContents().get(index));
		}

		setContents(newDeck);
	}


}
