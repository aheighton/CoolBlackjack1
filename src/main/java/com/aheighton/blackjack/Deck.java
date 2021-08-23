package com.aheighton.blackjack;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck extends Pile
{
	static Random r = new Random();

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
			int index =(getR().nextInt(getContents().size()));

			newDeck.add(getContents().get(index));
			getContents().remove(getContents().get(index));
		}

		setContents(newDeck);
	}

	public static Random getR()
	{
		return r;
	}


}
