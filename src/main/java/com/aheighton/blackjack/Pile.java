package com.aheighton.blackjack;

import java.util.List;

public abstract class Pile
{
	private List<Card> contents;

	public void addCard(Card card)
	{
		contents.add(card);
	}

	public Card removeCard()
	{
		return contents.remove(contents.size()-1);
	}

	public void addAll()
	{
		for (char suit: new char[]{'C', 'H', 'S', 'D'})
		{
			for (String value: new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"})
			{
				addCard(new Card(value, suit));
			}
		}
	}

	public List<Card> getContents()
	{
		return contents;
	}

	public void setContents(List<Card> contents)
	{
		this.contents = contents;
	}

	public String toString()
	{
		StringBuilder output = new StringBuilder();

		for (Card card : getContents())
		{
			output.append(card.toString());
		}

		return output.toString();
	}
}
