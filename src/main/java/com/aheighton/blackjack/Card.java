package com.aheighton.blackjack;

import static java.lang.Integer.parseInt;

public class Card
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";

	private String value;
	private char suit;

	public Card(String value, char suit)
	{
		setValue(value);
		setSuit(suit);
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public char getSuit()
	{
		return suit;
	}

	public void setSuit(char suit)
	{
		this.suit = suit;
	}

	public char getColour()
	{
		if (getSuit() == 'S' || getSuit() == 'C') return 'B';
		else return 'R';
	}

	public int getPoints()
	{
		try
		{
			return parseInt(getValue());
		} catch (Exception e)
		{
			if (getValue().equals("A"))
			{
				return 11;
			}
			return 10;
		}
	}

	public String toString()
	{
		StringBuilder output = new StringBuilder();

		if (getColour() == 'R')
		{
			output.append(ANSI_RED + "[");
		} else
		{
			output.append(ANSI_BLACK + "[");
		}

		if (getValue().equals("1"))
		{
			output.append("A");
		}
		else
		{
			output.append(getValue());
		}

		switch (getSuit())
		{
			case 'C' -> output.append("♣");
			case 'H' -> output.append("♥");
			case 'S' -> output.append("♠");
			case 'D' -> output.append("♦");
		}

		output.append("]"+ANSI_RESET);

		return output.toString();
	}
}
